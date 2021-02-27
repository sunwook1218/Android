package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class MainBbsMenuInfoItemVO extends DefaultVO<JSONObject> {
    public MainBbsMenuInfoListVO visible_menu_list;
    public MainBbsMenuInfoListVO hidden_menu_list;

    public MainBbsMenuInfoItemVO(JSONObject json) {
        super(json);
        visible_menu_list = new MainBbsMenuInfoListVO(getJson().optJSONArray("visible-menu-list"));
        hidden_menu_list = new MainBbsMenuInfoListVO(getJson().optJSONArray("hidden-menu-list"));
    }
}
