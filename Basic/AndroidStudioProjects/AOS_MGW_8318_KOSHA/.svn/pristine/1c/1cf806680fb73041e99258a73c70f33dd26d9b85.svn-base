package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class SpUserSettingsVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;
	private String squareId;
	private String squareTitle;
	private String squareBlindFlag;
	private String notifyNoticeFlag;
	private String notifyContentsFlag;
	private String notifyMentionFlag;
	private String notifyCommentFlag;

	//SQ_USER_SETTINGS TABLE JOIN COLUMN
	private String transactionMode;	// 파일 트랜젝션 모드(추가, 삭제)

	// ignore VO
	public boolean txCreate;
	public boolean txModify;
	public boolean txDelete;
	public boolean txNone;
	public boolean squareBlind;
	public boolean notifyNotice;
	public boolean notifyContents;
	public boolean notifyMention;
	public boolean notifyComment;

	public SpUserSettingsVO() {
		super(null);
	}

	public SpUserSettingsVO(JSONObject json) {
		super(json);
	}

	public SpUserSettingsVO(String userId, String squareId) {
		super(null);
		this.userId = userId;
		this.squareId = squareId;
		this.squareBlindFlag = SpSquareConst.FALSE_CH;
		this.notifyNoticeFlag = SpSquareConst.TRUE_CH;
		this.notifyMentionFlag = SpSquareConst.TRUE_CH;
		this.notifyCommentFlag = SpSquareConst.TRUE_CH;
		setTxNone();
	}

	public SpUserSettingsVO(String userId, String squareId, String squareBlindFlag) {
		super(null);
		this.userId = userId;
		this.squareId = squareId;
		this.squareBlindFlag = squareBlindFlag;
		this.notifyNoticeFlag = null;
		this.notifyMentionFlag = null;
		this.notifyCommentFlag = null;
		setTxNone();
	}

	public SpUserSettingsVO(String userId, String squareId, String notifyNoticeFlag, String notifyMentionFlag, String notifyCommentFlag) {
		super(null);
		this.userId = userId;
		this.squareId = squareId;
		this.squareBlindFlag = null;
		this.notifyNoticeFlag = notifyNoticeFlag;
		this.notifyMentionFlag = notifyMentionFlag;
		this.notifyCommentFlag = notifyCommentFlag;
		setTxNone();
	}

	public SpUserSettingsVO(String userId, String squareId, String squareBlindFlag, String notifyNoticeFlag,
			String notifyMentionFlag, String notifyCommentFlag) {
		super(null);
		this.userId = userId;
		this.squareId = squareId;
		this.squareBlindFlag = squareBlindFlag;
		this.notifyNoticeFlag = notifyNoticeFlag;
		this.notifyMentionFlag = notifyMentionFlag;
		this.notifyCommentFlag = notifyCommentFlag;
		setTxNone();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSquareId() {
		return squareId;
	}

	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}

	public String getSquareTitle() {
		return squareTitle;
	}

	public void setSquareTitle(String squareTitle) {
		this.squareTitle = squareTitle;
	}

	public String getSquareBlindFlag() {
		return squareBlindFlag;
	}

	public void setSquareBlindFlag(String squareBlindFlag) {
		this.squareBlindFlag = squareBlindFlag;
	}

	public String getNotifyNoticeFlag() {
		return notifyNoticeFlag;
	}

	public void setNotifyNoticeFlag(String notifyNoticeFlag) {
		this.notifyNoticeFlag = notifyNoticeFlag;
	}

	public String getNotifyContentsFlag() {
		return notifyContentsFlag;
	}

	public void setNotifyContentsFlag(String notifyContentsFlag) {
		this.notifyContentsFlag = notifyContentsFlag;
	}

	public String getNotifyMentionFlag() {
		return notifyMentionFlag;
	}

	public void setNotifyMentionFlag(String notifyMentionFlag) {
		this.notifyMentionFlag = notifyMentionFlag;
	}

	public String getNotifyCommentFlag() {
		return notifyCommentFlag;
	}

	public void setNotifyCommentFlag(String notifyCommentFlag) {
		this.notifyCommentFlag = notifyCommentFlag;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public boolean isTxCreate() {
		return txCreate;
	}

	public void setTxCreate(boolean txCreate) {
		this.txCreate = txCreate;
	}

	public boolean isTxModify() {
		return txModify;
	}

	public void setTxModify(boolean txModify) {
		this.txModify = txModify;
	}

	public boolean isTxDelete() {
		return txDelete;
	}

	public void setTxDelete(boolean txDelete) {
		this.txDelete = txDelete;
	}

	public boolean isTxNone() {
		return txNone;
	}

	public void setTxNone(boolean txNone) {
		this.txNone = txNone;
	}

	public void setTxNone() {
		this.transactionMode = SpSquareConst.TX_USER_NONE;
	}

	public void setTxDelete() {
		this.transactionMode = SpSquareConst.TX_USER_DELETE;
	}

	public void setTxCreate() {
		this.transactionMode = SpSquareConst.TX_USER_CREATE;
	}

	public void setTxModify() {
		this.transactionMode = SpSquareConst.TX_USER_MODIFY;
	}

	public boolean isSquareBlind() {
		return squareBlind;
	}

	public void setSquareBlind(boolean squareBlind) {
		this.squareBlind = squareBlind;
	}

	public boolean isNotifyNotice() {
		return notifyNotice;
	}

	public boolean isNotifyContents() {
		return notifyContents;
	}

	public boolean isNotifyMention() {
		return notifyMention;
	}

	public boolean isNotifyComment() {
		return notifyComment;
	}

	public void setNotifyNotice(boolean notifyNotice) {
		this.notifyNotice = notifyNotice;
	}

	public void setNotifyContents(boolean notifyContents) {
		this.notifyContents = notifyContents;
	}

	public void setNotifyMention(boolean notifyMention) {
		this.notifyMention = notifyMention;
	}

	public void setNotifyComment(boolean notifyComment) {
		this.notifyComment = notifyComment;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SpUserSettingsVO)) {
			return false;
		}

		return equalsUserSettings((SpUserSettingsVO)o);
	}

	private boolean equalsUserSettings(SpUserSettingsVO userSettingsVO) {
		if (userSettingsVO == null) {
			return false;
		}

		String userId = userSettingsVO.getUserId();
		String squareId = userSettingsVO.getSquareId();
		String squareBlindFlag = userSettingsVO.getSquareBlindFlag();
		String notifyNoticeFlag = userSettingsVO.getNotifyNoticeFlag();
		String notifyMentionFlag = userSettingsVO.getNotifyMentionFlag();
		String notifyCommentFlag = userSettingsVO.getNotifyCommentFlag();

		if (this.userId.equals(userId) && this.squareId.equals(squareId) && this.squareBlindFlag.equals(squareBlindFlag)
				&& this.notifyNoticeFlag.equals(notifyNoticeFlag) && this.notifyMentionFlag.equals(notifyMentionFlag)
				&& this.notifyCommentFlag.equals(notifyCommentFlag)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "{" + squareTitle + ", " + isNotifyNotice() + ", " + isNotifyContents() + ", " + isNotifyMention() + ", " + isNotifyComment() + "}";
	}
}