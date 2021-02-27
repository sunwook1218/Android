package com.hs.mobile.gw.openapi.squareplus.callback;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpUserSettingsVO;

import android.app.Activity;

public class SpUserSettingsCallBack extends SpSquarePlusDefaultAjaxCallBack<SpUserSettingsVO> {
	public SpUserSettingsVO item;

	public SpUserSettingsCallBack(Activity a, Class<SpUserSettingsVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS) {
			if(responseData != null) {
				item = responseData.item;
			}
		}
	}
}
