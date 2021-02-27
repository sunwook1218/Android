package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class MenuInfoItemVO extends DefaultVO<JSONObject> {
    public String section_id; //  tkofs
    public String section_name;
    public MenuListVO visible_menu_list;
    public MenuListVO hidden_menu_list;
    public boolean open_hidden_menu;

    public MenuInfoItemVO(JSONObject json) {
        super(json);
        section_id = getJson().optString("section-id");
        section_name = getJson().optString("section-name");
        open_hidden_menu = getJson().optBoolean("open-hidden-menu");
        visible_menu_list = new MenuListVO(getJson().optJSONArray("visible-menu-list"));
        visible_menu_list = new MenuListVO(getJson().optJSONArray("hidden-menu-list"));
    }
}
