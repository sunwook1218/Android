package com.hs.mobile.gw.fragment;

import java.util.ArrayList;

import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.mailwrite.MailWriteFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment;
import com.hs.mobile.gw.openapi.square.vo.ShowMailEditorViewVO;
import com.hs.mobile.gw.plugin.HMGWPlugin;
import com.hs.mobile.gw.plugin.HMGWPlugin.IPluginListener;
import com.hs.mobile.gw.plugin.HMGWPlugin.actionCode;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.HMGWServiceHelper.OpenAPI;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.TextViewUtils;
import com.hs.mobile.gw.view.CustomImageButton;
import com.hs.mobile.gw.view.CustomTabHost;
import com.hs.mobile.gw.view.CustomTextButton;
import com.hs.mobile.gw.view.IFunctionAble;
import com.hs.mobile.gw.view.SelectedListItem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class CustomWebViewFragment extends CommonFragment implements OnKeyListener, IPluginListener,IPreBackKeyListener  {

	public static final String FILE_ANDROID_ASSET_WWW_MOBILE_INDEX_HTML = "file:///android_asset/www/mobile_index.html";
	//public static final String FILE_ANDROID_ASSET_WWW_MOBILE_INDEX_HTML = OpenAPI.SERVER_URL + "/www/mobile_index.html"; // tkofs
	private static final String SCRIPT_SERVER_URL = "javascript:var APP_INFO_SERVER='" + OpenAPI.SERVER_URL + "';";
	public static final String ARG_KEY_URL = "url";
	public static final String INTENT_KEY_ORG_SELECT = "orgselected";
	public static final String ARG_KEY_IS_SEARCH_MODE = "is_search_mode";
	public static CordovaWebView mWebView;
	private CustomWebViewClient mClient;
	private boolean bFirst = true;
	private String mUrl;

	public static CordovaWebView getWebView() {
		return mWebView;
	}

	public enum CallBackId {
		to, cc, bcc;
	}

	public static class OrgSelectedVO {
		public OrgSelectedVO(String s) {
			try {
				JSONArray jarr = new JSONArray(s);
				callbackid = CallBackId.valueOf(jarr.optString(1));
				JSONArray items = new JSONObject(jarr.optString(2)).optJSONArray("selectedlist");
				if (items != null) {
					for (int i = 0; i < items.length(); ++i) {
						selectedlist.add(new SelectedListItem(items.getJSONObject(i)));
					}
				}
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}
		}

		public CallBackId callbackid;
		public ArrayList<SelectedListItem> selectedlist = new ArrayList<SelectedListItem>();
	}

	private boolean m_bSearchMode;
	private View rootView;
	private RelativeLayout m_naviLayout;
	private LinearLayout m_webViewToolBar;
	private CustomTabHost m_tabHost;
	private CordovaInterfaceImpl m_cordovaInterface;
	
	public boolean m_bTabInitiation; // to avoid call java script function

	public boolean isTabInitiation() {
		return m_bTabInitiation;
	}

	public void setTabInitiation(boolean isTabInitiation) {
		m_bTabInitiation = isTabInitiation;
	}
	
	// 조직도 탭바
	public static int m_nLastSelectedTabIndex; // memory last selected tap index
												// to

	public int getLastSelectedTabIndex() {
		return m_nLastSelectedTabIndex;
	}

	public void setLastSelectedTabIndex(int lastSelectedTabIndex) {
		m_nLastSelectedTabIndex = lastSelectedTabIndex;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_webview, container, false);
		setMenuVisibility(true);
		setHasOptionsMenu(true);
		m_naviLayout = (RelativeLayout) rootView.findViewById(R.id.ID_LAY_R_NAVI);
		m_webViewToolBar = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_TOOL_BAR);
		m_tabHost = (CustomTabHost) rootView.findViewById(R.id.tabhost);
		m_tabHost.setup();
		m_tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if (MainModel.getInstance().isTabInitiation())
					return;
				MainModel.getInstance().setLastSelectedTabIndex(Integer.parseInt(tabId));
				String jsFunction = m_tabHost.getFunction(MainModel.getInstance().getLastSelectedTabIndex());
				mWebView.loadUrl(jsFunction);
			}
		});
		container = (LinearLayout) rootView.findViewById(R.id.ID_WEBVIEW_CONTAINER);
		CordovaWebViewEngine cordovaWebViewEngine = CordovaWebViewImpl.createEngine(getActivity(), MainModel
				.getInstance().getCordovaPreferences());
		mWebView = new CordovaWebViewImpl(cordovaWebViewEngine);
		container.addView(mWebView.getView(), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		m_cordovaInterface = new CordovaInterfaceImpl(getActivity()) {
			@Override
			public Object onMessage(String id, Object data) {
				return super.onMessage(id, data);
			}
		};
		mWebView.init(m_cordovaInterface, MainModel.getInstance().getCordovaPluginEntrys(), MainModel.getInstance().getCordovaPreferences());
		((WebView) cordovaWebViewEngine.getView()).getSettings().setJavaScriptEnabled(true);
		((WebView) cordovaWebViewEngine.getView()).getSettings().setAppCacheEnabled(false);
		((WebView) cordovaWebViewEngine.getView()).getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		((WebView) cordovaWebViewEngine.getView()).getSettings().setUseWideViewPort(true);
		((WebView) cordovaWebViewEngine.getView()).setOnKeyListener(this);
		if (Build.VERSION.SDK_INT >= 11) {
			((WebView) cordovaWebViewEngine.getView()).setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		SystemWebViewClient m_cordovaWebViewClient = new SystemWebViewClient((SystemWebViewEngine) mWebView.getEngine()) {
			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
				Debug.trace("onLoadResource url[" + url + "]");
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				Debug.trace("onPageStarted url[" + url + "]");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				if (TextUtils.equals(FILE_ANDROID_ASSET_WWW_MOBILE_INDEX_HTML, url)) {
				}
				Debug.trace("onPageFinished url[" + url + "]");
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
					//noinspection deprecation
					CookieSyncManager.getInstance().sync();
				} else {
					// 롤리팝 이상에서는 CookieManager의 flush를 하도록 변경됨.
					CookieManager.getInstance().flush();
				}
				// SEOJAEHWA: page loading 이 완료 된 후 Server URL 을 초기화 하는 함수 호출
				getWebView().loadUrl("javascript:initializeServerInfo('"
						+ HMGWServiceHelper.OpenAPI.SERVER_URL + "')");
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (url.startsWith("tel:")) {
					url = url.replaceAll("#", "%23");
					Intent call_phone = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
					getActivity().startActivity(call_phone);
					return true;
				} else if (url.startsWith("mailto:")) {
					return true;
				} else if (url.startsWith("sms:")) {
					Intent sms_phone = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
					getActivity().startActivity(sms_phone);
					return true;
				} else if (url.endsWith("apk") || url.endsWith("APK")) {
					Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					getActivity().startActivity(web);
					return true;
				} else if (url.startsWith("http:") || url.startsWith("https:")) {
					view.loadUrl(url);
					return true;
				}

				// return false : WebView 자체 처리, true : 기본 어플리케이션이 처리
				return false;
			}
		};

		SystemWebChromeClient m_cordovaChromeClient = new SystemWebChromeClient((SystemWebViewEngine) mWebView.getEngine()) {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
				new AlertDialog.Builder(getActivity()).setTitle(R.string.app_name).setMessage(message)
				.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				}).setOnCancelListener(new OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface dialog) {
						// TODO Auto-generated method stub
						result.cancel();
						dialog.cancel();
					}
				}).setCancelable(true).create().show();
				return true;
			};

			@Override
			public boolean onJsConfirm(WebView view, String url, String message, final android.webkit.JsResult result) {
				new AlertDialog.Builder(getActivity()).setTitle(R.string.app_name).setMessage(message)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.confirm();
					}
				}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						result.cancel();
					}
				}).setOnCancelListener(new OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface dialog) {
						// TODO Auto-generated method stub
						result.cancel();
						dialog.cancel();
					}
				}).create().show();
				return true;
			};
		};
		SystemWebView wV = (SystemWebView) cordovaWebViewEngine.getView();
		wV.setWebViewClient(m_cordovaWebViewClient);
		wV.setWebChromeClient(m_cordovaChromeClient);
		mWebView.loadUrl("javascript:initializeServerInfo('" + HMGWServiceHelper.OpenAPI.SERVER_URL + "')");
		mWebView.loadUrl(FILE_ANDROID_ASSET_WWW_MOBILE_INDEX_HTML);

		if (getArguments() != null) {
			m_bSearchMode = getArguments().getBoolean(ARG_KEY_IS_SEARCH_MODE, false);
			mUrl = getArguments().getString(ARG_KEY_URL);
		}
		// if (sFirst == false && getArguments() != null) {
		// Debug.trace("sFirst == false && getArguments() != null");
		// mWebView.loadUrl(getArguments().getString(ARG_KEY_URL));
		// }
		if (!bFirst) {
			if (!TextUtils.isEmpty(mUrl))
				mWebView.loadUrl(mUrl);
		}

		return rootView;
	}

	@Override
	public void onStop() {
		HMGWPlugin.removeListener(this);
		super.onStop();
	}

	@Override
	public void onStart() {
		HMGWPlugin.addListener(this);
		super.onStart();
	}
	
	Bundle mBundle = new Bundle();
	public void loadUrl(final String url) {
		Debug.trace("url = ", url);
		Debug.trace("bFirst = ", bFirst);
		if (!TextUtils.isEmpty(url)) {
			if (bFirst) {
				mUrl = url;
			} else {
				getWebView().loadUrl(url);
			}
		}
	}

	class CustomWebViewClient extends WebViewClient {
		public CustomWebViewClient() {
		}

		@Override
		public void onLoadResource(WebView view, String url) {
			Debug.trace("onLoadResource", url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			Debug.trace("onPageStarted", url);
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			Debug.trace("onPageFinished", url);
			if (bFirst) {
				bFirst = false;
				Debug.trace("load : ", mUrl);
				if (!TextUtils.isEmpty(mUrl))
					mWebView.loadUrl(mUrl);
			} else {
				super.onPageFinished(view, url);
			}
		}
	}

	@Override
	public void onEventFromWeb(final actionCode ac, final Object obj) {
		Debug.trace("CustomWebViewFragment::onEventFromWeb", ac.name(), obj);
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				switch (ac) {
				case onBackKeyPressed:
					getSubActivity().finish();
					break;
				case closeImageDocViewer:
					break;
				case closePopupViewer:
					Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_SUB_CONTENT);
					if (fragment != null && fragment instanceof SpContentsDetailFragment) {
						((SpContentsDetailFragment) fragment).showContents();
						mWebView.clearHistory();
					}
					else					
						getActivity().finish();
					break;
				case closeWaitingView:
					// PopupUtil.hideLoading();
					break;
				case completeAppr:
					break;
				case deleteBoardMtrl:
					break;
				case deleteMail:
					break;
				case getImageDocViewerData:
					break;
				case hideDocViewerHeader:
					break;
				case hideTabBar:
					break;
				case hideToolBar:
					m_webViewToolBar.setVisibility(View.GONE);
					break;
				case notiLoadingCompleted:
					if (bFirst) {
						if (!TextUtils.isEmpty(mUrl) && mWebView != null) {
							Debug.trace("load : ", mUrl);							
							mWebView.loadUrl(mUrl);
						}
						bFirst = false;
						Debug.trace("notiLoadingCompleted");
					}
					// PopupUtil.hideLoading();
					break;
				case notiLoadingError:
					break;
				case popView:
					break;
				case sessioninfo:
					break;
				case setDocViewerHeader:
					break;
				case setDocViewerPageNo:
					break;
				case setLeftBarButton:
				case setRightBarButton:
					setNavibarButton((JSONArray) obj);
					break;
				case setSelectedTabBarItem:
					break;
				case setStateLeftBarButton:
					break;
				case setStateRightBarButton:
					break;
				case setTabBarButton:
					setWebviewTabbar((JSONArray) obj);
					break;
				case setTitle:
					((TextView) getView().findViewById(R.id.ID_TV_TITLE)).setText((String) obj);
					break;
				case setToolBarButton:
					m_webViewToolBar.removeAllViews();
					MainModel.getInstance().setWebviewToolbar(getActivity(), mWebView, m_webViewToolBar, (JSONArray) obj);
					break;
				case showCheckPasswdAnoBoard:
					break;
				case showDocViewerHeader:
					break;
				case showImageDocViewer:
					break;
				case showLocalViewer:
					break;
				case showLoginView:
					break;
				case showMenuItem:
					break;
				case showPhotoCamera:
					break;
				case showPopupMenu:
					break;
				case showPopupViewer:
					break;
				case showSidebarMenu:
					break;
				case showURL:
					break;
				case showWaitingView:
					// PopupUtil.showLoading(getActivity());
					break;
				case showWebView:
					break;
				case showWriteComment:
					MainModel.getInstance().showWriteComment(getActivity(), (JSONArray) obj, true);
					break;
				case returnOrgSelect:
					onReturnOrgSelect(obj.toString());
					break;
				case showMailEditorView:
					try {
						JSONArray jarr = (JSONArray) obj;
						MainModel.getInstance().showSubActivity(
								getActivity(),
								SubActivityType.MAIL_WRITE,
								new BundleUtils.Builder()
										.add(MailWriteFragment.INTENT_KEY_SHOW_MAIL_EDITOR_VIEW_VO,
												new ShowMailEditorViewVO(jarr.getString(0), jarr.getString(1), jarr.getString(2), jarr
														.getString(3), jarr.getString(4), jarr.getString(5))).build());
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
					break;
				default:
					break;
				}
			}
		});
	}

	protected void onReturnOrgSelect(String obj) {
		// ["to","{\"selectedlist\":[{\"id\":\"500446007\",\"type\":\"user\",\"name\":\"박지숙\",\"email\":\"\",\"recursive\":false}]}"]
		Debug.trace(obj);
		Intent intent = new Intent();
		intent.putExtra(INTENT_KEY_ORG_SELECT, obj);
		getActivity().setResult(Activity.RESULT_OK, intent);
		getActivity().finish();
	}

	public void setNavibarButton(JSONArray data) {
		LinearLayout btnContainer;
		int count = data.optInt(0);
		JSONArray labels = data.optJSONArray(1);
		JSONArray functions = data.optJSONArray(2);
		String position = data.optString(3);
		if (TextUtils.equals(position, "L")) {
			btnContainer = (LinearLayout) getView().findViewById(R.id.ID_LAY_L_NAVI_LEFT);
			btnContainer.setVisibility(View.VISIBLE);
		} else {
			btnContainer = (LinearLayout) getView().findViewById(R.id.ID_LAY_L_NAVI_RIGHT);
			btnContainer.setVisibility(View.VISIBLE);
		}
		btnContainer.removeAllViews();

		for (int i = 0; i < count; i++) {
			final String function = functions.optString(i);
			OnClickListener onClickListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					mWebView.loadUrl(function);
					TextViewUtils.hideKeyBoard(getActivity(), v);
				}
			};
			IFunctionAble btn = null;
			if (TextUtils.equals(labels.optString(i), "BACK")) {
				btn = (CustomImageButton) LayoutInflater.from(getActivity()).inflate(R.layout.template_back_btn_hu, btnContainer,
						false);
				/*((CustomImageButton) btn).setBackgroundResource(R.drawable.style_btn_back);
				((CustomImageButton) btn).setId(R.id.btn_navi_back);*/
				((CustomImageButton) btn).setOnClickListener(onClickListener);
			} else if (TextUtils.equals(functions.optString(i), "javascript:closePopupViewer();")) {
				btn = (CustomTextButton) LayoutInflater.from(getActivity()).inflate(R.layout.template_l_button_hu, btnContainer, false);
				((CustomTextButton) btn).setText(labels.optString(i));
				((CustomTextButton) btn).setId(R.id.btn_navi_back);
				((CustomTextButton) btn).setOnClickListener(onClickListener);
			} else if (TextUtils.equals(labels.optString(i), "MENU")) {
				if (m_bSearchMode) {
					return;
				}
				btn = (CustomTextButton) LayoutInflater.from(getActivity())
						.inflate(R.layout.template_menu_btn_hu, btnContainer, false);
				btn = new CustomImageButton(getActivity());
				/*((CustomImageButton) btn).setBackgroundResource(R.drawable.style_btn_menu);*/
				((CustomImageButton) btn).setOnClickListener(onClickListener);
			} else {
				if (TextUtils.equals(position, "L")) {
					btn = (CustomTextButton) LayoutInflater.from(getActivity()).inflate(R.layout.template_l_button_hu, btnContainer, false);
				} else {
					btn = (CustomTextButton) LayoutInflater.from(getActivity()).inflate(R.layout.template_r_button_hu, btnContainer, false);
				}
				((CustomTextButton) btn).setText(labels.optString(i));
				((CustomTextButton) btn).setOnClickListener(onClickListener);
			}
			btnContainer.addView((View) btn);
			btn.setOnclickFunction(function);
			/*if (TextUtils.equals(position, "L")) {
				LinearLayout.LayoutParams p = (LayoutParams) ((View) btn).getLayoutParams();
				p.leftMargin = (int) PixelUtils.getDip(getResources(), 5);
				((View) btn).setLayoutParams(p);
			} else {
				LinearLayout.LayoutParams p = (LayoutParams) ((View) btn).getLayoutParams();
				p.rightMargin = (int) PixelUtils.getDip(getResources(), 5);
				((View) btn).setLayoutParams(p);
			}*/
		}
	}
	
	public void setWebviewTabbar(JSONArray data) {
		// 샘플 데이타: [3, ["Btn1", "Btn2", "Btn3"], ["javascript:alert(1);",
		// "javascript:alert(2);", "javascript:alert(3);"]]
		setTabInitiation(true);
		CustomTabHost tabHost = getTabHost();

		if (tabHost.getTabWidget().getChildCount() > 0) {
			tabHost.setCurrentTab(0);
			tabHost.clearAllTabs();
		}
		int count = data.optInt(0);
		JSONArray labels = data.optJSONArray(1);
		JSONArray images = data.optJSONArray(2);
		JSONArray functions = data.optJSONArray(3);
		tabHost.setFunctions(functions);
		tabHost.getTabContentView().addView(
				LayoutInflater.from(getActivity()).inflate(R.layout.dummy, tabHost.getTabContentView(), false));

		for (int i = 0; i < count; i++) {
			String imageName;
			imageName = (images.optString(i)).split("\\.")[0].trim();
//			imageName = "ico_org";
			int imageId = getResources().getIdentifier("style_" + imageName, "drawable", getActivity().getPackageName());
			tabHost.addTab(createTab(tabHost, String.valueOf(i), labels.optString(i), imageId));
		}

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.style_tab_background);
			tabHost.getTabWidget().setStripEnabled(true);
			tabHost.getTabWidget().setRightStripDrawable(R.drawable.tab_bottom);
			tabHost.getTabWidget().setLeftStripDrawable(R.drawable.tab_bottom);
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);

			if (MainModel.getInstance().isTablet()) {
				tv.setPadding(0, 0, 0, 5);
			} else {
				tv.setPadding(0, 0, 0, 15);
			}
			tv.setTextColor(getResources().getColor(R.color.menu_text_color_up));
		}

		tabHost.setCurrentTab(getLastSelectedTabIndex());
		tabHost.setVisibility(View.VISIBLE);
		setTabInitiation(false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mWebView != null) {
			mWebView.getPluginManager().onDestroy();
			mWebView.getEngine().destroy();
			mWebView = null;
		}
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
//			switch (v.getId()) {
//			case R.id.ID_WEBVIEW:
				Debug.trace("메인 코도바뷰에서 BackKey 눌림");
				// WebViewContainer에서 Back키를 탐색하여 찾는다.
				View backButton = MainModel.getInstance().findBackButton(m_naviLayout);
				if (backButton != null && backButton instanceof IFunctionAble) {
					mWebView.loadUrl(((IFunctionAble) backButton).getOnclickFunction());
				} else {

				}
//				break;
//			}
		}
		return false;
	}

	@Override
	public boolean onPreBackKeyPressed() {
		boolean bRet = false;
		
		Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.ID_LAY_L_SUB_CONTENT);
		if (fragment != null && fragment instanceof SpContentsDetailFragment) {
			((SpContentsDetailFragment) fragment).showContents();
			mWebView.clearHistory();
		}
		else {
			if (!mWebView.canGoBack()) {
				bRet = true;
			} 
		}
		return bRet;
	}
	
	public CustomTabHost getTabHost() {
		return m_tabHost;
	}
	
	private TabSpec createTab(CustomTabHost tabHost, final String tag, final String title, final int drawable) {
		return tabHost.newTabSpec(tag).setIndicator(title, getResources().getDrawable(drawable)).setContent(R.id.dummy_view);
	}
}
