package com.hs.mobile.gw.openapi;

import android.content.Context;

import com.androidquery.callback.AjaxCallback;

import org.json.JSONArray;

import java.util.Map;

public class BoardNoticeBbsFolderList extends GWOpenApiEx<JSONArray> {

	public String endPoint = "/rest/openapi/board/favfolder";

	public BoardNoticeBbsFolderList(Context context) {
		super(context);
	}

	@Override
	protected String getEndPoint() {
		return endPoint;
	}

	@Override
	public void load(Context context, AjaxCallback<JSONArray> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params, JSONArray.class, callBack);	// tkofs
	}
}
