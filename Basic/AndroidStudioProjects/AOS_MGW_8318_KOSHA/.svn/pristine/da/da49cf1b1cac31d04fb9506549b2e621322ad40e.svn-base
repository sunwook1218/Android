package com.hs.mobile.gw.openapi.squareplus.callback;

import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;

import android.app.Activity;

/**
 * API 구조상 SquareVO 또는 List로 온다.
 * @author darkl
 *
 */
public class SpEndSquareCallBack extends SpSquarePlusDefaultAjaxCallBack<SpSquareVO> {
	public SpSquareVO item;
	public List<SpSquareVO> dataList;
	public int totalCount = 0;
	public int pageSize = 0;
	public int pageNum = 0;
	public String lastViewId = "";

	public SpEndSquareCallBack(Activity a, Class<SpSquareVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS){
			if(responseData != null && responseData.dataList != null){
				this.dataList = responseData.dataList;
				this.totalCount = responseData.totalCount;
				this.pageSize = responseData.pageSize;
				this.pageNum = responseData.pageNum;
				this.lastViewId = responseData.lastViewId;
			} else if(responseData != null) {
				this.item = responseData.item;
			}
		}
	}
}
