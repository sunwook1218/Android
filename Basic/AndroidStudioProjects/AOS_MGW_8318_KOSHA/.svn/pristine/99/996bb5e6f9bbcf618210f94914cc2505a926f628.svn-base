package com.hs.mobile.gw.openapi.squareplus.callback;

import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;

import android.app.Activity;
import android.text.Html;

public class SpContentsListCallBack extends SpSquarePlusDefaultAjaxCallBack<SpContentsVO> {
	public List<SpContentsVO> dataList;
	public int totalCount = 0;
	public int pageSize = 0;
	public int pageNum = 0;
	public String lastViewId = "";

	public SpContentsListCallBack(Activity a, Class<SpContentsVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS) {
			if(responseData != null) {
				dataList = responseData.dataList;
				
				for (SpContentsVO item : dataList) {
					// &lt;, &gt; 치환 < , >
					if (item.getBody() != null)
						item.setBody( Html.fromHtml(item.getBody().replace("\n", "<br>")).toString());
				}
				
				this.totalCount = responseData.totalCount;
				this.pageSize = responseData.pageSize;
				this.pageNum = responseData.pageNum;
				this.lastViewId = responseData.lastViewId;
			}
		}
	}
}
