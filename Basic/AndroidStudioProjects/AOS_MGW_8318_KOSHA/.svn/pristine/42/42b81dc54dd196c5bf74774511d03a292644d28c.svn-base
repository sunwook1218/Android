package com.hs.mobile.gw.openapi.square;

import java.util.ArrayList;

import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;

public class GetNewContentsListResult extends SquareDefaultCallback {
	public ArrayList<MainContentsListItemVO> dataList = new ArrayList<MainContentsListItemVO>();

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (responseData != null && responseData.dataList != null) {
			for (int i = 0; i < responseData.dataList.length(); ++i) {
				dataList.add(new MainContentsListItemVO(responseData.dataList.optJSONObject(i)));
			}
		}
	}
}