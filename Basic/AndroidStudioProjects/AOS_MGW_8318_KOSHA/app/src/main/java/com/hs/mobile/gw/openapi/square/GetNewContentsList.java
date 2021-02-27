package com.hs.mobile.gw.openapi.square;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class GetNewContentsList extends SquareOpenApiEx {

	public GetNewContentsList(Context context) {
		super(context);
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/getNewContentsList.do";
	}

	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}
	
	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params,JSONObject.class, callBack);
	}

}
