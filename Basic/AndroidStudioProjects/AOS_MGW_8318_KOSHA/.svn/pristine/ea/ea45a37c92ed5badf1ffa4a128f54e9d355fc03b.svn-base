package com.hs.mobile.gw.openapi;

import java.io.IOException;
import java.net.CookieManager;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.Response;

import android.content.Context;

public class GetDocViewAuth extends GWOpenApi {

	@Override
	protected String getEndPoint() {
		return "/rest/openapi/mail/searchaddress";
	}

	@Override
	protected String[] getParams() {
		return new String[] { "openapipath" };
	}

	@Override
	public Response load(Context context, String... params) {
		getOkhttpClient().setCookieHandler(CookieManager.getDefault());
		try {
			return NetUtils.requestPost(context, getOkhttpClient(), getUrl(), getTag(), makePostParams(params));
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}

}
