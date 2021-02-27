package com.hs.mobile.gw.openapi.square;

import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.openapi.GWOpenApiEx;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.content.Context;

public abstract class DocHandlerApi extends GWOpenApiEx<JSONObject> {
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
		return "/rest/doc/handle";
	}

	public abstract DataType getDataType();

	public DocHandlerApi(final Context context) {
		super(context);
	}

	@Override
	public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
	}
}