package com.hs.mobile.gw.openapi.square;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;

public class AddMessageResult extends SquareDefaultCallback {

	private MainContentsListItemVO m_data;

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		JSONObject json = getJsonObject().optJSONObject("responseData");
		m_data = new MainContentsListItemVO(json);
	}

	public MainContentsListItemVO getData() {
		return m_data;
	}
}