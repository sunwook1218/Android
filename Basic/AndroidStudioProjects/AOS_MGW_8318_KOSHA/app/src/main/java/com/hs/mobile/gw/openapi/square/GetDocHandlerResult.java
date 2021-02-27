package com.hs.mobile.gw.openapi.square;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.content.Context;

public class GetDocHandlerResult extends DocHandlerApi {

	public GetDocHandlerResult(Context context) {
		super(context);
	}

	@Override
	protected String getOpenApiPath() {
		return "/rest/doc/handle";
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
