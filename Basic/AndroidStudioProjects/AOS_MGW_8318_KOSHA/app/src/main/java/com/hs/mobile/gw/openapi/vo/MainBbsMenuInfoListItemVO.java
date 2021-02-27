package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class MainBbsMenuInfoListItemVO extends DefaultVO<JSONObject> {
    public String menu_type;
    public String menu_id;
    public String menu_name;
    public String icon_type;
    public String icon_name;
    public String function;

    public MainBbsMenuInfoListItemVO(JSONObject json) {
        super(json);
        menu_type = getJson().optString("menu-type");
        menu_id = getJson().optString("menu-id");
        menu_name = getJson().optString("menu-name");
        icon_type = getJson().optString("icon-type");
        icon_name = getJson().optString("icon-name");
        function = getJson().optString("function");
    }
}
