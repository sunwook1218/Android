package com.hs.mobile.gw.util;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DefaultCallback implements Callback {
	private String mResponseString;

	@Override
	public void onFailure(String strErr) {
		Debug.trace("onNetworkError!", strErr);
	}

	@Override
	public void onResponse(String strRet) {
		setResponseString(strRet);
	}

	private void setResponseString(String string) {
		mResponseString = string;
	}

	public String getResponseString() {
		return mResponseString;
	}

	public JSONObject getJsonObject() {
		try {
			return new JSONObject(mResponseString);
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}
}
