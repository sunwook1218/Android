package com.hs.mobile.gw.ext.otp;

import android.content.Context;

import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.openapi.GWOpenApiEx;

import org.json.JSONObject;

import java.util.Map;

public class OtpAuth extends GWOpenApiEx<JSONObject> {

    String endPoint = "/rest/openapi/otp/auth";

    public OtpAuth(Context context) {
        super(context);
    }

    @Override
    protected String getEndPoint() {
        return endPoint;
    }

    @Override
    public void load(Context context, AjaxCallback<JSONObject> callBack, Map<String, String> params) {
        super.load(context, callBack, params);
        getAq().ajax(getUrl(), params, JSONObject.class, callBack);
    }
}
