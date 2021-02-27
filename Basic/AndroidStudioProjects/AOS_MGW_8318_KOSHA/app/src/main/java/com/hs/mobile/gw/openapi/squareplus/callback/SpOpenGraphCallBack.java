package com.hs.mobile.gw.openapi.squareplus.callback;

import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpOpenGraphVO;

import android.app.Activity;

public class SpOpenGraphCallBack extends SpSquarePlusDefaultAjaxCallBack<SpOpenGraphVO> {
	public List<SpOpenGraphVO> dataList;

	public SpOpenGraphCallBack(Activity a, Class<SpOpenGraphVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS) {
			if(responseData != null) {
				dataList = responseData.dataList;
			}
		}
	}
}
