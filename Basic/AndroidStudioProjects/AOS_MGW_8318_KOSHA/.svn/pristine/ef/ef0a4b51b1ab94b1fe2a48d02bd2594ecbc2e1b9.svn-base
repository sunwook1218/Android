package com.hs.mobile.gw.fragment.squareplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeSpContentsListener;
import com.hs.mobile.gw.MainModel.ISpContentsDeleteListener;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.MainModel.Views;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.adapter.squareplus.SpContentsAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpFileListAdapter;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.fragment.squareplus.SpFileFragment.SpFileType;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpTopicView.ISpTopicViewListener;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteContents;
import com.hs.mobile.gw.openapi.squareplus.SpGetFavoriteList;
import com.hs.mobile.gw.openapi.squareplus.SpGetMentionList;
import com.hs.mobile.gw.openapi.squareplus.SpGetTagContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpGetWorkfeedContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpGetWorkfeedNoticeContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpSearchResult;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpAttachListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.ContentsType;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.BundleUtils.Builder;
import com.hs.mobile.gw.view.FileAttachArea.IFileAttachClickListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class SpContentsSubFragment extends CommonFragment implements
		OnClickListener, OnItemClickListener, ISpTopicViewListener,
		IActivityResultHandlerListener, IChangeSpContentsListener,
		IFileAttachClickListener, ISpClickListener,
		ISpCompletionViewListener, OnLastItemVisibleListener,
		OnRefreshListener<ListView> {
	public static final String ARG_KEY_SP_TAG_KEYWORD = "arg_key_sp_tag_keyword";
	public static final String ARG_KEY_SP_SEARCH_KEYWORD = "arg_key_sp_search_keyword";
	public static final String ARG_KEY_SP_SEARCH_TYPE = "arg_key_sp_search_type";
	public static final String ARG_KEY_CONTENTS_VIEW_TYPE = "contentsViewType";
	public static final String ARG_KEY_CLICKED_BUTTON_TYPE = "clicked_button_type";
	public enum ContentsViewType {
		WORKFEED(R.string.label_squareplus_menu_workfeed),
		FAVORITE(R.string.label_squareplus_menu_favorite),
		MENTION(R.string.label_squareplus_menu_mention),
		SEARCH_RESULT(R.string.label_squareplus_menu_search_result),
		POPULAR_TAG(R.string.label_squareplus_menu_popular_tag)
		;
		private int viewType;

		private ContentsViewType(int nTitleResource) {
			viewType = nTitleResource;
		}

		public int getTitleRes() {
			return viewType;
		}
	}

	public enum SpFavoriteType {
		CONTENTS("contents"), FILE("file");
		private String m_strType;

		private SpFavoriteType(String s) {
			m_strType = s;
		}

		public String getType() {
			return m_strType;
		}
	}
	
	public enum SpSearchType {
		CONTENTS("contents"), FILE("file"), AUTHOR("author"), TAG("tag");
		private String m_strType;
		
		private SpSearchType(String s) {
			m_strType = s;
		}
		
		public String getType() {
			return m_strType;
		}
	}
	
	private ContentsViewType currentContentsViewType;
	private SpFavoriteType currentFavoriteType = SpFavoriteType.CONTENTS;
	private SpSearchType currentSearchType = SpSearchType.CONTENTS;
	private SpSearchType m_searchType = SpSearchType.CONTENTS;
	private SpFileType m_fileType = SpFileType.ALL;
	private ImageView m_btnMore;
	private PullToRefreshListView m_lvSquareContents;
	protected ArrayList<ISpContent> m_data = new ArrayList<ISpContent>();
	protected List<SpAttachVO> m_fileData = new ArrayList<SpAttachVO>();
	private SpContentsAdapter m_adapter;
	private SpFileListAdapter m_fileAdapter;
	private String m_strLastContentsId;
	private String m_strLastFileId;
	private String m_strSearchKeyword;
	private String m_currentSquareId;
	private boolean mListRefreshNeededWhenResume = false;
	private LinearLayout m_tabLayout;
	private LinearLayout m_searchTabLayout;
	private LinearLayout m_btnFavoriteContents;
	private LinearLayout m_btnFavoriteFile;
	private LinearLayout m_btnSearchContents;
	private LinearLayout m_btnSearchFile;
	private TextView m_tvTitle;
	private TextView m_btnFileType;
	private boolean loadingComplete;
	private boolean isPopularTagView = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 전달 받은 데이터 확인
		if (getArguments() != null) {
			if (getArguments().getSerializable(ARG_KEY_CONTENTS_VIEW_TYPE) != null) {
				currentContentsViewType = (ContentsViewType) getArguments().getSerializable(ARG_KEY_CONTENTS_VIEW_TYPE);
				if (currentContentsViewType == ContentsViewType.SEARCH_RESULT) {
					m_strSearchKeyword = getArguments().getString(ARG_KEY_SP_SEARCH_KEYWORD);
					m_searchType = (SpSearchType) getArguments().getSerializable(ARG_KEY_SP_SEARCH_TYPE);
					currentSearchType = m_searchType;
					m_currentSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
					Debug.trace("keyword = " , m_strSearchKeyword);
					Debug.trace("Type = " , currentSearchType.getType());
					Debug.trace("SquareId = " , m_currentSquareId);
				}
				else if (currentContentsViewType == ContentsViewType.POPULAR_TAG) {
					m_strSearchKeyword = getArguments().getString(ARG_KEY_SP_TAG_KEYWORD);
					m_currentSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
				}
				MainModel.getInstance().addContentsChangeListener(this);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_sp_contents_sub, container, false);
//		v.findViewById(R.id.ID_BTN_WRITE).setOnClickListener(this);
//		v.findViewById(R.id.ID_BTN_BACK).setVisibility(MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_BACK).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_MENU).setOnClickListener(this);
//		v.findViewById(R.id.ID_BTN_MENU).setVisibility(MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_MORE).setOnClickListener(this);
		m_btnMore = (ImageView) v.findViewById(R.id.ID_BTN_MORE);
		m_tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		m_btnFileType = (TextView) v.findViewById(R.id.ID_BTN_TYPE);
		m_tabLayout = (LinearLayout) v.findViewById(R.id.ID_LL_FAVORITE_TAB_CONTENTS);
		m_searchTabLayout = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_TAB_CONTENTS);
		m_lvSquareContents = (PullToRefreshListView) v.findViewById(R.id.ID_LV_SP_CONTENTS);
		m_lvSquareContents.getRefreshableView().setDividerHeight(0);
		m_lvSquareContents.getRefreshableView().setScrollingCacheEnabled(false);
		m_lvSquareContents.setOnLastItemVisibleListener(this);
		m_lvSquareContents.setOnItemClickListener(this);
		m_lvSquareContents.setOnRefreshListener(this);
		m_btnFavoriteContents = (LinearLayout) v.findViewById(R.id.ID_LL_FAVORITE_CONTENTS);
		m_btnFavoriteFile = (LinearLayout) v.findViewById(R.id.ID_LL_FAVORITE_FILE);
		m_btnFavoriteContents.setOnClickListener(this);
		m_btnFavoriteFile.setOnClickListener(this);
		m_btnSearchContents = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_CONTENTS);
		m_btnSearchFile = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_FILE);
		m_btnSearchContents.setOnClickListener(this);
		m_btnSearchFile.setOnClickListener(this);

		String title = getResources().getString(currentContentsViewType.getTitleRes());
		boolean isOriginView = true;
		if (currentContentsViewType == ContentsViewType.POPULAR_TAG) {
			title = title + " : " + m_strSearchKeyword;
			isOriginView = false;
			isPopularTagView = true;
		}
		
		m_tvTitle.setText(title);		// 타입별 제목 처리
		m_btnFileType.setOnClickListener(this);
		m_adapter = new SpContentsAdapter(m_data, SpContentsSubFragment.this, SpContentsSubFragment.this, SpContentsSubFragment.this, isOriginView, false);
		m_fileAdapter = new SpFileListAdapter(getActivity(), m_fileData);
//		m_lvSquareContents.setAdapter(m_adapter);

		// 관심글 가져올 때 탭영역 활성화
		if (currentContentsViewType == ContentsViewType.FAVORITE) {
			m_tabLayout.setVisibility(View.VISIBLE);
			setFilter(currentFavoriteType);
		}
		// 검색 결과 가져올 때 탭영역 활성화
		else if (currentContentsViewType == ContentsViewType.SEARCH_RESULT) {
			m_searchTabLayout.setVisibility(View.VISIBLE);
			setSearchFilter(currentSearchType);
		}
		else {
			refreshList();
		}

		return v;
	}

	private void refreshList() {
		loadData(true);

		if (currentContentsViewType == ContentsViewType.WORKFEED) {
//			loadNoticeData();
		}
	}

	private void loadData(final boolean isFirst) {
		if (isFirst) {
			m_data.clear();
			m_fileData.clear();
		}

		SpContentsListCallBack spContentsListCallBack = new SpContentsListCallBack(getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);

				if (dataList != null && dataList.size() > 0) {
					m_data.addAll(this.dataList);

					if (isFirst) {
						m_lvSquareContents.setAdapter(m_adapter);
						// 화면이 갱신되는동안 다시 그려지는 것을 막는다.
						m_lvSquareContents.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
							@Override
							public boolean onPreDraw() {
								return m_bDraw;
							}
						});
					}

					m_strLastContentsId = this.lastViewId;
					m_adapter.notifyDataSetChanged();
				}
				else {
					SpContentsVO vo = new SpContentsVO();
					vo.setContentsType(ContentsType.UNKNOWN.getCode());
					m_data.clear();
					m_data.add(vo);
					m_lvSquareContents.setAdapter(m_adapter);
					m_adapter.notifyDataSetChanged();
				}

				loadingComplete = true;
				m_lvSquareContents.onRefreshComplete();
			}
		};

		SpAttachListCallBack spAttachListCallBack = new SpAttachListCallBack(getActivity(), SpAttachVO.class){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);

				try {
					Debug.trace(json.toString(5));
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}

				if (dataList != null && dataList.size() > 0) {
					m_fileData.addAll(this.dataList);

					if (isFirst) {
						m_lvSquareContents.setAdapter(m_fileAdapter);
						// 화면이 갱신되는동안 다시 그려지는 것을 막는다.
						m_lvSquareContents.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
							@Override
							public boolean onPreDraw() {
								return m_bDraw;
							}
						});
					}

					m_strLastFileId = this.lastViewId;
					m_fileAdapter.notifyDataSetChanged();
				}
				else {
					SpAttachVO vo = new SpAttachVO();
					vo.setAttachId("isEmpty");
					m_fileData.clear();
					m_fileData.add(vo);
					m_lvSquareContents.setAdapter(m_fileAdapter);
					m_fileAdapter.notifyDataSetChanged();
				}

				loadingComplete = true;
				m_lvSquareContents.onRefreshComplete();
			}
		};

		{ // API명
			HashMap<String, String> params = new HashMap<>();

			switch (currentContentsViewType) {
			case WORKFEED:
				if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
					params.put("lastViewContentsId", m_strLastContentsId);
				}

				spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
				new ApiLoaderEx<>(new SpGetWorkfeedContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
				break;
			case FAVORITE:
				params.put("favoriteType", currentFavoriteType.getType());

				switch(currentFavoriteType) {
				case CONTENTS:
					if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
						params.put("lastViewId", m_strLastContentsId);
					}

					spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
					new ApiLoaderEx<>(new SpGetFavoriteList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
					break;
				case FILE:
					if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
						params.put("lastViewId", m_strLastFileId);
					}

					params.put("fileType", m_fileType.getValue());
					spAttachListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
					new ApiLoaderEx<>(new SpGetFavoriteList(getActivity()), getActivity(), spAttachListCallBack, params).execute();
					break;
				}

				break;
			case SEARCH_RESULT:

				Debug.trace("SEARCH_RESULT");
				Debug.trace("currentSearchType = ", currentSearchType);
				Debug.trace("isFirst = ", isFirst);
				Debug.trace("m_strLastContentsId = ", m_strLastContentsId);
				
				params.put("squareId", m_currentSquareId);
				params.put("searchType", currentSearchType.getType());
				params.put("searchWord", m_strSearchKeyword);
				switch(currentSearchType) {
				case CONTENTS:
				case AUTHOR:
				case TAG:
					if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
						params.put("lastViewId", m_strLastContentsId);
					}

					spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
					
					new ApiLoaderEx<>(new SpSearchResult(getActivity()), getActivity(), spContentsListCallBack, params).execute();
					break;
				case FILE:
					if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
						params.put("lastViewId", m_strLastFileId);
					}

					params.put("fileType", m_fileType.getValue());
					spAttachListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
					new ApiLoaderEx<>(new SpSearchResult(getActivity()), getActivity(), spAttachListCallBack, params).execute();
					break;
				}
				
				break;
			case MENTION:
				if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
					params.put("lastViewContentsId", m_strLastContentsId);
				}

				spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
				new ApiLoaderEx<>(new SpGetMentionList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
				break;
			case POPULAR_TAG:
				if (!isFirst && !TextUtils.isEmpty(m_strLastContentsId)) {
					params.put("lastViewContentsId", m_strLastContentsId);
				}
				
				params.put("squareId", m_currentSquareId);
				params.put("tagName", m_strSearchKeyword);
				spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
				new ApiLoaderEx<>(new SpGetTagContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
				break;
			}
		}
	}

	public void setFilter(SpFavoriteType type) {
		currentFavoriteType = type;
		m_fileType = SpFileType.ALL;

		switch (type) {
		case CONTENTS:
			m_btnFavoriteContents.setSelected(true);
			m_btnFavoriteFile.setSelected(false);
			m_btnFileType.setVisibility(View.GONE);
			refreshList();
			break;
		case FILE:
			m_btnFavoriteContents.setSelected(false);
			m_btnFavoriteFile.setSelected(true);
			m_btnFileType.setVisibility(View.VISIBLE);
			refreshList();
			break;
		}
	}
	public void setSearchFilter(SpSearchType type) {
		currentSearchType = type;
		m_fileType = SpFileType.ALL;
		
		switch (type) {
		case CONTENTS:
		case AUTHOR:
		case TAG:
			m_btnSearchContents.setSelected(true);
			m_btnSearchFile.setSelected(false);
			m_btnFileType.setVisibility(View.GONE);
			refreshList();
			break;
		case FILE:
			m_btnSearchContents.setSelected(false);
			m_btnSearchFile.setSelected(true);
			m_btnFileType.setVisibility(View.VISIBLE);
			refreshList();
			break;
		}
	}

	private void setFileType(SpFileType ft) {
		m_fileType = ft;

		loadData(true);
	}

//	// 공지글 가져오기 (1차 개발 중지)
//	@SuppressWarnings("unused")
//	private void loadNoticeData() {
//		// 공지글 목록
//		SpContentsListCallBack spContentsListCallBack = new SpContentsListCallBack(getActivity(), SpContentsVO.class) {
//			@Override
//			public void callback(String url, JSONObject json, AjaxStatus status) {
//				super.callback(url, json, status);
//
//				if (dataList != null && dataList.get(0) != null) {
//					m_tvNoticeTitle.setText(dataList.get(0).getBody());
//					m_noticeLayout.setVisibility(View.VISIBLE);
//				} else {
//					m_noticeLayout.setVisibility(View.GONE);
//				}
//			}
//		};
//		{ // API명
//			HashMap<String, String> params = new HashMap<>();
//			new ApiLoaderEx<>(new SpGetWorkfeedNoticeContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
//		}
//	}

	IContentsListItem currentFirstItemContentId = null;
	private boolean m_bDraw = true;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			if (MainModel.getInstance().isTablet()) {
				if (getFragmentManager().getBackStackEntryCount() > 0) {
					getFragmentManager().popBackStack();
				}
			} else {
				getActivity().finish();
			}
			break;
		case R.id.ID_BTN_TYPE:
			String[] items = new String[] {
					getString(R.string.favorite_file_type_all), getString(R.string.favorite_file_type_doc),
					getString(R.string.favorite_file_type_img), getString(R.string.favorite_file_type_etc) };
			new AlertDialog.Builder(getActivity()).setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					SpFileType ft = SpFileType.values()[which];
					setFileType(ft);
				}
			}).show();
			break;
		case R.id.ID_BTN_MENU:
			break;
		case R.id.ID_BTN_MORE:
			PopupMenu popupMenu = new PopupMenu(getActivity(), m_btnMore);
			popupMenu.getMenuInflater().inflate(R.menu.squareplus_contents_list, popupMenu.getMenu());
			popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()) {
//					Bundle bundle = new BundleUtils.Builder().build();
//
//					case R.id.ID_MENU_SETTING:
//						// Debug.trace("R.id.ID_MENU_SETTING");
//						MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_SETTING, bundle);
//						break;
					}
					return true;
				}
			});
			popupMenu.show();
			break;
		case R.id.ID_BTN_WRITE:
			break;
		case R.id.ID_LL_FAVORITE_CONTENTS:
			setFilter(SpFavoriteType.CONTENTS);
			break;
		case R.id.ID_LL_FAVORITE_FILE:
			setFilter(SpFavoriteType.FILE);
			break;
		case R.id.ID_LL_SEARCH_CONTENTS:
			if (m_searchType == SpSearchType.FILE)
				setSearchFilter(SpSearchType.CONTENTS);
			else
				setSearchFilter(m_searchType);
			
			break;
		case R.id.ID_LL_SEARCH_FILE:
			setSearchFilter(SpSearchType.FILE);
			break;
		}
	}

	private void downloadFileOpen(SpAttachVO vo) {

		// SEOJAEHWA : 권한체크 추가
		downloadFile(vo.getContentsId(), vo.getAttachId(),
				getTargetFilePath(vo.getFileName(), vo.getFileExt()));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int nRealPosition = position - m_lvSquareContents.getRefreshableView().getHeaderViewsCount();

		if (currentContentsViewType == ContentsViewType.FAVORITE) {
			switch(currentFavoriteType) {
			case CONTENTS:
				break;
			case FILE:
				if (HMGWServiceHelper.doc_transform_server_use) {
					MainModel.getInstance().getOpenApiService().
							getDocHandlerResult(getActivity(), m_fileData.get(nRealPosition));
				} else {
					// SEOJAEHWA: call method
					downloadFileOpen(m_fileData.get(nRealPosition));
				}
				Debug.trace("onItemClick : " + nRealPosition);
			}
		}
		else if (currentContentsViewType == ContentsViewType.SEARCH_RESULT) {
			switch(currentSearchType) {
			case CONTENTS:
				break;
			case FILE:
				Debug.trace("onItemClick : " + nRealPosition);

				MainModel.getInstance().getOpenApiService()
					.getDocHandlerResult(
						getActivity(),
						m_fileData.get(nRealPosition)
					);

				// SEOJAEHWA: call method
//				downloadFileOpen(m_fileData.get(nRealPosition));
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MainModel.REQ_FROM_SQUARE_CONTENTS_DETAIL) {
			if (resultCode == Activity.RESULT_OK) {
			} else if (resultCode == Activity.RESULT_CANCELED) {
				getActivity().finish();
			}
		} else {
			if (resultCode == Activity.RESULT_OK) {
				MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
			}
		}
	}

	@Override
	public void onDestroy() {
		MainModel.getInstance().removeContentsChangeListener(this);
		getMainModel().setCurrentSquareId(null);
		super.onDestroy();
	}

	@Override
	public void onResume() {
		super.onResume();

		if (mListRefreshNeededWhenResume) {
			mListRefreshNeededWhenResume = false;
			refreshList();
		}
	}

	@Override
	public void onFileClick(AttachListItemVO item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadCompleteMedia(ResultType filePath, String strRet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDetailView(SpContentsVO item, boolean showKeyboard) {
		Bundle build = new BundleUtils.Builder()
				.add(MainModel.ARG_KEY_SQUARE_ID, item.getSquareId())
				.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId())
				.add(MainModel.ARG_KEY_SP_SQUARE_STATUS, Status.NONE)
				.add(MainModel.ARG_KEY_SP_IS_SUB_CONTENTS, true)
				.add(MainModel.ARG_KEY_SP_IS_POPULAR_TAG, isPopularTagView)
				.add(MainModel.ARG_KEY_SHOW_KEYBOARD, showKeyboard).build();
		
		if (MainModel.getInstance().isTablet()) {
			MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsDetailFragment(), build,
					R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, getString(currentContentsViewType.getTitleRes()));
		} else {
			MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_DETAIL, build);
		}
	}

	@Override
	public void onMentionClick(SpContentsMentionVO item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTagClick(String replace) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserClick(String itemId) {
		MainModel.getInstance().showSubActivity(this, SubActivityType.CUSTOM_WEBVIEW, new BundleUtils.Builder()
				.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showUserDetailsPopup('" + itemId + "');").build());
	}

	@Override
	public void onLastItemVisible() {
		Debug.trace("LastItemVisible");
		switch(currentFavoriteType) {
		case CONTENTS:
			if (!"00000000".equals(m_strLastContentsId) && loadingComplete == true) {
				loadingComplete = false;
				loadData(false);
			}
			break;
		case FILE:
			if (!"00000000".equals(m_strLastFileId) && loadingComplete == true) {
				loadingComplete = false;
				loadData(false);
			}
			break;
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		m_lvSquareContents.setRefreshing();
		refreshList();
	}

	@Override
	public void onFileView(SpAttachVO item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavoriteClick(SpAttachVO vo, IFavoriteCallbak favoriteCallback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFavoriteClick(SpContentsVO contentsVO, final IFavoriteCallbak favoriteCallback) {
		SpContentsCallBack spContentsCallBack = new SpContentsCallBack(
				getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				Debug.trace(this.item);
				favoriteCallback.onResponse(this.item);
				MainModel.getInstance().notifyChanged(this.item, ChangeType.MODIFY);
			}
		};

		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", contentsVO.getSquareId());
		params.put("contentsId", contentsVO.getContentsId());
		params.put("contentsType", contentsVO.getFavoriteType()); // FavoriteType에
																	// 맞춰서 삽입한다.
		params.put("favoriteFlag", "1".equals(contentsVO.getFavoriteFlag()) ? SpSquareConst.FALSE_CH:SpSquareConst.TRUE_CH);
		new ApiLoaderEx<>(new SpAddFavoriteContents(getActivity()),
				getActivity(), spContentsCallBack, params).execute();
	}

	@Override
	public void onDeleteClick(SpAttachVO item) {

	}

	@Override
	public void onModify(SpContentsVO item) {
		Builder builder = new BundleUtils.Builder();
		builder.add(MainModel.ARG_KEY_SQUARE_ID, item.getSquareId());
		builder.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId());
		MainModel.getInstance().showSubActivity(getActivity(),
				SubActivityType.SP_CONTENTS_WRITE, builder.build());
	}

	@Override
	public void onDelete(SpContentsVO item) {
		MainModel.getInstance().deleteContents(getActivity(), item, new ISpContentsDeleteListener() {
			@Override
			public void onDeleteComplete(SpContentsVO item) {
				MainModel.getInstance().notifyChanged(item, ChangeType.DELETE);
			}
		});
	}

	@Override
	public void onChange(ChangeType t, SpContentsVO item) {
		switch (t) {
		case ADD:
			mListRefreshNeededWhenResume = true;
			break;
		case DELETE:
			m_data.remove(item);
			m_adapter.notifyDataSetChanged();
			m_adapter.notifyDataSetInvalidated();
			break;
		case MODIFY:
			int nPosition = m_data.lastIndexOf(item);
			if (nPosition != -1) {
				m_data.remove(nPosition);
				m_data.add(nPosition, item);
				m_adapter.notifyDataSetChanged();
				m_adapter.notifyDataSetInvalidated();
			}
			break;
		}
	}

	@Override
	public void onBtnTagAddClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMoveToUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOrgImgDown(SpAttachVO spAttachVO) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onFavorClick(SpContentsVO item, int Type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNoticeView(SpContentsVO item) {
		// TODO Auto-generated method stub
		
	}

}