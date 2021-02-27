package com.hs.mobile.gw.config;

import com.hs.mobile.gw.Define.LoginType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class HMGWConfig extends PreferenceActivity {
	private static final String KEY_PREFERENCESCREEN = "preferenceScreen";
	private static final String KEY_NOTI_CATEGORY = "noti_category";

	private static final String KEY_LOGIN_MODE = "save_mode";
	private static final String KEY_NOTI_MODE = "noti_mode";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
		LoginType serverLoginType = HMGWServiceHelper.LOGIN_TYPE;
		boolean usePushService = HMGWServiceHelper.USE_PUSH_SERVICE;

		CheckBoxPreference loginMode = (CheckBoxPreference) findPreference(KEY_LOGIN_MODE);
		CheckBoxPreference notiMode = (CheckBoxPreference) findPreference(KEY_NOTI_MODE);

		if (serverLoginType != null && serverLoginType == LoginType.EMP_CODE) {
			loginMode.setChecked(true);
			loginMode.setEnabled(false);
		}

		if (!usePushService) {
			notiMode.setChecked(true);
			notiMode.setEnabled(false);

			PreferenceScreen mPreferenceScreen = (PreferenceScreen) findPreference(KEY_PREFERENCESCREEN);
			Preference notiCategory = findPreference(KEY_NOTI_CATEGORY);

			mPreferenceScreen.removePreference(notiCategory);
		}
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {

		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
}