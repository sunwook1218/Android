package com.hs.mobile.gw.fragment;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.MemberSelectFragment.MemberSelectType;
import com.hs.mobile.gw.openapi.square.AddFavoriteGroup;
import com.hs.mobile.gw.openapi.square.DeleteSquare;
import com.hs.mobile.gw.openapi.square.GroupInfo;
import com.hs.mobile.gw.openapi.square.ReopenSquare;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.SquareOpenApiEx;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.openapi.square.vo.MemberRight;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.PopupUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SquareSettingFragment extends CommonFragment
		implements OnClickListener, OnCheckedChangeListener, ISquarePushListener {

	private LinearLayout m_grouptTitleLayout;
	private CheckBox m_cbFavoriteGroup;
	private LinearLayout m_settingParticipantLayout;
	private TextView m_tvSettingParticipantCount;
	private LinearLayout m_endActivityLayout;
	private ImageView m_btnBack;
	private String m_strSquareId;
	private TextView m_tvGroupTitle;
	private GroupInfoVO m_groupInfoData;
	private MemberRight m_myRight = MemberRight.READ_ONLY_USER;
	private LinearLayout m_groupMemberLayout;
	private LinearLayout m_groupActivityLayout;
	private TextView m_tvActivityLabel;
	private TextView m_tvActivityDescription;
	private LinearLayout m_favoriteGroupLayout;
	private TextView m_tvNotifySetting;

	public static final int SQ_MEMBER_NOTIFY_ALL = 1; // 모두 받음
	public static final int SQ_MEMBER_NOTIFY_MENTION_ONLY = 2; // 멘션만 받음
	public static final int SQ_MEMBER_NOTIFY_NOTHING = 0; // 아무것도 받지 않음
	private CharSequence[] notifySettingItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
		} else {
			getActivity().finish();
		}
		getMainModel().addSquarePushListener(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getMainModel().removeSquarePushListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_square_setting, container, false);
		super.createHead(v); // create sub header
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);

		m_grouptTitleLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_GROUP_TITLE);
		m_tvGroupTitle = (TextView) v.findViewById(R.id.ID_TV_GROUP_TITLE);
		m_favoriteGroupLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FAVORITE_GROUP);
		m_cbFavoriteGroup = (CheckBox) v.findViewById(R.id.ID_CB_FAVORITE_GROUP);
		m_groupMemberLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_GROUP_MEMBER);
		m_settingParticipantLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_SETTING_PARTICIPANT);
		m_tvSettingParticipantCount = (TextView) v.findViewById(R.id.ID_TV_SETTING_PARTICIPANT_COUNT);
		m_groupActivityLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_GROUP_ACTIVITY);
		m_endActivityLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_END_ACTIVITY);
		m_tvActivityLabel = (TextView) v.findViewById(R.id.ID_TV_ACTIVITY_LABEL);
		m_tvActivityDescription = (TextView) v.findViewById(R.id.ID_TV_ACTIVITY_DESC);

		m_tvNotifySetting = (TextView) v.findViewById(R.id.ID_TV_NOTIFY_SETTING);

		m_favoriteGroupLayout.setVisibility(View.GONE);
		m_groupMemberLayout.setVisibility(View.GONE);
		m_groupActivityLayout.setVisibility(View.GONE);

		m_btnBack.setOnClickListener(this);
		m_grouptTitleLayout.setOnClickListener(this);
		m_settingParticipantLayout.setOnClickListener(this);
		m_endActivityLayout.setOnClickListener(this);
		m_tvNotifySetting.setOnClickListener(this);

		notifySettingItems = new CharSequence[] { getActivity().getResources().getText(R.string.label_notify_all),
				getActivity().getResources().getText(R.string.label_notify_req_only),
				getActivity().getResources().getText(R.string.label_notify_none) };
		return v;
	}

	@Override
	public void onResume() {
		ViewModel.Log("SqSetFrg blue 해제", "bluetooth");
		super.onResume();
		SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(), GroupInfoVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				if (getVO() != null) {
					m_groupInfoData = getVO();
					m_tvGroupTitle.setText(getVO().title);
					m_tvSettingParticipantCount.setText(String.valueOf(getVO().squareMemberList.size()));
					m_cbFavoriteGroup.setOnCheckedChangeListener(null);
					m_cbFavoriteGroup.setChecked(getVO().favoriteFlag);
					m_cbFavoriteGroup.setOnCheckedChangeListener(SquareSettingFragment.this);
					// 권한을 체크하여 관리자 관련 UI를 수정한다.
					for (SquareMemberVO member : getVO().squareMemberList) {
						if (TextUtils.equals(HMGWServiceHelper.userId, member.memberId)) {
							setMemberRight(member.memberRights);
							switch (Integer.valueOf(member.notifyAttr)) {
							case SQ_MEMBER_NOTIFY_NOTHING:
								m_tvNotifySetting.setText(notifySettingItems[2]);
								break;
							case SQ_MEMBER_NOTIFY_ALL:
								m_tvNotifySetting.setText(notifySettingItems[0]);
								break;
							case SQ_MEMBER_NOTIFY_MENTION_ONLY:
								m_tvNotifySetting.setText(notifySettingItems[1]);
								break;
							}
							break;
						}
					}
					m_cbFavoriteGroup.setOnCheckedChangeListener(SquareSettingFragment.this);
					if (getVO().status == Status.COMPLETE) {
						m_tvActivityLabel.setText(R.string.label_square_setting_restart_activity_label);
						m_tvActivityDescription.setText(R.string.label_square_setting_restart_activity);
						// 그룹이 종료되면 즐겨찾기도 막는다.
						m_favoriteGroupLayout.setVisibility(View.GONE);

					} else {
						m_tvActivityLabel.setText(R.string.label_square_setting_end_activity_label);
						m_tvActivityDescription.setText(R.string.label_square_setting_end_activity);
						m_favoriteGroupLayout.setVisibility(View.VISIBLE);
					}

				}
			}
		};
		callback.progress(PopupUtil.getProgressDialog(getActivity()));
		Map<String, String> params = new HashMap<String, String>();
		params.put("squareId", m_strSquareId);
		new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(), callback, params).execute();
	}

	@Override
	public void onPause() {
		m_cbFavoriteGroup.setOnCheckedChangeListener(null);
		super.onPause();
	}

	protected void setMemberRight(MemberRight memberRights) {
		switch (memberRights) {
		case ADMIN_USER:
			m_groupMemberLayout.setVisibility(View.VISIBLE);
			m_groupActivityLayout.setVisibility(View.VISIBLE);
			break;
		case NORMAL_USER:
			m_groupMemberLayout.setVisibility(View.GONE);
			m_groupActivityLayout.setVisibility(View.GONE);
			break;
		case READ_ONLY_USER:
			m_groupMemberLayout.setVisibility(View.GONE);
			m_groupActivityLayout.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_LAY_L_GROUP_TITLE:
			MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_GROUP_INFO,
					new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId).build());
			break;
		case R.id.ID_LAY_L_SETTING_PARTICIPANT:
			MainModel.getInstance().showSubActivity(getActivity(),SubActivityType.MEMBER_SELECT,
					new BundleUtils.Builder()
					.add(MemberSelectFragment.ARG_KEY_MEMBER_SELECT_TYPE, MemberSelectType.MODIFY)
							.add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId)
							.add(MemberSelectFragment.ARG_KEY_SELECTED_ITEMS, m_groupInfoData.squareMemberList)
							.build());
			break;
		case R.id.ID_LAY_L_END_ACTIVITY:
			if (m_groupInfoData.status == Status.IN_PROGRESS) {
				PopupUtil.showOkCancelDialog(getActivity(), R.string.square_group_delete_message, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new ApiLoader(new DeleteSquare(), getActivity(), new SquareDefaultCallback() {
							@Override
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								onResume();
							}
						}, m_strSquareId).execute();
					}
				});
			} else if (m_groupInfoData.status == Status.COMPLETE) {
				PopupUtil.showOkCancelDialog(getActivity(), R.string.square_group_restart_message, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new ApiLoader(new ReopenSquare(), getActivity(), new SquareDefaultCallback() {
							@Override
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								onResume();
							}
						}, m_strSquareId).execute();
					}
				});
			}
			break;
		case R.id.ID_TV_NOTIFY_SETTING:
			new AlertDialog.Builder(getActivity()).setItems(notifySettingItems, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, final int which) {
					String nofityAttr = "0";
					switch (which) {
					case 0:
						nofityAttr = String.valueOf(SQ_MEMBER_NOTIFY_ALL);
						break;
					case 1:
						nofityAttr = String.valueOf(SQ_MEMBER_NOTIFY_MENTION_ONLY);
						break;
					case 2:
						nofityAttr = String.valueOf(SQ_MEMBER_NOTIFY_NOTHING);
						break;
					}
					SquareOpenApiEx api = new SquareOpenApiEx(getActivity()) {

						@Override
						protected String getOpenApiPath() {
							return "/square/setSquareMemberNotifyAttr.do";
						}

						@Override
						public void load(Context context, AjaxCallback<JSONObject> callBack,
								Map<String, String> params) {
							super.load(context, callBack, params);
							getAq().ajax(getUrl(), params, JSONObject.class, callBack);
						}

						@Override
						public DataType getDataType() {
							return DataType.JSON;
						}
					};
					Map<String, String> params = new HashMap<String, String>();
					params.put("squareId", m_strSquareId);
					params.put("notifyAttr", nofityAttr);
					new ApiLoaderEx<JSONObject>(api, getActivity(), new AjaxCallback<JSONObject>() {
						@Override
						public void callback(String url, JSONObject object, AjaxStatus status) {
							super.callback(url, object, status);
							m_tvNotifySetting.setText(notifySettingItems[which]);
						}
					}, params).execute();
				}
			}).show();
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
		new ApiLoader(new AddFavoriteGroup(), getActivity(), new SquareDefaultCallback() {
			@Override
			public void onResponse(String strRet) {
				super.onResponse(strRet);
				if (isChecked) {
					PopupUtil.showDialog(getActivity(), R.string.square_group_favorite_add_success);
				}
			}
		}, m_strSquareId, isChecked ? "true" : "false").execute();
	}

	@Override
	public void onPushReceive(SquarePushVO vo) {
		switch (vo.square_action) {
		// 그룹에 뭔가 수정사항이 생겼을 경우
		case GROUP_MODIFY:
		case SQUARE_MEMBER_CHANGE:
		case SQURE_MEMBER_ADMIN_CHANGE:
			onResume();
			break;
		case GROUP_DELETE:
			// 속해 있던 그룹이 삭제 된 경우
			new AlertDialog.Builder(getActivity()).setTitle(R.string.message_confirm_title)
					.setMessage("현재 그룹이 삭제되었습니다. 스퀘어그룹 화면으로 되돌아 갑니다.")
					.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							getActivity().setResult(Activity.RESULT_CANCELED);
							getActivity().finish();
						}
					}).setCancelable(false).show();
			break;
		}
	}
}
