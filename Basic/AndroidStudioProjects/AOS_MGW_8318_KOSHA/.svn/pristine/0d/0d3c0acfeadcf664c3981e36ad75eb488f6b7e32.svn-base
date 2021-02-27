package com.hs.mobile.gw.openapi.rest.auth;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.GWDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.vo.LoginVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;

public class LoginCallBack extends GWDefaultAjaxCallBack<LoginVO, JSONObject> {

	public LoginCallBack() {
		super(LoginVO.class, JSONObject.class);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if (getVO() != null && getVO().code != null) {
			// 뭔가 에러가 생긴 경우
			Debug.trace(getVO().code.name());
		} else {
			Debug.trace("getVO().code is null.");
			// 쿠키 값을 저장한다.
		}
		if(status != null && status.getCookies() != null)
		{
			HMGWServiceHelper.cookies = status.getCookies();
		}
	}
}
