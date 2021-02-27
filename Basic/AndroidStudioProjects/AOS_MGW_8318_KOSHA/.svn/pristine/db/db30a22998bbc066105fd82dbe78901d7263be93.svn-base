package com.hs.mobile.gw.util;

import android.app.Activity;

public class PopupUtilFactory {
	private PopupUtil popupUtil;
	private Activity mActivity;
	private String mStrTitle;
	private String mStrPositiveButtonText;
	private String mStrNagativeButtonText;

	public PopupUtilFactory(Activity a) {
		mActivity = a;
	}

	public PopupUtil create() {
		popupUtil = new PopupUtil();
		return popupUtil;
	}

	public PopupUtil build() {
		return null;
	}

	public PopupUtilFactory setTitle(String string) {
		mStrTitle = string;
		return this;
	}

	public PopupUtilFactory setPositiveButtonText(String string) {
		mStrPositiveButtonText = string;
		return this;
	}

	public PopupUtilFactory setNegativeButtonText(String string) {
		mStrNagativeButtonText = string;
		return this;
	}
}