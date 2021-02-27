package com.hs.mobile.gw.openapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.Response;

import android.content.Context;

public class OrgTree extends GWOpenApi {

	@Override
	protected String getEndPoint() {
		return "/rest/openapi/bypass/json";
	}

	@Override
	protected String[] getParams() {
		return new String[] { "target", "acton", "todo", "scope", "base", "key"};
	}

	@Override
	public Response load(Context context, String... params) {
		ArrayList<BasicNameValuePair> paramArrayList = makePostParams(params);
		paramArrayList.add(new BasicNameValuePair("openapipath","/jsp/openapi/OpenApi.jsp"));
		try {
			return NetUtils.requestPost(context, getOkhttpClient(), getUrl(), getTag(), paramArrayList);
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}
}