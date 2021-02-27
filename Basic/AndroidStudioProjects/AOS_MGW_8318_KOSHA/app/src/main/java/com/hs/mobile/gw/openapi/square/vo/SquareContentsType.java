package com.hs.mobile.gw.openapi.square.vo;

import android.text.TextUtils;

public enum SquareContentsType {
	TOPIC("0"), MESSAGE("1"), FILE_UPLOAD("2"), OPINION("3"), SYSTEM_MESSAGE("4"), COMMAND("5"), GROUPINFO_SYSTEM_MESSAGE("6"), USER_SYSTEM_INFO(
			"7");
	private String m_code;

	SquareContentsType(String s) {
		m_code = s;
	}

	public String getCode() {
		return m_code;
	}

	public static SquareContentsType valueOfCode(String s) {
		for (SquareContentsType t : values()) {
			if (TextUtils.equals(s, t.getCode())) {
				return t;
			}
		}
		return null;
	}
}