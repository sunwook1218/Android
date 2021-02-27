package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum FavoriteType implements Serializable{
	SQUARE("0"), TOPIC("1"), COMMENT("2"), REPORT("5"), NONE("9999");
	private String m_strCode;

	private FavoriteType(String strCode) {
		m_strCode = strCode;
	}

	public static FavoriteType valueOfCode(String s) {
		for (FavoriteType m : values()) {
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