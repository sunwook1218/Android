package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class SpTagVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String squareId;
	private String contentsId;
	private String userId;
	private String tagId;
	private Date createDate;

	//SQ_TAG TABLE JOIN COLUMN
	private String tagName;
	private String tagType;
	private String tagCount;

	private String transactionMode;	// 태그 트랜젝션 모드(추가, 삭제)

	// ignore VO
	public boolean txDelete;
	public boolean txCreate;
	public boolean txNone;

	public SpTagVO() {
		super(null);
	}

	public SpTagVO(JSONObject json) {
		super(json);
	}

	public SpTagVO(String tagName) {
		super(null);
		this.tagName = tagName;
	}

	public String getSquareId() {
		return squareId;
	}

	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}

	public String getContentsId() {
		return contentsId;
	}

	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public TagType getTagTypeEnum() {
		return TagType.valueOfCode(this.tagType);
	}

	public String getTagCount() {
		return tagCount;
	}

	public void setTagCount(String tagCount) {
		this.tagCount = tagCount;
	}

	public boolean equals(Object o) {
		if (!(o instanceof SpTagVO)) {
			return false;
		}

		final SpTagVO tag = (SpTagVO) o;
		return equalsTag(tag);
	}

	public boolean equalsTag(SpTagVO tag) {
		if (tag != null && tag.getTagId() != null && this.tagId != null) {
			return (this.tagId.equals(tag.getTagId()));
		} else {
			return false;
		}
	}

	public void setTxNone() {
		this.transactionMode = SpSquareConst.TX_TAG_NONE;
	}

	public void setTxDelete() {
		this.transactionMode = SpSquareConst.TX_TAG_DELETE;
	}

	public void setTxCreate() {
		this.transactionMode = SpSquareConst.TX_TAG_CREATE;
	}

	public boolean isTxDelete() {
		return SpSquareConst.TX_TAG_DELETE.equals(this.transactionMode);
	}

	public boolean isTxCreate() {
		return SpSquareConst.TX_TAG_CREATE.equals(this.transactionMode);
	}

	public boolean isTxNone() {
		return SpSquareConst.TX_TAG_NONE.equals(this.transactionMode);
	}

	public String toString() {
		return "{" + tagId + ", " + tagName + "}";
	}
}