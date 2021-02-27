package com.hs.mobile.gw;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.engine.SystemWebChromeClient;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.IntentUtils;

public class WebViewHelper {

	/**
	 * CordovaWeb 설정
	 * 
	 * @param
	 * @return void
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public static void initCordovarWebView(final MainActivity activity, CordovaWebView webView) {
		webView.getView().setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_UP:
					if (!v.hasFocus()) {
						v.requestFocus();
					}
					break;
				}
				return false;
			}
		});
		SystemWebViewEngine systemWebViewEngine = (SystemWebViewEngine) webView.getEngine();
		SystemWebView wV = (SystemWebView) systemWebViewEngine.getView();
		wV.getSettings().setJavaScriptEnabled(true);
		wV.getSettings().setAppCacheEnabled(false);
		wV.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		wV.getSettings().setUseWideViewPort(true);
		wV.setWebViewClient(new SystemWebViewClient(systemWebViewEngine) {

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
					activity.startActivity(call_phone);
					return true;
				} else if (url.startsWith("mailto:")) {
					return true;
				} else if (url.startsWith("sms:")) {
					Intent sms_phone = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
					activity.startActivity(sms_phone);
					return true;
				} else if (url.endsWith("apk") || url.endsWith("APK")) {
					Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					activity.startActivity(web);
					return true;
				} else if (url.startsWith("http:") || url.startsWith("https:")) {
					view.loadUrl(url);
					return true;
				}

				// return false : WebView 자체 처리, true : 기본 어플리케이션이 처리
				return false;
			}
		});

		wV.setWebChromeClient(new SystemWebChromeClient(systemWebViewEngine) {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
				new AlertDialog.Builder(view.getContext()).setTitle(R.string.app_name).setMessage(message)
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
				new AlertDialog.Builder(view.getContext()).setTitle(R.string.app_name).setMessage(message)
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

	@SuppressLint("SetJavaScriptEnabled")
	public static void initCordovarPopupWebView(final MainActivity activity, CordovaWebView popupWebView) {
		popupWebView.getView().setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_UP:
					if (!v.hasFocus()) {
						v.requestFocus();
					}
					break;
				}
				return false;
			}
		});
		SystemWebViewEngine systemWebViewEngine = (SystemWebViewEngine) popupWebView.getEngine();
		SystemWebView wV = (SystemWebView) systemWebViewEngine.getView();
		wV.getSettings().setJavaScriptEnabled(true);
		wV.getSettings().setAppCacheEnabled(false);
		wV.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

		wV.setWebViewClient(new SystemWebViewClient(systemWebViewEngine) {

			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
				Debug.trace("onLoadResource popupWebView url[" + url + "]");
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				Debug.trace("onPageStarted popupWebView url[" + url + "]");
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
					activity.startActivity(call_phone);
					return true;
				} else if (url.startsWith("mailto:")) {
					return true;
				} else if (url.startsWith("sms:")) {
					Intent sms_phone = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
					activity.startActivity(sms_phone);
					return true;
				} else if (url.endsWith("apk") || url.endsWith("APK")) {
					Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					activity.startActivity(web);
					return true;
				} else if (url.startsWith("http:") || url.startsWith("https:")) {
					view.loadUrl(url);
					return true;
				}
				// return false : WebView 자체 처리, true : 기본 어플리케이션이 처리
				return false;
			}
		});

		wV.setWebChromeClient(new SystemWebChromeClient(systemWebViewEngine) {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
				new AlertDialog.Builder(view.getContext()).setTitle(R.string.app_name).setMessage(message)
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
				new AlertDialog.Builder(view.getContext()).setTitle(R.string.app_name).setMessage(message)
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

	@SuppressLint("SetJavaScriptEnabled")
	public static void initCustomWebView(final MainActivity activity, WebView customWebView) {
		customWebView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_UP:
					if (!v.hasFocus()) {
						v.requestFocus();
					}
					break;
				}
				return false;
			}
		});
		customWebView.getSettings().setJavaScriptEnabled(true);
		customWebView.getSettings().setAppCacheEnabled(false);
		customWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

		customWebView.setWebViewClient(new WebViewClient() {

			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
				Debug.trace("onLoadResource customWebView url[" + url + "]");
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				Debug.trace("onPageStarted customWebView url[" + url + "]");
//				MainActivity.loadingProgress(false);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				Debug.trace("onPageFinished customWebView url[" + url + "]");
//				MainActivity.loadingProgress(true);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
				Debug.trace("onReceivedError customWebView failingUrl[" + failingUrl + "]");
//				MainActivity.loadingProgress(true);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("tel:")) {
					url = url.replaceAll("#", "%23");
					Intent call_phone = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
					activity.startActivity(call_phone);
					return true;
				} else if (url.startsWith("mailto:")) {
					return true;
				} else if (url.startsWith("sms:")) {
					Intent sms_phone = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
					activity.startActivity(sms_phone);
					return true;
				} else if (url.endsWith("apk") || url.endsWith("APK")) {
					Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					activity.startActivity(web);
					return true;
				} else if (url.startsWith("http:") || url.startsWith("https:")) {
					view.loadUrl(url);
					return true;
				} else if (url.startsWith("showmenubyid://")) {
					String menuID = url.substring(15);
					Debug.trace("menuID : " + menuID);
					if (menuID.length() > 0)
						MainFragment.showMenuItem(menuID);
					return true;
				} else if (url.startsWith("startapp://")) {
					String packageName = url.substring(11);
					Debug.trace("packageName : " + packageName);

					if (packageName.length() > 0)
						IntentUtils.startApp(activity, packageName);

					return true;
				}

				return false;
			}
		});

		customWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
				new AlertDialog.Builder(view.getContext()).setTitle(R.string.app_name).setMessage(message)
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
				new AlertDialog.Builder(view.getContext()).setTitle(R.string.app_name).setMessage(message)
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
}
