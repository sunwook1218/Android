package com.hs.mobile.gw.util;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.vac.VacHelper;
import com.hs.mobile.gw.ext.vpn.VPNConnectHelper;
import com.hs.mobile.gw.hsuco.R;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.PluginEntry;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;

public class RootActivity extends FragmentActivity {
	int onStartCount = 0;
	private static MainModel mModel;
	// Read from config.xml:
	protected CordovaPreferences preferences;
	protected String launchUrl;
	protected ArrayList<PluginEntry> pluginEntries;

	private BroadcastReceiver screenOffFilter;


	@SuppressWarnings("deprecation")
	protected void loadConfig() {
		ConfigXmlParser parser = new ConfigXmlParser();
		parser.parse(this);
		preferences = parser.getPreferences();
		preferences.setPreferencesBundle(getIntent().getExtras());
		launchUrl = parser.getLaunchUrl();
		pluginEntries = parser.getPluginEntries();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// SEOJAEHWA: Get ThreadPolicy permission. Main thread 에서 네트워크 처리 관련 예외 회피
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		loadConfig();
		MainModel.getInstance().setCordovaPreferences(preferences);
		MainModel.getInstance().setCordovaPluginEntrys(pluginEntries);
		onStartCount = 1;
		if (savedInstanceState == null) // 1st time
		{
			this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
		} else // already created so reverse animation
		{
			onStartCount = 2;
		}
		// tkofs 현재 activity 저장
		ViewModel.currentActivity = this;
	}

	@Override
	protected void onStart() {
		if (onStartCount > 1) {
			this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
		} else if (onStartCount == 1) {
			onStartCount++;
		}
		try{
			super.onStart();
		}catch (Exception e){
			ViewModel.Log(e.toString(), "tkofs_bluetooth_exception root start ");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {

		// screen OFF remove tkofs
		if (screenOffFilter != null) {
			unregisterReceiver(screenOffFilter);
			screenOffFilter = null;
		}

		if(!ViewModel.apiName.VPN.isApiPass()) {
			ViewModel.Log("VPN 종료 RootActivity", "tkofs_vpn");
			VPNConnectHelper.sslvpnDisconnect();
		}

		super.onDestroy();
	}

	public void setModel(MainModel m) {
		mModel = m;
	}

	public MainModel getModel() {
		if (mModel == null) {
			setModel(new MainModel());
		}
		return mModel;
	}


	/**
     * @exit
     * @description - 안드로이드 강제종료
     */
    public void exitApp(String msg) {
        String alertMsg = String.valueOf(R.string.alert_message_app_exit);
        if (msg != null) alertMsg = msg;
        new AlertDialog.Builder(this).setCancelable(false).setTitle(R.string.menu_exit).setMessage(alertMsg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //isStop();

						if(!ViewModel.apiName.VPN.isApiPass()) {
							ViewModel.Log("VPN 종료 RootActivity", "tkofs_vpn");
							VPNConnectHelper.sslvpnDisconnect();
						}

						if(!ViewModel.apiName.VAC.isApiPass()) {
							ViewModel.Log("VAC 종료 RootActivity", "tkofs_vac");
							VacHelper.release();
						}

                        // 앱 인증 추가 화면인 ( Permission으로 안돌아가고 바로 앱 종료 ) 18.12.07 likearts
                        AndroidUtils.deleteSaveFolder(null);
                        //moveTaskToBack(true);

						if(ViewModel.apiName.VPN.isApiPass()) {
							finishAffinity();
						}
						//finish();
                        //android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }).create().show();
    }
}