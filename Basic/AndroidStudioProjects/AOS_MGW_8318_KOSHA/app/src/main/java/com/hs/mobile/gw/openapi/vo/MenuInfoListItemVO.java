package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class MenuInfoListItemVO extends DefaultVO<JSONObject> {

	public String menu_name;
	public String menu_id;
	public String icon_name;
	public String icon_type;
	public String menu_type;

	public MenuInfoListItemVO(JSONObject json) {
		super(json);
		menu_name = getJson().optString("menu-name");
		menu_id = getJson().optString("menu-id");
		icon_name = getJson().optString("icon-name");
		icon_type = getJson().optString("icon-type");
		menu_type = getJson().optString("menu-type");
	}

}
