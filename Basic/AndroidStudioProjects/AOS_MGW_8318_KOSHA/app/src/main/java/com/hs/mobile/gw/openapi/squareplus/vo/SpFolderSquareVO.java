package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpFolderSquareVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String folderId;
	private String squareId;
	private int seq;
	
	private String squareTitle;
	private String userId;
	private int newCount;
	private SpSquareVO squareVO;

	public SpFolderSquareVO() {
		super(null);
	}

	public SpFolderSquareVO(JSONObject json) {
		super(json);
	}
	
	public String getFolderId() {
		return folderId;
	}
	
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	
	public String getSquareId() {
		return squareId;
	}
	
	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getSquareTitle() {
		return squareTitle;
	}

	public void setSquareTitle(String squareTitle) {
		this.squareTitle = squareTitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public SpSquareVO getSquareVO() {
		return squareVO;
	}

	public void setSquareVO(SpSquareVO squareVO) {
		this.squareVO = squareVO;
	}
}