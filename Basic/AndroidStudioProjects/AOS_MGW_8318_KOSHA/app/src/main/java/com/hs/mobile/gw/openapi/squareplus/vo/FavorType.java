package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import android.text.TextUtils;

public enum FavorType implements Serializable{
	NONE("0"), // 사용안함
	LIKE("1"), // 좋아요
	SMILE("2"), // 웃겨요
	BEST("3"), // 최고예요
	SURPRISE("4"), // 놀랐어요
	SAD("5"), // 슬퍼요
	ANGRY("6");// 화나요

	private String m_strCode;

	private FavorType(String strCode) {
		m_strCode = strCode;
	}

	public static FavorType valueOfCode(String s) {
		for (FavorType m : values()) {
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