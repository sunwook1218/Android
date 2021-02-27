package com.hs.mobile.gw.openapi;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.HMGWServiceHelper.OpenAPI;
import com.hs.mobile.gw.util.RestAPI;

import android.text.TextUtils;

public abstract class GWOpenApi extends RestAPI {
	public static String BYPASS_STREAM = Define.SERVER_URL + "/rest/openapi/bypass/stream";
	public static String BYPASS_JSON = Define.SERVER_URL + "/rest/openapi/bypass/json";

	@Override
	protected String getHostUrl() {
		if (TextUtils.isEmpty(HMGWServiceHelper.OpenAPI.SERVER_URL)) {
			new HMGWServiceHelper.OpenAPI();
		}
		return OpenAPI.SERVER_URL;
	}

	public GWOpenApi() {
		getOkhttpClient().setConnectTimeout(HMGWServiceHelper.OpenAPI.MAX_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
		getOkhttpClient().setReadTimeout(HMGWServiceHelper.OpenAPI.MAX_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
		getOkhttpClient().setCookieHandler(CookieManager.getDefault());
	}
}