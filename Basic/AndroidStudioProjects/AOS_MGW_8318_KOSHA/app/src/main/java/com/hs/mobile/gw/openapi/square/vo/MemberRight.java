package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum MemberRight implements Serializable{
	ADMIN_USER("0"), NORMAL_USER("1"), READ_ONLY_USER("2");
	private String m_strCode;

	private MemberRight(String strCode) {
		m_strCode = strCode;
	}

	public static MemberRight valueOfCode(String s) {
		for (MemberRight m : values()) {
			if (TextUtils.equals(m.getCode(), s)) {
				return m;
			}
		}
		return null;
	}

	private String getCode() {
		return m_strCode;
	}

}