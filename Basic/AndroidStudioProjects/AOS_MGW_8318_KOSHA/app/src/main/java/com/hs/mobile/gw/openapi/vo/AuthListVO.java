package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class AuthListVO extends DefaultVO<JSONObject> {
	public String id;
	public String auth;
	public String relid;
	public String relcommunityid;

	public AuthListVO(JSONObject optJSONObject) {
		super(optJSONObject);
		id = getJson().optString("id");
		auth = getJson().optString("auth");
		relid = getJson().optString("relid");
		relcommunityid = getJson().optString("relcommunityid");
	}

}
