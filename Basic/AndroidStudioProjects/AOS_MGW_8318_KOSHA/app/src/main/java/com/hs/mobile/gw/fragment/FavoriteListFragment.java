package com.hs.mobile.gw.fragment;

import java.util.ArrayList;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.FavoriteFileListAdapter;
import com.hs.mobile.gw.adapter.FavoriteWorkListAdapter;
import com.hs.mobile.gw.openapi.square.FavoriteList;
import com.hs.mobile.gw.openapi.square.FavoriteList.FavoriteFileType;
import com.hs.mobile.gw.openapi.square.FavoriteList.FavoriteType;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.Mode;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FavoriteListFragment extends CommonFragment implements OnClickListener, IChangeContentsListener, OnLastItemVisibleListener,
		IBookmarkAndOptionViewListener, OnItemClickListener, OnRefreshListener<ListView> {

	private LinearLayout m_btnWork;
	private LinearLayout m_btnFile;
	private String m_strSquareId;
	private ArrayList<MainContentsListItemVO> m_workData = new ArrayList<MainContentsListItemVO>();
	private ArrayList<AttachListItemVO> m_fileData = new ArrayList<AttachListItemVO>();
	private FavoriteType m_currentFavoriteType;
	private PullToRefreshListView m_lvFavorite;
	private FavoriteWorkListAdapter m_workAdapter;
	private FavoriteFileListAdapter m_fileAdapter;
	private ImageView m_btnBack;
	private TextView m_btnFileType;
	private FavoriteFileType m_favoriteFileType;
	private Status m_groupStatus;
	private String m_strLastContentsId;
	private String m_strLastAttachId;
	private LinearLayout m_emptyListItem;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_favorite_list, container, false);
		super.createHead(v); // create sub header
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_btnFileType = (TextView) v.findViewById(R.id.ID_BTN_FILE_TYPE);
		m_btnWork = (LinearLayout) v.findViewById(R.id.ID_LAY_L_WORK);
		m_btnFile = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE);
		m_lvFavorite = (PullToRefreshListView) v.findViewById(R.id.ID_LV_FAVORITE);
		m_emptyListItem = (LinearLayout) v.findViewById(R.id.empty_list_item);

		m_lvFavorite.setMode(Mode.PULL_FROM_START);
		m_lvFavorite.setOnLastItemVisibleListener(this);
		m_lvFavorite.getRefreshableView().setDividerHeight(0);
		m_lvFavorite.getRefreshableView().setScrollingCacheEnabled(false);
		m_lvFavorite.setFadingEdgeLength(0);
		m_lvFavorite.setOnItemClickListener(this);
		m_lvFavorite.setOnRefreshListener(this);
		m_lvFavorite.setEmptyView(m_emptyListItem);

		m_btnBack.setOnClickListener(this);
		m_btnWork.setOnClickListener(this);
		m_btnFile.setOnClickListener(this);
		m_btnFileType.setOnClickListener(this);

		if (getArguments() != null) {
			if (getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID))  {
				m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
			}
			if (getArguments().containsKey(MainModel.ARG_KEY_GROUP_STATUS)) {
				m_groupStatus = (Status) getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS);
			}
		} else {
			getActivity().finish();
		}

		setFavoriteType(FavoriteType.WORK);
		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainModel.getInstance().addContentsChangeListener(this);
	}

	@Override
	public void onDestroy() {
		MainModel.getInstance().removeContentsChangeListener(this);
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_LAY_L_WORK:
			setFavoriteType(FavoriteType.WORK);
			break;
		case R.id.ID_LAY_L_FILE:
			setFavoriteType(FavoriteType.FILE);
			break;
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_BTN_FILE_TYPE:
			String[] strs = new String[] { getResources().getString(R.string.favorite_file_type_all),
					getResources().getString(R.string.favorite_file_type_doc), getResources().getString(R.string.favorite_file_type_img),
					getResources().getString(R.string.favorite_file_type_mov), getResources().getString(R.string.favorite_file_type_etc) };
			new AlertDialog.Builder(getActivity()).setTitle(R.string.favoirte_file_type_title)
					.setItems(strs, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							m_favoriteFileType = null;
							switch (which) {
							case 0:
								m_favoriteFileType = FavoriteFileType.ALL;
								break;
							case 1:
								m_favoriteFileType = FavoriteFileType.DOCUMENT;
								break;
							case 2:
								m_favoriteFileType = FavoriteFileType.IMAGE;
								break;
							case 3:
								m_favoriteFileType = FavoriteFileType.MOVIE;
								break;
							case 4:
								m_favoriteFileType = FavoriteFileType.ETC;
								break;
							}
							if (m_favoriteFileType == null) {
								return;
							}
							initFavoriteFile();
						}
					}).show();
			break;
		}
	}

	private void initFavoriteFile() {
		m_lvFavorite.setRefreshing();
		m_strLastAttachId = null;
		Debug.trace("m_favoriteFileType", m_favoriteFileType.toString());
		new ApiLoader(new FavoriteList(), getActivity(), new SquareDefaultCallback() {
			@Override
			public void onResponse(String strRet) {
				m_lvFavorite.stopRefreshing();
				super.onResponse(strRet);
				if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
					if (this.responseData.dataList != null && this.responseData.dataList.length() > 0) {
						if (m_fileData.size() > 0) {
							m_fileData.clear();
						}
						for (int i = 0; i < this.responseData.dataList.length(); ++i) {
							m_fileData.add(new AttachListItemVO(this.responseData.dataList.optJSONObject(i)));
						}
						m_strLastAttachId = m_fileData.get(m_fileData.size() - 1).attachId;
						m_fileAdapter.notifyDataSetChanged();
					} else {
						m_fileData.clear();
						m_fileAdapter.notifyDataSetChanged();
					}
				} else {
					m_fileData.clear();
					m_fileAdapter.notifyDataSetChanged();
				}
			}
			// "squareId", "fileType", "favoriteType",
			// "lastContentsId",
			// "lastAttachId"
		}, m_strSquareId, m_favoriteFileType.getCode(), FavoriteType.FILE.getCode(), "", "").execute();
	}

	private void setFavoriteType(FavoriteType t) {
		if (t == m_currentFavoriteType) {
			return;
		}
		m_currentFavoriteType = t;
		switch (t) {
		case FILE:
			m_btnFileType.setVisibility(View.VISIBLE);
			m_fileAdapter = new FavoriteFileListAdapter(getActivity(), m_fileData, this);
			m_lvFavorite.setAdapter(m_fileAdapter);
			m_btnWork.setSelected(false);
			m_btnFile.setSelected(true);
			m_favoriteFileType = FavoriteFileType.ALL;
			initFavoriteFile();
			break;
		case WORK:
			m_btnFileType.setVisibility(View.GONE);
			m_workAdapter = new FavoriteWorkListAdapter(getActivity(), m_groupStatus, m_workData, this);
			m_lvFavorite.setAdapter(m_workAdapter);
			m_btnWork.setSelected(true);
			m_btnFile.setSelected(false);
			new ApiLoader(new FavoriteList(), getActivity(), new SquareDefaultCallback() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
						if (this.responseData.dataList != null) {
							if (m_workData.size() > 0) {
								m_workData.clear();
							}
							for (int i = 0; i < this.responseData.dataList.length(); ++i) {
								m_workData.add(new MainContentsListItemVO(this.responseData.dataList.optJSONObject(i)));
							}
							m_strLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
							m_workAdapter.notifyDataSetChanged();
						} else {
							m_workData.clear();
							m_workAdapter.notifyDataSetChanged();
						}
					}
				}
				// "squareId", "fileType", "favoriteType", "lastContentsId",
				// "lastAttachId"
			}, m_strSquareId, "", m_currentFavoriteType.getCode(), "", "").execute();
			break;
		}
	}

	@Override
	public void onChange(ChangeType t, MainContentsListItemVO item) {

	}

	@Override
	public void onLastItemVisible() {
		switch (m_currentFavoriteType) {
		case FILE:
			if (m_strLastAttachId == null) {
				return;
			}
			new ApiLoader(new FavoriteList(), getActivity(), new SquareDefaultCallback() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
						if (this.responseData == null || this.responseData.dataList == null) {
							m_strLastAttachId = null;
							return;
						}
						if (this.responseData.dataList != null) {
							for (int i = 0; i < this.responseData.dataList.length(); ++i) {
								m_fileData.add(new AttachListItemVO(this.responseData.dataList.optJSONObject(i)));
							}
							m_strLastAttachId = m_fileData.get(m_fileData.size() - 1).attachId;
							m_fileAdapter.notifyDataSetChanged();
						}
					}
				}
				// "squareId", "fileType", "favoriteType",
				// "lastContentsId",
				// "lastAttachId"
			}, m_strSquareId, m_favoriteFileType.getCode(), FavoriteType.FILE.getCode(), "", m_strLastAttachId).execute();
			break;
		case WORK:
			if (m_strLastContentsId == null) {
				return;
			}
			new ApiLoader(new FavoriteList(), getActivity(), new SquareDefaultCallback() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
						if (this.responseData == null || this.responseData.dataList == null) {
							m_strLastContentsId = null;
							return;
						}
						for (int i = 0; i < this.responseData.dataList.length(); ++i) {
							m_workData.add(new MainContentsListItemVO(this.responseData.dataList.optJSONObject(i)));
						}
						m_strLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
					}
					m_workAdapter.notifyDataSetChanged();
				}
			}, m_strSquareId, "", FavoriteType.WORK.getCode(), m_strLastContentsId, "").execute();
			break;
		}
	}

	@Override
	public void onDelete(MainContentsListItemVO item) {
		

	}

	@Override
	public void onCopy(MainContentsListItemVO item) {
		

	}

	@Override
	public void onModify(MainContentsListItemVO item) {
		

	}

	@Override
	public void onFavoriteClick(String squareId, String contentsId, FavoriteContentsType favorityType, boolean flag,
			SquareDefaultCallback squareDefaultCallback) {
		MainModel.getInstance().addFavoriteContents(getActivity(), squareDefaultCallback, squareId, contentsId, favorityType, flag);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int nPosition = position - m_lvFavorite.getRefreshableView().getHeaderViewsCount();
		switch (m_currentFavoriteType) {
		case FILE: { // SEOJAEHWA : 권한체크 추가
			Debug.trace("onItemClick : " + nPosition);

			if (HMGWServiceHelper.doc_transform_server_use) {
				MainModel.getInstance().getOpenApiService()
						.getDocHandlerResult(getActivity(),m_fileAdapter.getItem(nPosition));
			}
			else {
				// SEOJAEHWA : 권한체크 추가
				AttachListItemVO item = m_fileAdapter.getItem(nPosition);
				downloadFile(item.contentsId, item.attachId, getTargetFilePath(item.fileName, item.fileExt));
			}
			break;
		}
		case WORK:
			MainContentsListItemVO item = m_workAdapter.getItem(nPosition);
			switch (item.contentsType) {
			case COMMAND:
			case FILE_UPLOAD:
			case TOPIC: {
				Bundle build = new BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, item)
						.add(MainModel.ARG_KEY_GROUP_STATUS, m_groupStatus).build();
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
			}
				break;
			case GROUPINFO_SYSTEM_MESSAGE:
				break;
			case MESSAGE:
				break;
			case OPINION: {
				// 의견의 경우 ParentsID를 찾아 가도록 한다.
				Bundle build = new BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, item)
						.add(MainModel.ARG_KEY_GROUP_STATUS, m_groupStatus).build();
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
			}
				break;
			case SYSTEM_MESSAGE:
				break;
			case USER_SYSTEM_INFO:
				break;
			}
			break;
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		switch (m_currentFavoriteType) {
		case FILE:
			initFavoriteFile();
			break;
		case WORK:
			m_lvFavorite.setRefreshing();
			new ApiLoader(new FavoriteList(), getActivity(), new SquareDefaultCallback() {
				@Override
				public void onResponse(String strRet) {
					m_lvFavorite.stopRefreshing();
					super.onResponse(strRet);
					if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
						if (this.responseData.dataList != null) {
							if (m_workData.size() > 0) {
								m_workData.clear();
							}
							for (int i = 0; i < this.responseData.dataList.length(); ++i) {
								m_workData.add(new MainContentsListItemVO(this.responseData.dataList.optJSONObject(i)));
							}
							m_strLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
							m_workAdapter.notifyDataSetChanged();
						}
					}
				}
				// "squareId", "fileType", "favoriteType", "lastContentsId",
				// "lastAttachId"
			}, m_strSquareId, "", m_currentFavoriteType.getCode(), "", "").execute();
			break;

		}
	}
}
