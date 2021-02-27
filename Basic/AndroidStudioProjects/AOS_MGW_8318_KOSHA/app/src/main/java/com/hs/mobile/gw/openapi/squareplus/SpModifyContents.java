package com.hs.mobile.gw.openapi.squareplus;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class SpModifyContents extends SpSquarePlusOpenApiEx {

	public SpModifyContents(Context context) {
		super(context, DataType.MULTIPART);
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/modifyContents.do";
	}
	
	@Override
	public void loadMultipart(Context context, AjaxCallback<JSONObject> callBack, Map<String, Object> params) {
		super.loadMultipart(context, callBack, params);
		getAq().ajax(getUrl(), params, JSONObject.class, callBack);
	}

	@Override
	public DataType getDataType() {
		return DataType.MULTIPART;
	}

}
