package com.hs.mobile.gw.openapi.square;

import java.io.IOException;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.text.TextUtils;

public class FavoriteList extends SquareOpenApi {
	/**
	 * <pre>
	 * favoriteType이 파일 타입일 경우 사용
	 * 		(공백) : 모든 파일
	 * 		0 : 기타 타입 파일
	 * 		1 : 문서 타입 파일
	 * 		2 : 이미지 타입 파일
	 * 		3 : 동영상 타입 파일
	 * </pre>
	 */
	public enum FavoriteFileType {
		ALL(""), ETC("0"), DOCUMENT("1"), IMAGE("2"), MOVIE("3");
		private String m_strType;

		private FavoriteFileType(String s) {
			m_strType = s;
		}

		public String getCode() {
			return m_strType;
		}

		public static FavoriteFileType valueOfType(String s) {
			for (FavoriteFileType t : values()) {
				if (TextUtils.equals(t.getCode(), s)) {
					return t;
				}
			}
			return null;
		}
	}

	public enum FavoriteType {
		WORK("0"), FILE("1");
		private String m_strType;

		private FavoriteType(String s) {
			m_strType = s;
		}

		public String getCode() {
			return m_strType;
		}

		public static FavoriteType valueOfType(String s) {
			for (FavoriteType t : values()) {
				if (TextUtils.equals(t.getCode(), s)) {
					return t;
				}
			}
			return null;
		}
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/favoriteList.do";
	}

	@Override
	protected String[] getParams() {
		return new String[] { "squareId", "fileType", "favoriteType", "lastContentsId", "lastAttachId" };
	}

	@Override
	public Response load(Context context, String... params) {
		try {
			return NetUtils.requestPost(context, getOkhttpClient(), getUrl(), getTag(), makePostParams(params));
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}

	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}

}
