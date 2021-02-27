package com.hs.mobile.gw.openapi.square;

import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;

public class GroupInfoResult extends SquareDefaultCallback {

	public GroupInfoVO m_data;

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (responseData != null) {
			m_data = new GroupInfoVO(getJsonObject().optJSONObject("responseData"));
		}
	}
}