package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.util.Debug;

public class SqOrgID extends DefaultVO<JSONObject> implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String type;
	private String rights;
	private String name;

	public static final String USER = SpSquareConst.SQ_MEMBER_TYPE_USER;	// 사용자
	public static final String DEPT = SpSquareConst.SQ_MEMBER_TYPE_DEPT;	// 부서
	public static final String GROUP = SpSquareConst.SQ_MEMBER_TYPE_GROUP;	// 그룹 사용안함

	public static final String ADMIN = SpSquareConst.SQ_MEMBER_RIGHTS_ADMIN;
	public static final String MEMBER = SpSquareConst.SQ_MEMBER_RIGHTS_MEMBER;
	public static final String OBSERVER = SpSquareConst.SQ_MEMBER_RIGHTS_OBSERVER;

	// ignore VO
	private boolean member;
	private boolean observer;
	private boolean admin;
	private boolean user;
	private boolean dept;

	public SqOrgID() {
		super(null);
	}

	public SqOrgID(JSONObject json) {
		super(json);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static String getUser() {
		return USER;
	}

	public static String getDept() {
		return DEPT;
	}

	public static String getGroup() {
		return GROUP;
	}

	public static String getAdmin() {
		return ADMIN;
	}

	public static String getMember() {
		return MEMBER;
	}

	public static String getObserver() {
		return OBSERVER;
	}

	@Override
	public Object clone()
	{
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			Debug.trace(e);
			return null;
		}
	}//end clone
}
