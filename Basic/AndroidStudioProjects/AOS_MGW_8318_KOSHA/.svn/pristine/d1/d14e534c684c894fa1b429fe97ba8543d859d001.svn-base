package com.hs.mobile.gw.openapi.squareplus.callback;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;

import android.app.Activity;
import android.text.Html;

public class SpContentsCallBack extends SpSquarePlusDefaultAjaxCallBack<SpContentsVO> {
	public SpContentsVO item;

	public SpContentsCallBack(Activity a, Class<SpContentsVO> cls) {
		super(a, cls);
	}

	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		if(responseHead != null && responseHead.resultCode == SUCCESS) {
			if(responseData != null) {
				item = responseData.item;
				
				// &lt;, &gt; 치환  < , >
				String replaceText = Html.fromHtml(item.getBody().replace("\n", "<br>")).toString();
				item.setBody(replaceText);
			}
		}
	}
}
