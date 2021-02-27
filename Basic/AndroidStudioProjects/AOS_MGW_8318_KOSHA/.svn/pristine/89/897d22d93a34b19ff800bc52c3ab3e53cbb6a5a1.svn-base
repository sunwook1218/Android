package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.TextUtils;

public class MyWorkGroupMenuListItemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Status {
		IN_PROGRESS, COMPLETE
	}

	private ArrayList<SquareMemberVO> squareMemberList = new ArrayList<SquareMemberVO>();
	public boolean defaultDept;
	public boolean end;
	public boolean ing;
	public boolean favorite;
	public String securityFlag;
	public long endDate;
	public long modifyDate;
	public int orderCount;
	public String createDateFormat;
	public boolean defaultDeptFlag;
	public String authorId;
	public String title;
	public String description;
	public String favoriteFlag;
	public long createDate;
	public int dueDate;
	public String authorDutyName;
	public String authorPositionName;
	public Status status;
	public String endDateFormat;
	public String authorDeptId;
	public String modifyDateFormat;
	public String authorName;
	public String authorDeptName;
	public String dueDateFormat;
	public int newCount;
	public String squareId;

	public MyWorkGroupMenuListItemVO(JSONObject json) {

		if (json.has("squareMemberList") && json.optJSONArray("squareMemberList") != null) {
			JSONArray squareMemberListJson = json.optJSONArray("squareMemberList");
			for (int i = 0; i < squareMemberListJson.length(); ++i) {
				squareMemberList.add(new SquareMemberVO(squareMemberListJson.optJSONObject(i)));
			}
		}
		defaultDept = json.optBoolean("defaultDept");
		end = json.optBoolean("end");
		ing = json.optBoolean("ing");
		favorite = json.optBoolean("favorite");
		securityFlag = json.optString("securityFlag");
		endDate = json.optLong("endDate");
		modifyDate = json.optLong("modifyDate");
		orderCount = json.optInt("orderCount");
		createDateFormat = json.optString("createDateFormat");
		defaultDeptFlag = TextUtils.equals(json.optString("defaultDeptFlag"), "1") ? true : false;
		authorId = json.optString("authorId");
		title = json.optString("title");
		description = json.optString("description");
		favoriteFlag = json.optString("favoriteFlag");
		createDate = json.optLong("createDate");
		dueDate = json.optInt("dueDate");
		authorDutyName = json.optString("authorDutyName");
		authorPositionName = json.optString("authorPositionName");
		status = TextUtils.equals(json.optString("status"), "0") ? Status.IN_PROGRESS : Status.COMPLETE;
		endDateFormat = json.optString("endDateFormat");
		authorDeptId = json.optString("authorDeptId");
		modifyDateFormat = json.optString("modifyDateFormat");
		// 현재는 데이터가 안들어 오고 있음.
		// "selectedMember": null,
		authorName = json.optString("authorName");
		authorDeptName = json.optString("authorDeptName");
		dueDateFormat = json.optString("dueDateFormat");
		newCount = json.optInt("newCount");
		squareId = json.optString("squareId");

	}
}