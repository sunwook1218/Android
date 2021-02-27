package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

import android.text.TextUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpSquareMemberVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String USER = SpSquareConst.SQ_MEMBER_TYPE_USER;	// 사용자
	public static final String DEPT = SpSquareConst.SQ_MEMBER_TYPE_DEPT;	// 부서

	private String memberId;
	private String squareId;
	private String memberType;
	private String memberName;
	private String memberRights;
	private Date registerDate;
	private String positionName;	// 사용자인경우, 직위명
	private String dutyName;		// 사용자인경우, 직책명
	private String deptName;		// 사용자인경우, 부서명
	private String deptId;			// 사용자인경우, 부서ID
	private int seq;
	private int sancLevel;
	private String empCode;
	private String rankName;
	private int rankLevel;
	private int memberCount;
	private String defaultDeptFlag;

	// NOT IN SQ_SQUARE_MEMBER DB
	private SpUserSettingsVO userSettingsVO;

	// ignore VO
	private boolean member;
	private boolean observer;
	private boolean admin;
	private boolean user;
	private boolean dept;
	private SqOrgID sqOrgMemberID;

	public SpSquareMemberVO() {
		super(null);
	}

	public SpSquareMemberVO(JSONObject json) {
		super(json);
		if (json == null) {
			return;
		}
	}

	public SpSquareMemberVO(String memberName, String memberType, String memberId) {
		super(null);

		if (TextUtils.equals(memberName, "null")) {
			memberName = "";
		}
		if (TextUtils.equals(memberType, "null")) {
			memberType = "";
		}
		if (TextUtils.equals(memberId, "null")) {
			memberId = "";
		}

		this.memberName = memberName;
		this.memberType = memberType;
		this.memberId = memberId;
		this.memberRights = SpSquareConst.SQ_MEMBER_RIGHTS_MEMBER;
	}

	public SpSquareMemberVO(String memberName, String memberType, String memberId, String memberRights) {
		super(null);

		if (TextUtils.equals(memberName, "null")) {
			memberName = "";
		}
		if (TextUtils.equals(memberType, "null")) {
			memberType = "";
		}
		if (TextUtils.equals(memberId, "null")) {
			memberId = "";
		}
		if (TextUtils.equals(memberRights, "null")) {
			memberRights = "";
		}

		this.memberName = memberName;
		this.memberType = memberType;
		this.memberId = memberId;
		this.memberRights = memberRights;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSquareId() {
		return squareId;
	}

	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberRights() {
		return memberRights;
	}

	public void setMemberRights(String memberRights) {
		this.memberRights = memberRights;
	}

	public MemberRights getMemberRightsEnum() {
		if (memberRights.equals(SpSquareConst.SQ_MEMBER_RIGHTS_ADMIN)) {
			return MemberRights.ADMIN_USER;
		} else if (memberRights.equals(SpSquareConst.SQ_MEMBER_RIGHTS_MEMBER)) {
			return MemberRights.NORMAL_USER;
		} else {
			return MemberRights.OBSERVER_USER;
		}
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSancLevel() {
		return sancLevel;
	}

	public void setSancLevel(int sancLevel) {
		this.sancLevel = sancLevel;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public int getRankLevel() {
		return rankLevel;
	}

	public void setRankLevel(int rankLevel) {
		this.rankLevel = rankLevel;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getDefaultDeptFlag() {
		return defaultDeptFlag;
	}

	public void setDefaultDeptFlag(String defaultDeptFlag) {
		this.defaultDeptFlag = defaultDeptFlag;
	}

	public SpUserSettingsVO getUserSettingsVO() {
		return userSettingsVO;
	}

	public void setUserSettingsVO(SpUserSettingsVO userSettingsVO) {
		this.userSettingsVO = userSettingsVO;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public boolean isObserver() {
		return observer;
	}

	public void setObserver(boolean observer) {
		this.observer = observer;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public boolean isDept() {
		return dept;
	}

	public void setDept(boolean dept) {
		this.dept = dept;
	}

	public SqOrgID getSqOrgMemberID() {
		return sqOrgMemberID;
	}

	public void setSqOrgMemberID(SqOrgID sqOrgMemberID) {
		this.sqOrgMemberID = sqOrgMemberID;
	}

	@Override
	public String toString() {
		return ";" + memberId + "," + memberName + "," + memberType + "," + memberRights;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SpSquareMemberVO) || o == null) {
			return false;
		}

		final SpSquareMemberVO squareMemberVO = (SpSquareMemberVO) o;
		return equalsSquareMember(squareMemberVO);
	}

	private boolean equalsSquareMember(SpSquareMemberVO squareMemberVO) {
		if (squareMemberVO.getMemberId().equals(memberId) && squareMemberVO.getMemberType().equals(memberType)) {
			return true;
		} else {
			return false;
		}
	}
}