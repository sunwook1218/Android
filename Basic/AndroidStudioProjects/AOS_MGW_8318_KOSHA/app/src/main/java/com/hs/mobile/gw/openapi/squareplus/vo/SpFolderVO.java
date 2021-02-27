package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpFolderVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String folderId;
	private String userId;
	private String folderName;
	private int seq;
	
	private boolean hasChild;
	private List<SpSquareVO> squareVOList;
	private List<SpFolderSquareVO> folderSquareVOList;

	public SpFolderVO() {
		super(null);
	}

	public SpFolderVO(JSONObject json) {
		super(json);
	}

	public String getFolderId() {
		return folderId;
	}
	
	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFolderName() {
		return folderName;
	}
	
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<SpSquareVO> getSquareVOList() {
		return squareVOList;
	}

	public void setSquareVOList(List<SpSquareVO> squareVOList) {
		this.squareVOList = squareVOList;
	}

	public List<SpFolderSquareVO> getFolderSquareVOList() {
		return folderSquareVOList;
	}

	public void setFolderSquareVOList(List<SpFolderSquareVO> folderSquareVOList) {
		this.folderSquareVOList = folderSquareVOList;
	}

	@Override
	public String toString() {
		return "{" + folderId + ", " + folderName + "}";
	}
}