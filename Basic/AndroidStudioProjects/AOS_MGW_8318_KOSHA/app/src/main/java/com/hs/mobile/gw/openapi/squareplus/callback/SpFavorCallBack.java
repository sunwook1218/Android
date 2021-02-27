package com.hs.mobile.gw.openapi.squareplus.callback;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFavorVO;

import android.app.Activity;

public class SpFavorCallBack extends SpSquarePlusDefaultAjaxCallBack<SpFavorVO> {
	public SpFavorVO item;

	public SpFavorCallBack(Activity a, Class<SpFavorVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS){
			if(responseData != null){
				this.item = responseData.item;
			}
		}
	}
}
