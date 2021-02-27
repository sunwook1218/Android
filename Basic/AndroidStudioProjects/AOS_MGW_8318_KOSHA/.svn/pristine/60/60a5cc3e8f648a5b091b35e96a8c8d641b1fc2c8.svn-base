package com.hs.mobile.gw.fragment.squareplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeSpContentsListener;
import com.hs.mobile.gw.MainModel.ISpContentsDeleteListener;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.adapter.squareplus.SpOpinionListAdapter;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment.PopupStatus;
import com.hs.mobile.gw.fragment.squareplus.view.CompletionSpan;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpContentsDetailHeaderView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteCompletionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteMentionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWritePopupView.IWritePopupResultListener;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.squareplus.SpAddComment;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavorContents;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteAttach;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteContents;
import com.hs.mobile.gw.openapi.squareplus.SpDeleteAttachFile;
import com.hs.mobile.gw.openapi.squareplus.SpGetContents;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMentionList;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpAttachCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsMentionListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFavorCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFavorVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.BundleUtils.Builder;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class SpContentsDetailFragment extends CommonFragment implements OnClickListener, IActivityResultHandlerListener,
		IChangeSpContentsListener, ISquarePushListener, ISpCompletionViewListener, ISpClickListener,
		OnRefreshListener<ListView>, TextWatcher, IWritePopupResultListener {
	public interface ISpMRListener {
		void onModify(SpContentsVO item);

		void onRemove(SpContentsVO item);
	}

	public interface IFavoriteCallbak {
		void onResponse(SpAttachVO item);

		void onResponse(SpContentsVO item);
	}

	public static final String ARG_KEY_WORKGROUP_ITEM = "workGroupItem";
	public static final String ARG_KEY_CLICKED_BUTTON_TYPE = "clicked_button_type";
	private ImageView m_btnMore;
	private SpContentsVO m_squareData;
	private boolean mListRefreshNeededWhenResume = false;
	private String m_squareId;
	private Status m_squareStatus;
	private boolean isSubContents;		// SpContentsSubFragment에서 진입여부
	private TextView m_tvTitle;
	private String contentsId;
//	private PullToRefreshListView listView;
	private ListView listView;
	// 의견의 아답터
	private SpOpinionListAdapter spOpinionListAdapter;
	private List<SpContentsVO> commentList;
	IContentsListItem currentFirstItemContentId = null;
	private SpContentsDetailHeaderView headerView;
	private SpWriteCompletionView m_edOpinion;
	private LinearLayout m_llOpinion;
	private TextView m_tvOpinionSend;
	private SpWriteMentionView mentionLayout;
	private int fontColor = 0xFF7086D3;
	private boolean showKeyboard;
	private boolean isPush;
	private boolean isPopularTagView = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 전달 받은 데이터 확인
		if (getArguments() != null) {
			if (getArguments().getString(MainModel.ARG_KEY_SQUARE_ID) != null
					&& getArguments().getString(MainModel.ARG_KEY_SP_CONTENTS_ID) != null) {
				m_squareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
				contentsId = getArguments().getString(MainModel.ARG_KEY_SP_CONTENTS_ID);
				m_squareStatus = (Status)getArguments().getSerializable(MainModel.ARG_KEY_SP_SQUARE_STATUS);
				showKeyboard = getArguments().getBoolean(MainModel.ARG_KEY_SHOW_KEYBOARD);
				isSubContents = getArguments().getBoolean(MainModel.ARG_KEY_SP_IS_SUB_CONTENTS, false);
				isPopularTagView = getArguments().getBoolean(MainModel.ARG_KEY_SP_IS_POPULAR_TAG, false);
				isPush = getArguments().getBoolean(MainModel.ARG_KEY_SP_PUSH, false);
				MainModel.getInstance().setCurrentSquareId(m_squareId);
				MainModel.getInstance().addContentsChangeListener(this);
				MainModel.getInstance().addSquarePushListener(this);
			}
		}
	}
	
	View webviewContainer;
	LinearLayout contentsLayout;
	CustomWebViewFragment customWebViewFragment;
	Animation translateInLeftAnim;
	Animation translateOutRightAnim;
	Animation translateOutLeftAnim;
	Animation translateInRightAnim;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_sp_contents_detail, container, false);
		
		contentsLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_CONTENTS_DETAIL);
		
		// 멘션 사용자정보의 빠른 표시를 위해 미리 생성
		webviewContainer = v.findViewById(R.id.webviewContainer);
		customWebViewFragment = new CustomWebViewFragment();
		getFragmentManager().beginTransaction().add(R.id.webviewContainer , customWebViewFragment)
		.commitAllowingStateLoss();
		webviewContainer.setVisibility(View.GONE);
		translateInLeftAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_in_left);
		translateOutRightAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_out_right);
		
		translateOutLeftAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_out_left);
		translateInRightAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_in_right);
		
		v.findViewById(R.id.ID_BTN_BACK).setOnClickListener(this);
		// v.findViewById(R.id.ID_BTN_BACK).setVisibility(
		// MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_MENU).setOnClickListener(this);
		// v.findViewById(R.id.ID_BTN_MENU).setVisibility(
		// MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_MORE).setOnClickListener(this);
		mentionLayout = (SpWriteMentionView) v.findViewById(R.id.ID_WRITE_MENTION_LAYOUT);
		mentionLayout.setVisibility(View.GONE);
		mentionLayout.setResultListener(this);
		m_btnMore = (ImageView) v.findViewById(R.id.ID_BTN_MORE);
		m_tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		m_edOpinion = (SpWriteCompletionView) v.findViewById(R.id.ID_ED_OPINION);
		m_llOpinion = (LinearLayout) v.findViewById(R.id.ID_LAY_L_OPINION);
		m_tvOpinionSend = (TextView) v.findViewById(R.id.ID_BTN_OPINION_SEND);
		m_tvOpinionSend.setOnClickListener(this);
		listView = (ListView) v.findViewById(R.id.ID_LV_SP_CONTENTS_DETAIL);
		headerView = new SpContentsDetailHeaderView(getActivity(), m_squareStatus);
		headerView.setVisibility(View.INVISIBLE);
		headerView.setMRListener(new ISpMRListener() {
			@Override
			public void onRemove(SpContentsVO item) {
				MainModel.getInstance().deleteContents(getActivity(), item, new ISpContentsDeleteListener() {
					@Override
					public void onDeleteComplete(SpContentsVO item) {
						MainModel.getInstance().notifyChanged(item,
								com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.DELETE);
						
						onBack();
					}
				});
			}

			@Override
			public void onModify(SpContentsVO item) {
				Builder builder = new BundleUtils.Builder();
				builder.add(MainModel.ARG_KEY_SQUARE_ID, item.getSquareId());
				builder.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId());
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_WRITE,
						builder.build());
			}
		});
		spOpinionListAdapter = new SpOpinionListAdapter();
		spOpinionListAdapter.setListener(new ISpMRListener() {

			@Override
			public void onRemove(SpContentsVO item) {
				MainModel.getInstance().deleteContents(getActivity(), item, new ISpContentsDeleteListener() {
					@Override
					public void onDeleteComplete(SpContentsVO item) {
						commentList.remove(item);
						m_squareData.setCommentCount(commentList.size());
						headerView.setData(m_squareData, SpContentsDetailFragment.this, SpContentsDetailFragment.this);
						spOpinionListAdapter.notifyDataSetChanged();
						MainModel.getInstance().notifyChanged(m_squareData,
								com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
					}
				});
			}

			@Override
			public void onModify(SpContentsVO item) {
				Builder builder = new BundleUtils.Builder();
				builder.add(MainModel.ARG_KEY_SQUARE_ID, item.getSquareId());
				builder.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId());
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_OPNION_WRITE,
						builder.build());
			}
		});
//		listView.getRefreshableView().addHeaderView(headerView);
//		listView.getRefreshableView().setDivider(null);
//		listView.setOnRefreshListener(this);
		listView.addHeaderView(headerView);
		listView.setDivider(null);
		listView.setAdapter(spOpinionListAdapter);
		commentList = new ArrayList<SpContentsVO>();
		spOpinionListAdapter.setData(commentList, this);
		spOpinionListAdapter.setClickListener(this);
		m_edOpinion.addTextChangedListener(this);
		SpContentsMentionListCallBack spGetSquareMentionListCallBack = new SpContentsMentionListCallBack(getActivity(),
				SpContentsMentionVO.class) {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				mentionLayout.setData(this.dataList);
			}
		};

		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", m_squareId);
		new ApiLoaderEx<>(new SpGetSquareMentionList(getActivity()), getActivity(), spGetSquareMentionListCallBack,
				params).execute();

		loadData();
		if (showKeyboard) {
			m_edOpinion.post(new Runnable() {

				@Override
				public void run() {
					m_edOpinion.requestFocus();
				}
			});
		}
		return v;
	}

	private void loadData() {
		PopupUtil.showLoading(getActivity());
		SpContentsCallBack spSquareCallBack = new SpContentsCallBack(getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(getActivity());
				
				if (status.getCode() == 200) {
					if (item != null) {
						m_squareData = item;
						m_tvTitle.setText(m_squareData.getSquareTitle() + " - " + getString(R.string.label_squareplus_detail));
						headerView.setData(m_squareData, SpContentsDetailFragment.this, SpContentsDetailFragment.this);
						headerView.setVisibility(View.VISIBLE);
						commentList.clear();
						if (m_squareData.getCommentCount() != 0) {
							commentList.addAll(m_squareData.getCommentList());
						}
					}
					spOpinionListAdapter.notifyDataSetChanged();
//					listView.onRefreshComplete();
					if (m_squareData != null && (!m_squareData.mySquareMember || m_squareData.getMemberRightsEnum() == MemberRights.OBSERVER_USER || m_squareStatus == Status.END))
						m_llOpinion.setVisibility(View.GONE);
				}
			}
		};
		
		{
			HashMap<String, String> params = new HashMap<>();
			params.put("squareId", m_squareId);
			params.put("contentsId", contentsId);
			new ApiLoaderEx<>(new SpGetContents(getActivity()), getActivity(), spSquareCallBack, params).execute();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			onBack();
			break;
		case R.id.ID_BTN_MENU:
			break;
		case R.id.ID_BTN_OPINION_SEND:
			if (m_edOpinion.getText().length() == 0) {
				return;
			}
			TextViewUtils.hideKeyBoard(getActivity(), m_tvOpinionSend);
			PopupUtil.showLoading(getActivity());
			SpContentsCallBack spContentsCallBack = new SpContentsCallBack(getActivity(), SpContentsVO.class) {
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);
					loadData();
					m_squareData.setCommentCount(m_squareData.getCommentCount() + 1);
					MainModel.getInstance().notifyChanged(m_squareData,
							com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
				}
			};

			{ // API명
				HashMap<String, String> params = new HashMap<>();
				params.put("squareId", m_squareId); // 스퀘어id
				params.put("originalParentContentsId", contentsId); // 부모id
				Editable editable = m_edOpinion.getText();

				String sb = new String(editable.toString());
				CompletionSpan[] tokens = editable.getSpans(0, editable.length(), CompletionSpan.class);
				for (CompletionSpan token : tokens) {
					if (token.getToken() instanceof SpContentsMentionVO) {
						sb = sb.replace("@" + ((SpContentsMentionVO) token.getToken()).getItemName(),
								"@{u" + ((SpContentsMentionVO) token.getToken()).getItemId() + "}");
					}
				}

				params.put("comment", sb.toString()); // 의견내용
				m_edOpinion.getText().clear();
				new ApiLoaderEx<>(new SpAddComment(getActivity()), getActivity(), spContentsCallBack, params).execute();
			}
			break;
		case R.id.ID_BTN_MORE:

			PopupMenu popupMenu = new PopupMenu(getActivity(), m_btnMore);
			popupMenu.getMenuInflater().inflate(R.menu.square_contents_list, popupMenu.getMenu());
			popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()) {
					// case R.id.ID_MENU_SEARCH:
					// MainModel.getInstance().showSubActivity(getActivity(),
					// SubActivityType.SQUARE_SEARCH,
					// new
					// BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID,
					// m_squareId)
					// .add(MainModel.ARG_KEY_GROUP_STATUS,
					// m_squareData.getStatus()).build());
					// break;
					// case R.id.ID_MENU_FAVORITE:
					// MainModel.getInstance().showSubActivity(getActivity(),
					// SubActivityType.SQUARE_FAVORITE_LIST,
					// new
					// BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID,
					// m_squareId)
					// .add(MainModel.ARG_KEY_GROUP_STATUS,
					// m_squareData.getStatus()).build());
					// break;
					// case R.id.ID_MENU_COMMAND:
					// MainModel.getInstance().showSubActivity(getActivity(),
					// SubActivityType.SQUARE_ORDER,
					// new
					// BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID,
					// m_squareId)
					// .add(MainModel.ARG_KEY_GROUP_STATUS,
					// m_squareData.getStatus()).build());
					// break;
					// case R.id.ID_MENU_FILE:
					// MainModel.getInstance().showSubActivity(getActivity(),
					// SubActivityType.SQUARE_FILE,
					// new
					// BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID,
					// m_squareId)
					// .add(MainModel.ARG_KEY_GROUP_STATUS,
					// m_squareData.getStatus()).build());
					// break;
					// case R.id.ID_MENU_SETTING:
					// // Debug.trace("R.id.ID_MENU_SETTING");
					// MainModel.getInstance().showSubActivity(getActivity(),
					// SubActivityType.SP_SETTING,
					// new
					// BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID,
					// m_squareId).build());
					// break;
					}
					return true;
				}
			});
			popupMenu.show();
			break;
		// case R.id.ID_BTN_UPLOAD:
		// if (m_squareData.getStatusEnum() == Status.IN_PROGRESS) {
		// MainModel.getInstance().showUploadDialog(this, m_squareId, false);
		// }
		// break;
		case R.id.ID_BTN_SEND:
			Debug.trace("전송");
			// if (!TextUtils.isEmpty(m_edMessage.getText().toString().trim())
			// && m_squareData.getStatusEnum() == Status.IN_PROGRESS) {
			// new ApiLoader(new AddMessage(), getActivity(), new
			// AddMessageResult() {
			// @Override
			// public void onResponse(String strRet) {
			// super.onResponse(strRet);
			// if (this.responseHead.resultCode == SUCCESS) {
			// m_edMessage.getText().clear();
			// SpContentsFragment.this.m_data.add(getData());
			// m_adapter.notifyDataSetChanged();
			// m_lvSquareContents.getRefreshableView().setSelection(m_adapter.getCount()
			// - 1);
			// } else {
			// Debug.trace("전송 실패 팝업");
			// }
			// }
			// }, m_squareId,
			// m_edMessage.getText().toString().trim()).execute();
			// }
			break;
		case R.id.ID_BTN_WORKFEED:

			break;
		case R.id.ID_BTN_FAVORITE:
			break;
		case R.id.ID_BTN_WRITE:
			break;
		case R.id.ID_BTN_MENTION:
			break;
		case R.id.ID_BTN_FILE:
			break;
		}
	}

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
				MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
			}
		}
	}

	// @Override
	// public void onLoadCompleteMedia(ResultType t, String strRet) {
	// switch (t) {
	// case FILE_PATH:
	// if (MainModel.getInstance().checkFileSize(strRet) > 0) {
	// PopupUtil.showDialog(getActivity(), R.string.square__limit_message);
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
		Debug.trace("");
		getFragmentManager().beginTransaction().remove(customWebViewFragment)
				.commitAllowingStateLoss();

		MainModel.getInstance().removeContentsChangeListener(this);
		MainModel.getInstance().removeSquarePushListener(this);
		getMainModel().setCurrentSquareId(null);
		super.onDestroy();
	}

	@Override
	public void onResume() {
		ViewModel.Log("SpConFrg blue 해제", "bluetooth");
		super.onResume();

		if (mListRefreshNeededWhenResume) {
			mListRefreshNeededWhenResume = false;
			loadData();
		}
	}

	@Override
	public void onPushReceive(SquarePushVO vo) {

	}

	@Override
	public void onLoadCompleteMedia(ResultType filePath, String strRet) {

	}

	@Override
	public void onMentionClick(SpContentsMentionVO item) {

	}

	@Override
	public void onTagClick(String replace) {
		Debug.trace(replace);
		Bundle bundle = new BundleUtils.Builder()
				.add(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpContentsSubFragment.ContentsViewType.POPULAR_TAG)
				.add(SpContentsSubFragment.ARG_KEY_SP_TAG_KEYWORD, replace).add(MainModel.ARG_KEY_SQUARE_ID, m_squareId).build();

		if (MainModel.getInstance().isTablet()) {
			if (getFragmentManager().getBackStackEntryCount() > 0) {

				if (isPopularTagView) {
					getFragmentManager().popBackStack(getString(SpContentsSubFragment.ContentsViewType.POPULAR_TAG.getTitleRes()),
							FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}

				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsSubFragment(), bundle,
						R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, getString(SpContentsSubFragment.ContentsViewType.POPULAR_TAG.getTitleRes()));
			}
		} else {
			if (isPopularTagView) {
				Intent intent = new Intent();
				intent.putExtra(SubActivity.INTENT_KEY_VALUE, bundle);
				getActivity().setResult(Activity.RESULT_OK, intent);
				getActivity().finish();
			} else {
				getMainModel().showSubActivity(getActivity(), SubActivityType.SP_POPULAR_TAG_RESULT, bundle);
			}
		}
	}

	@Override
	public void onUserClick(String itemId) {
		
		customWebViewFragment.loadUrl("javascript:showUserDetailsPopup('" + itemId + "');");
		webviewContainer.setVisibility(View.VISIBLE);
		webviewContainer.startAnimation(translateInLeftAnim);
		contentsLayout.startAnimation(translateOutLeftAnim);
		contentsLayout.setVisibility(View.GONE);
//		BundleUtils.Builder builder = new BundleUtils.Builder()
//		.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showUserDetailsPopup('" + itemId + "');");
//		MainModel.getInstance().showFragmentToTarget(getActivity(), MainModel.getInstance().getCustomWebViewFragment(), builder.build(),
//				R.id.ID_LAY_L_SUB_CONTENT, true, null);
	}

	@Override
	public void onDetailView(SpContentsVO item, boolean showKeyboard) {
		// Nothing to do..
	}

	@Override
	public void onFileView(SpAttachVO item) {
		if (HMGWServiceHelper.doc_transform_server_use) {
			MainModel.getInstance().getOpenApiService().getDocHandlerResult(getActivity(), item);
		} else {
			// SEOJAEHWA : 권한체크 추가
			downloadFile(item.getContentsId(), item.getAttachId(), getTargetFilePath(item.getFileName(), item.getFileExt()));
		}
	}

	@Override
	public void onFavoriteClick(SpAttachVO vo, final IFavoriteCallbak favoriteCallback) {
		SpAttachCallBack spContentsCallBack = new SpAttachCallBack(getActivity(), SpAttachVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				favoriteCallback.onResponse(item);
				MainModel.getInstance().notifyChanged(m_squareData,
						com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
			}
		};

		{ // 컨텐츠에 즐겨찾기 하기
			HashMap<String, String> params = new HashMap<>();
			params.put("squareId", vo.getSquareId()); // 스퀘어id
			params.put("attachId", vo.getAttachId()); // 첨부id
			params.put("favoriteFlag",
					"0".equals(vo.getFavoriteFlag()) ? SpSquareConst.TRUE_CH : SpSquareConst.FALSE_CH); // 0:false,
																										// 1:true
			new ApiLoaderEx<>(new SpAddFavoriteAttach(getActivity()), getActivity(), spContentsCallBack, params)
					.execute();
		}
	}

	@Override
	public void onFavoriteClick(SpContentsVO vo, final IFavoriteCallbak favoriteCallback) {
		SpContentsCallBack spContentsCallBack = new SpContentsCallBack(getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				if (this.item != null) {
					favoriteCallback.onResponse(this.item);
					m_squareData.setFavoriteFlag(item.getFavoriteFlag());
					MainModel.getInstance().notifyChanged(m_squareData,
							com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
				}
			}
		};

		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", vo.getSquareId());
		params.put("contentsId", vo.getContentsId());
		params.put("contentsType", vo.getFavoriteType()); // FavoriteType에
															// 맞춰서 삽입한다.
		params.put("favoriteFlag", "1".equals(vo.getFavoriteFlag()) ? SpSquareConst.FALSE_CH : SpSquareConst.TRUE_CH);
		new ApiLoaderEx<>(new SpAddFavoriteContents(getActivity()), getActivity(), spContentsCallBack, params)
				.execute();
	}

	@Override
	public void onDeleteClick(final SpAttachVO attachVO) {
		Debug.trace("R.id.ID_ATTACH_DELETE");
		SpAttachCallBack spAttachCallBack = new SpAttachCallBack(getActivity(), SpAttachVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				List<SpAttachVO> list = new ArrayList<>(m_squareData.getAttachList());
				Iterator<SpAttachVO> iterator = list.iterator();
				while(iterator.hasNext()){
					SpAttachVO next = iterator.next();
					if(TextUtils.equals(next.getAttachId(), attachVO.getAttachId())){
						iterator.remove();
					}
				}
				m_squareData.setAttachList(list);
				MainModel.getInstance().notifyChanged(m_squareData,
						com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
			}
		};
		{
			HashMap<String, String> params = new HashMap<>();
			params.put("attachId", attachVO.getAttachId()); // 첨부id
			new ApiLoaderEx<>(new SpDeleteAttachFile(getActivity()), getActivity(), spAttachCallBack, params).execute();
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		Debug.trace("");
//		listView.setRefreshing();
		loadData();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() > 0 && s.toString().charAt(s.length() - 1) == '@') {
			mentionLayout.setVisibility(View.VISIBLE);
		} else {
			mentionLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onWritePopupResult(PopupStatus popup, DefaultVO vo) {
		SpContentsMentionVO spContentsMentionVO = (SpContentsMentionVO) vo;
		final String itemName = "@" + spContentsMentionVO.getItemName();
		SpannableString ssb = new SpannableString(itemName);

		ssb.setSpan(new CompletionSpan(fontColor, spContentsMentionVO), 0, itemName.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		if (m_edOpinion.getSelectionStart() > 0) {
			m_edOpinion.getText().delete(m_edOpinion.getSelectionStart() - 1, m_edOpinion.getSelectionStart());
		}
		m_edOpinion.getText().insert(m_edOpinion.getSelectionStart(), ssb);
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				m_edOpinion.requestFocus();
				m_edOpinion.setSelection(m_edOpinion.getSelectionStart());
			}
		});
	}

	@Override
	public void onBtnTagAddClick() {

	}

	@Override
	public void onChange(ChangeType t, SpContentsVO item) {
		switch (t) {
		case ADD:
		case MODIFY:
			mListRefreshNeededWhenResume = true;
//			loadData();
		case DELETE:
			break;
		}
	}

	@Override
	public void onMoveToUrl(String url) {
		// TODO Auto-generated method stub
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		
		Intent call_browser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		getActivity().startActivity(call_browser);
	}

	@Override
	public void onOrgImgDown(SpAttachVO item) {
		// TODO Auto-generated method stub
		if (MainModel.getInstance().getOpenApiService()!=null && item != null) MainModel.getInstance().getOpenApiService().downloadOrgImg(getActivity(), item);
	}

	@Override
	public void onFavorClick(final SpContentsVO vo, int type) {
		// TODO Auto-generated method stub
		SpFavorCallBack spFavorCallBack = new SpFavorCallBack(getActivity(), SpFavorVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				
				if (this.item != null) {
					vo.setFavorCount(item.getFavorCount());
					if (item.getTransactionMode().equals("2"))
						vo.setFavorType(String.valueOf(0));
					else
						vo.setFavorType(item.getFavorType());
					headerView.setFavorCountView((vo.getCommentCount() == 0) ? true : false, vo.getFavorCount());
					headerView.setFavorSelect(Integer.valueOf(vo.getFavorType()));
					
					MainModel.getInstance().notifyChanged(0, vo,
							com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
				}
			}
		};

		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", vo.getSquareId());
		params.put("contentsId", vo.getContentsId());
		params.put("favorType", String.valueOf(type));
		new ApiLoaderEx<>(new SpAddFavorContents(getActivity()), getActivity(), spFavorCallBack, params).execute();
	}
	
	@Override
	public void onNoticeView(SpContentsVO item) {
		// TODO Auto-generated method stub
		
	}

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

	private void onBack() {
		if (MainModel.getInstance().isTablet()) {
			if (isPush) {
				getActivity().finish();
			}
			else if (getFragmentManager().getBackStackEntryCount() > 0) {
				getFragmentManager().popBackStack();
			}
		} else {
			getActivity().finish();
		}
	}

	public void showContents() {
		contentsLayout.setVisibility(View.VISIBLE);
		contentsLayout.startAnimation(translateOutRightAnim);
		webviewContainer.startAnimation(translateInRightAnim);
		webviewContainer.setVisibility(View.GONE);
	}
	
	@Override
	public boolean onPreBackKeyPressed() {
		boolean ret = false;
		if (contentsLayout.getVisibility() == View.VISIBLE)
			ret = true;
		else {
			showContents();
		}
		return ret;
	}
}