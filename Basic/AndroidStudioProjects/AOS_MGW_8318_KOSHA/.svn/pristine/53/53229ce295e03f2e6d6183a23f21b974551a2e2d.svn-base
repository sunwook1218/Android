package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;

import org.json.JSONObject;

import com.hs.mobile.gw.MainModel;

import android.text.TextUtils;

public class ContentsMemberListItemVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String contentsId;
	public String status;
	public String deptName;
	public String dutyName;
	public String userId;
	public String positionName;
	public String memberName;
	public long endDate;
	public boolean charger;
	public boolean chargerFlag;
	public boolean ing;
	public boolean end;

	public ContentsMemberListItemVO(JSONObject json) {
		if (json == null) {
			return;
		}

		charger = json.optBoolean("charger");
		contentsId = json.optString("contentsId");
		status = json.optString("status");
		deptName = MainModel.getInstance().checkNullString(json.optString("deptName"));
		dutyName = json.optString("dutyName");
		userId = json.optString("userId");
		positionName = MainModel.getInstance().checkNullString(json.optString("positionName"));
		memberName = json.optString("memberName");
		endDate = json.optLong("endDate");
		chargerFlag = TextUtils.equals(json.optString("chargerFlag"), "1") ? true : false;
		ing = json.optBoolean("ing");
		end = json.optBoolean("end");

	}
}