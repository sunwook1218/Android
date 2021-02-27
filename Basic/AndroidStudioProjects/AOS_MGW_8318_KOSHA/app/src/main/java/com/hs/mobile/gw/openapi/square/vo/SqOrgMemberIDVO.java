package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;

import org.json.JSONObject;

public class SqOrgMemberIDVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String type;
	public String id;
	public boolean user;
	public boolean dept;

	public SqOrgMemberIDVO(JSONObject json) {
		if (json == null) {
			return;
		}
		type = json.optString("type");
		id = json.optString("id");
		user = json.optBoolean("user");
		dept = json.optBoolean("dept");
	}
}