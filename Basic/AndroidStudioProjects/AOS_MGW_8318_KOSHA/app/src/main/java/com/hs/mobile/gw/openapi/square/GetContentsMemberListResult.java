package com.hs.mobile.gw.openapi.square;

import java.util.ArrayList;

import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;

public class GetContentsMemberListResult extends SquareDefaultCallback {
	public ArrayList<ContentsMemberListItemVO> dataList = new ArrayList<ContentsMemberListItemVO>();

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (responseData != null && responseData.dataList != null) {

			for (int i = 0; i < responseData.dataList.length(); ++i) {
				dataList.add(new ContentsMemberListItemVO(responseData.dataList.optJSONObject(i)));
			}
		}
	}
}