package com.hs.mobile.gw.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.MemberSelectAdapter;
import com.hs.mobile.gw.adapter.MemberSelectAdapter.ISelectActivateListener;
import com.hs.mobile.gw.fragment.CustomWebViewFragment.OrgSelectedVO;
import com.hs.mobile.gw.openapi.square.GroupInfo;
import com.hs.mobile.gw.openapi.square.ModifySquareAdmin;
import com.hs.mobile.gw.openapi.square.ModifySquareMember;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.ViewAdminList;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.openapi.square.vo.MemberRight;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.SelectedListItem.ObjectType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MemberSelectFragment extends CommonFragment implements OnClickListener, ISelectActivateListener, ISquarePushListener {
	public enum MemberSelectType {
		REQ_RETURN, MODIFY, SINGLE_CHOICE
	}

	public static final String ARG_KEY_SQUARE_ID = "square_id";
	public static final String INTENT_KEY_MEMBERS = "selected_members";
	public static final String ARG_KEY_SELECTED_ITEMS = "selected_members";
	public static final String ARG_KEY_MEMBER_SELECT_TYPE = "member_select_type";
	private ListView m_lvParticipant;
	private ImageView m_btnBack;
	private ImageView m_btnMenu;
	private ImageView m_btnDone;
	private String m_strSquareId;
	private ArrayList<SquareMemberVO> m_memberData = new ArrayList<SquareMemberVO>();
	private MemberSelectAdapter m_adapter;
	private ArrayList<SquareMemberVO> m_selectedMemberData;
	private TextView m_btnInvite;
	private MemberSelectType m_mode;
	private LinearLayout m_functionLayout;
	private TextView m_btnOut;
	private TextView m_btnDeleteAdmin;
	private TextView m_btnAddAdmin;

	private void setMode(MemberSelectType memberSelectType) {
		m_mode = memberSelectType;
		switch (memberSelectType) {
		case MODIFY:
			m_btnInvite.setVisibility(View.VISIBLE);
			m_btnDone.setVisibility(View.GONE);
			m_btnOut.setVisibility(View.VISIBLE);
			getMainModel().addSquarePushListener(this);
			break;
		case REQ_RETURN:
			m_btnInvite.setVisibility(View.GONE);
			break;
		case SINGLE_CHOICE:
			m_btnInvite.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_member_select, container, false);
		super.createHead(v); // create sub header
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_btnMenu = (ImageView) v.findViewById(R.id.ID_BTN_MENU);
		m_btnDone = (ImageView) v.findViewById(R.id.ID_BTN_DONE);
		m_btnInvite = (TextView) v.findViewById(R.id.ID_BTN_INVITE);
		m_lvParticipant = (ListView) v.findViewById(R.id.ID_LV_PARTICIPANT);
		m_lvParticipant.setItemsCanFocus(false);
		m_lvParticipant.setScrollingCacheEnabled(false);
		m_functionLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FUNCTION);
		m_functionLayout.setVisibility(View.GONE);
		m_btnOut = (TextView) v.findViewById(R.id.ID_BTN_OUT);
		m_btnDeleteAdmin = (TextView) v.findViewById(R.id.ID_BTN_ADMIN);
		m_btnAddAdmin = (TextView) v.findViewById(R.id.ID_BTN_NORMAL);

		m_btnBack.setOnClickListener(this);
		m_btnDone.setOnClickListener(this);
		m_btnInvite.setOnClickListener(this);
		m_btnOut.setOnClickListener(this);
		m_btnDeleteAdmin.setOnClickListener(this);
		m_btnAddAdmin.setOnClickListener(this);

		if (getArguments() != null) {
			if (getArguments().getSerializable(ARG_KEY_MEMBER_SELECT_TYPE) != null) {
				setMode((MemberSelectType) getArguments().getSerializable(ARG_KEY_MEMBER_SELECT_TYPE));
			}
			if (getArguments().getString(ARG_KEY_SQUARE_ID) != null) {
				m_strSquareId = getArguments().getString(ARG_KEY_SQUARE_ID);
			}
			if (getArguments().getSerializable(ARG_KEY_SELECTED_ITEMS) != null) {
				m_selectedMemberData = (ArrayList<SquareMemberVO>) getArguments().getSerializable(ARG_KEY_SELECTED_ITEMS);
			}

			switch (m_mode) {
			case MODIFY:
				m_lvParticipant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				m_adapter = new MemberSelectAdapter(m_memberData, null);
				m_adapter.setSelectActivateListener(this);
				break;
			case REQ_RETURN:
				m_lvParticipant.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				m_adapter = new MemberSelectAdapter(m_memberData, m_selectedMemberData);
				break;
			case SINGLE_CHOICE:
				m_lvParticipant.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				m_adapter = new MemberSelectAdapter(m_memberData, null);
				break;
			}

			m_lvParticipant.setAdapter(m_adapter);
			SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(), GroupInfoVO.class) {
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);
					if (getVO() != null) {
						// 기본 부서/그룹일 경우에는 구성원 추가/삭제를 막는다.
						if (getVO().defaultDeptFlag) {
							m_btnInvite.setVisibility(View.GONE);
							m_btnOut.setVisibility(View.GONE);
						}
						m_memberData.addAll(getVO().squareMemberList);
						m_adapter.notifyDataSetChanged();
					}
				}
			};
			callback.progress(PopupUtil.getProgressDialog(getActivity()));
			Map<String, String> params = new HashMap<String, String>();
			params.put("squareId", m_strSquareId);
			new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(), callback, params).execute();

		} else {
			getActivity().finish();
		}
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_BTN_DONE:
			switch (m_mode) {
			case MODIFY:
				break;
			case REQ_RETURN:
				if (m_adapter.getCheckedItems() != null && m_adapter.getCheckedItems().size() > 0) {
					Intent intent = new Intent();
					intent.putExtra(INTENT_KEY_MEMBERS, m_adapter.getCheckedItems());
					getActivity().setResult(Activity.RESULT_OK, intent);
					getActivity().finish();
				} else {
					PopupUtil.showDialog(getActivity(), R.string.squere_member_select_not_selected_people);
				}
				break;
			case SINGLE_CHOICE:
				if (m_adapter.getCheckedItems() != null && m_adapter.getCheckedItems().size() > 0) {
					Intent intent = new Intent();
					intent.putExtra(INTENT_KEY_MEMBERS, m_adapter.getCheckedItems());
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
									+ MainModel.getInstance().convertSquareMemberToJson(m_memberData) + "}');").build());
			break;
		case R.id.ID_BTN_OUT: {
			// 강퇴
			if (m_adapter.getCheckedItems().size() <= 0) {
				return;
			}
			StringBuilder sb = new StringBuilder();
			m_memberData.removeAll(m_adapter.getCheckedItems());
			for (SquareMemberVO member : m_memberData) {
				sb.append(member.toString());
				// sb.append(";");
			}
			// if (sb.length() > 0) {
			// sb.replace(sb.lastIndexOf(";"), sb.length(), "");
			// }
			new ApiLoader(new ModifySquareMember(), getActivity(), new SquareDefaultCallback() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
						if (this.responseData.dataList != null && this.responseData.dataList.length() > 0) {
							m_memberData.clear();
							for (int i = 0; i < this.responseData.dataList.length(); ++i) {
								m_memberData.add(new SquareMemberVO(this.responseData.dataList.optJSONObject(i)));
							}
							m_adapter.clearCheck();
							m_lvParticipant.clearChoices();
						}
					}
				}
			}, m_strSquareId, sb.toString()).execute();
		}
			break;
		case R.id.ID_BTN_ADMIN: {
			if (m_adapter.getCheckedItems().size() <= 0) {
				return;
			}
			// 관리자 삭제
			// 기존 관리자를 구한다.
			SquareDefaultAjaxCallBack<GroupInfoVO> viewAdminCallback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(),
					GroupInfoVO.class) {
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);
					ArrayList<SquareMemberVO> tempMemberData = new ArrayList<SquareMemberVO>();
					for (SquareMemberVO member : getVO().squareMemberList) {
						if (member.memberRights == MemberRight.ADMIN_USER) {
							tempMemberData.add(member);
						}
					}
					tempMemberData.removeAll(m_adapter.getCheckedItems());
					StringBuilder sb = new StringBuilder();
					for (SquareMemberVO member : tempMemberData) {
						sb.append(member.memberId.toString());
						sb.append(';');
					}
					if (sb.length() > 0) {
						sb.replace(sb.lastIndexOf(";"), sb.length(), "");
					}
					new ApiLoader(new ModifySquareAdmin(), getActivity(), new SquareDefaultCallback() {
						@Override
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
								if (this.responseData.dataList != null && this.responseData.dataList.length() > 0) {
									SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(
											getActivity(), GroupInfoVO.class) {
										@Override
										public void callback(String url, JSONObject json, AjaxStatus status) {
											super.callback(url, json, status);
											if (getVO() != null) {
												m_memberData.clear();
												m_memberData.addAll(getVO().squareMemberList);
												m_adapter.clearCheck();
												m_lvParticipant.clearChoices();
											}
										}
									};
									callback.progress(PopupUtil.getProgressDialog(getActivity()));
									Map<String, String> params = new HashMap<String, String>();
									params.put("squareId", m_strSquareId);
									new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(),
											callback, params).execute();
								}
							}
						}
					}, m_strSquareId, sb.toString()).execute();
				};
			};

			Map<String, String> viewAdminParams = new HashMap<String, String>();
			viewAdminParams.put("squareId", m_strSquareId);
			new ApiLoaderEx<JSONObject>(new ViewAdminList(getActivity()), getActivity(), viewAdminCallback, viewAdminParams).execute();
		}
			break;
		case R.id.ID_BTN_NORMAL: {
			if (m_adapter.getCheckedItems().size() <= 0) {
				return;
			}
			// 관리자 추가
			// 기존 관리자를 구한다.
			SquareDefaultAjaxCallBack<GroupInfoVO> viewAdminCallback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(),
					GroupInfoVO.class) {
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);
					final ArrayList<SquareMemberVO> tempMemberData = new ArrayList<SquareMemberVO>();
					for (SquareMemberVO m : getVO().squareMemberList) {
						if (m.memberRights == MemberRight.ADMIN_USER) {
							tempMemberData.add(m);
						}
					}
					tempMemberData.addAll(m_adapter.getCheckedItems());
					StringBuilder sb = new StringBuilder();
					for (SquareMemberVO member : tempMemberData) {
						sb.append(member.memberId);
						sb.append(';');
					}
					new ApiLoader(new ModifySquareAdmin(), getActivity(), new SquareDefaultCallback() {
						@Override
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
								if (this.responseData.dataList != null && this.responseData.dataList.length() > 0) {
									SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(
											getActivity(), GroupInfoVO.class) {
										@Override
										public void callback(String url, JSONObject json, AjaxStatus status) {
											super.callback(url, json, status);
											if (getVO() != null) {
												m_memberData.clear();
												m_memberData.addAll(getVO().squareMemberList);
												m_adapter.clearCheck();
												m_lvParticipant.clearChoices();
											}
										}
									};
									callback.progress(PopupUtil.getProgressDialog(getActivity()));
									Map<String, String> params = new HashMap<String, String>();
									params.put("squareId", m_strSquareId);
									new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(),
											callback, params).execute();
								}
							}
						}
					}, m_strSquareId, sb.toString()).execute();
				}
			};
			Map<String, String> viewAdminParams = new HashMap<String, String>();
			viewAdminParams.put("squareId", m_strSquareId);
			new ApiLoaderEx<JSONObject>(new ViewAdminList(getActivity()), getActivity(), viewAdminCallback, viewAdminParams).execute();
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
				if (m_memberData.size() > 0) {
					m_memberData.clear();
				}
				String strOrgSelected = data.getStringExtra(CustomWebViewFragment.INTENT_KEY_ORG_SELECT);
				OrgSelectedVO vo = new OrgSelectedVO(strOrgSelected);
				for (SelectedListItem item : vo.selectedlist) {
					Debug.trace(item);
					m_memberData.add(new SquareMemberVO(item.name, item.objectType.equals(ObjectType.USER) ? "0" : "1", item.id));
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < m_memberData.size(); ++i) {
					sb.append(m_memberData.get(i).toString());
				}
				new ApiLoader(new ModifySquareMember(), getActivity(), new SquareDefaultCallback() {
					@Override
					public void onResponse(String strRet) {
						super.onResponse(strRet);
						if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
							if (this.responseData.dataList != null && this.responseData.dataList.length() > 0) {
								m_memberData.clear();
								for (int i = 0; i < this.responseData.dataList.length(); ++i) {
									m_memberData.add(new SquareMemberVO(this.responseData.dataList.optJSONObject(i)));
									m_adapter.notifyDataSetChanged();
									m_adapter.notifyDataSetInvalidated();
								}
							}
						}
					}
				}, m_strSquareId, sb.toString()).execute();
				break;
			}
		}
	}

	@Override
	public void onChangeSelection(final ArrayList<SquareMemberVO> arrayList) {
		m_functionLayout.post(new Runnable() {
			@Override
			public void run() {
				m_functionLayout.setVisibility(arrayList.size() > 0 ? View.VISIBLE : View.GONE);
			}
		});
		if (arrayList.size() > 0) {
			int nAdmin = 0;
			int nNormal = 0;
			int nDepartmentCount = 0;
			for (SquareMemberVO member : arrayList) {
				if (TextUtils.equals(member.memberType, "1")) {
					nDepartmentCount += 1;
				}
				switch (member.memberRights) {
				case ADMIN_USER:
					nAdmin++;
					break;
				case NORMAL_USER:
					nNormal++;
					break;
				case READ_ONLY_USER:
					break;
				}
			}
			// 부서가 선택되었을 경우 관리자 지정 기능을 막는다.
			if (nDepartmentCount > 0) {
				m_btnDeleteAdmin.setEnabled(false);
				m_btnAddAdmin.setEnabled(false);
			} else {
				m_btnDeleteAdmin.setEnabled(true);
				m_btnAddAdmin.setEnabled(true);
				// 관리자가 한명도 없을 경우 관리자 해제가 비활성화 되도록 한다.
				// 관리자의 카운트를 구한다.
				int nAdminCount = 0;
				for (SquareMemberVO m : m_memberData) {
					if (m.memberRights == MemberRight.ADMIN_USER) {
						nAdminCount += 1;
					}
				}
				Debug.trace("관리자 수:", nAdminCount);
				if (nAdmin >= nAdminCount) {
					m_btnDeleteAdmin.setEnabled(false);
				} else {
					m_btnDeleteAdmin.setEnabled(true);
				}
			}
			// 관리자가 한명이라도 들어 있으면 강퇴 버튼을 비활성 시킨다.
			if (nAdmin > 0) {
				m_btnOut.setEnabled(false);
			} else {
				m_btnOut.setEnabled(true);
			}

			m_btnDeleteAdmin.setText(getString(R.string.label_square_member_delete_admin_format, nAdmin));
			m_btnAddAdmin.setText(getString(R.string.label_square_member_add_admin_format, nNormal));
			m_btnOut.setText(getString(R.string.label_square_member_out_format, arrayList.size()));
		}
	}

	@Override
	public void onPushReceive(SquarePushVO vo) {
		switch (vo.square_action) {
		case SQUARE_MEMBER_CHANGE:
		case SQURE_MEMBER_ADMIN_CHANGE:
			if(m_memberData != null && m_memberData.size() > 0)
			{
				m_memberData.clear();
			}
			SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(), GroupInfoVO.class) {
				@Override
				public void callback(String url, JSONObject json, AjaxStatus status) {
					super.callback(url, json, status);
					if (getVO() != null) {
						// 기본 부서/그룹일 경우에는 구성원 추가/삭제를 막는다.
						if (getVO().defaultDeptFlag) {
							m_btnInvite.setVisibility(View.GONE);
							m_btnOut.setVisibility(View.GONE);
						}
						m_memberData.addAll(getVO().squareMemberList);
						m_adapter.notifyDataSetChanged();
						m_lvParticipant.clearChoices();
					}
				}
			};
			callback.progress(PopupUtil.getProgressDialog(getActivity()));
			Map<String, String> params = new HashMap<String, String>();
			params.put("squareId", m_strSquareId);
			new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(), callback, params).execute();
			break;
		}
	}
}
