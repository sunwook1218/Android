package com.hs.mobile.gw.fragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.WebViewBridge;

public abstract class CordovaWebViewFragment extends CommonFragment implements CordovaInterface {
	private CordovaWebView m_webView;
	// Plugin to call when activity result is received
	protected CordovaPlugin activityResultCallback = null;
	protected boolean activityResultKeepRunning;

	// Keep app running when pause is received. (default = true)
	// If true, then the JavaScript and native code continue to run in the
	// background
	// when another application (activity) is started.
	protected boolean keepRunning = true;
	private final ExecutorService threadPool = Executors.newCachedThreadPool();

	public abstract int getRootViewResourceId();

	public CordovaWebView getWebView() {
		return m_webView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LayoutInflater localInflater = inflater.cloneInContext(new CordovaContext(getActivity(), this));
		View rootView = localInflater.inflate(getRootViewResourceId(), container, false);
		initWebView();
		return rootView;
	}

	private void initWebView() {
		CordovaWebViewEngine cordovaWebViewEngine = CordovaWebViewImpl.createEngine(getActivity(), MainModel
				.getInstance().getCordovaPreferences());
		m_webView = new CordovaWebViewImpl(cordovaWebViewEngine);
		CordovaInterface cordovainterface = new CordovaInterfaceImpl(getActivity());
		m_webView.init(cordovainterface, MainModel.getInstance().getCordovaPluginEntrys(), MainModel.getInstance().getCordovaPreferences());
		/*
        MGW-715 조직도에서 검색할때 두번 터치해야 검색 되는 오류
        키보드를 명시적으로 닫은 후 터치 이벤트 전달하는 방식. 개선 필요
         */
        m_webView.getView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
		((WebView) m_webView.getEngine().getView()).getSettings().setJavaScriptEnabled(true);
		((WebView) m_webView.getEngine().getView()).getSettings().setLoadWithOverviewMode(true);
		((WebView) m_webView.getEngine().getView()).getSettings().setUseWideViewPort(true);
		((WebView) m_webView.getEngine().getView()).getSettings().setDomStorageEnabled(true);
		((WebView) m_webView.getEngine().getView()).getSettings().setBuiltInZoomControls(true);
		((WebView) m_webView.getEngine().getView()).getSettings().setDisplayZoomControls(false);
		((WebView) m_webView.getEngine().getView()).getSettings().setSupportZoom(true);
		((WebView) m_webView.getEngine().getView()).getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		((WebView) m_webView.getEngine().getView()).getSettings().setTextZoom(100);
		((WebView) m_webView.getEngine().getView()).addJavascriptInterface(new WebViewBridge((WebView) getWebView().getEngine().getView()), "WebViewBridge");
		((WebView) m_webView.getEngine().getView()).setWebViewClient(new SystemWebViewClient((SystemWebViewEngine) m_webView.getEngine()) {
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
		});
		((WebView) m_webView.getEngine().getView()).setWebChromeClient(new SystemWebChromeClient((SystemWebViewEngine) m_webView
				.getEngine()) {
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
		});
	}

	public void loadUrl(final String url) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				m_webView.loadUrl(url);
				getWebView().getView().requestFocus();
			}
		});
	}

	public Object onMessage(String id, Object data) {
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (m_webView.getPluginManager() != null) {
			m_webView.getPluginManager().onDestroy();
			m_webView.getEngine().destroy();
			m_webView = null;
		}
	}

	@Override
	public ExecutorService getThreadPool() {
		return threadPool;
	}

	@Override
	public void setActivityResultCallback(CordovaPlugin plugin) {
		this.activityResultCallback = plugin;
	}

	public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
		this.activityResultCallback = command;
		this.activityResultKeepRunning = this.keepRunning;

		// If multitasking turned on, then disable it for activities that return
		// results
		if (command != null) {
			this.keepRunning = false;
		}

		// Start activity
		super.startActivityForResult(intent, requestCode);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		CordovaPlugin callback = this.activityResultCallback;
		if (callback != null) {
			callback.onActivityResult(requestCode, resultCode, intent);
		}
	}

	private class CordovaContext extends ContextWrapper implements CordovaInterface {
		CordovaInterface cordova;

		public CordovaContext(Context base, CordovaInterface cordova) {
			super(base);
			this.cordova = cordova;
		}

		public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
			cordova.startActivityForResult(command, intent, requestCode);
		}

		public void setActivityResultCallback(CordovaPlugin plugin) {
			cordova.setActivityResultCallback(plugin);
		}

		public Activity getActivity() {
			return cordova.getActivity();
		}

		public Object onMessage(String id, Object data) {
			return cordova.onMessage(id, data);
		}

		public ExecutorService getThreadPool() {
			return cordova.getThreadPool();
		}
	}
}