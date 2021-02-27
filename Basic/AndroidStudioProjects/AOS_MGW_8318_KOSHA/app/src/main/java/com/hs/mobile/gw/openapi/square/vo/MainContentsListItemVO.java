package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.TextUtils;

public class MainContentsListItemVO implements IContentsListItem, Serializable {
	// orderStatus가 있는데
	// "0"; //기본 사용하지 않음
	// "1"; //진행중
	// "2"; //종료
	public enum OrderStatus {
		NONE("0"), PROCESS("1"), COMPLETE("2");
		private String m_strCode;

		private OrderStatus(String s) {
			m_strCode = s;
		}

		public String getCode() {
			return m_strCode;
		}

		public static OrderStatus valueOfCode(String code) {
			for (OrderStatus t : values()) {
				if (TextUtils.equals(t.getCode(), code)) {
					return t;
				}
			}
			return null;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int TOPIC = 0;
	public static final int MESSAGE = 1;
	public static final int FILE_UPLOAD = 2;
	public static final int OPINION = 3;
	public static final int SYSTEM_MESSAGE = 4;
	public static final int COMMAND = 5;
	public static final int GROUPINFO_SYSTEM_MESSAGE = 6;
	public static final int USER_SYSTEM_MESSAGE = 7;

	public String squareId;
	public String contentsId;
	public long modifyDate;
	public ArrayList<AttachListItemVO> attachList = new ArrayList<AttachListItemVO>();
	public String body;
	public ArrayList<ContentsMemberListItemVO> contentsMemberList = new ArrayList<ContentsMemberListItemVO>();
	public String authorId;
	public long endDate;
	public long dueDate;
	public String parentId;
	public long indexDate;
	public int viewDepth;
	public String authorName;
	public String authorPositionName;
	public String authorDutyName;
	public String authorDeptId;
	public long createDate;
	public String securityFlag;
	public boolean favoriteFlag;
	public SquareContentsType contentsType;
	public String authorDeptName;
	public String title;
	public String originalParentId;
	public OrderStatus orderStatus;
	public int commentCount;

	public MainContentsListItemVO(JSONObject json) {
		if (json == null) {
			return;
		}
		squareId = json.optString("squareId");
		contentsId = json.optString("contentsId");
		modifyDate = json.optLong("modifyDate");
		JSONArray attachListArray = json.optJSONArray("attachList");
		if (attachListArray != null) {
			for (int i = 0; i < attachListArray.length(); ++i) {
				attachList.add(new AttachListItemVO(attachListArray.optJSONObject(i)));
			}
		}
		body = json.optString("body");
		if (TextUtils.equals("null", body)) {
			body = "";
		}

		JSONArray memberListArray = json.optJSONArray("contentsMemberList");
		if (memberListArray != null) {
			for (int i = 0; i < memberListArray.length(); ++i) {
				contentsMemberList.add(new ContentsMemberListItemVO(json.optJSONArray("contentsMemberList").optJSONObject(i)));
			}
		}
		authorId = json.optString("authorId");
		endDate = json.optLong("endDate");
		originalParentId = json.optString("originalParentId");
		parentId = json.optString("parentId");
		title = json.optString("title");
		indexDate = json.optLong("indexDate");
		viewDepth = json.optInt("viewDepth");
		authorDeptName = json.optString("authorDeptName");
		authorName = json.optString("authorName");
		authorPositionName = json.optString("authorPositionName");
		authorDutyName = json.optString("authorDutyName");
		authorDeptId = json.optString("authorDeptId");
		createDate = json.optLong("createDate");
		dueDate = json.optLong("dueDate");
		securityFlag = json.optString("securityFlag");
		favoriteFlag = TextUtils.equals(json.optString("favoriteFlag"), "1") ? true : false;
		contentsType = SquareContentsType.valueOfCode(json.optString("contentsType"));
		orderStatus = OrderStatus.valueOfCode(json.optString("orderStatus"));
		commentCount = json.optInt("commentCount");
	}
	
	public MainContentsListItemVO(AttachListItemVO vo) {
		squareId = vo.squareId;
		contentsId = vo.contentsId;
		authorId = vo.authorId;
		originalParentId = vo.originalParentId;
		authorName = vo.authorName;
		createDate = vo.createDate;
		favoriteFlag = vo.favoriteFlag;
	}
	@Override
	public ContentsObjectType getObjectType() {
		return ContentsObjectType.CONTENTS;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
		{
			return true;
		}
		boolean bRet = false;
		if(!(o instanceof MainContentsListItemVO))
		{
			bRet = false;
		}else
		{
			MainContentsListItemVO s = (MainContentsListItemVO) o;
			if (TextUtils.equals(s.squareId, squareId) && TextUtils.equals(s.contentsId, contentsId)) {
				bRet = true;
			} else {
				bRet = false;
			}
		}
		return bRet;
	}

	@Override
	public int hashCode() {
		String s = squareId + "." + contentsId;
		return s.hashCode();
	}
}