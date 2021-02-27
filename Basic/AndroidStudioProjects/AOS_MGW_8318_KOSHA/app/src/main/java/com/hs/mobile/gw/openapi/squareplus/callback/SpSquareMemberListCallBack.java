package com.hs.mobile.gw.openapi.squareplus.callback;

import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;

import android.app.Activity;

public class SpSquareMemberListCallBack extends SpSquarePlusDefaultAjaxCallBack<SpSquareMemberVO> {
	public List<SpSquareMemberVO> dataList;

	public SpSquareMemberListCallBack(Activity a, Class<SpSquareMemberVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS){
			if(responseData != null){
				this.dataList = responseData.dataList;
			}
		}
	}
}
