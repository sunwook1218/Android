package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class SpFavorVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String squareId;
	private String contentsId;
	private String userId;
	private String userName;
	private Date createDate;
	private String favorType;

	// NOT IN SQ_Favor DB
	private int favorCount;
	private List<SpFavorVO> favorList;
	private String transactionMode;

	public boolean actionAdd;
	public boolean actionModify;
	public boolean actionDelete;
	public SpFavorVO() {
		super(null);
		this.transactionMode = SpSquareConst.TX_FAVOR_NONE;
	}

	public SpFavorVO(JSONObject json) {
		super(json);
		this.transactionMode = SpSquareConst.TX_FAVOR_NONE;
	}

	public SpFavorVO(String squareId, String contentsId, String userId, String favorType) {
		super(null);
		this.squareId = squareId;
		this.contentsId = contentsId;
		this.userId = userId;
		this.favorType = favorType;
		this.transactionMode = SpSquareConst.TX_FAVOR_NONE;
	}

	public SpFavorVO(String squareId, String contentsId, String userId, String userName, String favorType, String transactionMode) {
		super(null);
		this.squareId = squareId;
		this.contentsId = contentsId;
		this.userId = userId;
		this.userName = userName;
		this.favorType = favorType;
		this.transactionMode = transactionMode;
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
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFavorType() {
		return favorType;
	}
	public void setFavorType(String favorType) {
		this.favorType = favorType;
	}

	public FavorType getFavorTypeEnum() {
		if (SpSquareConst.FAVOR_TYPE_LIKE.equals(favorType)) {
			return FavorType.LIKE;
		} else if (SpSquareConst.FAVOR_TYPE_SMILE.equals(favorType)) {
			return FavorType.SMILE;
		} else if (SpSquareConst.FAVOR_TYPE_BEST.equals(favorType)) {
			return FavorType.BEST;
		} else if (SpSquareConst.FAVOR_TYPE_SURPRISE.equals(favorType)) {
			return FavorType.SURPRISE;
		} else if (SpSquareConst.FAVOR_TYPE_SAD.equals(favorType)) {
			return FavorType.SAD;
		} else if (SpSquareConst.FAVOR_TYPE_ANGRY.equals(favorType)) {
			return FavorType.ANGRY;
		} else {
			return FavorType.NONE;
		}
	}

	public int getFavorCount() {
		return favorCount;
	}

	public void setFavorCount(int favorCount) {
		this.favorCount = favorCount;
	}

	public List<SpFavorVO> getFavorList() {
		return favorList;
	}

	public void setFavorList(List<SpFavorVO> favorList) {
		this.favorList = favorList;
	}

	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public boolean isActionAdd() {
		return this.transactionMode.equals(SpSquareConst.TX_FAVOR_CREATE);
	}
	public boolean isActionModify() {
		return this.transactionMode.equals(SpSquareConst.TX_FAVOR_MODIFY);
	}
	public boolean isActionDelete() {
		return this.transactionMode.equals(SpSquareConst.TX_FAVOR_DELETE);
	}
}