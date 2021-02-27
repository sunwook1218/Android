package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;

import org.json.JSONObject;

import android.text.TextUtils;

public class AttachListItemVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public String attachId;
	public String contentsId;
	public String fileName;
	public String fileExt;
	public long fileSize;
	public String fileType;
	public long createDate;
	public String squareId;
	public boolean favoriteFlag;
	public String authorId;
	public String authorName;
	public String createDateFormat;
	public String originalParentId;
	public String transactionMode;
	
	public AttachListItemVO(JSONObject json) {
		if (json == null) {
			return;
		}
		attachId = json.optString("attachId");
		contentsId = json.optString("contentsId");
		fileName = json.optString("fileName");
		fileExt = json.optString("fileExt");
		fileSize = json.optLong("fileSize");
		fileType = json.optString("fileType");
		createDate = json.optLong("createDate");
		squareId = json.optString("squareId");
		favoriteFlag = TextUtils.equals(json.optString("favoriteFlag"), "1") ? true : false;
		authorId = json.optString("authorId");
		authorName = json.optString("authorName");
		createDateFormat = json.optString("createDateFormat");
		originalParentId = json.optString("originalParentId");
		transactionMode = json.optString("transactionMode");
	}
}