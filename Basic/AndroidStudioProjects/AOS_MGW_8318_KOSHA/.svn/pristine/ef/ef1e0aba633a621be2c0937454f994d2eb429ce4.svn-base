package com.hs.mobile.gw.fragment.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.crypto.Crypto;
import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.Define.LoginType;
import com.hs.mobile.gw.MainActivity;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.Views;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.otp.OtpActivity;
import com.hs.mobile.gw.ext.vpn.VPNConnectHelper;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.login.LoginModel.IAutologinBackgroundVisiblityListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.IconInfo;
import com.hs.mobile.gw.openapi.IconInfoCallBack;
import com.hs.mobile.gw.openapi.MainBbsMenuInfo;
import com.hs.mobile.gw.openapi.MainBbsMenuInfoCallBack;
import com.hs.mobile.gw.openapi.MenuInfo;
import com.hs.mobile.gw.openapi.MenuInfoCallBack;
import com.hs.mobile.gw.openapi.rest.auth.Login;
import com.hs.mobile.gw.openapi.rest.auth.LoginCallBack;
import com.hs.mobile.gw.openapi.vo.AdditionalOfficerItemVO;
import com.hs.mobile.gw.openapi.vo.AuthListVO;
import com.hs.mobile.gw.openapi.vo.IconInfoItemVO;
import com.hs.mobile.gw.openapi.vo.LoginVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.sqlite.CustomIcon;
import com.hs.mobile.gw.sqlite.CustomIconDBHelper;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.IntentUtils;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LoginController extends CommonFragmentController<LoginFragment, LoginModel> implements OnClickListener, OnCheckedChangeListener,
		OnTouchListener, TextWatcher, OnFocusChangeListener, IAutologinBackgroundVisiblityListener {
	private AlertDialog m_overlappingDlg;
	private AlertDialog additionalofficerDlg;

	public LoginController(LoginFragment view, LoginModel model) {
		super(view, model);
	}

	public void doLogIn(final String id, String pw) {
		boolean useCrypto = HMGWServiceHelper.USE_CRYPTO;
		Map<String, String> params = new HashMap<String, String>();
		String param_id = id;
		String param_passwd = pw;
		String param_phone_uid = HMGWServiceHelper.deviceId;
		String param_token_Id = HMGWServiceHelper.tokenId;
		String param_phone_number = HMGWServiceHelper.phoneNumber;
		String param_phone_os_type = HMGWServiceHelper.phoneOSType;
		String param_phone_os_version = HMGWServiceHelper.phoneOSVersion;
		String param_appVersion = MainModel.getInstance().getVersionName(getView().getActivity());
		String param_use_sso = Define.USE_SSO ? "true" : "false";
		// 겸직자 로그인 파라미터
		if (HMGWServiceHelper.hasAdditionalOfficer) {
			String param_userId = HMGWServiceHelper.userId;
			String param_selectUserId = HMGWServiceHelper.selectUserId;
			if (useCrypto) {
				param_userId = Crypto.getInstance().encrypt(param_userId);
				param_selectUserId = Crypto.getInstance().encrypt(param_selectUserId);
			}
			params.put("userid", param_userId);
			params.put("selectuserid", param_selectUserId);
			params.put("phone_uid", param_phone_uid);
			HMGWServiceHelper.selectUserId = null;
			HMGWServiceHelper.userId = null;
		}
		// Login Parameter 암호화
		if (useCrypto) {
			param_id = Crypto.getInstance().encrypt(param_id);
			param_passwd = Crypto.getInstance().encrypt(param_passwd);
			param_phone_uid = Crypto.getInstance().encrypt(param_phone_uid);
			param_phone_number = Crypto.getInstance().encrypt(param_phone_number);
			param_phone_os_type = Crypto.getInstance().encrypt(param_phone_os_type);
			param_phone_os_version = Crypto.getInstance().encrypt(param_phone_os_version);
			param_appVersion = Crypto.getInstance().encrypt(param_appVersion);
			params.put("crypto", "true");
		} else {
			param_token_Id = Crypto.getInstance().decrypt(param_token_Id);
		}

		if(HMGWServiceHelper.LOGIN_TYPE == LoginType.EMAIL){
			params.put("email", param_id);
		}else{
			if (getView().mCbEmpLogin.isChecked()) {
				params.put("empcode", param_id);
			} else {
				params.put("name", param_id);
			}
		}
		params.put("use_sso", param_use_sso);
		params.put("passwd", param_passwd);
		params.put("phone_uid", param_phone_uid);
		params.put("token_id", param_token_Id);
		params.put("phone_number", param_phone_number);
		params.put("phone_os_type", param_phone_os_type);
		params.put("phone_os_version", param_phone_os_version);
		params.put("appversion", param_appVersion);
		LoginCallBack callBack = new LoginCallBack() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				ViewModel.Log(""+status.getCode(), "tkofs");
				super.callback(url, json, status);
				getModel().setAutologinBackground(false);

				ViewModel.Log(""+status.getCode(), "tkofs");

				if(getVO() == null){
					PopupUtil.showToastMessage(getActivity(), R.string.error_connect_timeout);
					return;
				}
				if (!getVO().skipOtherOffice && !HMGWServiceHelper.hasAdditionalOfficer && getVO().isadditionalofficer) {
					checkAdditionalOfficer(getVO());
				} else if (getVO().code == null) {
					HMGWServiceHelper.hasAdditionalOfficer = false;
					HMGWServiceHelper.isGuest = false;
					if (getVO().additionalofficer != null) {
						HMGWServiceHelper.hasAdditionalOfficer = true;
						HMGWServiceHelper.additionalusers = getVO().additionalofficer.getJson();
					}
					HMGWServiceHelper.userId = getVO().id;
					HMGWServiceHelper.mail = getVO().mail;
					HMGWServiceHelper.empcode = getVO().empcode;
					HMGWServiceHelper.deptId = getVO().deptid;
					HMGWServiceHelper.key = getVO().key;
					HMGWServiceHelper.name = getVO().name;
					HMGWServiceHelper.uname = getVO().uname;
					HMGWServiceHelper.deptName = getVO().deptname;
					HMGWServiceHelper.start_menu_id = getVO().start_menu_id;
					HMGWServiceHelper.start_menu_title = getVO().start_menu_title;
					HMGWServiceHelper.mail_search_use = getVO().mail_search_use;
					HMGWServiceHelper.noti_mps_badge = getVO().noti_mps_badge;
					HMGWServiceHelper.start_menu_custom_id = getVO().start_menu_custom_id;
					HMGWServiceHelper.password = getView().mEdPassword.getText().toString();
					HMGWServiceHelper.photoLink = getVO().photo;
					HMGWServiceHelper.start_menu_url = getVO().start_menu_url;
					HMGWServiceHelper.doc_transform_server_use = "Y".equals(getVO().doc_transform_server_use) ? true : false;
					HMGWServiceHelper.list_escape_title_use = "Y".equals(getVO().list_escape_title_use) ? true : false;
					HMGWServiceHelper.appr_approve_comment_intodocument = "Y".equals(getVO().appr_approve_comment_intodocument.trim());
					if (getVO().config != null) {
						HMGWServiceHelper.config = getVO().config.getJson();
						HMGWServiceHelper.notice_bid = getVO().config.board_notice_bid;
						HMGWServiceHelper.gw_version = getVO().config.gw_version;
						HMGWServiceHelper.mail_type = getVO().config.mail_type;
						HMGWServiceHelper.schedule_type = getVO().config.schedule_type;
						HMGWServiceHelper.sign_doctype = getVO().config.sign_doctype;
						HMGWServiceHelper.contact_type = getVO().config.contact_type;
						HMGWServiceHelper.settings_type = getVO().config.settings_type;
						HMGWServiceHelper.mail_directinputuser_use = getVO().config.mail_directinputuser_use;
						HMGWServiceHelper.mail_replyall_use = getVO().config.mail_replyall_use;
						HMGWServiceHelper.bbs_dm_onlybody_use = getVO().config.bbs_dm_onlybody_use;
						HMGWServiceHelper.appr_linkdoc_use = getVO().config.appr_linkdoc_use;
						HMGWServiceHelper.appr_approve_input_comment_use = getVO().config.appr_approve_openapi_use;
						HMGWServiceHelper.appr_approve_openapi_use = getVO().config.appr_approve_openapi_use;
						if (getVO().config.commmon_count_pooling_interval > 0) {
							HMGWServiceHelper.count_pooling_interval = getVO().config.commmon_count_pooling_interval;
						}
					}
					if (getVO().appr_approval_enablereceiptdoc) {
						HMGWServiceHelper.appr_auth = true;
					}
					else {
						if (getVO().authList != null && getVO().authList.size() > 0) {
							HMGWServiceHelper.appr_auth = false;
							for(AuthListVO vo : getVO().authList) {  // 접수대기 메뉴 권한 설정 -권한이 있어야 결재대기 메뉴 보임
								if ("D1".equals(vo.auth) || "D6".equals(vo.auth)) {
									HMGWServiceHelper.appr_auth = true;
									break;
								}
							}
						}
						else {
							Debug.trace("authList == null");
							HMGWServiceHelper.appr_auth = false;
						}
					}
					//Login 후 id,pw 저장함 
					getModel().getMainModel().prefEdit.putString(getView().getString(R.string.key_user_information_name), Crypto.getInstance()
							.encrypt(getView().mEdUserID.getText().toString()));
						getModel().getMainModel().prefEdit.putString(getView().getString(R.string.key_user_information_password), Crypto
								.getInstance().encrypt(getView().mEdPassword.getText().toString()));
					getModel().getMainModel().prefEdit.commit();
					
					// 중복 에러메세지 방지용 모바일서버통신방식이 2가지 존재 ajax 이용 task 방식
					HMGWServiceHelper.forceLogoutFlag = true;	// tkofs 로그인시 에러창에대한 상태를 초기화(ajax)
					HMGWServiceHelper.LogoutFlag = true;	// tkofs 로그인시 에러창에대한 상태를 초기화(task)

					/**
					 * otp 새로운 라이브러리에 따라 변경 필요. tkofs
					 */
					if(ViewModel.apiName.OTP.isApiPass()){
						loadDynamicMenu();
					}else{
						Intent intent = new Intent(getActivity(), OtpActivity.class);
						//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("isOtpSave", getMainModel().dataSet.isOtpSave());
						getActivity().startActivityForResult(intent, ViewModel.OTP_REQUEST_CODE);
					}

				} else {
					switch (getVO().code) {
					case APPVERSION_NOTEQUAL_ERROR:
						String installPageUrl = getVO().message;
						IntentUtils.webBrowser(getView().getActivity(), installPageUrl);
						PopupUtil.showToastMessage(getView().getActivity(), getVO().code.getErrorRes());
						break;
					case HOMONYM_EXIST_ERROR:
						if (m_overlappingDlg == null) {
							AlertDialog.Builder builder = new AlertDialog.Builder(getView().getActivity());
							builder.setTitle(R.string.overlapping_account_title);
							int userCnt = getVO().homonym.size();
							final List<String> userNames = new ArrayList<String>();
							for (int i = 0; i < userCnt; i++) {
								userNames.add(getVO().homonym.get(i).uname);
							}
							builder.setItems(userNames.toArray(new String[userCnt]), new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int item) {
									getView().mEdUserID.setText(userNames.get(item));
									
									//id,pw 저장함 
									getModel().getMainModel().prefEdit.putString(getView().getString(R.string.key_user_information_name), Crypto.getInstance()
												.encrypt(getView().mEdUserID.getText().toString()));
									getModel().getMainModel().prefEdit.putString(getView().getString(R.string.key_user_information_password), Crypto
												.getInstance().encrypt(getView().mEdPassword.getText().toString()));
									getModel().getMainModel().prefEdit.commit();
									
									doLogIn(getView().mEdUserID.getText().toString(), getView().mEdPassword.getText().toString());
								}
							});
							builder.setCancelable(false);
							m_overlappingDlg = builder.create();
							m_overlappingDlg.show();
						} else {
							if (!m_overlappingDlg.isShowing()) {
								m_overlappingDlg.show();
							}
							Debug.trace("m_overlappingDlg");
						}
						break;
					case UNREGISTERED_NEW_MULTI_DEVICE:
						PopupUtil.showDialog(getView().getActivity(), R.string.error_unregistered_new_multi_device_guide,
								R.string.message_add_new_device, new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										String result = regDevice(getResult());
										if (result != null) {
											PopupUtil.showDialog(getView().getActivity(), R.string.message_add_new_device_successful);
										} else {
											PopupUtil.showToastMessage(getView().getActivity(), R.string.error_regist_error);
										}
									}
								}, R.string.message_close, null);
						break;
					case ALREADYLOGIN_EXIST:
					case INVALID_PASSWORD_ERROR:
					case UNREGISTERED_USER_ERROR:
					case UNREGISTERED_IP_ERROR:
					case SYSTEM_ERROR:
					case ACCESS_DENIED:
					case ACCESS_DENIED_ERROR:
					case FILE_DOWNLOAD_ERROR:
					case FILE_TRANSFORM_ERROR:
					case FILE_UNSUPPORTED_TYPE_ERROR:
					case INVALID_REQUEST:
					case LICENSE_ERROR_EXCEEDUSER:
					case LICENSE_ERROR_EXPIRED:
					case LICENSE_ERROR_FILENOTFOUND:
					case LICENSE_ERROR_INVALIDINFO:
					case LICENSE_ERROR_SERVERIP:
					case MDM_APPLICATION_PERMISSTION_ERROR:
					case MDM_DUPLICATED_DEVICE_ERROR:
					case MDM_LICENSE_EXCEEDUSER_ERROR:
					case MDM_MULTIDEVICE_ERROR:
					case MDM_UNAPPROVED_DEVICE_ERROR:
					case MDM_UNEXPECTED_ERROR:
					case MDM_UNREGISTERED_APP_ERROR:
					case MDM_UNREGISTERED_USER_ERROR:
					case SESSION_EXPIRED:
					case UNAUTHORIZED_PHONE_UID_NUMBER:
					case SERVICE_EXPIRED:
					case PASSWORD_ERROR_EXCEED:
						PopupUtil.showToastMessage(getView().getActivity(), getVO().code.getErrorRes());
						break;
					default:
						Debug.trace(getVO().name);
						break;
					}
				}
			}
		};
		ProgressDialog progressDialog = new ProgressDialog(getView().getActivity());
		progressDialog.setTitle(R.string.login_dialog_title);
		progressDialog.setMessage(getView().getResources().getString(R.string.login_dialog_message));
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setCancelable(false);
		callBack.progress(progressDialog);
		new ApiLoaderEx<JSONObject>(new Login(getView().getActivity().getApplicationContext()), getView().getActivity().getApplicationContext(), callBack, params).execute();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			TextViewUtils.hideKeyBoard(getView().getActivity().getApplicationContext(), v);
			HMGWServiceHelper.resetLoginInfo();
			if (!AndroidUtils.isNetworkConnected(getView().getActivity().getApplicationContext())) {
				PopupUtil.showToastMessage(getView().getActivity(), R.string.message_not_connection);
			} else {
				String category = null;
				if (getView().getArguments() != null)
					category = getView().getArguments().getString(MainActivity.PUSH_MSG_CATEGORY);
			
				if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(getModel().getMainModel().dataSet.getUserName()) && !TextUtils.isEmpty(getModel().getMainModel().dataSet.getUserPassword()))
				{
					final String dec_id = Crypto.getInstance().decrypt(getModel().getMainModel().dataSet.getUserName());
					final String dec_pw = Crypto.getInstance().decrypt(getModel().getMainModel().dataSet.getUserPassword());

					if(ViewModel.apiName.VPN.isApiPass()) {
						doLogIn(dec_id.trim(), dec_pw.trim());
					} else {
						VPNConnectHelper vpnHelper = new VPNConnectHelper(getView().getContext(),
								getView().getActivity(),
								getView().mEdUserID.getText().toString(),
								getView().mEdPassword.getText().toString(),
								this);

						vpnHelper.init();

					}

				}
				else if (TextUtils.isEmpty(getView().mEdUserID.getText().toString().trim())) {
					PopupUtil.showToastMessage(getView().getActivity(), R.string.message_insert_username);
				} else if (TextUtils.isEmpty(getView().mEdPassword.getText().toString())) {
					PopupUtil.showToastMessage(getView().getActivity(), R.string.message_insert_password);
				} else {
					if(ViewModel.apiName.VPN.isApiPass()) {
						doLogIn( getView().mEdUserID.getText().toString(), getView().mEdPassword.getText().toString());
					} else {

						VPNConnectHelper vpnHelper = new VPNConnectHelper(getView().mContext,
								getView().getActivity(),
								getView().mEdUserID.getText().toString(),
								getView().mEdPassword.getText().toString(),
								this);

						vpnHelper.init();
					}
				}
			}
			break;
		case R.id.password:
			Debug.trace("onClick password");
			break;
		}
	}

	private String regDevice(JSONObject jObj) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		try {
			JSONObject userObj = jObj.getJSONObject("userDevice");
			String userid = userObj.optString("userId");
			String name = userObj.optString("name");
			String phone_uid = userObj.optString("phoneUid");
			String phone_os_type = userObj.optString("phoneOsType");
			String phone_os_version = userObj.optString("phoneOsVersion");
			String token_id = userObj.optString("tokenId");

			formparams.add(new BasicNameValuePair("userid", userid));
			formparams.add(new BasicNameValuePair("name", name));
			formparams.add(new BasicNameValuePair("phone_uid", phone_uid));
			formparams.add(new BasicNameValuePair("phone_os_type", phone_os_type));
			formparams.add(new BasicNameValuePair("phone_os_version", phone_os_version));
			formparams.add(new BasicNameValuePair("token_id", token_id));

		} catch (JSONException e) {
			Debug.trace(e.getMessage());
			return null;
		}
		if(MainModel.getInstance().openApiService==null) return null;
		return MainModel.getInstance().openApiService.registMultiDevice(formparams);
	}

	IconInfoCallBack iconCallBack = new IconInfoCallBack() {
		@Override
		public void callback(String url, JSONArray json, AjaxStatus status) {
			super.callback(url, json, status);
			if (status.getCode() == 200) {
				if (json != null) {
					CustomIconDBHelper db = CustomIconDBHelper.getDBHelper(getView().getActivity());
					for (IconInfoItemVO item : getVO().icons) {
						CustomIcon savedIcon = db.getIcon(item.name);
						if (savedIcon == null || item.version > savedIcon.getVersion()) {
							// download image from server.
							InputStream is = MainModel.getInstance().openApiService.getIcon(item.name);
							Bitmap iconBitmap = BitmapFactory.decodeStream(is);
							if (iconBitmap == null) {
								String message = getView().getString(R.string.error_menu_icon_not_exist);
								PopupUtil.showToastMessage(getView().getActivity(), String.format(message, item.name));
							} else {
								ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
								iconBitmap.compress(CompressFormat.PNG, 0, outputStream);
								byte[] iconData = outputStream.toByteArray();
								CustomIcon newIcon = new CustomIcon(item.name, item.version, iconData);
								if (savedIcon == null) {
									db.addImage(newIcon);
								} else {
									db.updateIcon(newIcon);
								}
							}
						}
					}
				}
			}
			
			String category = null;
			String event = null;
			if (getArguments() != null) {
				category = getArguments().getString(MainActivity.PUSH_MSG_CATEGORY);
				event = getArguments().getString(MainActivity.PUSH_MSG_EVENT);
			}
					
			MainModel.getInstance().showView(getView().getActivity().getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.MAIN, category != null ?
					new BundleUtils.Builder().add(MainActivity.PUSH_MSG_CATEGORY, category).add(MainActivity.PUSH_MSG_EVENT, event).build() : null);
			getModel().setAutologinBackground(false);
		}
	};

	// 동적 메뉴 로딩
	public void loadDynamicMenu() {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("locale", Locale.getDefault().getLanguage());
		// home 게시판 메뉴 가져옴
		new ApiLoaderEx<JSONArray>(new MainBbsMenuInfo(getView().getActivity().getApplicationContext()), getView().getActivity().getApplicationContext(),
				new MainBbsMenuInfoCallBack() {

					@Override
					public void callback(String url, JSONArray json, AjaxStatus status) {
						super.callback(url, json, status);

						if (getVO() != null) {
							HMGWServiceHelper.mgw_home_bbs_menu = getVO().mainBbsInfo.get(0).hidden_menu_list.getJson();
						} else {
							((MainActivity) getActivity()).exitApp(String.valueOf(R.string.error_menu_download_menuinfo));
						}
						HashMap<String, String> param = new HashMap<String, String>();
						param.put("locale", Locale.getDefault().getLanguage());
						new ApiLoaderEx<JSONArray>(new MenuInfo(getView().getActivity().getApplicationContext()), getView().getActivity()
								.getApplicationContext(), new MenuInfoCallBack() {
							@Override
							public void callback(String url, JSONArray json, AjaxStatus status) {
								super.callback(url, json, status);
								if (getVO() == null || getVO().menuInfo == null) {
									// PopupUtil.showDialog(getActivity(), R.string.error_menu_download_menuinfo);
									((MainActivity) getActivity()).exitApp(String.valueOf(R.string.error_menu_download_menuinfo));
								} else {
									HMGWServiceHelper.mgw_menu = getVO().getJson();
									ViewModel.Log("MenuInfo" + HMGWServiceHelper.mgw_menu.toString());
									new ApiLoaderEx<JSONArray>(new IconInfo(getView().getActivity().getApplicationContext()), getView().getActivity()
											.getApplicationContext(), iconCallBack, null).execute();
								}
							}
						}, param).execute();
					}
				}, param).execute();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.save_mode_checkbox:
			getModel().setSaveMode(isChecked);
			break;
		case R.id.login_mode_checkbox:
			getModel().setLoginMode(isChecked, getString(R.string.label_login_number), getString(R.string.label_login_user), getView().mTvLoginUser);
			break;
		case R.id.otp_save_mode_checkbox:	// tkofs otp save mode
			getModel().setOtpSaveMode(isChecked);
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case R.id.login_clear_btn:
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				getView().mBtnUserClear.setBackgroundDrawable(getDrawable(R.drawable.box_selected));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				getView().mEdUserID.getText().clear();
				getView().mBtnUserClear.setVisibility(View.INVISIBLE);
				getView().mEdUserID.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_login_id, 0, 0, 0);
				getView().mBtnSubmit.setBackgroundDrawable(getDrawable(R.drawable.template_btn_radius));
				getView().mBtnSubmit.setTextColor(Color.argb(0xff,0xa8,0xa8,0xa8));
				getView().mBtnSubmit.setEnabled(false);
			}
			break;
		case R.id.pwd_clear_btn:
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				getView().mBtnPwdClear.setBackgroundDrawable(getDrawable(R.drawable.box_selected));
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				getView().mEdPassword.getText().clear();
				getView().mBtnPwdClear.setVisibility(View.INVISIBLE);
				getView().mEdPassword.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_login_pw, 0, 0, 0);
				getView().mBtnSubmit.setBackgroundDrawable(getDrawable(R.drawable.template_btn_radius));
				getView().mBtnSubmit.setTextColor(Color.argb(0xff,0xa8,0xa8,0xa8));
				getView().mBtnSubmit.setEnabled(false);
			}
			break;
		}
		return false;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}
	// tkofs
	public boolean loginIdState = false;
	public boolean loginPwState = false;
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (getView().mEdUserID.getText().length() == 0) {
			getView().mBtnUserClear.setVisibility(View.INVISIBLE);
			getView().mEdUserID.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_login_id, 0, 0, 0);
			/*getView().mEdUserID.setBackgroundDrawable(getDrawable(R.drawable.template_input_radius));*/
			getView().mBtnSubmit.setBackgroundDrawable(getDrawable(R.drawable.template_btn_radius));
			getView().mBtnSubmit.setTextColor(Color.argb(0xff,0xa8,0xa8,0xa8));
			getView().mBtnSubmit.setEnabled(false);
			loginIdState=false;
		} else {
			getView().mBtnUserClear.setBackgroundDrawable(getDrawable(R.drawable.box_unselected));
			getView().mBtnUserClear.setVisibility(View.VISIBLE);
			/*getView().mEdUserID.setBackgroundDrawable(getDrawable(R.drawable.template_input_radius_on));*/
			getView().mEdUserID.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_login_id_on, 0, 0, 0);
			loginIdState=true;
		}
		if (getView().mEdPassword.getText().length() == 0) {
			getView().mBtnPwdClear.setVisibility(View.INVISIBLE);
			/*getView().mEdPassword.setBackgroundDrawable(getDrawable(R.drawable.template_input_radius));*/
			getView().mEdPassword.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_login_pw, 0, 0, 0);
			getView().mBtnSubmit.setBackgroundDrawable(getDrawable(R.drawable.template_btn_radius));
			getView().mBtnSubmit.setTextColor(Color.argb(0xff,0xa8,0xa8,0xa8));
			getView().mBtnSubmit.setEnabled(false);
			loginPwState=false;
		} else {
			getView().mBtnPwdClear.setBackgroundDrawable(getDrawable(R.drawable.box_unselected));
			getView().mBtnPwdClear.setVisibility(View.VISIBLE);
			/*getView().mEdPassword.setBackgroundDrawable(getDrawable(R.drawable.template_input_radius_on));*/
			getView().mEdPassword.setCompoundDrawablesWithIntrinsicBounds( R.drawable.icon_login_pw_on, 0, 0, 0);
			loginPwState=true;
		}
		if(loginIdState && loginPwState){
			getView().mBtnSubmit.setEnabled(true);
			getView().mBtnSubmit.setTextColor(getResources().getColor(R.color.white));
			getView().mBtnSubmit.setBackgroundDrawable(getDrawable(R.drawable.template_btn_radius_on));
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
	
	}


	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// tkofs 로그인화면 이미지 변경
		switch (v.getId()) {
		case R.id.userid:
			if (!hasFocus) {
				getView().mBtnUserClear.setVisibility(View.INVISIBLE);
			} else {
				if (getView().mEdUserID.getText().length() == 0) {
					getView().mBtnUserClear.setVisibility(View.INVISIBLE);
				} else {
					getView().mBtnUserClear.setVisibility(View.VISIBLE);
				}
			}
			break;
		case R.id.password:

			if (!hasFocus) {
				getView().mBtnPwdClear.setVisibility(View.INVISIBLE);
			} else {
				if (getView().mEdPassword.getText().length() == 0) {
					getView().mBtnPwdClear.setVisibility(View.INVISIBLE);
				} else {
					getView().mBtnPwdClear.setVisibility(View.VISIBLE);
				}
			}
			break;
		}
	}

	public void checkLoginType() {
		switch(HMGWServiceHelper.LOGIN_TYPE){
		case EMAIL:
		case EMP_CODE:
			getView().mCbEmpLogin.setVisibility(View.GONE);
			getModel().getMainModel().prefEdit.putBoolean(getString(R.string.key_nomal_login_mode), true);
			getModel().getMainModel().prefEdit.commit();
			break;
		case NAME:
			getView().mCbEmpLogin.setOnCheckedChangeListener(this);
			break;
		}
	}

	public void checkEmpMode() {
		switch(HMGWServiceHelper.LOGIN_TYPE){
		case EMAIL:
			getView().mTvLoginUser.setText(getString(R.string.label_login_email));
			break;
		case EMP_CODE:
			getView().mTvLoginUser.setText(getString(R.string.label_login_number));
			break;
		case NAME:
			if (getView().mCbEmpLogin.isChecked())
				getView().mTvLoginUser.setText(getString(R.string.label_login_number));
			else
				getView().mTvLoginUser.setText(getString(R.string.label_login_user));
			break;
		}
		if (getModel().getMainModel().dataSet.isEmpCodeMode()) {
			
		} else {
			
		}
	}

	public void checkAdditionalOfficer(LoginVO vo) {
		HMGWServiceHelper.hasAdditionalOfficer = true;
		if (vo.isguest)
			HMGWServiceHelper.isGuest = true;
		else
			HMGWServiceHelper.isGuest = false;
		if (additionalofficerDlg == null) {

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			TextView title = new TextView(getActivity());
			title.setText(R.string.additionalofficer_account_title);
			title.setPadding(10, 10, 10, 10);
			title.setTextSize(23);
			title.setGravity(Gravity.CENTER);
			builder.setCustomTitle(title);
			final List<String> userNames = new ArrayList<String>();
			final List<String> userEmpcodes = new ArrayList<String>();
			final List<String> userIds = new ArrayList<String>();

			userNames.add(vo.deptname + "." + vo.name);
			userEmpcodes.add(vo.empcode);
			userIds.add(vo.id);

			for (AdditionalOfficerItemVO additionalOfficerItemVO : vo.additionalofficer.additionalofficer) {
				userNames.add(additionalOfficerItemVO.username);
				userEmpcodes.add(additionalOfficerItemVO.empcode);
				userIds.add(additionalOfficerItemVO.id);
			}

			builder.setItems(userNames.toArray(new String[userNames.size()]), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int position) {

					if (getView().mCbEmpLogin.isChecked()) {
						getView().mEdUserID.setText(userEmpcodes.get(position));
					} else {
						getView().mEdUserID.setText(userNames.get(position));
					}

					HMGWServiceHelper.userId = userIds.get(position);
					HMGWServiceHelper.selectUserId = userIds.get(position);

					HMGWServiceHelper.hasAdditionalOfficer = true;
					doLogIn(getView().mEdUserID.getText().toString(), getView().mEdPassword.getText().toString());
				}
			});
			additionalofficerDlg = builder.create();
			additionalofficerDlg.show();
		} else {
			if (!additionalofficerDlg.isShowing()) {
				additionalofficerDlg.show();
			}
		}
	}

	@Override
	public void onVisiblelityChange(boolean b) {
		if (getView().autologinBackground != null) {
			getView().autologinBackground.setVisibility(b ? View.VISIBLE : View.GONE);
		}
	}


}
