package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class AdditionalOfficerItemVO extends DefaultVO<JSONObject> {
	public String department;
	public String name;
	public String empcode;
	public String id;
	public String username;

	public AdditionalOfficerItemVO(JSONObject json) {
		super(json);
		department = getJson().optString("department");
		name = getJson().optString("name");
		empcode = getJson().optString("empcode");
		id = getJson().optString("id");
		username = department + "." + name;
	}

}
