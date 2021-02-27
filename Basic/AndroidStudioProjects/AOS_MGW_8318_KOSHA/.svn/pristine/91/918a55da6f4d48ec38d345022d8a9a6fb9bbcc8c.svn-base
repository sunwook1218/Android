package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum FileType implements Serializable{
	ALL("all"), DOCUMENT("document"), IMAGE("image"), OTHER("other");
	private String m_strCode;

	private FileType(String strCode) {
		m_strCode = strCode;
	}

	public static FileType valueOfCode(String s) {
		for (FileType m : values()) {
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