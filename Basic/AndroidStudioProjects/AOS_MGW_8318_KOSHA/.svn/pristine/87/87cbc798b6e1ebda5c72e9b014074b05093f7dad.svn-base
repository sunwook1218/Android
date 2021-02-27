package com.hs.mobile.gw.openapi;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class Counts extends GWOpenApiEx<JSONObject> {

	public Counts(Context context) {
		super(context);
	}

	@Override
	protected String getEndPoint() {
		return "/rest/openapi/counts";
	}
	
	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().post(getUrl(), new JSONObject(), JSONObject.class, callBack);
	}

}
