package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum GetFavoriteType implements Serializable{
	CONTENTS("contents"), FILE("file");
	private String m_strCode;

	private GetFavoriteType(String strCode) {
		m_strCode = strCode;
	}

	public static GetFavoriteType valueOfCode(String s) {
		for (GetFavoriteType m : values()) {
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