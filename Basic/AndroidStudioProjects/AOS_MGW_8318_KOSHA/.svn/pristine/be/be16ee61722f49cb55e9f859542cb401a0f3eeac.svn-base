package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

import android.text.TextUtils;

public class SpContentsMentionVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String squareId;
	private String contentsId;
	private String userId;
	private String userName;
	private Date createDate;
	private Date indexDate;
	private String originalParentId;
	private String indexId;

	// NOT IN SQ_mention DB
	private String itemId;
	private String itemName;
	private String itemText;
	private String itemType;

	private String transactionMode; // 멘션 트랜젝션 모드(추가, 삭제)

	// ignore VO
	public boolean txDelete;
	public boolean txCreate;
	public boolean txNone;

	public SpContentsMentionVO() {
		super(null);
		this.transactionMode = SpSquareConst.TX_MENTION_NONE;
	}

	public SpContentsMentionVO(JSONObject json) {
		super(json);
		this.transactionMode = SpSquareConst.TX_FAVOR_NONE;
	}

	public SpContentsMentionVO(String userId) {
		super(null);
		this.userId = userId;
		if (userId != null)
			this.itemId = userId;
		this.transactionMode = SpSquareConst.TX_MENTION_NONE;
	}

	public SpContentsMentionVO(String userId, String userName) {
		super(null);
		this.userId = userId;
		if (userId != null)
			this.itemId = userId;
		this.userName = userName;
		if (userName != null)
			this.itemName = userName;
		this.transactionMode = SpSquareConst.TX_MENTION_NONE;
	}

	public SpContentsMentionVO(String squareId, String contentsId, String userId) {
		super(null);
		this.squareId = squareId;
		this.contentsId = contentsId;
		this.userId = userId;
		if (userId != null)
			this.itemId = userId;
		this.transactionMode = SpSquareConst.TX_MENTION_NONE;
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
		if (userId != null)
			this.itemId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		if (userName != null)
			this.itemName = userName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getIndexDate() {
		return indexDate;
	}

	public void setIndexDate(Date indexDate) {
		this.indexDate = indexDate;
	}

	public String getOriginalParentId() {
		return originalParentId;
	}

	public void setOriginalParentId(String originalParentId) {
		this.originalParentId = originalParentId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public boolean equals(Object o) {
		if (!(o instanceof SpContentsMentionVO)) {
			return false;
		}

		final SpContentsMentionVO mention = (SpContentsMentionVO) o;
		return equalsContentsMention(mention);
	}

	public boolean equalsContentsMention(SpContentsMentionVO mention) {
		return mention == null ? false : TextUtils.equals(this.userId, mention.getUserId()) || TextUtils.equals(this.itemId, mention.getItemId());
	}

	public void setTxNone() {
		this.transactionMode = SpSquareConst.TX_MENTION_NONE;
	}

	public void setTxDelete() {
		this.transactionMode = SpSquareConst.TX_MENTION_DELETE;
	}

	public void setTxCreate() {
		this.transactionMode = SpSquareConst.TX_MENTION_CREATE;
	}

	public boolean isTxDelete() {
		return SpSquareConst.TX_MENTION_DELETE.equals(this.transactionMode);
	}

	public boolean isTxCreate() {
		return SpSquareConst.TX_MENTION_CREATE.equals(this.transactionMode);
	}

	public boolean isTxNone() {
		return SpSquareConst.TX_MENTION_NONE.equals(this.transactionMode);
	}

	public String toString() {
		return userId;
	}
}