package com.hs.mobile.gw.openapi;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusOpenApiEx.DataType;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.HMGWServiceHelper.OpenAPI;
import com.hs.mobile.gw.util.RestApiEx;

import android.content.Context;
import android.text.TextUtils;

public abstract class GWOpenApiEx<J> extends RestApiEx<J> {
	public static String BYPASS_STREAM = Define.SERVER_URL + "/rest/openapi/bypass/stream";
	public static String BYPASS_JSON = Define.SERVER_URL + "/rest/openapi/bypass/json";

	@Override
	protected String getHostUrl() {
		if (TextUtils.isEmpty(HMGWServiceHelper.OpenAPI.SERVER_URL)) {
			new HMGWServiceHelper.OpenAPI();
		}
		return OpenAPI.SERVER_URL;
	}

	public GWOpenApiEx(Context context) {
		super(context);
	}

	public GWOpenApiEx(Context context, DataType type) {
		super(context, type);
	}
}
