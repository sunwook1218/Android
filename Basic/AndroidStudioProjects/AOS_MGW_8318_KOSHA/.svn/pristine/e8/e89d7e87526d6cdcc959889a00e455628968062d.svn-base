package com.hs.mobile.gw.fragment.login;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.crypto.Crypto;
import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainActivity;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.mis.MisHelper;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService;
import com.hs.mobile.gw.util.Debug;
import com.softsecurity.transkey.TransKeyCtrl;

public class LoginFragment extends CommonFragment {
	public static final String ARG_KEY_IS_LOGOUT = "isLogout";
	public Button mBtnSubmit;
	public TextView mTvLoginUser;
	public EditText mEdUserID;
	public EditText mEdPassword;
	public CheckBox mCbAutoLogin;
	public CheckBox mCbOtpAutoLogin;
	public CheckBox mCbEmpLogin;
	public ImageButton mBtnUserClear;
	public ImageButton mBtnPwdClear;
	public View autologinBackground;
	private LoginController m_controller;
	private LoginModel m_model;
	/*private ViewGroup mBGContainer;	로그인 페이지 변경으로 인한 수정 tkofs
	private ViewGroup mBGlogin_background;*/
	public Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OpenAPIService.isSessionExpired = false;
	}	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.mgw_login, container, false);
		mContext = container.getContext();
		initLogin(rootView);
		return rootView;
	}

	@Override
	public void onResume() {
		ViewModel.Log("LoginFrg blue 해제", "bluetooth");
		// tkofs otp 성공시
		if(HMGWServiceHelper.otp_cancle == 2 && !ViewModel.apiName.OTP.isApiPass()){
			HMGWServiceHelper.otp_cancle = 1;
			m_controller.loadDynamicMenu();
			//return;
		}
		super.onResume();
	}

	private void initLogin(View rootView) {
		// tkofs 로그인 페이지 수정으로 인한 수정.
		m_model = new LoginModel(this);
		m_controller = new LoginController(this, m_model);
		// mBGContainer = rootView.findViewById(R.id.bg_container);
		mTvLoginUser = (TextView) rootView.findViewById(R.id.label_loginuser);
		mEdUserID = (EditText) rootView.findViewById(R.id.userid);
		mEdPassword = (EditText) rootView.findViewById(R.id.password);
		mBtnSubmit = (Button) rootView.findViewById(R.id.submit);
		mCbAutoLogin = (CheckBox) rootView.findViewById(R.id.save_mode_checkbox);
		mCbOtpAutoLogin = (CheckBox) rootView.findViewById(R.id.otp_save_mode_checkbox);
		mCbAutoLogin.setVisibility(Define.USE_AUTO_LOGIN?View.VISIBLE:View.GONE);
		mCbOtpAutoLogin.setVisibility(ViewModel.USE_OTP_AUTO_LOGIN?View.VISIBLE:View.GONE);
		mCbEmpLogin = (CheckBox) rootView.findViewById(R.id.login_mode_checkbox);
		mBtnUserClear = (ImageButton) rootView.findViewById(R.id.login_clear_btn);
		mBtnPwdClear = (ImageButton) rootView.findViewById(R.id.pwd_clear_btn);
		autologinBackground = rootView.findViewById(R.id.autologin_background);
		getMainModel().getAq().id(rootView.findViewById(R.id.version_text)).getTextView()
				.setText(getMainModel().getVersionName(getActivity()));

		if(Define.USE_AUTO_LOGIN){
			mCbAutoLogin.setOnCheckedChangeListener(m_controller);
		}
		if(ViewModel.USE_OTP_AUTO_LOGIN){
			mCbOtpAutoLogin.setOnCheckedChangeListener(m_controller);
		}
		mBtnSubmit.setOnClickListener(m_controller);
		mBtnUserClear.setOnTouchListener(m_controller);
		mEdUserID.addTextChangedListener(m_controller);
		mEdUserID.setOnFocusChangeListener(m_controller);
		mBtnPwdClear.setOnTouchListener(m_controller);
		mEdPassword.addTextChangedListener(m_controller);
		mEdPassword.setOnFocusChangeListener(m_controller);

		// tkofs 비밀번호 복붙 방지
		mEdPassword.setLongClickable(false);

		if (!ViewModel.apiName.MIS.isApiPass()) {
			MisHelper helper = new MisHelper(getContext(), rootView);
			helper.inint();
		}

		m_controller.checkLoginType();
		m_model.setAutoLoginBackgroundVisiblityListener(m_controller);

		boolean isLogout = false;
		String category = null;
		String event = null;

		if (getArguments() != null) {
			isLogout = getArguments().getBoolean(ARG_KEY_IS_LOGOUT);
			category = getArguments().getString(MainActivity.PUSH_MSG_CATEGORY);
			event = getArguments().getString(MainActivity.PUSH_MSG_EVENT);
			Debug.trace("Arguments::isLogout= " + isLogout);
			Debug.trace("Arguments::Category= " + category);
			Debug.trace("Arguments::Event= " + event);
		}

		ViewModel.Log("onResume loginfrag", "tkofs_otp");

		mBtnSubmit.setEnabled(false);
		if(Define.USE_SSO)
		{
			// 사용자로 부터 임의의 입력을 받지 못하도록 컨트롤 들을 모두 막음
			mEdUserID.setEnabled(false);
			mEdPassword.setEnabled(false);
			mBtnSubmit.setEnabled(false);			
			// 자동로그인이 안되도록 데이터를 모두 지움.			
			Editor prefEd = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
			prefEd.putString(getString(R.string.key_user_information_name), "");
			prefEd.putString(getString(R.string.key_user_information_password), "");
			prefEd.putBoolean(getString(R.string.key_save_mode), false);
			prefEd.commit();
			m_model.getMainModel().dataSet.setSaveMode(false);
		}
		m_model.getMainModel().setTaskAndDataSet(getActivity().getApplicationContext());
		if(Define.USE_AUTO_LOGIN){
			mCbAutoLogin.setChecked(getMainModel().dataSet.isSave());
		}else{
			if (TextUtils.isEmpty(category))
			m_model.getMainModel().dataSet.setSaveMode(false);
		}
		mCbEmpLogin.setChecked(getMainModel().dataSet.isEmpCodeMode());
		if(ViewModel.USE_OTP_AUTO_LOGIN){
			mCbOtpAutoLogin.setChecked(getMainModel().dataSet.isOtpSave());
		}else{
			if (TextUtils.isEmpty(category))
				m_model.getMainModel().dataSet.setOtpSave(false);
		}
		m_controller.checkEmpMode();
		if (getMainModel().dataSet.isSave() || !TextUtils.isEmpty(category)) {
			if (!TextUtils.isEmpty(getMainModel().dataSet.getUserName())) {
				mEdUserID.setText(Crypto.getInstance().decrypt(getMainModel().dataSet.getUserName()));
			}
			if (!TextUtils.isEmpty(getMainModel().dataSet.getUserPassword()))
				mEdPassword.setText(Crypto.getInstance().decrypt(getMainModel().dataSet.getUserPassword()));
		}else if(!Define.USE_AUTO_LOGIN){
			if (!TextUtils.isEmpty(getMainModel().dataSet.getUserName())) {
				mEdUserID.setText(Crypto.getInstance().decrypt(getMainModel().dataSet.getUserName()));
			}
		}else {
			mEdUserID.setText("");
			mEdPassword.setText("");
		}

		mBtnPwdClear.setVisibility(View.INVISIBLE);
		mBtnUserClear.setVisibility(View.INVISIBLE);

		// push click 로그인
		if (!TextUtils.isEmpty(category) && !TextUtils.isEmpty(getMainModel().dataSet.getUserName())
				&& !TextUtils.isEmpty(getMainModel().dataSet.getUserPassword())) {
			m_model.setAutologinBackground(true);
			mBtnSubmit.performClick();
		}
		else if (getMainModel().dataSet.isSave() && !isLogout && !TextUtils.isEmpty(getMainModel().dataSet.getUserName())
				&& !TextUtils.isEmpty(getMainModel().dataSet.getUserPassword()) && HMGWServiceHelper.otp_cancle == 0) {

			m_model.setAutologinBackground(true);
			mBtnSubmit.performClick();
		} else {
			m_model.setAutologinBackground(false);
		}
		if(Define.USE_SSO){
			mEdUserID.setText(getMainModel().getUserId());
			mEdPassword.setText("1");
			mBtnSubmit.performClick();
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		m_model.setAutologinBackground(false);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		ViewGroup view = (ViewGroup) getView();
		view.removeAllViewsInLayout();
		View subview = inflater.inflate(R.layout.mgw_login, view);

/*		mBGContainer.removeAllViewsInLayout();
		mBGlogin_background.removeAllViewsInLayout();
		View.inflate(getContext(), R.layout.mgw_login_bg, mBGContainer);
		View.inflate(getContext(), R.layout.mgw_login, mBGlogin_background);*/
		initLogin(subview);
	}
}