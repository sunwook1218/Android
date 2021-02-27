package com.hs.mobile.gw.openapi;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.vo.MainBbsMenuInfoVO;

import org.json.JSONArray;

public class MainBbsMenuInfoCallBack extends GWDefaultAjaxCallBack<MainBbsMenuInfoVO, JSONArray> {

    public MainBbsMenuInfoCallBack() {
        super(MainBbsMenuInfoVO.class, JSONArray.class);
    }

    @Override
    public void callback(String url, JSONArray json, AjaxStatus status) {
        super.callback(url, json, status);
    }
}