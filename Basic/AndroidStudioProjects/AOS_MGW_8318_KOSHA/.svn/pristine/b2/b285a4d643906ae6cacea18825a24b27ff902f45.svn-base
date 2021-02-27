package com.hs.mobile.gw.openapi.square.vo;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class ResponseHeader extends DefaultVO<JSONObject> {
	public int resultCode = -1;
	public String resultMessage;

	public ResponseHeader(JSONObject json) {
		super(json);
		if (getJson() != null && getJson().has("resultCode")) {
			resultCode = Integer.valueOf(getJson().optString("resultCode"));
		}
		if (getJson() != null && getJson().has("resultMessage")) {
			resultMessage = getJson().optString("resultMessage");
		}
	}
}
