package com.hs.mobile.gw.openapi;

import android.content.Context;

import com.androidquery.callback.AjaxCallback;

import org.json.JSONArray;

import java.util.Map;

public class MainBbsMenuInfo extends GWOpenApiEx<JSONArray> {

    public MainBbsMenuInfo(Context context) {
        super(context);
    }

    @Override
    protected String getEndPoint() {
        return "/rest/openapi/main/mainbbsmenuinfo";
    }

    protected String[] getParams() {
        return new String[]{"locale"};
    }

    @Override
    public void load(Context context, AjaxCallback<JSONArray> callBack, Map<String, String> params) {
        super.load(context, callBack, params);
        getAq().ajax(getUrl(), params, JSONArray.class, callBack);
    }
}