package com.hs.mobile.gw.openapi.square;

import java.util.ArrayList;

import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;

public class DetailInfoResult extends SquareDefaultCallback {
	public ArrayList<MainContentsListItemVO> dataList = new ArrayList<MainContentsListItemVO>();

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (responseData != null && responseData.dataList != null && responseData.dataList.length() > 0) {
			for (int i = 0; i < responseData.dataList.length(); ++i) {
				dataList.add(new MainContentsListItemVO(responseData.dataList.optJSONObject(i)));
			}
		}
	}
}