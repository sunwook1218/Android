package com.hs.mobile.gw.util;

import java.util.ArrayList;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.PluginEntry;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class SubRootActivity extends FragmentActivity implements OnKeyListener {
	public int onStartCount = 0;
	private static MainModel mModel;
	// Read from config.xml:
	protected CordovaPreferences preferences;
	protected String launchUrl;
	protected ArrayList<PluginEntry> pluginEntries;

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

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		return false;
	}

}