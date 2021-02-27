package com.hs.mobile.gw.openapi.vo;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.util.Debug;

/**
 * 
 * @author handy
 *
 * @param <J>
 * J is JSONArray or JSONObject
 */
public class DefaultVO<J> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private J mJson;

	public DefaultVO(J json) {
		if (json == null) {
			return;
		}
		setJson(json);
	}

	public void setJson(J json) {
		mJson = json;
	}

	public J getJson() {
		return mJson;
	}

	public String toString() {
		try {
			if (mJson instanceof JSONObject) {
				return mJson == null ? "" : ((JSONObject) mJson).toString(5);
			} else if (mJson instanceof JSONArray) {
				return mJson == null ? "" : ((JSONArray) mJson).toString(5);
			}
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
		return this.getClass().getName() + ":mJson is null";
	}
}
