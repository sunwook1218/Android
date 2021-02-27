package com.hs.mobile.gw.openapi.squareplus;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class SpExpandMySquareMenu extends SpSquarePlusOpenApiEx {
	public SpExpandMySquareMenu(Context context) {
		super(context);
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/expandMySquareMenu.do";
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
