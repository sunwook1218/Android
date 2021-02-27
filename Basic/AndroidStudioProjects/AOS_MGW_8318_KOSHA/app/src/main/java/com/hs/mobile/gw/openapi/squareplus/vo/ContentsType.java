package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum ContentsType implements Serializable{
	TOPIC("0"), MESSAGE("1"), FILE("2"), COMMENT("3"), REPORT("5"), SYSTEM_SQUARE("6"), SYSTEM_USER("7"), SYSTEM_SQUARE_INTRO("8"), UNKNOWN("9999");
	private String m_strCode;

	private ContentsType(String strCode) {
		m_strCode = strCode;
	}

	public static ContentsType valueOfCode(String s) {
		for (ContentsType m : values()) {
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