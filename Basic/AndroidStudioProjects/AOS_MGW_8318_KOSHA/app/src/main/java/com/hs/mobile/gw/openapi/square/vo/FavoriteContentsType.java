package com.hs.mobile.gw.openapi.square.vo;

import android.text.TextUtils;

public enum FavoriteContentsType {
	TOPIC("1"), OPINION("2"), MESSAGE("3"), FILE_UPLOAD("4"), COMMAND("5"), SINGLE_FILE("6");
	private String m_code;

	FavoriteContentsType(String s) {
		m_code = s;
	}

	public String getCode() {
		return m_code;
	}

	public static FavoriteContentsType valueOfCode(String s) {
		for (FavoriteContentsType t : values()) {
			if (TextUtils.equals(s, t.getCode())) {
				return t;
			}
		}
		return null;
	}

	public static FavoriteContentsType findObjectByContentsType(SquareContentsType contentsType) {
		if (contentsType == null) {
			return null;
		} else {
			switch (contentsType) {
			case COMMAND:
				return COMMAND;
			case FILE_UPLOAD:
				return FILE_UPLOAD;
			case GROUPINFO_SYSTEM_MESSAGE:
				break;
			case MESSAGE:
				return MESSAGE;
			case OPINION:
				return OPINION;
			case SYSTEM_MESSAGE:
				break;
			case TOPIC:
				return TOPIC;
			case USER_SYSTEM_INFO:
				break;
			default:
				break;
			}
		}
		return null;
	}
}