package com.hs.mobile.gw.view;

import com.hs.mobile.gw.hsuco.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class PopupWebViewContainer extends LinearLayout {

	private FrameLayout m_popupWebViewContainer;

	public PopupWebViewContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.popup_webview_container, this);
		m_popupWebViewContainer = (FrameLayout) findViewById(R.id.cordovaWebViewContainer);
	}

	public PopupWebViewContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PopupWebViewContainer(Context context) {
		super(context);
		initView();
	}

	public FrameLayout getPopupWebViewContainer() {
		return m_popupWebViewContainer;
	}

}
