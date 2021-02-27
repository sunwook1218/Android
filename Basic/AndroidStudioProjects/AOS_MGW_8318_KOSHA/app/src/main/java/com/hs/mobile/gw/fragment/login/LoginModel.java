package com.hs.mobile.gw.fragment.login;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.util.Debug;

import android.widget.TextView;

public class LoginModel {
	public interface IAutologinBackgroundVisiblityListener {

		void onVisiblelityChange(boolean b);

	}

	private MainModel mMainModel;
	private boolean m_bAutologinBackground;
	private IAutologinBackgroundVisiblityListener autologinBackgroundListener;

	public LoginModel(CommonFragment f) {
		setMainModel(f.getMainModel());
	}

	public void setMainModel(MainModel mainModel) {
		mMainModel = mainModel;
	}

	public void setAutoLoginBackgroundVisiblityListener(IAutologinBackgroundVisiblityListener listener) {
		autologinBackgroundListener = listener;
	}

	public MainModel getMainModel() {
		return mMainModel;
	}

	public void setSaveMode(boolean isChecked) {
		getMainModel().dataSet.setSaveMode(isChecked);
		getMainModel().prefEdit.putBoolean("save_mode", isChecked);
		getMainModel().prefEdit.commit();
	}

	// tkofs otp save mode
	public void setOtpSaveMode(boolean isChecked) {
		getMainModel().dataSet.setOtpSave(isChecked);
		getMainModel().prefEdit.putBoolean("otp_save_mode", isChecked);
		getMainModel().prefEdit.commit();
	}

	public void setLoginMode(boolean isChecked, String strLoginNumber, String strLoginUser, TextView v) {
		getMainModel().prefEdit.putBoolean("login_mode", isChecked);
		if (isChecked) {
			v.setText(strLoginNumber);
		} else {
			v.setText(strLoginUser);
		}
		if(MainModel.getInstance().prefEdit != null) MainModel.getInstance().prefEdit.commit();
	}

	/**
	 * 
	 * @param b true - 로딩 Background 화면 표시, false - 숨김
	 */
	public void setAutologinBackground(boolean b) {
		// SSO로그인이 설정되어 있으면 항상 autologinBackground가 로그인 화면을 덮어 사용자의 입력을 막는다.
		if(Define.USE_SSO)
		{
			b = true;
		}
		Debug.trace("setAutologinBackground", b);
		m_bAutologinBackground = b;
		if (autologinBackgroundListener != null) {
			autologinBackgroundListener.onVisiblelityChange(m_bAutologinBackground);
		}
	}

	public boolean isAutoLoginBackground() {
		return m_bAutologinBackground;
	}
}
