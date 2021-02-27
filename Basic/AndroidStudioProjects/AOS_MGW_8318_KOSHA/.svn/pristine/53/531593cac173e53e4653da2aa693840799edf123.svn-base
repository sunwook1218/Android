package com.hs.mobile.gw.fragment.squareplus;

import java.util.HashMap;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.squareplus.SpMemberSelectFragment.SpMemberSelectType;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteSquare;
import com.hs.mobile.gw.openapi.squareplus.SpEndSquare;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquare;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMemberNotifyAttr;
import com.hs.mobile.gw.openapi.squareplus.SpReopenSquare;
import com.hs.mobile.gw.openapi.squareplus.SpSetSquareMemberNotifyAttr;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpEndSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpReopenSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpUserSettingsCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.openapi.squareplus.vo.SpUserSettingsVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.PopupUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
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

public class SpSettingFragment extends CommonFragment
		implements OnClickListener, OnCheckedChangeListener {

	private LinearLayout m_grouptTitleLayout;
	private CheckBox m_cbFavoriteGroup;
	private LinearLayout m_settingParticipantLayout;
	private TextView m_tvSettingParticipantCount;
	private LinearLayout m_endActivityLayout;
	private ImageView m_btnBack;
	private String m_strSquareId;
	private TextView m_tvGroupTitle;
	private SpSquareVO m_squareData;
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
	private boolean[] notifySettingCheckedItems;
	private boolean[] notifySettingUserCheckedItems;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
		} else {
			getActivity().finish();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_squareplus_setting, container, false);
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

		notifySettingItems = new CharSequence[] {
			getActivity().getResources().getText(R.string.label_squareplus_notify_newnotice_only),
			getActivity().getResources().getText(R.string.label_squareplus_notify_newcontents_only),
			getActivity().getResources().getText(R.string.label_squareplus_notify_newmention_only),
			getActivity().getResources().getText(R.string.label_squareplus_notify_newcomment_only)
		};
		notifySettingCheckedItems = new boolean[4];

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		
		SpSquareCallBack spGetSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);

				if (getVO() != null) {
					m_squareData = this.item;
					m_tvGroupTitle.setText(this.item.getTitle());
					m_tvSettingParticipantCount.setText(String.valueOf(this.item.getSquareMemberList().size()));
					m_cbFavoriteGroup.setOnCheckedChangeListener(null);
					m_cbFavoriteGroup.setChecked(this.item.isFavorite());
					m_cbFavoriteGroup.setOnCheckedChangeListener(SpSettingFragment.this);
					// 권한을 체크하여 관리자 관련 UI를 수정한다.
					for (final SpSquareMemberVO member : this.item.getSquareMemberList()) {
						if (TextUtils.equals(HMGWServiceHelper.userId, member.getMemberId())) {
							setMemberRight(member.getMemberRightsEnum());		// 멤버 권한 상태 따른 UI 결정
							SpUserSettingsCallBack spUserSettingsCallBack = new SpUserSettingsCallBack(getActivity(), SpUserSettingsVO.class){
								@Override
								public void callback(String url, JSONObject json, AjaxStatus status) {
									super.callback(url, json, status);
									CharSequence text = "";

									if (this.item.isNotifyNotice()) {
										text = text + ", " + notifySettingItems[0];
										notifySettingCheckedItems[0] = true;
									} else {
										notifySettingCheckedItems[0] = false;
									}

									if (this.item.isNotifyContents()) {
										text = text + ", " + notifySettingItems[1];
										notifySettingCheckedItems[1] = true;
									} else {
										notifySettingCheckedItems[1] = false;
									}

									if (this.item.isNotifyMention()) {
										text = text + ", " + notifySettingItems[2];
										notifySettingCheckedItems[2] = true;
									} else {
										notifySettingCheckedItems[2] = false;
									}

									if (this.item.isNotifyComment()) {
										text = text + ", " + notifySettingItems[3];
										notifySettingCheckedItems[3] = true;
									} else {
										notifySettingCheckedItems[3] = false;
									}

									if ("".equals(text)) {
										m_tvNotifySetting.setText(getString(R.string.squareplus_notify_not_receive));
									} else {
										m_tvNotifySetting.setText(text.toString().replaceFirst(", ", ""));
									}
								}
							};

							{ // API명
								HashMap<String,String> params = new HashMap<>();
								params.put("squareId", m_strSquareId);
								new ApiLoaderEx<>(new SpGetSquareMemberNotifyAttr(getActivity()), getActivity(), spUserSettingsCallBack, params).execute();
							}

							break;
						}
					}
					m_cbFavoriteGroup.setOnCheckedChangeListener(SpSettingFragment.this);
					if (this.item.getStatusEnum() == Status.END) {
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

		spGetSquareCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

		{ // API명
			HashMap<String,String> params = new HashMap<>();
			params.put("squareId", m_strSquareId);
			new ApiLoaderEx<>(new SpGetSquare(getActivity()), getActivity(), spGetSquareCallBack, params).execute();
		}
	}

	@Override
	public void onPause() {
		m_cbFavoriteGroup.setOnCheckedChangeListener(null);
		super.onPause();
	}

	protected void setMemberRight(MemberRights memberRights) {
		switch (memberRights) {
		case ADMIN_USER:
			m_groupMemberLayout.setVisibility(View.VISIBLE);
			m_groupActivityLayout.setVisibility(View.VISIBLE);
			break;
		case NORMAL_USER:
			m_groupMemberLayout.setVisibility(View.GONE);
			m_groupActivityLayout.setVisibility(View.GONE);
			break;
		case OBSERVER_USER:
			m_groupMemberLayout.setVisibility(View.GONE);
			m_groupActivityLayout.setVisibility(View.GONE);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			if (getFragmentManager().getBackStackEntryCount() > 0) {
				getFragmentManager().popBackStack();
			} else {
				getActivity().finish();
			}
			break;
		case R.id.ID_LAY_L_GROUP_TITLE: {
			Bundle bundle = new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId).build();
			if (MainModel.getInstance().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpSquareInfoFragment(), bundle, R.id.ID_LAY_L_FRAGMENT_LAYOUT,
						true, null);
			} else {
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_SQUARE_INFO, bundle);
			}
		}
			break;
		case R.id.ID_LAY_L_SETTING_PARTICIPANT: {
			Bundle bundle = new BundleUtils.Builder().add(SpMemberSelectFragment.ARG_KEY_MEMBER_SELECT_TYPE, SpMemberSelectType.MODIFY)
					.add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId)
					// .add(SpMemberSelectFragment.ARG_KEY_SELECTED_ITEMS, m_squareData.getSquareMemberArrayList())
					.build();
			if (MainModel.getInstance().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpMemberSelectFragment(), bundle,
						R.id.ID_LAY_L_FRAGMENT_LAYOUT, true, null);
			} else {
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SP_MEMBER_SELECT, bundle);
			}
		}
			break;
		case R.id.ID_LAY_L_END_ACTIVITY:
			if (m_squareData.getStatusEnum() == Status.IN_PROGRESS) {
				PopupUtil.showOkCancelDialog(getActivity(), R.string.square_group_delete_message, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SpEndSquareCallBack spEndSquareCallBack = new SpEndSquareCallBack(getActivity(), SpSquareVO.class){
							@Override
							public void callback(String url, JSONObject json, AjaxStatus status) {
								super.callback(url, json, status);
								onResume();
							}
						};

						{ // API명
							HashMap<String,String> params = new HashMap<>();
							params.put("squareId", m_strSquareId);
							new ApiLoaderEx<>(new SpEndSquare(getActivity()), getActivity(), spEndSquareCallBack, params).execute();
						}
					}
				});
			} else if (m_squareData.getStatusEnum() == Status.END) {
				PopupUtil.showOkCancelDialog(getActivity(), R.string.square_group_restart_message, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SpReopenSquareCallBack spReopenSquareCallBack = new SpReopenSquareCallBack(getActivity(), SpSquareVO.class){
							@Override
							public void callback(String url, JSONObject json, AjaxStatus status) {
								super.callback(url, json, status);
								onResume();
							}
						};

						{ // API명
							HashMap<String,String> params = new HashMap<>();
							params.put("squareId", m_strSquareId);
							new ApiLoaderEx<>(new SpReopenSquare(getActivity()), getActivity(), spReopenSquareCallBack, params).execute();
						}
					}
				});
			}
			break;
		case R.id.ID_TV_NOTIFY_SETTING:
			this.notifySettingUserCheckedItems = this.notifySettingCheckedItems.clone();		// 사용자가 선택한 정보 초기화
			Builder builder = new AlertDialog.Builder(getActivity()).setTitle(R.string.label_squareplus_notify_all);
			builder.setCancelable(false);
			builder.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					SpUserSettingsCallBack spUserSettingsCallBack = new SpUserSettingsCallBack(getActivity(), SpUserSettingsVO.class){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);

							CharSequence text = "";
							if (notifySettingUserCheckedItems[0]) {
								text = text + ", " + notifySettingItems[0];
								notifySettingCheckedItems[0] = true;
							} else {
								notifySettingCheckedItems[0] = false;
							}

							if (notifySettingUserCheckedItems[1]) {
								text = text + ", " + notifySettingItems[1];
								notifySettingCheckedItems[1] = true;
							} else {
								notifySettingCheckedItems[1] = false;
							}

							if (notifySettingUserCheckedItems[2]) {
								text = text + ", " + notifySettingItems[2];
								notifySettingCheckedItems[2] = true;
							} else {
								notifySettingCheckedItems[2] = false;
							}

							if (notifySettingUserCheckedItems[3]) {
								text = text + ", " + notifySettingItems[3];
								notifySettingCheckedItems[3] = true;
							} else {
								notifySettingCheckedItems[3] = false;
							}

							if ("".equals(text)) {
								m_tvNotifySetting.setText(getString(R.string.squareplus_notify_not_receive));
							} else {
								m_tvNotifySetting.setText(text.toString().replaceFirst(", ", ""));
							}

							notifySettingCheckedItems = notifySettingUserCheckedItems;
						}
					};

					spUserSettingsCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

					{ // API명
						HashMap<String,String> params = new HashMap<>();
						params.put("squareId", m_strSquareId);
						params.put("notifyNoticeFlag", notifySettingUserCheckedItems[0] ? SpSquareConst.TRUE_CH : SpSquareConst.FALSE_CH);
						params.put("notifyContentsFlag", notifySettingUserCheckedItems[1] ? SpSquareConst.TRUE_CH : SpSquareConst.FALSE_CH);
						params.put("notifyMentionFlag", notifySettingUserCheckedItems[2] ? SpSquareConst.TRUE_CH : SpSquareConst.FALSE_CH);
						params.put("notifyCommentFlag", notifySettingUserCheckedItems[3] ? SpSquareConst.TRUE_CH : SpSquareConst.FALSE_CH);
						new ApiLoaderEx<>(new SpSetSquareMemberNotifyAttr(getActivity()), getActivity(), spUserSettingsCallBack, params).execute();
					}
				}
			});
			builder.setNegativeButton(R.string.label_cancel, null);
			builder.setMultiChoiceItems(notifySettingItems, notifySettingUserCheckedItems, new OnMultiChoiceClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					notifySettingUserCheckedItems[which] = isChecked;
				}
			});
			builder.show();
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
		SpSquareCallBack spSquareCallBack = new SpSquareCallBack(getActivity(), SpSquareVO.class){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);

				if (isChecked) {
					PopupUtil.showDialog(getActivity(), R.string.square_group_favorite_add_success);
				}
			}
		};

		{ // API명
			HashMap<String,String> params = new HashMap<>();
			params.put("squareId", m_strSquareId);
			params.put("favoriteFlag", isChecked ? "true" : "false");		// 1은 활성화
			new ApiLoaderEx<>(new SpAddFavoriteSquare(getActivity()), getActivity(), spSquareCallBack, params).execute();
		}
	}
}
