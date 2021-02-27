package com.hs.mobile.gw.view;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class WebViewContainer extends LinearLayout {

	public WebViewContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		if (MainModel.getInstance().isTablet()) {
			LayoutInflater.from(getContext()).inflate(R.layout.webview_container_tablet, this);
		} else {
			LayoutInflater.from(getContext()).inflate(R.layout.webview_container, this);
		}		
	}

	public WebViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public WebViewContainer(Context context) {
		super(context);
		initView();
	}

}
