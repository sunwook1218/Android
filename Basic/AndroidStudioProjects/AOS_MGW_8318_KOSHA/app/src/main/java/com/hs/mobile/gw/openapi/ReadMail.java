package com.hs.mobile.gw.openapi;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class ReadMail extends GWOpenApiEx<JSONObject> {

	public ReadMail(Context context) {
		super(context);
	}

	protected String getEndPoint() {
		return null;
	}
	
	@Override
	public String getUrl() {
		return BYPASS_JSON;
	}

	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack,
			Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params, JSONObject.class, callBack);
	}
}