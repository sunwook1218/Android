package com.hs.mobile.gw;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ArrayAdapter;

import com.androidquery.util.AQUtility;
import com.hs.mobile.gw.MainModel.Views;
import com.hs.mobile.gw.MenuListHelper.MenuIDsMap;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.vac.VacHelper;
import com.hs.mobile.gw.ext.vpn.VPNConnectHelper;
import com.hs.mobile.gw.ext.vpn.VpnConfig;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.fragment.OptionMenuDialogFragment;
import com.hs.mobile.gw.fragment.login.LoginFragment;
import com.hs.mobile.gw.gnb.GnbDataModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.ForecdTerminationService;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.INetworkFailListener;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.RestAPI;
import com.hs.mobile.gw.util.RestApiProgress;
import com.hs.mobile.gw.util.RestApiProgress.IProgressListener;
import com.hs.mobile.gw.util.RootActivity;
import com.hs.mobile.gw.view.IFunctionAble;
import com.secui.sslvpnsdk.api.SSLVPNConst;
import com.secureland.smartmedic.core.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;


@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends RootActivity implements INetworkFailListener,
		OptionMenuDialogFragment.Callback {

	public static final String PUSH_MSG_CATEGORY = "msg_category";
	public static final String PUSH_MSG_EVENT = "msg_event";

	private RestApiProgress m_restApiProgress;
	private MainModel m_model;

	private static final int REQUEST_READ_PHONE_STATE_PERMISSION	= 1000;

	private String[] permissions = {Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

	//백신 연동을 위한 Helper
	private VacHelper vacHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		ViewModel.Log("MainActivity start ", "tkofs_push_currentActivity");
		super.onCreate(savedInstanceState);

		startService(new Intent(this, ForecdTerminationService.class));

		m_model = MainModel.getInstance();
		if (savedInstanceState != null) {
			Debug.trace("LoginActivity is creating from the killed state.");
			m_model.setTaskAndDataSet(getApplicationContext());
		}
		if (!ViewModel.apiName.VAC.isApiPass()) {
			VacHelper.mainActivity = this;
			vacHelper = new VacHelper(this);
			vacHelper.inint();
		}


		clearApplicationCache(null);
		clearApplicationExternalCache(null);

		AQUtility.setDebug(Define.DEBUG);

//		Debug.configure(this);
		Debug.trace("DebugMode:", Debug.isDebug());
		Debug.trace("############# Program Start", m_model.getVersionName(this));

		m_model.setFragmentManager(getSupportFragmentManager());
		m_model.createAqueryIntence(getApplicationContext());
		MainModel.getInstance().setIsTablet(getResources());
		CookieSyncManager.createInstance(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		if (savedInstanceState != null) {
			Debug.trace("MainActivity is creating from the killed state.");
			m_model.setWasProcessKilled(true);
			return;
		}
		setContentView(R.layout.activity_main_ex);
		m_model.openApiService = new OpenAPIService(this);
		m_model.createPreferenceManager(this);
		// m_model.setTaskAndDataSet(getApplicationContext());
		HMGWServiceHelper.deviceId = AndroidUtils.getDeviceID(this);
		HMGWServiceHelper.tokenId = m_model.pref.getString(getString(R.string.key_registration_id), "");
		HMGWServiceHelper.phoneNumber = AndroidUtils.getPhoneNumber(this);
		HMGWServiceHelper.phoneOSVersion = android.os.Build.VERSION.RELEASE;

		GnbDataModel.mainActivity = this;

		if (!ViewModel.apiName.VAC.isApiPass()) {
			vacHelper.check();
		} else {
			String category = getIntent().getStringExtra(PUSH_MSG_CATEGORY);
			String event = getIntent().getStringExtra(PUSH_MSG_EVENT);

			if (Define.USE_SSO) {
				if (getIntent() != null && getIntent().getData() != null && getIntent().getData().getHost() != null) {
					m_model.setUserId(getIntent().getData().getHost());
					m_model.showView(getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOADING, category != null ?
							new BundleUtils.Builder().add(PUSH_MSG_CATEGORY, category).add(PUSH_MSG_EVENT, event).build() : null);
				} else {
					finish();
				}
			} else {
				m_model.showView(getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOADING, category != null ?
						new BundleUtils.Builder().add(PUSH_MSG_CATEGORY, category).add(PUSH_MSG_EVENT, event).build() : null);
			}
		}
	}

	@Override
	protected void onStart() {
		ViewModel.isLiveMainAct = true;
		m_model.setTaskAndDataSet(getApplicationContext());
		if (m_model.isWasProcessKilled()) {
			m_model.setWasProcessKilled(false);
		}
		RestAPI.setLoadingProgressHandler(getLoadingProgressHandler());
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		PopupUtil.hideLoading(this);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
		}
	}

	public RestApiProgress getLoadingProgressHandler() {
		if (m_restApiProgress == null) {
			m_restApiProgress = new RestApiProgress(this, new IProgressListener() {

				@Override
				public void showProgress() {
					if (this != null && MainModel.getInstance().isLoadingProgressShow()) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								PopupUtil.showLoading(MainActivity.this);
							}
						});
					}
				}

				@Override
				public void onProgress(int n) {

				}

				@Override
				public void hideProgress() {
					if (this != null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								PopupUtil.hideLoading(MainActivity.this);
							}
						});
					}
				}
			});
		}
		return m_restApiProgress;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		String category = intent.getStringExtra(PUSH_MSG_CATEGORY);
		String event = intent.getStringExtra(PUSH_MSG_EVENT);
		Debug.trace("Intent::Category= " + category);
		Debug.trace("Intent::Event= " + event);

		if (getVisibleFragment() == null) {
			ViewModel.Log("getVisibleFragment() == null", "tkofs_push_newIntent");
			return;
		}
		if (LoginFragment.class.getName().equals(getVisibleFragment().getClass().getName())) {
			ViewModel.Log("LoginFragment.class.getName()", "tkofs_push");
			m_model.showView(getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOGIN, category != null ?
					new BundleUtils.Builder().add(PUSH_MSG_CATEGORY, category).add(PUSH_MSG_EVENT, event).build() : null);
		} else {
			if (!TextUtils.isEmpty(category)) {
				try {
					JSONObject menuData = new JSONObject();
					menuData.put(PUSH_MSG_EVENT, event);
					switch (category) {
						case "mail":
							menuData.put(MenuListHelper.MENU_NAME, getString(R.string.label_menu_mail_inbox));
							MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.mail_received, menuData);
							break;
						case "board":
							menuData.put(MenuListHelper.MENU_NAME, getString(R.string.label_menu_board_new));
							MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.board_recent, menuData);
							break;
						case "appr":
							menuData.put(MenuListHelper.MENU_NAME, getString(R.string.label_menu_sign_wait));
							MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.approval_waiting, menuData);
							break;
						case "square":
							menuData.put(MenuListHelper.MENU_NAME, getString(R.string.label_squareplus_group_me));
							MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.square_plus_my_group, menuData);
							break;
						case "hscalendar":
							menuData.put(MenuListHelper.MENU_NAME, getString(R.string.label_menu_schedule_schdlist));
							MainFragment.menuListHelper.changeContentByProductMenu(MenuIDsMap.schedule_schdlist, menuData);
							break;
					}
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}
			}
		}
	}

	private Fragment getVisibleFragment() {
		return getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_STAGE);
		// 아래코드 수정 [사용하지 말라고함] 20181122 tkofs
        /*for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment.isVisible()) {
                return ((Fragment) fragment);
            }
        }
        return null;*/
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		// tkofs vac
		PopupUtil.cancelAllDlg();
		// 쿠키 삭제
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeSessionCookie();
		clearApplicationCache(null);
		clearApplicationExternalCache(null);

		ViewModel.isLiveMainAct = false;
		super.onDestroy();
	}

	// 백버튼 뒤로가기
	@SuppressLint("WrongConstant")
	@Override
	public void onBackPressed() {
		// 메인 화면이고, drawerLayout이 Null이 아니면 메뉴를 닫는다.tkofs
		if (MainFragment.getDrawerLayout() != null && MainFragment.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
			MainFragment.getDrawerLayout().closeDrawers();
			return;
		}

		// 팝업 창이 보이는 상태면 팝업창 닫기
		if (m_model.isTablet() && findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT) != null
				&& findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).getVisibility() == View.VISIBLE) {
			findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.GONE);
			return;
		}
		// 커스텀 웹뷰가 뒤로 갈 수 있으면
		else if (m_model.getMainFragment() != null &&  m_model.getMainFragment().getCustomWebView().canGoBack()) {
			m_model.getMainFragment().getCustomWebView().goBack();
			return;
		} else if (!m_model.isPopupMode() && MainFragment.getController() != null && MainFragment.getController().isWebViewShowing() && !m_model.isTablet()) {
			View backBtn = m_model.findBackButton((ViewGroup) m_model.getWebViewFragment().getView());
			if (backBtn != null && backBtn instanceof IFunctionAble) { // tkofs
				m_model.getWebViewFragment().loadUrl(((IFunctionAble) backBtn).getOnclickFunction());
				return;
			}
			// tkofs
		} else if (m_model.getMainFragment() != null && (m_model.getMainFragment().getMiddleMenuContainer().findViewById(R.id.middleBackButton)).getVisibility() == View.VISIBLE) {
			MainFragment.getController().goBackToPrev((m_model.getMainFragment().getMiddleMenuContainer().findViewById(R.id.middleBackButton)));
			return;
		} else if (m_model.isPopupMode() && !m_model.isTablet()) {
			View backBtn = m_model.findBackButton((ViewGroup) m_model.getPopupWebViewFragment().getView());
			if (backBtn != null && backBtn instanceof IFunctionAble) {
				m_model.getPopupWebViewFragment().loadUrl(((IFunctionAble) backBtn).getOnclickFunction());
				return;
			}
		}
		// Popup모드가 아니고, WebViewFragment가 null이 아니고 back버튼이 발견 되었으면..
		else if(MainFragment.getController() != null && MainFragment.getController().isWebViewShowing() && !m_model.isPopupMode() && m_model.getWebViewFragment() != null && m_model.findBackButton((ViewGroup)m_model.getWebViewFragment().getView()) != null)
		{
			View backBtn = m_model.findBackButton((ViewGroup) m_model.getWebViewFragment().getView());
			if (backBtn != null && backBtn instanceof IFunctionAble) {
				m_model.getWebViewFragment().loadUrl(((IFunctionAble) backBtn).getOnclickFunction());
				return;
			}
		} else{
			// tkofs
		}

		try {
			// tkofs || 팝업 웹뷰는 입력폼이고 뒤로가기시 취소 confirm이 뜨기 때문에 종료 알럿은 띄우기 않는다.
			if (!MainModel.getInstance().isPopupMode() && ViewModel.backKey_Lock) {
				return;
			} else if (MainModel.getInstance().isPopupMode()) {
				super.onBackPressed();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_STAGE);
		if (fragment instanceof LoginFragment) {
			Debug.trace("Login back");
			new AlertDialog.Builder(this).setTitle(R.string.menu_exit).setMessage(R.string.alert_message_app_exit)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							ViewModel.Log("LoginPage 종료", "tkofs_mdm_dis");

							if(!ViewModel.apiName.VPN.isApiPass()) {
								ViewModel.Log("VPN 종료 MainActivity", "tkofs_vpn");
								VPNConnectHelper.sslvpnDisconnect();
							}
							if(!ViewModel.apiName.VAC.isApiPass()) {
								ViewModel.Log("VAC 종료 MainActivity", "tkofs_vac");
								VacHelper.release();
							}
							AndroidUtils.deleteSaveFolder(null);
							finish();
						}
					}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			}).create().show();
			return;
		}

		// 메인 화면이고, drawerLayout이 Null이 아니면 메뉴를 닫는다.
		if (MainFragment.getDrawerLayout() != null && MainFragment.getDrawerLayout().isDrawerOpen(Gravity.START)) {
			MainFragment.getDrawerLayout().closeDrawers();
			return;
		}

		new AlertDialog.Builder(this).setTitle(R.string.menu_exit).setMessage(R.string.alert_message_app_exit)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						ViewModel.Log("General 종료", "tkofs_mdm_dis");

						if(!ViewModel.apiName.VPN.isApiPass()) {
							ViewModel.Log("VPN 종료 MainActivity General", "tkofs_vpn");
							VPNConnectHelper.sslvpnDisconnect();
						}
						if(!ViewModel.apiName.VAC.isApiPass()) {
							ViewModel.Log("VAC 종료 MainActivity General", "tkofs_vac");
							VacHelper.release();
						}
						AndroidUtils.deleteSaveFolder(null);
						//moveTaskToBack(true);// tkofs
						if(ViewModel.apiName.VPN.isApiPass()) {
							finishAffinity();	// tkofs
						}
						//finish();
						//android.os.Process.killProcess(android.os.Process.myPid()); // tkofs
					}
				}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		}).create().show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		OnMenuItemClickListener menuItemClickListener = new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.logout:
						MainFragment.getController().logout(null);
						break;
					case R.id.exit:
						AndroidUtils.deleteSaveFolder(null);
						finish();
						break;
					case R.id.main_menu:
						MainFragment.getController().showMainMenu(null);
						break;
					case R.id.initial_menu:
						showInitialMenuSelectDialog();
						break;
				}
				return true;
			}
		};
		menu.findItem(R.id.logout).setOnMenuItemClickListener(menuItemClickListener);
		menu.findItem(R.id.exit).setOnMenuItemClickListener(menuItemClickListener);
		menu.findItem(R.id.main_menu).setOnMenuItemClickListener(menuItemClickListener);
		menu.findItem(R.id.initial_menu).setOnMenuItemClickListener(menuItemClickListener);

		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean optionsAfterLogin = !TextUtils.isEmpty(HMGWServiceHelper.userId);
		menu.findItem(R.id.logout).setVisible(optionsAfterLogin);
		menu.findItem(R.id.exit).setVisible(optionsAfterLogin);
		menu.findItem(R.id.main_menu).setVisible(optionsAfterLogin);
		return super.onPrepareOptionsMenu(menu);
	}

	public void clearApplicationCache(java.io.File dir) {

		// 캐시 디렉토리 삭제
		if (dir == null)
			dir = getCacheDir();

		java.io.File[] children = dir.listFiles();
		if (children != null) {
			for (int i = 0; i < children.length; i++){
				if (children[i].isDirectory()) {
					clearApplicationCache(children[i]);
				} else {
					children[i].delete();
				}
			}
		}
	}

	public void clearApplicationExternalCache(java.io.File dir) {

		// 캐시 디렉토리 삭제
		if (dir == null)
			dir = getExternalCacheDir();

		java.io.File[] children = dir.listFiles();
		if (children != null) {
			for (int i = 0; i < children.length; i++){
				if (children[i].isDirectory()) {
					clearApplicationCache(children[i]);
				} else {
					children[i].delete();
				}
			}
		}
	}

	@Override
	public void onNetworkFail(String strMsg) {
		if (TextUtils.isEmpty(strMsg)) {
			PopupUtil.showDialog(this, R.string.message_not_connection);
		} else {
			PopupUtil.showDialog(this, strMsg);
		}
	}

	private void showInitialMenuSelectDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		List<String> list = new ArrayList<String>();
		list.add(getString(R.string.label_menu_home_home));
		list.add(getString(R.string.label_menu_mail_inbox));
		list.add(getString(R.string.label_menu_sign_wait));
		list.add(getString(R.string.label_menu_board_new));
		list.add(getString(R.string.label_menu_server_setting));

		final Map<Integer, MenuListHelper.MenuIDsMap> menuIdMap = new HashMap<Integer, MenuListHelper.MenuIDsMap>();
		menuIdMap.put(0, MenuListHelper.MenuIDsMap.home_home);
		menuIdMap.put(1, MenuListHelper.MenuIDsMap.mail_received);
		menuIdMap.put(2, MenuListHelper.MenuIDsMap.approval_waiting);
		menuIdMap.put(3, MenuListHelper.MenuIDsMap.board_recent);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_single_choice, list);

		final String prefKeyInitialMenu = MenuListHelper.PREF_KEY_INITIAL_MENU;
		final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String initMenuId = pref.getString(prefKeyInitialMenu, null);
		int position = list.size() - 1;
		if (initMenuId != null) {
			for (Integer i : menuIdMap.keySet()) {
				if(menuIdMap.get(i).name().equals(initMenuId)){
					position = i;
					break;
				}
			}
		}

		builder.setSingleChoiceItems(adapter, position, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MenuListHelper.MenuIDsMap id = menuIdMap.get(which);
				SharedPreferences.Editor prefEditor = pref.edit();
				if (id != null) {
					prefEditor.putString(prefKeyInitialMenu, id.name());
				} else {
					prefEditor.remove(prefKeyInitialMenu);
				}
				prefEditor.apply();

				dialog.dismiss();
			}
		});

		builder.show();

	}

	// 버튼 tkofs
	public void mOnPopupClick(View v) {
		//데이터 담아서 팝업(액티비티) 호출
		Intent intent = new Intent(this, LnbActivity.class);
		intent.putExtra("activity", "Sub");
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Debug.trace(requestCode);
		ViewModel.Log("requestCode : " + requestCode, "tkofs_vaccine");
		ViewModel.Log("resultCode : " + resultCode, "tkofs_vaccine");
		if (!ViewModel.apiName.VAC.isApiPass()) {
			if (requestCode == ViewModel.VAC_REQUEST_CODE) {
				if (resultCode == Constants.ROOTING_EXIT_APP
						|| resultCode == Constants.ROOTING_YES_OR_NO) {
					PopupUtil.showDialog(this, "루팅된 단말입니다.\n앱을 종료합니다.", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							finish();
						}
					});
				}
				else if (resultCode == Constants.V_DB_FAIL) {
					PopupUtil.showDialog(this, "DB파일 무결성검증 실패하였습니다.\n앱을 종료합니다.", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							finish();
						}
					});
				}
				else if (resultCode == Constants.CK_REMOTE) {

				}
				else if (resultCode == Constants.EMPTY_VIRUS) {
					Debug.trace("EMPTY_VIRUS");
					String category = getIntent().getStringExtra(PUSH_MSG_CATEGORY);
					String event = getIntent().getStringExtra(PUSH_MSG_EVENT);
					if (Define.USE_SSO) {
						if (getIntent() != null && getIntent().getData() != null && getIntent().getData().getHost() != null) {
							m_model.setUserId(getIntent().getData().getHost());
							m_model.showView(getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOADING, category != null ?
									new BundleUtils.Builder().add(PUSH_MSG_CATEGORY, category).add(PUSH_MSG_EVENT, event).build() : null);
						} else {
							finish();
						}
					} else {
						ViewModel.Log("test log ", "tkofs_vaccine");
						m_model.showView(getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOADING, category != null ?
								new BundleUtils.Builder().add(PUSH_MSG_CATEGORY, category).add(PUSH_MSG_EVENT, event).build() : null);
					}
				} else if (resultCode == Constants.EXIST_VIRUS_CASE1) {
					PopupUtil.showDialog(this, "악성코드 탐지 후 사용자가 해당 악성코드 앱을 삭제.\n확인을 눌러 종료 후 앱을 재시작 해 주세요.", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							finish();
						}
					});
				} else if (resultCode == Constants.EXIST_VIRUS_CASE2) {
					PopupUtil.showDialog(this, "악성코드 탐지 후 사용자가 해당 악성코드 앱을 미삭제.\n앱을 종료합니다.", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							finish();
						}
					});
				}
			}
		}

		if (requestCode == MainModel.REQ_SP_GROUP_FOLDER) {
			super.onActivityResult(requestCode, resultCode, data);
			MainModel.getInstance().getMainFragment().onActivityResult(requestCode, resultCode, data);
		}else if(requestCode == ViewModel.OTP_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				HMGWServiceHelper.otp_cancle = 2;
			} else {
				ViewModel.Log("activity result fail", "tkofs_otp");
			}
		}else if(requestCode == VpnConfig.VPN_REQUEST_PERMISSION){
			ViewModel.Log("onActivityResult VPN_REQUEST_PERMISSION ", "tkofs_vpn");
		}else{
			super.onActivityResult(requestCode, resultCode, data);
		}
		MainModel.getInstance().setNavibarVisible(false); // 18.12.31
	}

	@Override
	public void exitApp(String msg) {
		super.exitApp(msg);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			OptionMenuDialogFragment.newInstance()
					.show(getSupportFragmentManager(), OptionMenuDialogFragment.TAG);
			return true;
		}
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public void onOptionItemSelected(String tag) {
		Debug.trace(tag + " Option item has been selected!");
		switch (tag) {
			case OptionMenuDialogFragment.TAG_OPT_ITEM_EXIT:
				AndroidUtils.deleteSaveFolder(null);
				finish();
				break;
			case OptionMenuDialogFragment.TAG_OPT_ITEM_INIT:
				showInitialMenuSelectDialog();
				break;
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (REQUEST_READ_PHONE_STATE_PERMISSION == requestCode) {
			if (0 < grantResults.length) {
				if (PackageManager.PERMISSION_GRANTED == grantResults[0]) {
					// 전화 권한 활성화 완료
				}
			}
		}
	}

}
