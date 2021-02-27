package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum ContentsSerachType implements Serializable{
	CONTENTS("contents"),	// 검색어 사용자 + 태그
	AUTHOR("author"),		// 검색어 사용자
	TAG("tag"),				// 검색어 태그
	FILE("file");			// 검색어 파일
	private String m_strCode;

	private ContentsSerachType(String strCode) {
		m_strCode = strCode;
	}

	public static ContentsSerachType valueOfCode(String s) {
		for (ContentsSerachType m : values()) {
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