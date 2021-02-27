package com.hs.mobile.gw.openapi.rest.auth;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.openapi.GWOpenApiEx;

import android.content.Context;

public class Login extends GWOpenApiEx<JSONObject> {

	public Login(Context context) {
		super(context);
	}

	@Override
	protected String getEndPoint() {
		return "/rest/auth/login";
	}

	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack,  Map<String,String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(),params, JSONObject.class, callBack);
	}

}