package com.hs.mobile.gw.openapi;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.vo.BbsMenuInfoVO;

import org.json.JSONArray;

public class BbsMenuInfoCallBack extends GWDefaultAjaxCallBack<BbsMenuInfoVO, JSONArray> {

    public BbsMenuInfoCallBack() {
        super(BbsMenuInfoVO.class, JSONArray.class);
    }

    @Override
    public void callback(String url, JSONArray json, AjaxStatus status) {
        super.callback(url, json, status);
    }
}
