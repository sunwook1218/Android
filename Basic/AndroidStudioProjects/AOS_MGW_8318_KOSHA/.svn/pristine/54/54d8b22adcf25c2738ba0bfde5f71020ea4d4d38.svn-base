package com.hs.mobile.gw.openapi.squareplus;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import com.hs.mobile.gw.openapi.GWOpenApi;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.RestAPI;

public abstract class SpSquarePlusOpenApi extends GWOpenApi {
	public enum DataType {
		JSON("json"), MULTIPART("multipart");
		private String m_strType;

		private DataType(String s) {
			m_strType = s;
		}

		public String getType() {
			return m_strType;
		}
	}

	protected abstract String getOpenApiPath();

	@Override
	protected String getEndPoint() {
		return getDataType() == DataType.JSON ? "/rest/openapi/bypass/stream" : "/rest/openapi/bypass/multipart2stream";
	}

	public abstract DataType getDataType();

	public SpSquarePlusOpenApi() {
		super();
	}

	@Override
	protected ArrayList<BasicNameValuePair> makePostParams(String... params) {
		Debug.trace("getOpenApiPath()", getOpenApiPath());
		ArrayList<BasicNameValuePair> p = new ArrayList<BasicNameValuePair>();
		p.addAll(super.makePostParams(params));
		p.add(new BasicNameValuePair("userID", HMGWServiceHelper.userId));
		p.add(new BasicNameValuePair("K", HMGWServiceHelper.key));
		p.add(new BasicNameValuePair("dataType", getDataType().getType()));
		p.add(new BasicNameValuePair("openapipath", getOpenApiPath()));
		Debug.trace("postParam:", RestAPI.printPostParam(p));
		return p;
	}

	@Override
	protected String makeGetParams(String... params) throws UnsupportedEncodingException {
		return super.makeGetParams(params) + "&userID=" + HMGWServiceHelper.userId + "&K=" + HMGWServiceHelper.key + "&openapipath="
				+ getOpenApiPath();
	}
}