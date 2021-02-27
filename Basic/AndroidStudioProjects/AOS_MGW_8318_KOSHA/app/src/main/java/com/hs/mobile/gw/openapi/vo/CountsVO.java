package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

import android.text.TextUtils;
public class CountsVO extends DefaultVO<JSONObject> {
	public int mailNew;
	public int mailUnread;
	public int mtrlNew;
	public int apprWait;
	public int approving;
	public int publicWait;
	public int recvWait;
	public int receiving;
	public int resCount;
	public int schToday;
	public int schShare;

	public CountsVO(JSONObject json) {
		super(json);
		if (json != null) {
			mailNew = convertStringToInt(json, "mailNew");
			mailUnread = convertStringToInt(json, "mailUnread");
			mtrlNew = convertStringToInt(json, "mtrlNew");
			apprWait = convertStringToInt(json, "apprWait");
			approving = convertStringToInt(json, "approving");
			publicWait = convertStringToInt(json, "publicWait");
			recvWait = convertStringToInt(json, "recvWait");
			receiving = convertStringToInt(json, "receiving");
			resCount = convertStringToInt(json, "resCount");
			schToday = convertStringToInt(json, "schToday");
			schShare = convertStringToInt(json, "schShare");
		}
	}

	private int convertStringToInt(JSONObject json, String strName) {
		int nRet = 0;
		if (json.optString(strName) != null && TextUtils.isDigitsOnly(json.optString(strName))) {
			nRet = Integer.valueOf(json.optString(strName, "0"));
		}
		return nRet;
	}
}
