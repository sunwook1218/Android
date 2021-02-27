package com.hs.mobile.gw.openapi.squareplus;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.openapi.GWOpenApiEx;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;

import android.content.Context;

public abstract class SpSquarePlusOpenApiEx extends GWOpenApiEx<JSONObject> {
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

	public SpSquarePlusOpenApiEx(final Context context) {
		super(context);
	}

	public SpSquarePlusOpenApiEx(final Context context, DataType type) {
		super(context, type);
	}

	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
		if (params != null) {
			params.put("userID", HMGWServiceHelper.userId);
			Debug.trace(" userID = " + HMGWServiceHelper.userId);
			params.put("K", HMGWServiceHelper.key);
//			params.put("dataType", getDataType().getType());
            params.put("dataType", DataType.JSON.getType());
			params.put("openapipath", getOpenApiPath());
		}
		super.load(context, callBack, params);
	}

	@Override
	public void loadMultipart(Context context, AjaxCallback<JSONObject> callBack, Map<String, Object> params) {
		if (params != null) {
			params.put("K", HMGWServiceHelper.key);
//			params.put("dataType", getDataType().getType());
			params.put("dataType", DataType.JSON.getType());
			params.put("openapipath", getOpenApiPath());
		}
		super.loadMultipart(context, callBack, params);
	}
}