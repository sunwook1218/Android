package com.hs.mobile.gw.openapi.squareplus.callback;

import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;

import android.app.Activity;

public class SpAttachListCallBack extends SpSquarePlusDefaultAjaxCallBack<SpAttachVO> {
	public List<SpAttachVO> dataList;
	public int totalCount = 0;
	public int pageSize = 0;
	public int pageNum = 0;
	public String lastViewId = "";

	public SpAttachListCallBack(Activity a, Class<SpAttachVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS) {
			if(responseData != null) {
				dataList = responseData.dataList;
				this.totalCount = responseData.totalCount;
				this.pageSize = responseData.pageSize;
				this.pageNum = responseData.pageNum;
				this.lastViewId = responseData.lastViewId;
			}
		}
	}
}
