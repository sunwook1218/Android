package com.hs.mobile.gw.fragment.squareplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeSpContentsListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.SpNoticeAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpNoticeAdapter.IOnClickListener;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem;
import com.hs.mobile.gw.openapi.squareplus.SpGetNoticeContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpGetWorkfeedNoticeContentsList;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

public class SpNoticeFragment extends CommonFragment implements OnClickListener, IOnClickListener, IChangeSpContentsListener {
	public static final String ARG_KEY_CONTENTS_VIEW_TYPE = "contentsViewType";
	public static final String ARG_KEY_SQUARE_ID = "squareId";

	public enum ContentsViewType {
		WORKFEED, SQUARE;
	}

	private ContentsViewType currentContentsViewType;
	private String m_squareId;
	private int m_noticeTotalCount;
	private String m_strLastContentsId;
	private PullToRefreshListView m_lvSquareContents;
	protected List<SpContentsVO> m_data = new ArrayList<SpContentsVO>();
	private SpNoticeAdapter m_adapter;
	private boolean mListRefreshNeededWhenResume = false;
	private TextView m_tvTitle;
	private boolean loadingComplete;
	private Status m_squareStatus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 전달 받은 데이터 확인
		if (getArguments() != null) {
			currentContentsViewType = (ContentsViewType) getArguments().getSerializable(ARG_KEY_CONTENTS_VIEW_TYPE);

			if (currentContentsViewType == ContentsViewType.SQUARE) {
				m_squareId = getArguments().getString(ARG_KEY_SQUARE_ID);
				m_squareStatus = (Status)getArguments().getSerializable(MainModel.ARG_KEY_SP_SQUARE_STATUS);
				MainModel.getInstance().addContentsChangeListener(this);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_squareplus_notice, container, false);
//		v.findViewById(R.id.ID_BTN_BACK).setVisibility(MainModel.getInstance().isTablet() ? View.GONE : View.VISIBLE);
		v.findViewById(R.id.ID_BTN_BACK).setOnClickListener(this);
		m_tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		m_lvSquareContents = (PullToRefreshListView) v.findViewById(R.id.ID_LV_SP_CONTENTS);
		int[] colors = { 0xBBDDDDDD, 0xBBDDDDDD, 0xBBDDDDDD };
		m_lvSquareContents.getRefreshableView().setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		m_lvSquareContents.getRefreshableView().setDividerHeight(1);
		m_lvSquareContents.getRefreshableView().setScrollingCacheEnabled(false);
		m_lvSquareContents.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				// TODO Auto-generated method stub
				Debug.trace("m_data.size() = " , m_data.size());
				Debug.trace("m_noticeTotalCount = ", m_noticeTotalCount);
				
				if (m_data.size() < m_noticeTotalCount && loadingComplete == true) {
					m_strLastContentsId = m_data.get(m_data.size()-1).getContentsId();
					loadingComplete = false;
					loadData(false);
				}
			}
		});

		m_tvTitle.setText(R.string.label_squareplus_menu_notice);
		m_adapter = new SpNoticeAdapter(m_data, null);
		m_adapter.setOnClickListener(this);
		m_lvSquareContents.setAdapter(m_adapter);
		// 화면이 갱신되는동안 다시 그려지는 것을 막는다.
		m_lvSquareContents.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				return m_bDraw;
			}
		});

		m_lvSquareContents.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				refreshList();

				m_lvSquareContents.post(new Runnable() {
					@Override
					public void run() {
						m_adapter.notifyDataSetChanged();
						m_lvSquareContents.onRefreshComplete();
					}
				});
			}
		});

		refreshList();

		return v;
	}

	private void refreshList() {
		loadData(true);
	}

	private void loadData(boolean first) {
		if (first)
			m_data.clear();
		
		SpContentsListCallBack spContentsListCallBack = new SpContentsListCallBack(getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				
				if (dataList != null) {
					Debug.trace("dataList.size() = ",dataList.size());
					
					m_data.addAll(this.dataList);
					
					m_noticeTotalCount = this.totalCount;
					
					m_adapter.notifyDataSetChanged();
					
					loadingComplete = true;
					m_lvSquareContents.onRefreshComplete();
				}
			}
		};
		spContentsListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
		{ // API명
			HashMap<String, String> params = new HashMap<>();
			switch (currentContentsViewType) {
			case WORKFEED:
				new ApiLoaderEx<>(new SpGetWorkfeedNoticeContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
				break;
			case SQUARE:
				params.put("squareId", m_squareId);
				if (!first && !TextUtils.isEmpty(m_strLastContentsId))
					params.put("lastViewContentsId", m_strLastContentsId);
				
				new ApiLoaderEx<>(new SpGetNoticeContentsList(getActivity()), getActivity(), spContentsListCallBack, params).execute();
				break;
			}
		}
	}

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
//				MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
			}
		}
	}

	@Override
	public void onDestroy() {
		getMainModel().setCurrentSquareId(null);
		MainModel.getInstance().removeContentsChangeListener(this);
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

//	@Override		//ISpCompletionViewListener
//	public void onMentionClick(SpContentsMentionVO item) {
//		MainModel.getInstance().showSubActivity(this, SubActivityType.CUSTOM_WEBVIEW, new BundleUtils.Builder()
//				.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showUserDetailsPopup('" + item.getUserId() + "');").build());
//	}
//
//	@Override		//ISpCompletionViewListener
//	public void onTagClick(String replace) {
//		
//	}
//
//	@Override		//ISpCompletionViewListener
//	public void onUserClick(String itemId) {
//		MainModel.getInstance().showSubActivity(this, SubActivityType.CUSTOM_WEBVIEW, new BundleUtils.Builder()
//				.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showUserDetailsPopup('" + itemId + "');").build());
//	}
//
//	@Override
//	public void onBtnTagAddClick() {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void OnItemClick(SpContentsVO item) {
		// TODO Auto-generated method stub
		Bundle build = new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_squareId)
				.add(MainModel.ARG_KEY_SP_SQUARE_STATUS, m_squareStatus)
				.add(MainModel.ARG_KEY_SP_IS_SUB_CONTENTS, true)
				.add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId()).add(MainModel.ARG_KEY_SHOW_KEYBOARD, false).build();

		if (MainModel.getInstance().isTablet()) {
			MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsDetailFragment(), build,
					R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
		} else {
			MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_CONTENTS_DETAIL, build);
		}
	}
	

	@Override
	public void onChange(ChangeType t, SpContentsVO item) {
		// TODO Auto-generated method stub
		Debug.trace(t.toString());
		switch (t) {
		case ADD:
			mListRefreshNeededWhenResume = true;
			break;
		case DELETE:
			for (int i = m_data.size() - 1; i >= 0; i--) {
				SpContentsVO vo = (SpContentsVO) m_data.get(i);
				if (vo.getContentsId().equals(item.getContentsId())) {
					m_data.remove(i);
				}
			}
			m_adapter.notifyDataSetChanged();
			m_adapter.notifyDataSetInvalidated();
			break;
		case MODIFY:
			for (int i = m_data.size() - 1; i >= 0; i--) {
				SpContentsVO vo = (SpContentsVO) m_data.get(i);
				if (vo.getContentsId().equals(item.getContentsId())) {
					if (vo.getContentsType() == SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE) {
						vo.setBody(item.getBody());
						m_data.set(i, vo);
					} else {
						m_data.remove(i);
						m_data.add(i, item);
					}
				}
			}
			
			m_adapter.notifyDataSetChanged();
			m_adapter.notifyDataSetInvalidated();
			break;
		}
	}
}