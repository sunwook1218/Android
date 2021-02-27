package com.hs.mobile.gw.fragment.squareplus;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeSpContentsListener;
import com.hs.mobile.gw.MainModel.ISpContentsDeleteListener;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.adapter.squareplus.SpContentsAdapter;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpTopicView.ISpTopicViewListener;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteContents;
import com.hs.mobile.gw.openapi.squareplus.SpGetContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpGetNoticeContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquare;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.BundleUtils.Builder;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.FileAttachArea.IFileAttachClickListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class SpContentsFragment extends CommonFragment implements
		OnClickListener, OnItemClickListener, ISpTopicViewListener,
		IActivityResultHandlerListener, IChangeSpContentsListener,
		IFileAttachClickListener, ISquarePushListener, ISpClickListener,
		ISpCompletionViewListener, OnLastItemVisibleListener,
		OnRefreshListener<ListView> {
	public static final String ARG_KEY_WORKGROUP_ITEM = "workGroupItem";
	public static final String ARG_KEY_CLICKED_BUTTON_TYPE = "clicked_button_type";
	private ImageView m_btnMore;
	private PullToRefreshListView m_lvSquareContents;
	protected ArrayList<ISpContent> m_data = new ArrayList<ISpContent>();
	private SpContentsAdapter m_adapter;
	private SpSquareVO m_squareData;
	private String m_strLastContentsId;
	private boolean mListRefreshNeededWhenResume = false;
	private String m_squareId;
	private boolean m_squareOpen;
	private TextView m_tvTitle;
	private boolean loadingComplete;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 전달 받은 데이터 확인
		if (getArguments() != null) {
			if (getArguments().getSerializable(ARG_KEY_WORKGROUP_ITEM) != null) {
				ArrayList<String> strList = (ArrayList<String>) getArguments()
						.getSerializable(ARG_KEY_WORKGROUP_ITEM);
				m_squareId = strList.get(0);
				m_squareOpen = SpSquareConst.FALSE_CH.equals(strList.get(2));	// true : 공개그룹, false : 비공개그룹
				MainModel.getInstance().setCurrentSquareId(m_squareId);
				MainModel.getInstance().addContentsChangeListener(this);
				MainModel.getInstance().addSquarePushListener(this);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
 Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_sp_contents, container, false);
		v.findViewById(R.id.ID_BTN_WORKFEED).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_FAVORITE).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_MENTION).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_FILE).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_BACK).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_BACK).setVisibility(MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_MENU).setOnClickListener(this);
		// v.findViewById(R.id.ID_BTN_MENU).setVisibility(
		// MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_MORE).setOnClickListener(this);
		final ImageButton btnWrite = (ImageButton) v.findViewById(R.id.ID_BTN_WRITE);
		m_btnMore = (ImageView) v.findViewById(R.id.ID_BTN_MORE);
		m_tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		m_lvSquareContents = (PullToRefreshListView) v.findViewById(R.id.ID_LV_SP_CONTENTS);
		m_lvSquareContents.getRefreshableView().setDividerHeight(0);
		m_lvSquareContents.getRefreshableView().setScrollingCacheEnabled(false);
		m_lvSquareContents.setOnLastItemVisibleListener(this);
		m_lvSquareContents.setOnRefreshListener(this);

		PopupUtil.showLoading(getActivity());
		SpSquareCallBack spSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(getActivity());
				m_squareData = item;
				m_tvTitle.setText(item.getTitle());

				// 해당 그룹의 구성원이거나 그룹상태가 진행중이며 참관인권한이 아니면 글쓰기 버튼 Enable
				if (m_squareData.isIng() && m_squareData.getMemberRightsEnum() != MemberRights.OBSERVER_USER
						&& m_squareData.isMySquareMember()) {
					btnWrite.setOnClickListener(SpContentsFragment.this);
				} else {
					btnWrite.setImageResource(R.drawable.sp_btn_write_disable);
				}

				m_adapter = new SpContentsAdapter(m_data, SpContentsFragment.this, SpContentsFragment.this, SpContentsFragment.this,
						item.getMemberRightsEnum() == MemberRights.OBSERVER_USER || m_squareOpen);
				m_lvSquareContents.setAdapter(m_adapter);
				// 화면이 갱신되는동안 다시 그려지는 것을 막는다.
				m_lvSquareContents.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
					@Override
					public boolean onPreDraw() {
						return m_bDraw;
					}
				});
				refreshList();
			}
		};

		{ // API명
			HashMap<String, String> params = new HashMap<>();
			params.put("squareId", m_squareId);
			new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spSquareCallBack, params).execute();
		}
		return v;
	}

	private void refreshList() {
		// 공지글 관련 
		loadNoticeData(true);
	}

	private void loadData(final boolean first) {
		Debug.trace("first = " , first);
		PopupUtil.showLoading(getActivity());
		SpContentsListCallBack spContentsListCallBack = new SpContentsListCallBack(getActivity(), SpContentsVO.class) {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(getActivity());
				try {
					Debug.trace(json.toString(5));
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}
				if (dataList != null) {
					m_data.addAll(this.dataList);
					m_strLastContentsId = this.lastViewId;
					m_adapter.notifyDataSetChanged();
				}

				loadingComplete = true;
				m_lvSquareContents.onRefreshComplete();
			}
		};
		spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
		{ // API명
			HashMap<String, String> params = new HashMap<>();
			params.put("squareId", m_squareId);
			if (!first && !TextUtils.isEmpty(m_strLastContentsId)) {
				params.put("lastViewContentsId", m_strLastContentsId);
			}
			new ApiLoaderEx<>(new SpGetContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
		}
	}

	// 공지글 가져오기
	private void loadNoticeData(final boolean first) {
		Debug.trace("");
		// 공지글 목록
		if (first)
			m_data.clear();

		SpContentsListCallBack spContentsListCallBack = new SpContentsListCallBack(getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				if (dataList != null && dataList.size() > 0) {
					if (dataList.get(0) != null) {
						SpContentsVO vo = dataList.get(0);
						Debug.trace("notice code = ", SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE);
						vo.setContentsType(SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE);
						m_data.add(0, vo);
					}
				} else {
//					m_noticeLayout.setVisibility(View.GONE);
				}
				
				if (first) {
					loadData(true);
				} else {
					m_adapter.notifyDataSetChanged();
				}
					
			}
		};
		{ // API명
			HashMap<String, String> params = new HashMap<>();
			params.put("squareId", m_squareId);
			params.put("count", "1");
			new ApiLoaderEx<>(new SpGetNoticeContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
		}
	}

	IContentsListItem currentFirstItemContentId = null;
	private boolean m_bDraw = true;

	@Override
	public void onClick(View v) {
		Builder builder = new BundleUtils.Builder();

		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_BTN_MENU:
			break;
		case R.id.ID_BTN_MORE:
			PopupMenu popupMenu = new PopupMenu(getActivity(), m_btnMore);
			popupMenu.getMenuInflater().inflate(
					R.menu.squareplus_contents_list, popupMenu.getMenu());
			popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Builder builder = new BundleUtils.Builder();
					builder.add(MainModel.ARG_KEY_SQUARE_ID, m_squareId).build();
					if (MainModel.getInstance().isTablet()) {
						switch (item.getItemId()) {
						case R.id.ID_MENU_SEARCH:
							MainModel.getInstance().showFragmentToTarget(getActivity(), new SpSearchFragment(), builder.build(),
									R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);

							break;
						case R.id.ID_MENU_POPULAR_TAG:
							MainModel.getInstance().showFragmentToTarget(getActivity(), new SpPopularTagFragment(), builder.build(),
									R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
							break;
						case R.id.ID_MENU_SETTING:
							MainModel.getInstance().showFragmentToTarget(getActivity(), new SpSettingFragment(), builder.build(),
									R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
							break;
						}
					} else {
						switch (item.getItemId()) {
						case R.id.ID_MENU_SEARCH:
							MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_SEARCH, builder.build());
							break;
						case R.id.ID_MENU_POPULAR_TAG:
							MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_POPULAR_TAG, builder.build());

							break;
						case R.id.ID_MENU_SETTING:
							// Debug.trace("R.id.ID_MENU_SETTING");
							MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_SETTING, builder.build());
							break;
						}
					}
					return true;
				}
			});
			popupMenu.show();
			break;
		case R.id.ID_BTN_UPLOAD:
			if (m_squareData.getStatusEnum() == Status.IN_PROGRESS) {
				MainModel.getInstance().showUploadDialog(this, m_squareId, false);
			}
			break;
		case R.id.ID_BTN_WORKFEED:
			builder.add(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpContentsSubFragment.ContentsViewType.WORKFEED);

			if (MainModel.getInstance().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsSubFragment(), builder.build(),
						R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
			} else {
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_WORKFEED, builder.build());
			}

			break;
		case R.id.ID_BTN_FAVORITE:
			builder.add(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpContentsSubFragment.ContentsViewType.FAVORITE);

			if (MainModel.getInstance().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsSubFragment(), builder.build(),
						R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
			} else {
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_FAVORITE, builder.build());
			}

			break;
		case R.id.ID_BTN_WRITE:
			builder.add(MainModel.ARG_KEY_SQUARE_ID, m_squareId);
			MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_WRITE, builder.build());
			break;
		case R.id.ID_BTN_MENTION:
			builder.add(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpContentsSubFragment.ContentsViewType.MENTION);

			if (MainModel.getInstance().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsSubFragment(), builder.build(),
						R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
			} else {
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_MENTION, builder.build());
			}

			break;
		case R.id.ID_BTN_FILE:
			builder.add(MainModel.ARG_KEY_SQUARE_ID, m_squareId);

			if (MainModel.getInstance().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpFileFragment(), builder.build(),
						R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
			} else {
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_FILE, builder.build());
			}

			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		int nRealPosition = position
				- m_lvSquareContents.getRefreshableView().getHeaderViewsCount();
		if (m_data.get(nRealPosition) instanceof SpContentsVO) {
			SpContentsVO item = (SpContentsVO) m_data.get(nRealPosition);
			Bundle build = new BundleUtils.Builder()
					.add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO,
							item)
					.add(MainModel.ARG_KEY_GROUP_STATUS,
							m_squareData.getStatus()).build();
			switch (item.getContentsTypeEnum()) {
			case TOPIC:
			case COMMENT:
			case FILE:
			case MESSAGE:
				// if (MainModel.getInstance().isTablet()) {
				// getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
				// MainModel.getInstance().showFragmentToTarget(getActivity(),
				// new SquareContentsDetailFragment(), build,
				// R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT);
				// } else {
				MainModel.getInstance().showSubActivity(getActivity(),
						SubActivityType.SQUARE_CONTENTS_DETAIL, build);
				// }
				break;
			default:
				return;
			}
		}
	}

	// @Override
	// public void onDelete(final SpContentsVO item) {
	// MainModel.getInstance().deleteContents(getActivity(), item, new
	// IContentsDeleteListener() {
	// @Override
	// public void onDeleteComplete(SpContentsVO item) {
	// MainModel.getInstance().notifyChanged(item, ChangeType.DELETE);
	// }
	// });
	// }
	//
	// @Override
	// public void onCopy(SpContentsVO item) {
	// MainModel.getInstance().copyBodyText(getActivity(), item);
	// }
	//
	// @Override
	// public void onModify(SpContentsVO item) {
	// MainModel.getInstance().modifyContents(getActivity(), item, new
	// SquareDefaultCallback() {
	// @Override
	// public void onResponse(String strRet) {
	// super.onResponse(strRet);
	// SpContentsVO item = new
	// SpContentsVO(getJsonObject().optJSONObject("responseData"));
	// MainModel.getInstance().notifyChanged(item, ChangeType.MODIFY);
	// }
	//
	// @Override
	// public void onFailure(String err) {
	// }
	// });
	// }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == MainModel.REQ_FROM_SQUARE_CONTENTS_DETAIL) {
			if (resultCode == Activity.RESULT_OK) {
				Debug.trace("잘 갔다 왔냐");
			} else if (resultCode == Activity.RESULT_CANCELED) {
				getActivity().finish();
			}
		} else {
			if (resultCode == Activity.RESULT_OK) {
				MainModel.getInstance().activityResultMediaHandler(
						getActivity(), requestCode, resultCode, data, this);
			}
		}
	}

	// @Override
	// public void onLoadCompleteMedia(ResultType t, String strRet) {
	// switch (t) {
	// case FILE_PATH:
	// if (MainModel.getInstance().checkFileSize(strRet) > 0) {
	// PopupUtil.showDialog(getActivity(), R.string.square_file_limit_message);
	// return;
	// }
	// AddFile api = new AddFile();
	// ArrayList<String> fileList = new ArrayList<String>();
	// fileList.add(strRet);
	// api.setFilePath(fileList);
	// new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
	// @Override
	// public void onResponse(String strRet) {
	// super.onResponse(strRet);
	// if (this.responseHead != null && this.responseHead.resultCode == SUCCESS)
	// {
	// SpContentsVO item = new
	// SpContentsVO(getJsonObject().optJSONObject("responseData"));
	// m_data.add(item);
	// m_adapter.notifyDataSetChanged();
	// }
	// }
	// }, m_squareData.getSquareId()).execute();
	// break;
	// }
	// }
	//
	// @Override
	// public void onItemClick(SpContentsVO item, ButtonType t) {
	// Bundle build = new
	// BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO,
	// item)
	// .add(ARG_KEY_CLICKED_BUTTON_TYPE,
	// t.toString()).add(MainModel.ARG_KEY_GROUP_STATUS,
	// m_squareData.getStatus()).build();
	// if (MainModel.getInstance().isTablet()) {
	// getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
	// //
	// MainActivity.viewActionsContentView.findViewById(R.id.ID_LAY_L_WEBVIEW_LAYOUT).setVisibility(View.GONE);
	// MainModel.getInstance().showFragmentToTarget(getActivity(), new
	// SquareContentsDetailFragment(), build,
	// R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT);
	// } else {
	// MainModel.getInstance().showSubActivity(getActivity(),
	// SubActivityType.SQUARE_CONTENTS_DETAIL, build);
	// }
	// }
	//
	// @Override
	// public void onFavoriteClick(String squareId, String contentsId,
	// FavoriteContentsType favorityType, boolean flag,
	// SquareDefaultCallback squareDefaultCallback) {
	// MainModel.getInstance().addFavoriteContents(getActivity(),
	// squareDefaultCallback, squareId, contentsId, favorityType, flag);
	// }

	@Override
	public void onDestroy() {
		MainModel.getInstance().removeContentsChangeListener(this);
		MainModel.getInstance().removeSquarePushListener(this);
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
		
		if(m_adapter != null){
			m_adapter.setTopicViewListener(this);
		}
	}

	@Override
	public void onPushReceive(SquarePushVO vo) {

	}

	@Override
	public void onFileClick(AttachListItemVO item) {

	}

	@Override
	public void onChange(ChangeType t, SpContentsVO item) {
		Debug.trace(t.toString());
		switch (t) {
		case ADD:
			mListRefreshNeededWhenResume = true;
			break;
		case DELETE: {
			boolean bRefreshNotice = false;
			for (int i = m_data.size() - 1; i >= 0; i--) {
				SpContentsVO vo = (SpContentsVO) m_data.get(i);
				if (vo.getContentsId().equals(item.getContentsId())) {
					if (vo.getContentsType() == SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE) {
						bRefreshNotice = true;
					}
					m_data.remove(i);
					Debug.trace("DELETE index = " , i);
				}
			}

			if (bRefreshNotice)
				loadNoticeData(false);

			m_adapter.notifyDataSetChanged();
			m_adapter.notifyDataSetInvalidated();
		}
			break;
		case MODIFY: {
			boolean bRefreshNotice = false;
			for (int i = m_data.size() - 1; i >= 0; i--) {
				SpContentsVO vo = (SpContentsVO) m_data.get(i);
				if (vo.getContentsId().equals(item.getContentsId())) {
					if (vo.getContentsType() == SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE) {
						m_data.remove(i);
						bRefreshNotice = true;
					} else {
						m_data.remove(i);
						m_data.add(i, item);
					}
				}
			}

			if (bRefreshNotice)
				loadNoticeData(false);

			m_adapter.notifyDataSetChanged();
			m_adapter.notifyDataSetInvalidated();
		}
			break;
		}
	}

	@Override
	public void onLoadCompleteMedia(ResultType filePath, String strRet) {

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
	public void onModify(SpContentsVO item) {
		Builder builder = new BundleUtils.Builder();
		builder.add(MainModel.ARG_KEY_SQUARE_ID, item.getSquareId());
		builder.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId());
		MainModel.getInstance().showSubActivity(getActivity(),
				SubActivityType.SP_CONTENTS_WRITE, builder.build());
	}

	@Override
	public void onDetailView(SpContentsVO item, boolean showKeyboard) {
		Bundle build = new BundleUtils.Builder()
			.add(MainModel.ARG_KEY_SQUARE_ID, m_squareId)
			.add(MainModel.ARG_KEY_SP_SQUARE_STATUS, m_squareData.getStatusEnum())
			.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId())
			.add(MainModel.ARG_KEY_SHOW_KEYBOARD, showKeyboard)
			.build();

		if (MainModel.getInstance().isTablet()) {
			MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsDetailFragment(), build,
					R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
		} else {
			MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_DETAIL, build);
		}
	}

	@Override
	public void onMentionClick(SpContentsMentionVO item) {
		MainModel.getInstance().showSubActivity(
				this,
				SubActivityType.CUSTOM_WEBVIEW,
				new BundleUtils.Builder().add(
						CustomWebViewFragment.ARG_KEY_URL,
						"javascript:showUserDetailsPopup('" + item.getUserId()
								+ "');").build());
	}

	@Override
	public void onTagClick(String replace) {

	}

	@Override
	public void onUserClick(String itemId) {
		MainModel.getInstance().showSubActivity(
				this,
				SubActivityType.CUSTOM_WEBVIEW,
				new BundleUtils.Builder().add(
						CustomWebViewFragment.ARG_KEY_URL,
						"javascript:showUserDetailsPopup('" + itemId + "');")
						.build());
	}

	@Override
	public void onLastItemVisible() {
		Debug.trace("LastItemVisible");
		if (!"00000000".equals(m_strLastContentsId) && loadingComplete == true) {
			loadingComplete = false;
			loadData(false);
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		m_lvSquareContents.setRefreshing();
		refreshList();
	}

	@Override
	public void onFileView(SpAttachVO item) {

	}

	@Override
	public void onFavoriteClick(SpAttachVO vo, IFavoriteCallbak favoriteCallback) {

	}

	@Override
	public void onFavoriteClick(SpContentsVO vo,
			final IFavoriteCallbak favoriteCallback) {
		SpContentsCallBack spContentsCallBack = new SpContentsCallBack(
				getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				Debug.trace(this.item);
				favoriteCallback.onResponse(this.item);
			}
		};

		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", vo.getSquareId());
		params.put("contentsId", vo.getContentsId());
		params.put("contentsType", vo.getFavoriteType()); // FavoriteType에
																	// 맞춰서 삽입한다.
		params.put("favoriteFlag", "1".equals(vo.getFavoriteFlag()) ? SpSquareConst.FALSE_CH:SpSquareConst.TRUE_CH);
		new ApiLoaderEx<>(new SpAddFavoriteContents(getActivity()),
				getActivity(), spContentsCallBack, params).execute();

	}

	@Override
	public void onDeleteClick(SpAttachVO item) {

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
		Bundle build = new BundleUtils.Builder().add(SpNoticeFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpNoticeFragment.ContentsViewType.SQUARE)
				.add(MainModel.ARG_KEY_SP_SQUARE_STATUS, m_squareData.getStatusEnum())
				.add(SpNoticeFragment.ARG_KEY_SQUARE_ID, m_squareId).build();

		if (MainModel.getInstance().isTablet()) {
			MainModel.getInstance().showFragmentToTarget(getActivity(), new SpNoticeFragment(), build, R.id.ID_LAY_L_FRAGMENT_LAYOUT, true,
					null);
		} else {
			MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_WORKFEED_NOTICE, build);
		}
	}

	// @Override
	// public void onChange(ChangeType t, SpContentsVO item) {
	// switch (t) {
	// case ADD:
	// mListRefreshNeededWhenResume = true;
	// break;
	// case DELETE:
	// if (item.contentsType.equals(SquareContentsType.TOPIC) ||
	// item.contentsType.equals(SquareContentsType.COMMAND)
	// || item.contentsType.equals(SquareContentsType.FILE_UPLOAD)) {
	// // 부모가 지워져서 자식들도 없애야 하는 경우 orgParentId와, contentsId가 같으면 부모 콘텐츠라고 판단한다.
	// if (item.originalParentId.equals(item.contentsId)) {
	// ArrayList<SpContentsVO> removeList = new ArrayList<SpContentsVO>();
	// for (int i = 0; i < m_data.size(); ++i) {
	// if (m_data.get(i).getObjectType() == ContentsObjectType.CONTENTS) {
	// SpContentsVO mcitem = (SpContentsVO) m_data.get(i);
	// if (mcitem.contentsType.equals(SquareContentsType.OPINION)) {
	// if (TextUtils.equals(item.originalParentId, item.originalParentId)) {
	// removeList.add(mcitem);
	// }
	// }
	// }
	// }
	// m_data.removeAll(removeList);
	// }
	// }
	// m_data.remove(item);
	// m_adapter.notifyDataSetChanged();
	// m_adapter.notifyDataSetInvalidated();
	// break;
	// case MODIFY:
	// int nPosition = m_data.lastIndexOf(item);
	// if (nPosition != -1) {
	// m_data.remove(nPosition);
	// m_data.add(nPosition, item);
	// m_adapter.notifyDataSetChanged();
	// m_adapter.notifyDataSetInvalidated();
	// }
	// break;
	// }
	// }

	// @Override
	// public void onFileClick(AttachListItemVO item) {
	// MainModel.getInstance().downloadFile(getActivity(), item.contentsId,
	// item.attachId,
	// MainModel.getInstance().getExternamDownloadDirectory(item.fileName,
	// item.fileExt));
	// }
	//
	// @Override
	// public void onPushReceive(final SquarePushVO vo) {
	// if (!isAdded()) {
	// return;
	// }
	// if (TextUtils.equals(vo.square_id, m_squareData.getSquareId())) {
	// if (vo.square_action == SquarePushAction.CONTENTS_ADD || vo.square_action
	// == SquarePushAction.COMMENT_ADD) {
	// // 마지막 아이템에 표시중인 데이터의 parent_id를 비교한다.
	// IContentsListItem lastItem = m_data.get(m_data.size() - 1);
	// if (lastItem.getObjectType() == ContentsObjectType.CONTENTS
	// && TextUtils.equals(((SpContentsVO) lastItem).parentId,
	// vo.square_parent_id)) {
	// SquareDefaultAjaxCallBack<ContentsVO> contentsCallBack = new
	// SquareDefaultAjaxCallBack<ContentsVO>(getActivity(),
	// ContentsVO.class) {
	// public void callback(String url, JSONObject json, AjaxStatus status) {
	// super.callback(url, json, status);
	// SpContentsVO item = new SpContentsVO(getVO().responseData.getJson());
	// m_data.add(item);
	// m_adapter.notifyDataSetChanged();
	// }
	// };
	// Map<String, String> contentsParams = new HashMap<String, String>();
	// contentsParams.put("squareId", vo.square_id);
	// contentsParams.put("contentsId", vo.square_contents_id);
	// new ApiLoaderEx<JSONObject>(new GetContents(getActivity()),
	// getActivity(), contentsCallBack, contentsParams).execute();
	// } else {
	// final ArrayList<IContentsListItem> tempDataList = new
	// ArrayList<IContentsListItem>();
	// SquareDefaultAjaxCallBack<SquareDefaultVO> newContentsCallBack = new
	// SquareDefaultAjaxCallBack<SquareDefaultVO>(
	// getActivity(), SquareDefaultVO.class) {
	// public void callback(String url, JSONObject json, AjaxStatus status) {
	// super.callback(url, json, status);
	// if (getVO().responseData != null && getVO().responseData.dataList !=
	// null) {
	// ArrayList<SpContentsVO> newContentsDataList = new
	// ArrayList<SpContentsVO>();
	// for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
	// SpContentsVO addItem = new SpContentsVO(
	// getVO().responseData.dataList.optJSONObject(i));
	// newContentsDataList.add(addItem);
	// }
	// tempDataList.addAll(newContentsDataList);
	// }
	// m_data.addAll(addDateSeperator(tempDataList));
	// deleteDuplecateSystemDate(m_data);
	// m_adapter.notifyDataSetChanged();
	// m_lvSquareContents.getRefreshableView().setSelection(m_adapter.getCount()
	// - 1);
	// m_lvSquareContents.setOnScrollListener(SpContentsFragment.this);
	// }
	// };
	// Map<String, String> newContentsParams = new HashMap<String, String>();
	// newContentsParams.put("squareId", m_squareData.getSquareId());
	// new ApiLoaderEx<JSONObject>(new GetNewContentsList(getActivity()),
	// getActivity(), newContentsCallBack,
	// newContentsParams).execute();
	// }
	//
	// } else if (vo.square_action == SquarePushAction.CONTENTS_DELETE) {
	// for (IContentsListItem content : m_data) {
	// if (content.getObjectType() == ContentsObjectType.CONTENTS) {
	// SpContentsVO contentVO = (SpContentsVO) content;
	// if (TextUtils.equals(contentVO.contentsId, vo.square_contents_id)) {
	// if (contentVO.contentsType.equals(SquareContentsType.TOPIC)
	// || contentVO.contentsType.equals(SquareContentsType.COMMAND)
	// || contentVO.contentsType.equals(SquareContentsType.FILE_UPLOAD)) {
	// ArrayList<SpContentsVO> removeList = new ArrayList<SpContentsVO>();
	// for (int i = 0; i < m_data.size() &&
	// m_data.get(i).getObjectType().equals(ContentsObjectType.CONTENTS); ++i) {
	// SpContentsVO mcitem = (SpContentsVO) m_data.get(i);
	// if (mcitem.contentsType.equals(SquareContentsType.OPINION)) {
	// if (TextUtils.equals(mcitem.originalParentId,
	// contentVO.originalParentId)) {
	// removeList.add(mcitem);
	// }
	// }
	// }
	// m_data.removeAll(removeList);
	// m_adapter.notifyDataSetChanged();
	// } else {
	// m_data.remove(contentVO);
	// m_adapter.notifyDataSetChanged();
	// break;
	// }
	// }
	//
	// }
	// }
	// } else if (vo.square_action == SquarePushAction.CONTENTS_MODIFY) {
	// for (IContentsListItem content : m_data) {
	// if (content.getObjectType() == ContentsObjectType.CONTENTS) {
	// final SpContentsVO contentVO = (SpContentsVO) content;
	// if (TextUtils.equals(contentVO.contentsId, vo.square_contents_id)) {
	// SquareDefaultAjaxCallBack<ContentsVO> contentsCallBack = new
	// SquareDefaultAjaxCallBack<ContentsVO>(
	// getActivity(), ContentsVO.class) {
	// public void callback(String url, JSONObject json, AjaxStatus status) {
	// super.callback(url, json, status);
	// SpContentsVO item = new SpContentsVO(getVO().responseData.getJson());
	// int n = m_data.indexOf(item);
	// m_data.remove(item);
	// m_data.add(n, item);
	// m_adapter.notifyDataSetChanged();
	// }
	// };
	// Map<String, String> contentsParams = new HashMap<String, String>();
	// contentsParams.put("squareId", vo.square_id);
	// contentsParams.put("contentsId", vo.square_contents_id);
	// new ApiLoaderEx<JSONObject>(new GetContents(getActivity()),
	// getActivity(), contentsCallBack, contentsParams)
	// .execute();
	// break;
	// }
	// }
	// }
	// } else if (vo.square_action == SquarePushAction.GROUP_DELETE) {
	// // 속해 있던 그룹이 삭제 된 경우
	// new
	// AlertDialog.Builder(getActivity()).setTitle(R.string.message_confirm_title)
	// .setMessage(R.string.square_group_deleted_message)
	// .setPositiveButton(R.string.label_confirm, new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// getActivity().finish();
	// }
	// }).setCancelable(false).show();
	// } else if (vo.square_action == SquarePushAction.GROUP_MODIFY ||
	// vo.square_action == SquarePushAction.SQUARE_MEMBER_CHANGE
	// || vo.square_action == SquarePushAction.SQURE_MEMBER_ADMIN_CHANGE) {
	// SquareDefaultAjaxCallBack<GroupInfoVO> callback = new
	// SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(),
	// GroupInfoVO.class) {
	// @Override
	// public void callback(String url, JSONObject json, AjaxStatus status) {
	// super.callback(url, json, status);
	// switch (vo.square_action) {
	// case GROUP_MODIFY:
	// // 그룹 Info가 바뀌었을 때는 타이틀이 바뀌었을 경우도 있으므로 타이틀을 GroupInfo로 부터 받은 정보로 교체해 준다.
	// m_tvTitle.setText(getVO().title);
	// case SQUARE_MEMBER_CHANGE:
	// case SQURE_MEMBER_ADMIN_CHANGE:
	// if (MainModel.getInstance().checkMember(getActivity(), getVO())) {
	// SquareDefaultAjaxCallBack<ContentsVO> contentsCallBack = new
	// SquareDefaultAjaxCallBack<ContentsVO>(
	// getActivity(), ContentsVO.class) {
	// public void callback(String url, JSONObject json, AjaxStatus status) {
	// super.callback(url, json, status);
	// SpContentsVO item = new SpContentsVO(getVO().responseData.getJson());
	// m_data.add(item);
	// m_adapter.notifyDataSetChanged();
	// }
	// };
	// Map<String, String> contentsParams = new HashMap<String, String>();
	// contentsParams.put("squareId", vo.square_id);
	// contentsParams.put("contentsId", vo.square_contents_id);
	// new ApiLoaderEx<JSONObject>(new GetContents(getActivity()),
	// getActivity(), contentsCallBack, contentsParams)
	// .execute();
	// }
	// break;
	// }
	// }
	// };
	// callback.progress(PopupUtil.getProgressDialog(getActivity()));
	// MainModel.getInstance().loadGroupInfo(getActivity(), vo.square_id,
	// callback);
	// }
	// }
	// }

}