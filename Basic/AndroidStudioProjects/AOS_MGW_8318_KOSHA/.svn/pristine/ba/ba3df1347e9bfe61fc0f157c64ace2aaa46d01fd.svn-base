package com.hs.mobile.gw.openapi.square;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO;

public class CreateSquareResult extends SquareDefaultCallback {

	public MyWorkGroupMenuListItemVO m_data;

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if(this.responseHead != null && this.responseHead.resultCode == SUCCESS)
		{
			JSONObject json = getJsonObject().optJSONObject("responseData");
			m_data = new MyWorkGroupMenuListItemVO(json);
		}
	}
}