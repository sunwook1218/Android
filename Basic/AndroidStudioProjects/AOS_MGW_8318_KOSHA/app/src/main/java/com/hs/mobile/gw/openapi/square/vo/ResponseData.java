package com.hs.mobile.gw.openapi.square.vo;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class ResponseData extends DefaultVO<JSONObject> {
	public int totalCount = 0;
	public int pageSize = 0;
	public int pageNum = 0;
	public String lastViewId = "";
	public JSONArray dataList;

	public ResponseData(JSONObject json) {
		super(json);
		if (json != null) {
			totalCount = json.optInt("totalCount", 0);
			pageSize = json.optInt("pageSize", 0);
			pageNum = json.optInt("pageNum", 0);
			lastViewId = json.optString("lastViewId");
			dataList = json.optJSONArray("dataList");
		}
	}
}