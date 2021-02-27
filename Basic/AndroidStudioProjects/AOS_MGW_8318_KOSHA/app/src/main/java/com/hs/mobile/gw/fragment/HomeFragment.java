package com.hs.mobile.gw.fragment;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MenuListHelper;
import com.hs.mobile.gw.MenuListHelper.MenuIDsMap;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.main.Tabs;
import com.hs.mobile.gw.openapi.BoardNoticeBbsFolderList;
import com.hs.mobile.gw.openapi.BoardNoticeList;
import com.hs.mobile.gw.openapi.BoardNoticeListBbsFolderCallBack;
import com.hs.mobile.gw.openapi.BoardNoticeListCallBack;
import com.hs.mobile.gw.openapi.Counts;
import com.hs.mobile.gw.openapi.CountsCallBack;
import com.hs.mobile.gw.openapi.vo.BoardNoticeListVO.Item;
import com.hs.mobile.gw.openapi.vo.CountsVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.DateUtils;
import com.hs.mobile.gw.util.GridViewEx;
import com.hs.mobile.gw.util.PopupUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 *  factory method to create an instance of this
 * fragment.
 * 
 */
public class HomeFragment extends CommonFragment implements OnItemClickListener, OnClickListener {

	private final float RATIO_LIST_ITEM_HEIGHT = 80f / 1280f;
	private final float RATIO_LIST_ITEM_SHADOW_HEIGHT = 5f / 1280f;
	private final float RATIO_LIST_ITEM_WIDTH = 104f / 1280f;
	private final float RATIO_LIST_ITEM_SHADOW_WIDTH = 5f / 1280f;
	public String m_boardMenuID = "";
	public String m_boardMenuName = "";
	public String m_boardID = "";
	private OnFragmentInteractionListener mListener;
	private GridViewEx m_gridMenu;
	private LinearLayout m_btnMore;
	private ListView m_lvNotice;
	private TextView m_lvNoticeEmpty;
	private LinearLayout m_lnbAll; // 20.06.23 tkofs
	private LinearLayout m_boardBtns;
	private GridMenuAdapter m_gridMenuAdapter;
	private NoticeListAdapter m_noticeListAdapter;
	private Handler mHandler = new Handler();
	private CountsVO m_countVo;
	private ArrayList<Item> mBoardData = new ArrayList<Item>();
	private Runnable mCounterRunnable;
	private CountsCallBack m_countResultListener = new CountsCallBack() {
		public void callback(String url, JSONObject json, AjaxStatus status) {
			super.callback(url, json, status);
			if (getVO() != null) {
				m_countVo = getVO();
				if (m_gridMenuAdapter != null) {
					m_gridMenuAdapter.notifyDataSetChanged();
				}
			}
		};
	};
	private ArrayList<HomeMenuButtionItem> m_buttonItems;
	private Tabs tabcontroll;
	private Thread runCount;
	private ProgressDialog initProgressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, container, false);
		initView(v);
		return v;
	}

	/**
	 * 메인 게시판 버튼 셋팅
	 * @Autor tkofs
	 * @Date 20.06.23
	 */
	public void setMainBbsMenu() {
		// 버튼, 이벤트 생성
		JSONObject jBtnObj;

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		ArrayList<Button> trigerList = new ArrayList();
		if (HMGWServiceHelper.mgw_home_bbs_menu != null) {
			int boardCnt = HMGWServiceHelper.mgw_home_bbs_menu.length();
			for (int i = 0; i < boardCnt; i++) {
				try {
					jBtnObj = HMGWServiceHelper.mgw_home_bbs_menu.getJSONObject(i);

					// Button 생성
					Button m_boardBtn = new Button(getActivity());
					m_boardBtn.setId(i + 1);
					m_boardBtn.setLayoutParams(params);
					m_boardBtn.setText(jBtnObj.getString("menu-name"));

					// 버튼 속성 추가
					m_boardBtn.setBackgroundColor(0xffffff);
					m_boardBtn.setTextSize(18);
					m_boardBtn.setTypeface(m_boardBtn.getTypeface(), Typeface.BOLD);

					final String menuID = jBtnObj.optString("menu-id");
					final String menuName = jBtnObj.optString("menu-name");
					final String boardID = jBtnObj.optString("function");

					final int syncID = i;
					// Button Click Event 생성
					m_boardBtn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							//tabcontroll.triggerSync(syncID);

							// TODO Auto-generated method stub
							Map<String, String> paramMap = new HashMap<String, String>();
							paramMap.put("pno", "1");
							String url = "";
							m_boardMenuID = menuID;
							m_boardMenuName = menuName;
							m_boardID = boardID;
							switch (m_boardMenuID) {
								case "board_notice":    // 공지사항
									url = "/rest/openapi/board/noticelist";
									break;
								case "board_recent":    // 최근게시물
									url = "/rest/openapi/board/recentlist";
									break;
								case "board_bookmark":    // 즐겨찾기
									url = "/rest/openapi/board/favfolder";
									break;
								default:
									paramMap.put("boardid", m_boardID);
									url = "/rest/openapi/board/list";
							}

							BoardNoticeListCallBack callback = new BoardNoticeListCallBack() {
								@Override
								public void callback(String url, JSONObject json, AjaxStatus status) {
									super.callback(url, json, status);

									if (!mBoardData.isEmpty()) {
										mBoardData.clear();
									}
									if (getVO().channel.item != null && getVO().channel.item.size() > 0) {
										mBoardData.addAll(getVO().channel.item);
										m_lvNotice.setVisibility(View.VISIBLE);
										m_lvNoticeEmpty.setVisibility(View.GONE);
									} else {
										m_lvNoticeEmpty.setVisibility(View.VISIBLE);
										m_lvNotice.setVisibility(View.GONE);
									}
									m_noticeListAdapter.notifyDataSetChanged();
								}
							};
							BoardNoticeListBbsFolderCallBack callbackBF = new BoardNoticeListBbsFolderCallBack() {
								@Override
								public void callback(String url, JSONArray json, AjaxStatus status) {
									super.callback(url, json, status);

									if (!mBoardData.isEmpty()) {
										mBoardData.clear();
									}
									if (getVO().items != null && getVO().items.size() > 0) {
										mBoardData.addAll(getVO().items);
										m_lvNotice.setVisibility(View.VISIBLE);
										m_lvNoticeEmpty.setVisibility(View.GONE);
									} else {
										m_lvNoticeEmpty.setVisibility(View.VISIBLE);
										m_lvNotice.setVisibility(View.GONE);
									}
									m_noticeListAdapter.notifyDataSetChanged();
								}
							};

							if("board_bookmark".equals(m_boardMenuID)){
								callbackBF.progress(initProgressDialog);
								new ApiLoaderEx<JSONArray>(new BoardNoticeBbsFolderList(getActivity()), getActivity(), callbackBF, paramMap).execute();
							}else{
								callback.progress(initProgressDialog);
								new ApiLoaderEx<JSONObject>(new BoardNoticeList(getActivity(), url), getActivity(), callback, paramMap).execute();
							}
						}
					});
					m_boardBtns.addView(m_boardBtn);

					// Tabs.java에 버튼을 넘겨 트리거 이벤트로 클릭
					trigerList.add(m_boardBtn);

                   /* if(i==0){
                        m_boardBtn.performClick();
                    }*/
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.e("MGW", "= ERROR !!"+e.toString());
				}
			}
		}

		if (trigerList.size() > 0) {
			tabcontroll.setTrigger(trigerList);
			trigerList.get(0).performClick();   // 최초 첫번째 탭 리스트 조회
		}
	}

	private MainModel m_model;
	private void initView(View v) {
		// tkofs
		m_model = MainModel.getInstance();
		m_model.setHomeFragment(this);
		
		initProgressDialog = new ProgressDialog(getActivity());
		initProgressDialog.setMessage(getResources().getString(R.string.message_notify_loading_data));
		initProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		initProgressDialog.setCancelable(false);
		initProgressDialog.show();

		m_boardBtns = (LinearLayout) v.findViewById(R.id.ID_BTN_BOARD);
		// TabLayout 반영
		tabcontroll = new Tabs(v);
		tabcontroll.setFragment(getActivity().getSupportFragmentManager());
		tabcontroll.setAppendData(HMGWServiceHelper.mgw_home_bbs_menu);

		runCount = new Thread(
				mCounterRunnable = new Runnable() {
					@Override
					public void run() {
						if(getActivity().getApplicationContext()!=null){
							new ApiLoaderEx<JSONObject>(new Counts(getActivity().getApplicationContext()),
									getActivity().getApplicationContext(), m_countResultListener, null).execute();
							mHandler.postDelayed(mCounterRunnable, 60000);
						}
					}
				}
		);

		runCount.setDaemon(true);
		runCount.start();

		m_buttonItems = new ArrayList<HomeMenuButtionItem>();
		m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_approval, getSubActivity().getResources().getString(R.string.label_menu_sign), "approval_waiting"));	// 결재
		m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_docubox, "문서함", "approval_archive"));	// 문서함
		//m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_workmail, "업무메일", "https://office.hiworks.com/hsuco.or.kr", "EX"));	// 업무메일(외부)
		// 외부메일을 내부 사내메일(받은메일함)로 변경 요청 20200924 tkofs
		m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_workmail, "사내메일", "mail_received"));
		m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_orgchart, getSubActivity().getResources().getString(R.string.label_menu_orgchart), "organization_chart"));	// 조직도
		m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_rules, "업무규정", "00000094o", "BC"));	// 업무규정
		m_buttonItems.add(new HomeMenuButtionItem(R.drawable.home_gd_budget, "예산서", "00000094k", "BC"));	// 예산서

		m_gridMenu = (GridViewEx) v.findViewById(R.id.ID_GRID_MENU);
		m_btnMore = v.findViewById(R.id.ID_BTN_MORE);
		m_lvNotice = v.findViewById(R.id.ID_LV_NOTICE);
		m_lvNoticeEmpty = v.findViewById(R.id.ID_LV_NOTICE_EMPTY);

		m_lnbAll = v.findViewById(R.id.BTN_LNB_OPEN);
		m_lnbAll.setOnClickListener(this);

		if (MainModel.getInstance().isTablet() || getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
			m_gridMenu.setNumColumns(6);
		} else {
			m_gridMenu.setNumColumns(3);
		}

		// 그리드 메뉴 관련 설정
		m_gridMenuAdapter = new GridMenuAdapter(m_buttonItems);
		m_gridMenu.setAdapter(m_gridMenuAdapter);
		m_gridMenu.setOnItemClickListener(this);

		// 더보기 버튼 동작 설정
		m_btnMore.setOnClickListener(this);

		// 공지사항 리스트 관련 설정
		m_noticeListAdapter = new NoticeListAdapter(mBoardData);
		m_lvNotice.setAdapter(m_noticeListAdapter);
		m_lvNotice.setOnItemClickListener(this);
		m_lvNotice.setDividerHeight(0);

		//mHandler.removeCallbacks(mCounterRunnable);
		//mHandler.post(mCounterRunnable);
		setMainBbsMenu();
	}

	@Override
	public void onStop() {
		super.onStop();
		try {
			mHandler.removeCallbacks(mCounterRunnable);
		} catch (Exception e) {
		}
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		// if (mListener != null) {
		// mListener.onFragmentInteraction(uri);
		// }
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
			case R.id.ID_GRID_MENU:
				ViewModel.Log(m_gridMenuAdapter.getItem(position).m_strMenuID, "tkofs_grid_click");
				if (m_gridMenuAdapter.getItem(position).m_strMenuType.equals("ID")) {
					getActivity().finish();
					MainFragment.menuListHelper.showMenuByID(m_gridMenuAdapter.getItem(position).m_strMenuID);
				}else if(m_gridMenuAdapter.getItem(position).m_strMenuType.equals("EX")){	// 업무메일(외부)
					String function = m_gridMenuAdapter.getItem(position).m_strMenuID;
					Intent httpIntent = new Intent(Intent.ACTION_VIEW);
					httpIntent.setData(Uri.parse(function));
					getActivity().startActivity(httpIntent);
				}else if(m_gridMenuAdapter.getItem(position).m_strMenuType.equals("BC")){    // 업무규정, 예산서 : 커스텀 게시판인경우 tkofs
					JSONObject menuData = new JSONObject();
					try {
						menuData.put(MenuListHelper.MENU_NAME, m_gridMenuAdapter.getItem(position).m_strButtonName);
						menuData.put("function", m_gridMenuAdapter.getItem(position).m_strMenuID);
						MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.valueOf("board_custom"), menuData);
						getActivity().finish();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						ViewModel.Log("home grd click exception");
					}
				}else{
					PopupUtil.showToastMessage(getActivity(), "서비스가 준비중입니다.");
					return;
				}
				break;
			case R.id.ID_LV_NOTICE:    // 게시판 리스트 상세 뷰
				if("board_bookmark".equals(m_boardMenuID)){
					MainFragment.menuListHelper.showMenuByBbsID(m_boardMenuID, m_noticeListAdapter.getItem(position).brdid.trim(), m_noticeListAdapter.getItem(position).brdType.trim(), m_noticeListAdapter.getItem(position).title.trim());
				}else{
					MainFragment.menuListHelper.showMenuByBbsID(m_boardMenuID, m_boardMenuName, m_noticeListAdapter.getItem(position).link.trim(), String.valueOf(m_noticeListAdapter.getItem(position).commentcount), m_boardID);
				}
				getActivity().finish();
				break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ID_BTN_MORE:    // 더보기 버튼 클릭 시
				if (m_boardMenuID.equals("board_custom")) {    // 커스텀메뉴
					JSONObject menuData = new JSONObject();
					try {

						menuData.put(MenuListHelper.MENU_NAME, m_boardMenuName);
						menuData.put("function", m_boardID);

						MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.valueOf(m_boardMenuID), menuData);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						Log.e("MGW", "= ERROR !!");
					}
				} else {
					MainFragment.menuListHelper.showMenuByID(m_boardMenuID);
				}
				getActivity().finish();
				break;
			case R.id.BTN_LNB_OPEN:
				// 팝업
				ViewModel.Log(HMGWServiceHelper.mgw_menu, "tkofs_menu_grid_home");
				getSubActivity().mOnPopupClick(v);
				break;
		}
	}

	public class HomeMenuButtionItem {
		public int m_nIconId;
		public int m_nCount = -1;
		public String m_strButtonName;
		public String m_strMenuID;
		public String m_strMenuType;

		public HomeMenuButtionItem(int nIconId, String strButtonName, String strMenuID) {
			m_nIconId = nIconId;
			m_strButtonName = strButtonName;
			m_strMenuID = strMenuID;
			m_strMenuType = "ID";	// menuType ID 메뉴아이디, EX 외부메뉴(외부브라우져), BC(board_custom)
		}

		public HomeMenuButtionItem(int nIconId, String strButtonName, String strMenuID, String strMenuType) {
			m_nIconId = nIconId;
			m_strButtonName = strButtonName;
			m_strMenuID = strMenuID;
			m_strMenuType = strMenuType;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		try {
			mHandler.removeCallbacks(mCounterRunnable);
		} catch (Exception e) {
		}
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// 기기가 가로로 회전할때 할 작업
			if (m_gridMenuAdapter != null) {
				m_gridMenuAdapter.notifyDataSetChanged();
			}
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			// 기기가 세로로 회전할때 할 작업
			if (m_gridMenuAdapter != null) {
				m_gridMenuAdapter.notifyDataSetChanged();
			}
		}
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		populateViewForOrientation(inflater, (ViewGroup) getView());
	}

	private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
		viewGroup.removeAllViewsInLayout();
		View subview = inflater.inflate(R.layout.fragment_home, viewGroup);
		initView(subview);
	}

	@Override
	public void onResume() {
		ViewModel.Log("HomeFrg blue 해제", "bluetooth");
		super.onResume();
		MainModel.getInstance().setCurrentSubActivity(SubActivityType.HOME_HOME);
	}

	@Override
	public boolean onPreBackKeyPressed() {
		// 홈 화면에서는 Back키가 안눌리도록 처리
		return false;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

	public class NoticeListAdapter extends BaseAdapter {

		private ArrayList<Item> mData;
		private LayoutParams m_listItemPortraitParam;
		private LayoutParams m_listItemLandscapeParam;
		private LinearLayout.LayoutParams m_listItemPortraitShadowParam;
		private LinearLayout.LayoutParams m_listItemLandscapeShadowParam;

		public NoticeListAdapter(ArrayList<Item> boardData) {
			mData = boardData;
		}

		@Override
		public int getCount() {

			return mData == null ? 0 : mData.size();
		}

		@Override
		public Item getItem(int position) {

			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {

			return 0;
		}

		private class ViewHolder {

			public ImageView statusView;
			public ImageView emergencyView;
			public ImageView annView;
			public ImageView modifiedView;
			// public ImageView attachView;
			public TextView titleView;
			public TextView pubdateView;
			public LinearLayout shadow;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home_notice, null);
				holder = new ViewHolder();
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.statusView = convertView.findViewById(R.id.board_memo_status);
			holder.emergencyView = convertView.findViewById(R.id.board_memo_emergency);
			holder.modifiedView = convertView.findViewById(R.id.board_memo_modified);
			holder.annView = convertView.findViewById(R.id.board_ann_status);
			holder.shadow = convertView.findViewById(R.id.ID_LAY_L_SHADOW);

			holder.titleView = convertView.findViewById(R.id.ID_TV_TITLE);
			holder.pubdateView = convertView.findViewById(R.id.ID_TV_DATE);

			if (!TextUtils.isEmpty(getItem(position).title)) {
				holder.titleView.setText(getItem(position).title.trim());
			}

			if(!TextUtils.isEmpty(getItem(position).pubDate)){
				holder.pubdateView.setText(DateUtils.getDateStringFromGWDate(getItem(position).pubDate));
			}
			/*
			 * statusString : /img/bbs/BR0AEM.GIF B : Board의 준말 접두어 R0 : 오늘 게시되고
			 * 안 읽은 게시물 R1 : 최근 게시물중 안 읽은 게시물 R2 : 최근 게시물중 읽은 게시물 R3 : 오래된
			 * 게시물(오래된 게시물은 읽음 유무를 구분하지 않음) A : 게시물에 첨부가 있음 E : 긴급 게시물 M : 수정
			 * 이력이 있는 게시물
			 */

			String statusString = null;
			if(!TextUtils.isEmpty(getItem(position).brdType)){
				statusString = "MARK";
			}else{
				statusString = Uri.parse(getItem(position).status).getLastPathSegment();
				statusString = statusString.substring(0, statusString.lastIndexOf("."));
			}
			// 리스트 아이콘 블릿 고정 이미지 사용 20.06.23 tkofs
			int imageResourceID;
			if (statusString.contains("ANN")) {
				holder.annView.setVisibility(View.VISIBLE);
				holder.statusView.setVisibility(View.GONE);
			}else{
				holder.annView.setVisibility(View.GONE);
				holder.statusView.setVisibility(View.VISIBLE);
			}
			holder.titleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			holder.titleView.setTypeface(null, Typeface.BOLD);

			if (statusString.contains("R0")) {
				holder.titleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.n, 0);
				holder.titleView.setTypeface(null, Typeface.BOLD);
			}else{
				holder.titleView.setTypeface(null, Typeface.NORMAL);
			}

			if (m_listItemPortraitParam == null) {
				m_listItemPortraitParam = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, (int) (parent.getContext().getResources()
						.getDisplayMetrics().heightPixels * RATIO_LIST_ITEM_HEIGHT));
			}
			if (m_listItemPortraitShadowParam == null) {
				m_listItemPortraitShadowParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) (parent.getContext()
						.getResources().getDisplayMetrics().heightPixels * RATIO_LIST_ITEM_SHADOW_HEIGHT));
			}
			convertView.setLayoutParams(m_listItemPortraitParam);
			return convertView;
		}
	}

	public class GridMenuAdapter extends BaseAdapter {

		private ArrayList<HomeMenuButtionItem> m_data;
		private HashMap<String, View> convertView;

		public GridMenuAdapter(ArrayList<HomeMenuButtionItem> buttonItems) {
			m_data = buttonItems;
			convertView = new HashMap<String, View>();
		}

		@Override
		public int getCount(){
			return m_data == null ? 0 : m_data.size();
		}

		@Override
		public HomeMenuButtionItem getItem(int position) {
			return m_data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		public void setConvertView(int position, View v) {
			convertView.put("P" + position, v);
		}

		public View getConvertView(int position) {
			return convertView.get("P" + position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View v;
			if (convertView == null) {
				if (getConvertView(position) != null) {
					v = getConvertView(position);
				} else {
					v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_home_menu, null);
					setConvertView(position, v);
				}
			} else {
				v = convertView;
			}

			final View fv = v;
			v.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
				@Override
				public void onGlobalLayout() {
					AbsListView.LayoutParams p = (LayoutParams) fv.getLayoutParams();
					p.height = fv.getWidth();
					fv.setLayoutParams(p);
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
						fv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
					} else {
						fv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
					}
				}
			});

			((ImageView) v.findViewById(R.id.ID_IMG_HOME_MENU_ICON)).setImageResource(getItem(position).m_nIconId);
			((TextView) v.findViewById(R.id.ID_TV_NAME)).setText(getItem(position).m_strButtonName);
			if (m_countVo != null) {
				if (m_countVo.mtrlNew != 0 && TextUtils.equals(getItem(position).m_strMenuID, "board_recent")) {
					// (v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.VISIBLE);	// tkofs 게시판 카운트 안보이게
					// ((TextView) v.findViewById(R.id.ID_TV_COUNT)).setText(String.valueOf(m_countVo.mtrlNew));
				} else if (m_countVo.apprWait != 0 && TextUtils.equals(getItem(position).m_strMenuID, "approval_waiting")) {
					(v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.VISIBLE);
					((TextView) v.findViewById(R.id.ID_TV_COUNT)).setText(String.valueOf(m_countVo.apprWait));
				} else {
					( v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.GONE);
				}
			} else {
				(v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.GONE);
			}
			return v;
		}
	}
}
