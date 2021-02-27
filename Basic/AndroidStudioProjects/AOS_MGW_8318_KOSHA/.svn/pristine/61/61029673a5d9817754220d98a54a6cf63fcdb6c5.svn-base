package com.hs.mobile.gw.ext.otp;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

import org.json.JSONObject;

public class OtpAuthVO extends DefaultVO<JSONObject> {
    public String code;
    public String message;

    public OtpAuthVO(JSONObject json) {
        super(json);
        ViewModel.Log("::: " + json.toString(), "tkofs_otp");
        if (json != null) {
            code = json.optString("code");
            message = json.optString("message");
        }
    }
}
