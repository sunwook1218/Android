package com.hs.mobile.gw.fragment;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.CustomTabHost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class WebViewFragment extends CordovaWebViewFragment {
	private RelativeLayout m_navibarContainer;
	private TextView m_tvTitle;
	private LinearLayout m_toolBar;
	private FrameLayout m_webViewContainer;
	private CustomTabHost m_tabHost;
	private View m_btnTogleMiddleList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		super.createHead(v); // create sub header
//		getWebView().loadUrl("javascript:var APP_INFO_SERVER='" + OpenAPI.SERVER_URL + "'");
		getWebView().loadUrl("javascript:initializeServerInfo('" + HMGWServiceHelper.OpenAPI.SERVER_URL + "')");
		getWebView().loadUrl(CustomWebViewFragment.FILE_ANDROID_ASSET_WWW_MOBILE_INDEX_HTML); // tkofs
		m_webViewContainer = ((FrameLayout) v.findViewById(R.id.cordovaWebViewContainer));
		m_webViewContainer.addView(getWebView().getView(), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		m_navibarContainer = (RelativeLayout) v.findViewById(R.id.ID_LAY_R_NAVIBAR_CONTAINER);
		m_tvTitle = (TextView) v.findViewById(R.id.navibarTitle);
		m_toolBar = (LinearLayout) v.findViewById(R.id.webviewToolBar);
		m_tabHost = (CustomTabHost) v.findViewById(R.id.tabhost);
		m_tabHost.setup();
		m_tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				if (MainModel.getInstance().isTabInitiation())
					return;
				MainModel.getInstance().setLastSelectedTabIndex(Integer.parseInt(tabId));
				String jsFunction = m_tabHost.getFunction(MainModel.getInstance().getLastSelectedTabIndex());
				loadUrl(jsFunction);
			}
		});
		if (MainModel.getInstance().isTablet()) {
			m_btnTogleMiddleList =v.findViewById(R.id.toggMiddleleListView);
			m_btnTogleMiddleList.setSelected(false);
			m_btnTogleMiddleList.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					MainFragment.getController().toggMiddleleListView(v);
				}
			});
		}
		return v;
	}

	@Override
	public void loadUrl(String url) {
		Debug.trace("Load in WebFragment:", url);
		super.loadUrl(url);
	}

	public View getBtnTogleMiddleList() {
		return m_btnTogleMiddleList;
	}

	@Override
	public int getRootViewResourceId() {
		if (MainModel.getInstance().isTablet()) {
			return R.layout.webview_container_tablet;
		} else {
			return R.layout.webview_container;
		}
	}

	public ViewGroup getNaviBarContainer() {
		return m_navibarContainer;
	}

	public TextView getTvNaviBarTitle() {
		return m_tvTitle;
	}

	public ViewGroup getWebviewToolBar() {
		return m_toolBar;
	}

	public FrameLayout getWebViewContainer() {
		return m_webViewContainer;
	}

	public CustomTabHost getTabHost() {
		return m_tabHost;
	}

}
