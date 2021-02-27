package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class IconInfoItemVO extends DefaultVO<JSONObject> {

	public int version;
	public String name;

	public IconInfoItemVO(JSONObject json) {
		super(json);
		version = json.optInt("version");
		name = json.optString("name");
	}

}
