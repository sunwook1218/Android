package com.hs.mobile.gw.openapi.squareplus;

import java.io.InputStream;
import java.util.Map;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.openapi.GWOpenApiEx;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.content.Context;

public abstract class SpSquarePlusThumnailOpenApiEx extends GWOpenApiEx<InputStream> {
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

	public SpSquarePlusThumnailOpenApiEx(final Context context) {
		super(context);
	}

	@Override
	public void load(Context context, AjaxCallback<InputStream> callBack, Map<String, String> params) {
		if (params != null) {
			params.put("K", HMGWServiceHelper.key);
			params.put("dataType", getDataType().getType());
			params.put("openapipath",getOpenApiPath());
		}
		super.load(context, callBack, params);
	}
}