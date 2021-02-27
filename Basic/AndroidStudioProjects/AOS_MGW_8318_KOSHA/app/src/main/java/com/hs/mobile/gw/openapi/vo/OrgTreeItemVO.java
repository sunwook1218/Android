package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class OrgTreeItemVO extends DefaultVO<JSONObject> {

	private static final long serialVersionUID = 1L;
	public boolean hasChildren;
	public String deptId;
	public String level;
	public String parentId;
	public String deptName;
	public String userCount;

	public OrgTreeItemVO(JSONObject json) {
		super(json);
		hasChildren = getJson().optBoolean("@hasChildren");
		deptId = getJson().optString("@id");
		level = getJson().optString("@level");
		parentId = getJson().optString("@parid");
		deptName = getJson().optString("@text");
		userCount = getJson().optString("@userCount");
	}
}
