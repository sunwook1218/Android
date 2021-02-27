package com.hs.mobile.gw.openapi.squareplus;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class SpGetFavoriteMemberDir extends SpSquarePlusOpenApiEx {
	public SpGetFavoriteMemberDir(Context context) {
		super(context);
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/getFavoriteMemberDir.do";
	}

	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params, JSONObject.class, callBack);
	}

	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}
}
