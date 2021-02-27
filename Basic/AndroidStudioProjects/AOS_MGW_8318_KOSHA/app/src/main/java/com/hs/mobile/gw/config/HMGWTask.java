package com.hs.mobile.gw.config;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class HMGWTask {
	private static final HMGWTask instance = new HMGWTask();
	
	private HMGWTask(){
	}
	
	public static HMGWTask getInstance(){
		return instance;
	}
	
	public ConfigDataSet getConfigDataSet(Context context){
		ConfigDataSet dataset = new ConfigDataSet();
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		dataset.setServerUri(pref.getString(context.getString(R.string.key_server_information_url), ""));
		dataset.setUserName(pref.getString(context.getString(R.string.key_user_information_name), ""));
		dataset.setUserPassword(pref.getString(context.getString(R.string.key_user_information_password), ""));
		dataset.setTokenId(pref.getString(context.getString(R.string.key_registration_id), ""));
		dataset.setSaveMode(pref.getBoolean(context.getString(R.string.key_save_mode), Define.USE_AUTO_LOGIN));
		dataset.setOtpSave(pref.getBoolean(context.getString(R.string.key_otp_save_mode), ViewModel.USE_OTP_AUTO_LOGIN));
		dataset.setFullScreenMode(pref.getBoolean(context.getString(R.string.key_nomal_window_mode), false));
		dataset.setEmpCodeMode(pref.getBoolean(context.getString(R.string.key_nomal_login_mode), HMGWServiceHelper.USE_EMPCODE_MODE));
		dataset.setNotiMode(pref.getBoolean(context.getString(R.string.key_noti_mode), false));
		return dataset; 
	}
	
	public class ConfigDataSet {
		
		private String serverUri;
		private String userName;
		private String userPassword;
		private String tokenId;
		private boolean isSave;
		private boolean isEmpCodeMode;
		private boolean isFullScreen;
		private boolean isNotiMode;
		private boolean isOtpSave;

		public ConfigDataSet() {
		}

		public String getServerUri() {
			return serverUri;
		}

		public void setServerUri(String serverUri) {
			this.serverUri = serverUri;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		
		public String getTokenId() {
			return tokenId;
		}

		public void setTokenId(String tokenId) {
			this.tokenId = tokenId;
		}

		public boolean isSave() {
			return isSave;
		}

		public void setSaveMode(boolean isSave) {
			this.isSave = isSave;
		}

		public boolean isOtpSave() {
			return isOtpSave;
		}

		public void setOtpSave(boolean otpSave) {
			isOtpSave = otpSave;
		}

		public boolean isFullScreen() {
			return isFullScreen;
		}

		public void setFullScreenMode(boolean isFullScreen) {
			this.isFullScreen = isFullScreen;
		}
		
		public boolean isEmpCodeMode() {
			return isEmpCodeMode;
		}

		public void setEmpCodeMode(boolean isEmpCodeMode) {
			this.isEmpCodeMode = isEmpCodeMode;
		}
		
		public boolean isNotiMode() {
			return isNotiMode;
		}
		public void setNotiMode(boolean isNotiMode) {
			this.isNotiMode = isNotiMode;
		}
	}
}
