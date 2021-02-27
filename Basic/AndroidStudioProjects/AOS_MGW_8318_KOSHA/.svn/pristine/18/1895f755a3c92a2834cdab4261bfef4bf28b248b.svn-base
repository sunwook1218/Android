package com.hs.mobile.gw.openapi.square;

import java.util.ArrayList;

import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO;

public class MyWorkGroupMenuListResult extends SquareDefaultCallback {
	public ArrayList<MyWorkGroupMenuListItemVO> dataList = new ArrayList<MyWorkGroupMenuListItemVO>();

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (responseHead != null && responseHead.resultCode == SUCCESS) {
			if (responseData != null && responseData.dataList != null) {
				for (int i = 0; i < responseData.dataList.length(); ++i) {
					dataList.add(new MyWorkGroupMenuListItemVO(responseData.dataList.optJSONObject(i)));
				}
			}
		}
	}
}