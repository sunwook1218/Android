package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum MemberRights implements Serializable{
	ADMIN_USER("0"), NORMAL_USER("1"), OBSERVER_USER("2");
	private String m_strCode;

	private MemberRights(String strCode) {
		m_strCode = strCode;
	}

	public static MemberRights valueOfCode(String s) {
		for (MemberRights m : values()) {
			if (TextUtils.equals(m.getCode(), s)) {
				return m;
			}
		}
		return null;
	}

	public String getCode() {
		return m_strCode;
	}

}