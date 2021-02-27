package com.hs.mobile.gw.openapi;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class BoardNoticeList extends GWOpenApiEx<JSONObject> {

	// tkofs 추가
	String endPoint = "";
	public BoardNoticeList(Context context, String endpoint) {
		this(context);
		this.endPoint = endpoint;
	}

	public BoardNoticeList(Context context) {
		super(context);
		this.endPoint = "/rest/openapi/board/noticelist";
	}

	@Override
	protected String getEndPoint() {
		return endPoint;
	}

	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params, JSONObject.class, callBack);	// tkofs
	}
}
