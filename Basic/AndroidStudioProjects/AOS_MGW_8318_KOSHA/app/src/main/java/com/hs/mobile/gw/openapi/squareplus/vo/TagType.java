package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum TagType implements Serializable{
	POPULAR("popular"), RECENTLY("recentUse");
	private String m_strCode;

	private TagType(String strCode) {
		m_strCode = strCode;
	}

	public static TagType valueOfCode(String s) {
		for (TagType m : values()) {
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