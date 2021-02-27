package com.hs.mobile.gw.openapi.square.vo;

import java.util.Locale;

import com.hs.mobile.gw.hsuco.R;

import android.text.TextUtils;

public enum FileType {
	DOC(R.drawable.file_doc, "doc", "docx"), HWP(R.drawable.file_hwp, "hwp"), IMAGE(R.drawable.file_image, "png", "jpg", "gif"), MEDIA(
			R.drawable.file_media, "avi", "mov", "mp4"), PPT(R.drawable.file_ppt, "ppt", "pptx"), XLS(R.drawable.file_xls, "xls"), ZIP(
			R.drawable.file_zip, "zip"), ETC(R.drawable.file_etc, "0xFF"), TXT(R.drawable.file_txt, "txt");
	private int m_nRes;
	private String[] m_strExtention;

	private FileType(int nRes, String... strExtention) {
		m_nRes = nRes;
		m_strExtention = strExtention;
	}

	public static FileType valueOfExtention(String fileExt) {
		if (!TextUtils.isEmpty(fileExt)) {
			fileExt = fileExt.toLowerCase(Locale.getDefault());
			for (FileType t : values()) {
				for (String s : t.m_strExtention) {
					if (fileExt.endsWith(s)) {
						return t;
					}
				}
			}
		}
		return ETC;
	}

	public int getRes() {
		return m_nRes;
	}
}