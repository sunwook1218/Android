package com.hs.mobile.gw.fragment.squareplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.SpMemberSelectAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpMemberSelectAdapter.ISpSelectActivateListener;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment.OrgSelectedVO;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquare;
import com.hs.mobile.gw.openapi.squareplus.SpModifySquareMember;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareMemberListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.SelectedListItem.ObjectType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SpMemberSelectFragment extends CommonFragment implements OnClickListener, ISpSelectActivateListener {
	public enum SpMemberSelectType {
		REQ_RETURN, MODIFY, SINGLE_CHOICE
	}

	public static final String ARG_KEY_SQUARE_ID = "square_id";
	public static final String INTENT_KEY_MEMBERS = "selected_members";
	public static final String ARG_KEY_SELECTED_ITEMS = "selected_sp_members";
	public static final String ARG_KEY_MEMBER_SELECT_TYPE = "member_select_type";
	private ListView m_lvParticipant;
	private ImageView m_btnBack;
//	private ImageView m_btnMenu;
	private ImageView m_btnDone;
	private String m_strSquareId;
	private List<SpSquareMemberVO> m_memberData = new ArrayList<SpSquareMemberVO>();
	private List<SpSquareMemberVO> m_dbMemberData = new ArrayList<SpSquareMemberVO>();
	private List<SpSquareMemberVO> m_viewMemberData = new ArrayList<SpSquareMemberVO>();
	private SpMemberSelectAdapter m_adapter;
	private List<SpSquareMemberVO> m_selectedMemberData;
	private TextView m_btnInvite;
	private SpMemberSelectType m_mode;
	private LinearLayout m_functionLayout;
	private TextView m_btnOut;
	private TextView m_btnObserver;
	private TextView m_btnAdmin;
	private boolean m_bAdminFlag = true;		// true : 추가flag, false : 삭제flag
	private boolean m_bObserverFlag = true;		// true : 추가flag, false : 삭제flag
	private boolean m_bOpenSquare = false;		// true : 공개그룹, false : 비공개그룹

	private void setMode(SpMemberSelectType memberSelectType) {
		m_mode = memberSelectType;
		switch (memberSelectType) {
		case MODIFY:
			m_btnInvite.setVisibility(View.VISIBLE);
			m_btnDone.setVisibility(View.GONE);
			m_btnOut.setVisibility(View.VISIBLE);
			break;
		case REQ_RETURN:
			m_btnInvite.setVisibility(View.GONE);
			break;
		case SINGLE_CHOICE:
			m_btnInvite.setVisibility(View.GONE);
			break;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_squareplus_member_select, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
//		m_btnMenu = (ImageView) v.findViewById(R.id.ID_BTN_MENU);
		m_btnDone = (ImageView) v.findViewById(R.id.ID_BTN_DONE);
		m_btnInvite = (TextView) v.findViewById(R.id.ID_BTN_INVITE);
		m_lvParticipant = (ListView) v.findViewById(R.id.ID_LV_PARTICIPANT);
		m_lvParticipant.setItemsCanFocus(false);
		m_lvParticipant.setScrollingCacheEnabled(false);
		m_functionLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FUNCTION);
		m_functionLayout.setVisibility(View.GONE);
		m_btnOut = (TextView) v.findViewById(R.id.ID_BTN_OUT);
		m_btnObserver = (TextView) v.findViewById(R.id.ID_BTN_OBSERVER);
		m_btnAdmin = (TextView) v.findViewById(R.id.ID_BTN_ADMIN);

		m_btnBack.setOnClickListener(this);
		m_btnDone.setOnClickListener(this);
		m_btnInvite.setOnClickListener(this);
		m_btnOut.setOnClickListener(this);
		m_btnObserver.setOnClickListener(this);
		m_btnAdmin.setOnClickListener(this);

		if (getArguments() != null) {
			if (getArguments().getSerializable(ARG_KEY_MEMBER_SELECT_TYPE) != null) {
				setMode((SpMemberSelectType) getArguments().getSerializable(ARG_KEY_MEMBER_SELECT_TYPE));
			}

			if (getArguments().getString(ARG_KEY_SQUARE_ID) != null) {
				m_strSquareId = getArguments().getString(ARG_KEY_SQUARE_ID);
			}

			if (getArguments().getSerializable(ARG_KEY_SELECTED_ITEMS) != null) {
				m_selectedMemberData = (List<SpSquareMemberVO>) getArguments().getSerializable(ARG_KEY_SELECTED_ITEMS);
			}

			switch (m_mode) {
			case MODIFY:
				m_lvParticipant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				m_adapter = new SpMemberSelectAdapter(m_memberData, null);
				m_adapter.setSelectActivateListener(this);
				break;
			case REQ_RETURN:
				m_lvParticipant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				m_adapter = new SpMemberSelectAdapter(m_memberData, m_selectedMemberData);
				break;
			case SINGLE_CHOICE:
				m_lvParticipant.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				m_adapter = new SpMemberSelectAdapter(m_memberData, null);
				break;
			}

			m_lvParticipant.setAdapter(m_adapter);
			
			SpSquareCallBack spGetSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);
					// 기본 부서/그룹일 경우에는 구성원 추가/삭제를 막는다.
					if (this.item.isDefaultDept()) {
						m_btnInvite.setVisibility(View.GONE);
						m_btnOut.setVisibility(View.GONE);
					}

					m_memberData.addAll(this.item.getSquareMemberList());
					m_dbMemberData.addAll(this.item.getDbMemberList());
					m_viewMemberData.addAll(this.item.getViewMemberList());
					m_adapter.notifyDataSetChanged();
					m_bOpenSquare = !this.item.isSecurity();
				}
			};

			{ // API명
				HashMap<String,String> params = new HashMap<>();
				params.put("squareId", m_strSquareId);
				new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spGetSquareCallBack, params).execute();
			}
		} else {
			getActivity().finish();
		}
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			if (MainModel.getInstance().isTablet()) {
				if (getFragmentManager().getBackStackEntryCount() > 0) {
					getFragmentManager().popBackStack();
				}
				else {
					getActivity().finish();
				}
			} else {
				getActivity().finish();
			}
			break;
		case R.id.ID_BTN_DONE:
			switch (m_mode) {
			case MODIFY:
				break;
			case REQ_RETURN:
				if (m_adapter.getCheckedItems() != null && m_adapter.getCheckedItems().size() > 0) {
					Intent intent = new Intent();
					for (int i=0; i<m_adapter.getCheckedItems().size(); i++) {
						m_adapter.getCheckedItems().get(i).setRegisterDate(null);
					}
					intent.putExtra(INTENT_KEY_MEMBERS, new Gson().toJson(m_adapter.getCheckedItems()));
					getActivity().setResult(Activity.RESULT_OK, intent);
					getActivity().finish();
				} else {
					PopupUtil.showDialog(getActivity(), R.string.squere_member_select_not_selected_people);
				}
				break;
			case SINGLE_CHOICE:
				if (m_adapter.getCheckedItems() != null && m_adapter.getCheckedItems().size() > 0) {
					Intent intent = new Intent();
					intent.putExtra(INTENT_KEY_MEMBERS, (ArrayList<SpSquareMemberVO>)m_adapter.getCheckedItems());
					getActivity().setResult(Activity.RESULT_OK, intent);
					getActivity().finish();
				} else {
					PopupUtil.showDialog(getActivity(), R.string.squere_member_select_not_selected_people);
				}
				break;
			}
			break;
		case R.id.ID_BTN_INVITE:
			MainModel.getInstance().showSubActivity(
					this,
					SubActivityType.ORG_CHART,
					new BundleUtils.Builder().add(
							CustomWebViewFragment.ARG_KEY_URL,
							"javascript:showOrgSelectForApp('square','to','{\"selectedlist\":"
									+ MainModel.getInstance().convertSpSquareMemberToJson(m_viewMemberData) + "}');").build());
			break;
		case R.id.ID_BTN_OUT: {
			// 강퇴
			if (m_adapter.getCheckedItems().size() <= 0) {
				return;
			}

			StringBuilder sb = new StringBuilder();
			List<SpSquareMemberVO> checkedMemberList = m_adapter.getCheckedItems();

			// 체크된 멤버가 부서안의 유저인지 파악하여 재결합여부를 정리해야함
			for (SpSquareMemberVO member : checkedMemberList) {
				SpSquareMemberVO dept = new SpSquareMemberVO(member.getMemberName(), SpSquareMemberVO.DEPT, member.getDeptId());

				if (m_dbMemberData.contains(dept)) {
					Debug.trace(dept.getMemberName() + " --> 부서 구성원인원");
					List<SpSquareMemberVO> addMemberList = new ArrayList<SpSquareMemberVO>();

					for (SpSquareMemberVO memberVO : m_memberData) {
						if (memberVO.getDeptId().equals(dept.getMemberId()) && !memberVO.equals(member) && !m_dbMemberData.contains(memberVO)) {
							addMemberList.add(memberVO); 
						}
					}

					m_dbMemberData.remove(dept);
					m_dbMemberData.remove(member);
					m_dbMemberData.addAll(addMemberList);
				} else {
					Debug.trace(dept.getMemberName() + " --> 부서 구성원인원 아님");
					m_dbMemberData.remove(member);
				}
			}

			Debug.trace(m_dbMemberData);

			SpSquareMemberListCallBack spSquareMemberListCallBack = new SpSquareMemberListCallBack(getActivity(), SpSquareMemberVO.class){
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);

					// 다시 호출하여 정보를 갱신시켜준다.
					SpSquareCallBack spGetSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);

							m_memberData.clear();
							m_memberData.addAll(this.item.getSquareMemberList());
							m_dbMemberData.clear();
							m_dbMemberData.addAll(this.item.getDbMemberList());
							m_adapter.clearCheck();
							m_lvParticipant.clearChoices();
						}
					};

					{ // API명
						HashMap<String,String> params = new HashMap<>();
						params.put("squareId", m_strSquareId);
						new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spGetSquareCallBack, params).execute();
					}
				}
			};

			{ // 파일에 즐겨찾기된 컨텐츠들의 작성자 목록을 가져온다.(모든 그룹 기준)
				/**
				 * 멤버 변경은 기존 스퀘어와 다르게 한 개의 api에서 admin과 member observer 3가지를 동시에 처리해야한다.
				 * GetSquare.dbMemberList기준으로 작성되어야하며 변경된 사용자 정보를 추가 하거나 빼서 적용시키도록 제공하면된다.
				 * ex) 001002907,이평복,0,0
				 * id,name,type,rights
				 * id : userId또는 deptId를 사용
				 * name : id에 따른 명칭이다.
				 * type : 0:유저, 1:부서
				 * rights : 0:관리자, 1:멤버, 2:참관인(Observer)
				 */
				for (SpSquareMemberVO member : m_dbMemberData) {
					sb.append(member.toString());
				}

				HashMap<String,String> params = new HashMap<>();
				params.put("squareId", m_strSquareId);
				params.put("member", sb.toString().replaceFirst(";", ""));
				new ApiLoaderEx<>(new SpModifySquareMember(getActivity()), getActivity(), spSquareMemberListCallBack, params).execute();
			}
		}
			break;
		case R.id.ID_BTN_ADMIN: {
			if (m_adapter.getCheckedItems().size() <= 0) {
				return;
			}

			SpSquareMemberListCallBack spSquareMemberListCallBack = new SpSquareMemberListCallBack(getActivity(), SpSquareMemberVO.class){
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);

					// 다시 호출하여 정보를 갱신시켜준다.
					SpSquareCallBack spGetSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);

							m_memberData.clear();
							m_memberData.addAll(this.item.getSquareMemberList());
							m_dbMemberData.clear();
							m_dbMemberData.addAll(this.item.getDbMemberList());
							m_adapter.clearCheck();
							m_lvParticipant.clearChoices();
						}
					};

					spGetSquareCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

					{ // API명
						HashMap<String,String> params = new HashMap<>();
						params.put("squareId", m_strSquareId);
						new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spGetSquareCallBack, params).execute();
					}
				}
			};

			spSquareMemberListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

			{ // 파일에 즐겨찾기된 컨텐츠들의 작성자 목록을 가져온다.(모든 그룹 기준)
				/**
				 * 멤버 변경은 기존 스퀘어와 다르게 한 개의 api에서 admin과 member observer 3가지를 동시에 처리해야한다.
				 * GetSquare.dbMemberList기준으로 작성되어야하며 변경된 사용자 정보를 추가 하거나 빼서 적용시키도록 제공하면된다.
				 * ex) 001002907,이평복,0,0
				 * id,name,type,rights
				 * id : userId또는 deptId를 사용
				 * name : id에 따른 명칭이다.
				 * type : 0:유저, 1:부서
				 * rights : 0:관리자, 1:멤버, 2:참관인(Observer)
				 */
				StringBuilder sb = new StringBuilder();

				// 기존에 db구성원에 admin으로 등록하고자 하는 사용자들을 지운다. (중복되는 데이터 제거용)
				for (SpSquareMemberVO member : m_adapter.getCheckedItems()) {
					if (m_dbMemberData.contains(member)) {
						m_dbMemberData.remove(member);
					}
				}

				// 기존 멤버추가
				for (SpSquareMemberVO member : m_dbMemberData) {
					if(member != null){ 
						sb.append(member.toString());
					}else{
						sb.append("null") ;
					}					
				}

				if (m_bAdminFlag) {
					// 관리자 추가
					for (SpSquareMemberVO member : m_adapter.getCheckedItems()) {
						member.setMemberRights(SpSquareConst.SQ_MEMBER_RIGHTS_ADMIN);
						sb.append(member.toString());
					}
				} else {
					// 관리자 제거
					for (SpSquareMemberVO member : m_adapter.getCheckedItems()) {
						member.setMemberRights(SpSquareConst.SQ_MEMBER_RIGHTS_MEMBER);
						sb.append(member.toString());
					}
				}

				HashMap<String,String> params = new HashMap<>();
				params.put("squareId", m_strSquareId);
				params.put("member", sb.toString().replaceFirst(";", ""));
				new ApiLoaderEx<>(new SpModifySquareMember(getActivity()), getActivity(), spSquareMemberListCallBack, params).execute();
			}
		}
			break;
		case R.id.ID_BTN_OBSERVER: {
			if (m_adapter.getCheckedItems().size() <= 0) {
				return;
			}

			SpSquareMemberListCallBack spSquareMemberListCallBack = new SpSquareMemberListCallBack(getActivity(), SpSquareMemberVO.class){
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);

					// 다시 호출하여 정보를 갱신시켜준다.
					SpSquareCallBack spGetSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);

							m_memberData.clear();
							m_memberData.addAll(this.item.getSquareMemberList());
							m_dbMemberData.clear();
							m_dbMemberData.addAll(this.item.getDbMemberList());
							m_adapter.clearCheck();
							m_lvParticipant.clearChoices();
						}
					};

					spGetSquareCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

					{ // API명
						HashMap<String,String> params = new HashMap<>();
						params.put("squareId", m_strSquareId);
						new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spGetSquareCallBack, params).execute();
					}
				}
			};

			spSquareMemberListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

			{ // 파일에 즐겨찾기된 컨텐츠들의 작성자 목록을 가져온다.(모든 그룹 기준)
				/**
				 * 멤버 변경은 기존 스퀘어와 다르게 한 개의 api에서 admin과 member observer 3가지를 동시에 처리해야한다.
				 * GetSquare.dbMemberList기준으로 작성되어야하며 변경된 사용자 정보를 추가 하거나 빼서 적용시키도록 제공하면된다.
				 * ex) 001002907,이평복,0,0
				 * id,name,type,rights
				 * id : userId또는 deptId를 사용
				 * name : id에 따른 명칭이다.
				 * type : 0:유저, 1:부서
				 * rights : 0:관리자, 1:멤버, 2:참관인(Observer)
				 */
				StringBuilder sb = new StringBuilder();

				// 기존에 db구성원에 admin으로 등록하고자 하는 사용자들을 지운다. (중복되는 데이터 제거용)
				for (SpSquareMemberVO member : m_adapter.getCheckedItems()) {
					if (m_dbMemberData.contains(member)) {
						m_dbMemberData.remove(member);
					}
				}

				// 기존 멤버추가
				for (SpSquareMemberVO member : m_dbMemberData) {
					if(member != null){ 
						sb.append(member.toString());
					}else{
						sb.append("null") ;
					}
					
				}

				if (m_bObserverFlag) {
					// 옵저버 추가
					for (SpSquareMemberVO member : m_adapter.getCheckedItems()) {
						member.setMemberRights(SpSquareConst.SQ_MEMBER_RIGHTS_OBSERVER);
						sb.append(member.toString());
					}
				} else {
					// 옵저버 제거
					for (SpSquareMemberVO member : m_adapter.getCheckedItems()) {
						member.setMemberRights(SpSquareConst.SQ_MEMBER_RIGHTS_MEMBER);
						sb.append(member.toString());
					}
				}

				HashMap<String,String> params = new HashMap<>();
				params.put("squareId", m_strSquareId);
				params.put("member", sb.toString().replaceFirst(";", ""));
				new ApiLoaderEx<>(new SpModifySquareMember(getActivity()), getActivity(), spSquareMemberListCallBack, params).execute();
			}
		}
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_ORG_SELECT:
				List<SpSquareMemberVO> selectedMemberList = new ArrayList<SpSquareMemberVO>();
				List<SpSquareMemberVO> tempMemberList = new ArrayList<SpSquareMemberVO>();
				String strOrgSelected = data.getStringExtra(CustomWebViewFragment.INTENT_KEY_ORG_SELECT);
				OrgSelectedVO vo = new OrgSelectedVO(strOrgSelected);

				for (SelectedListItem item : vo.selectedlist) {
					Debug.trace(item);
					selectedMemberList.add(new SpSquareMemberVO(item.name, item.objectType.equals(ObjectType.USER) ? "0" : "1", item.id));
				}
				tempMemberList.addAll(m_dbMemberData);
				
//				// 제거할 멤버 찾기
//				for (SpSquareMemberVO dbMember : m_dbMemberData) {
//					Debug.trace("orgMember = ",dbMember.toString());
//					if (dbMember.isUser() && (dbMember.isAdmin() || dbMember.isObserver())) {
//						// 부서안의 멤버들은 살려준다. (관리자, 참관인만)
//						SpSquareMemberVO dept = new SpSquareMemberVO(dbMember.getMemberName(), SpSquareMemberVO.DEPT, dbMember.getDeptId());
//
//						if (m_dbMemberData.contains(dept)) {
//							tempMemberList.add(dbMember);
//						} else if (selectedMemberList.contains(dbMember)) {
//							// 선택된 멤버들은 살려준다.
//							tempMemberList.add(dbMember);
//						}
//					} else if (selectedMemberList.contains(dbMember)) {
//						// 선택된 멤버들은 살려준다.
//						tempMemberList.add(dbMember);
//					}
//				}
							
				// 신규 멤버 추가
				for (SpSquareMemberVO orgSelectedMember : selectedMemberList) {
					if (!tempMemberList.contains(orgSelectedMember)) {
						tempMemberList.add(orgSelectedMember);
					}
				}

				Debug.trace(tempMemberList);

				SpSquareMemberListCallBack spSquareMemberListCallBack = new SpSquareMemberListCallBack(getActivity(), SpSquareMemberVO.class) {
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);

						// 다시 호출하여 정보를 갱신시켜준다.
						SpSquareCallBack spGetSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
							@Override
							public void callback(String url, JSONObject json, AjaxStatus status) {
								super.callback(url, json, status);

								m_memberData.clear();
								m_memberData.addAll(this.item.getSquareMemberList());
								m_dbMemberData.clear();
								m_dbMemberData.addAll(this.item.getDbMemberList());
								m_adapter.clearCheck();
								m_lvParticipant.clearChoices();
							}
						};

						{ // API명
							HashMap<String,String> params = new HashMap<>();
							params.put("squareId", m_strSquareId);
							new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spGetSquareCallBack, params).execute();
						}
					}
				};

				{ // 파일에 즐겨찾기된 컨텐츠들의 작성자 목록을 가져온다.(모든 그룹 기준)
					/**
					 * 멤버 변경은 기존 스퀘어와 다르게 한 개의 api에서 admin과 member observer 3가지를 동시에 처리해야한다.
					 * GetSquare.dbMemberList기준으로 작성되어야하며 변경된 사용자 정보를 추가 하거나 빼서 적용시키도록 제공하면된다.
					 * ex) 001002907,이평복,0,0
					 * id,name,type,rights
					 * id : userId또는 deptId를 사용
					 * name : id에 따른 명칭이다.
					 * type : 0:유저, 1:부서
					 * rights : 0:관리자, 1:멤버, 2:참관인(Observer)
					 */
					StringBuilder sb = new StringBuilder();

					for (SpSquareMemberVO member : tempMemberList) {
						sb.append(member.toString());
					}

					HashMap<String,String> params = new HashMap<>();
					params.put("squareId", m_strSquareId);
					params.put("member", sb.toString().replaceFirst(";", ""));
					new ApiLoaderEx<>(new SpModifySquareMember(getActivity()), getActivity(), spSquareMemberListCallBack, params).execute();
				}

				break;
			}
		}
	}

	@Override
	public void onChangeSelection(final List<SpSquareMemberVO> spSquareMemberList) {
		m_functionLayout.post(new Runnable() {
			@Override
			public void run() {
				m_functionLayout.setVisibility(spSquareMemberList.size() > 0 ? View.VISIBLE : View.GONE);
			}
		});

		if (spSquareMemberList.size() > 0) {
			int nAdmin = 0;
			int nMember = 0;
			int nObserver = 0;

			for (SpSquareMemberVO member : spSquareMemberList) {
				switch (member.getMemberRightsEnum()) {
				case ADMIN_USER:
					nAdmin++;
					break;
				case NORMAL_USER:
					nMember++;
					break;
				case OBSERVER_USER:
					nObserver++;
					break;
				}
			}

			Debug.trace("관리자 수:", nAdmin);
			Debug.trace("일반멤버 수:", nMember);
			Debug.trace("참관인 수:", nObserver);

			m_btnObserver.setEnabled(true);
			m_btnAdmin.setEnabled(true);

			// 서로 다른 타입의 유저를 체크하였을 경우 버튼을 모두 비활성화 한다.
			if ((nAdmin > 0 && nObserver > 0) || (nMember > 0 && nAdmin > 0) || (nObserver > 0 && nMember > 0)) {
				m_btnObserver.setText(getString(R.string.label_squareplus_member_delete_observer_format, nObserver));
				if (m_bOpenSquare) {
					m_btnObserver.setVisibility(View.GONE);
				} else {
					m_btnObserver.setEnabled(false);
				}
				m_btnAdmin.setText(getString(R.string.label_squareplus_member_delete_admin_format, nAdmin));
				m_btnAdmin.setEnabled(false);
			} else if (nAdmin > 0) {
				m_btnObserver.setText(getString(R.string.label_squareplus_member_delete_observer_format, nObserver));
				if (m_bOpenSquare) {
					m_btnObserver.setVisibility(View.GONE);
				} else {
					m_btnObserver.setEnabled(true);
				}
				m_btnAdmin.setText(getString(R.string.label_squareplus_member_delete_admin_format, nAdmin));
				m_btnAdmin.setEnabled(true);
				m_bAdminFlag=false;
			} else if (nObserver > 0) {
				m_btnObserver.setText(getString(R.string.label_squareplus_member_delete_observer_format, nObserver));
				if (m_bOpenSquare) {
					m_btnObserver.setVisibility(View.GONE);
				} else {
					m_btnObserver.setEnabled(true);
				}
				m_btnAdmin.setText(getString(R.string.label_squareplus_member_delete_admin_format, nAdmin));
				m_btnAdmin.setEnabled(false);
				m_bObserverFlag=false;
			} else if (nMember > 0) {
				m_btnObserver.setText(getString(R.string.label_squareplus_member_add_observer_format, nMember));
				if (m_bOpenSquare) {
					m_btnObserver.setVisibility(View.GONE);
				} else {
					m_btnObserver.setEnabled(true);
				}
				m_btnAdmin.setText(getString(R.string.label_squareplus_member_add_admin_format, nMember));
				m_btnAdmin.setEnabled(true);
				m_bAdminFlag=true;
				m_bObserverFlag=true;
			}

			// 관리자나 참관인이 한명이라도 들어 있으면 강퇴 버튼을 비활성 시킨다.
			if (nAdmin > 0 || nObserver > 0) {
				m_btnOut.setEnabled(false);
			} else {
				m_btnOut.setEnabled(true);
			}

			m_btnOut.setText(getString(R.string.label_squareplus_member_out_format, spSquareMemberList.size()));
		}
	}
}
