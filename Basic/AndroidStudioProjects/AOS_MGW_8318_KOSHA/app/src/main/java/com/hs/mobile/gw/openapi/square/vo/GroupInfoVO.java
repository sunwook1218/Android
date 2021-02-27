package com.hs.mobile.gw.openapi.square.vo;

import java.util.ArrayList;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;

import android.text.TextUtils;
public class GroupInfoVO extends SquareDefaultVO{
	public String description;
	public Status status;
	public ArrayList<SquareMemberVO> squareMemberList = new ArrayList<SquareMemberVO>();
	public boolean defaultDept;
	public boolean favoriteFlag;
	public String securityFlag;
	public long endDate;
	public long modifyDate;
	public int orderCount;
	public String authorName;
	public String authorPositionName;
	public String authorDutyName;
	public String authorDeptId;
	public boolean defaultDeptFlag;
	public String authorId;
	public long createDate;
	public long dueDate;
	public String news;
	public ArrayList<AttachListItemVO> attachList;
	public String title;
	public String squareId;

	public GroupInfoVO(JSONObject json) {
		super(json);		
		description = responseData.getJson().optString("description");
		status = TextUtils.equals(responseData.getJson().optString("status"), "0") ? Status.IN_PROGRESS : Status.COMPLETE;
		if (responseData.getJson().has("squareMemberList") && responseData.getJson().optJSONArray("squareMemberList") != null) {
			for (int i = 0; i < responseData.getJson().optJSONArray("squareMemberList").length(); ++i) {
				squareMemberList.add(new SquareMemberVO(responseData.getJson().optJSONArray("squareMemberList").optJSONObject(i)));
			}
		}
		authorName = responseData.getJson().optString("authorName");
		authorPositionName = responseData.getJson().optString("authorPositionName");
		authorDutyName = responseData.getJson().optString("authorDutyName");
		authorDeptId = responseData.getJson().optString("authorDeptId");
		createDate = responseData.getJson().optLong("createDate");
		securityFlag = responseData.getJson().optString("securityFlag");
		defaultDeptFlag = TextUtils.equals(responseData.getJson().optString("defaultDeptFlag"), "1") ? true : false;
		favoriteFlag = TextUtils.equals(responseData.getJson().optString("favoriteFlag"), "1") ? true : false;
		defaultDept = responseData.getJson().optBoolean("defaultDept");
		orderCount = responseData.getJson().optInt("orderCount");
		endDate = responseData.getJson().optLong("endDate");
		authorId = responseData.getJson().optString("authorId");
		news = responseData.getJson().optString("news");
		modifyDate = responseData.getJson().optLong("modifyDate");
		if (responseData.getJson().has("attachList")) {
			for (int i = 0; i < responseData.getJson().optJSONArray("attachList").length(); ++i) {
				if(responseData.getJson().optJSONArray("attachList").optJSONObject(i)!=null){
					if(attachList!=null)attachList.add(new AttachListItemVO(responseData.getJson().optJSONArray("attachList").optJSONObject(i)));
				}
			}
		}
		title = responseData.getJson().optString("title");
		dueDate = responseData.getJson().optLong("dueDate");
		squareId = responseData.getJson().optString("squareId");
	}
}