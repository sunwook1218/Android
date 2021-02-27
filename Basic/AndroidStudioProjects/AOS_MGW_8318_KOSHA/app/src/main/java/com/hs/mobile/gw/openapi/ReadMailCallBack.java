package com.hs.mobile.gw.openapi;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.vo.ReadMailResultVO;

public class ReadMailCallBack extends GWDefaultAjaxCallBack<ReadMailResultVO, JSONObject> {

	public ReadMailCallBack() {
		super(ReadMailResultVO.class, JSONObject.class);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
	}
}
