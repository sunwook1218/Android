package com.hs.mobile.gw.openapi;

import java.util.Map;

import org.json.JSONArray;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class IconInfo extends GWOpenApiEx<JSONArray> {

	public IconInfo(Context context) {
		super(context);
	}

	@Override
	protected String getEndPoint() {
		return "/rest/openapi/iconinfo";
	}

	@Override
	public void load(Context context, AjaxCallback<JSONArray> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params, JSONArray.class, callBack);
	}

}
