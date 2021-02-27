package com.hs.mobile.gw.openapi.squareplus.callback;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;

import android.app.Activity;

public class SpAttachCallBack extends SpSquarePlusDefaultAjaxCallBack<SpAttachVO> {
	public SpAttachVO item;

	public SpAttachCallBack(Activity a, Class<SpAttachVO> cls) {
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
