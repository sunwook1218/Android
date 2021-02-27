package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;

import org.json.JSONObject;

import android.text.TextUtils;

public class SquareMemberVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean member;
	public String deptName;
	public MemberRight memberRights;
	public long registerDate;
	public String memberType;
	public String dutyName;
	public boolean observer;
	public SqOrgMemberIDVO sqOrgMemberID;
	public String memberId;
	public String notifyAttr;
	public String memberName;
	public boolean dept;
	public boolean user;
	public String deptId;
	public String positionName;
	public String squareId;

	public SquareMemberVO(String strMemberName, String strMemberType,
			String strMemberId) {
		if (TextUtils.equals(strMemberName, "null")) {
			strMemberName = "";
		}
		if (TextUtils.equals(strMemberType, "null")) {
			strMemberType = "";
		}
		if (TextUtils.equals(strMemberId, "null")) {
			strMemberId = "";
		}
		memberName = strMemberName;
		memberType = strMemberType;
		memberId = strMemberId;
	}

	public SquareMemberVO(JSONObject json) {
		if (json == null) {
			return;
		}
		deptId = json.optString("deptId", "");
		deptName = json.optString("deptName", "");
		dutyName = json.optString("dutyName");
		memberId = json.optString("memberId", json.optString("Id"));
		member = json.optBoolean("member");
		memberRights = MemberRight.valueOfCode(json.optString("memberRights"));
		registerDate = json.optLong("registerDate");
		memberType = json.optString("memberType", "type");
		observer = json.optBoolean("observer");
		sqOrgMemberID = new SqOrgMemberIDVO(json.optJSONObject("sqOrgMemberID"));
		notifyAttr = json.optString("notifyAttr");
		memberName = json.optString("memberName", json.optString("name"));
		dept = json.optBoolean("dept");
		user = json.optBoolean("user");
		positionName = json.optString("positionName", "");
		squareId = json.optString("squareId");
	}

	public String toString() {
		StringBuilder bd = new StringBuilder();
		bd.append(memberId);
		bd.append(',');
		bd.append(memberName);
		bd.append(',');
		bd.append(memberType);
		bd.append(';');
		return bd.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		boolean bRet = false;
		if (!(o instanceof SquareMemberVO)) {
			bRet = false;
		} else {
			SquareMemberVO s = (SquareMemberVO) o;
			if (TextUtils.equals(s.memberId, memberId)) {
				bRet = true;
			} else {
				bRet = false;
			}
		}
		return bRet;
	}

	public int hashCode() {
		String s = memberId;
		return s.hashCode();
	}

}