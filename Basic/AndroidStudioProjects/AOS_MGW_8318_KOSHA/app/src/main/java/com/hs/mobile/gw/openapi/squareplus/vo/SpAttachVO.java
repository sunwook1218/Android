package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.util.Debug;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpAttachVO extends DefaultVO<JSONObject> implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	private String attachId;
	private String attachType;
	private String contentsId;
	private String fileName;
	private String fileExt;
	private long fileSize;
	private String fileType;
	private Date createDate;
	private String squareId;
	
	//SQ_CONTENTS TABLE JOIN COLUMN
	private String authorId;
	private String authorName;
	private String authorPositionName;
	private String authorDutyName;
	private String authorDeptId;
	private String authorDeptName;
	private String createDateFormat;
	private String originalParentId;	//의견의 실제 상위 컨텐츠 ID
	private String squareTitle;
	private String defaultDeptId;
	private String defaultDeptFlag;
	private String indexId;
	
	private String tempFilePath;	// WAS에 올라온 파일의 임시경로.
	private String favoriteFlag;	// 즐겨찾기 여부
	private String transactionMode;	// 파일 트랜젝션 모드(추가, 삭제)
	private boolean isDeleteShow;	// 이미지 모드(삭제버튼 보이기, 삭제버튼 감추기)
	
	public boolean createState;
	public boolean txDelete;
	public boolean txCreate;
	public boolean txNone;
	public boolean typeDoc;
	public boolean typeImage;
	public boolean typeMov;
	public String parentId;
	public SpAttachVO() {
		super(null);
		this.attachType = SpSquareConst.ATTACH_TYPE_FILE;
		this.createDate = new Date();
		this.transactionMode = SpSquareConst.TX_FILE_NONE;
		this.isDeleteShow = false;
		this.createState = false;
	}

	public SpAttachVO(JSONObject json) {
		super(json);
		this.transactionMode = SpSquareConst.TX_FAVOR_NONE;
		this.isDeleteShow = false;
		this.createState = false;
	}

	public SpAttachVO(String attachId, String contentsId, String fileName,
			String fileExt, long fileSize, String fileType,
			Date createDate, String squareId, String authorId, String authorName) {
		super(null);
		this.attachId = attachId;
		this.contentsId = contentsId;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.createDate = createDate;
		this.squareId = squareId;
		this.authorId = authorId;
		this.authorName = authorName;
		this.isDeleteShow = false;
		this.createState = false;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getContentsId() {
		return contentsId;
	}

	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public Date getCreateDate() {
		if (createDate != null) {
			return (Date)createDate.clone();
		} else {
			return null;
		}
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSquareId() {
		return squareId;
	}

	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}
	
	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getAuthorPositionName() {
		return authorPositionName;
	}

	public void setAuthorPositionName(String authorPositionName) {
		this.authorPositionName = authorPositionName;
	}

	public String getAuthorDutyName() {
		return authorDutyName;
	}

	public void setAuthorDutyName(String authorDutyName) {
		this.authorDutyName = authorDutyName;
	}

	public String getAuthorDeptId() {
		return authorDeptId;
	}

	public void setAuthorDeptId(String authorDeptId) {
		this.authorDeptId = authorDeptId;
	}

	public String getAuthorDeptName() {
		return authorDeptName;
	}

	public void setAuthorDeptName(String authorDeptName) {
		this.authorDeptName = authorDeptName;
	}

	public String getOriginalParentId() {
		return originalParentId;
	}

	public void setOriginalParentId(String originalParentId) {
		this.originalParentId = originalParentId;
	}

	public String getSquareTitle() {
		return squareTitle;
	}

	public void setSquareTitle(String squareTitle) {
		this.squareTitle = squareTitle;
	}

	public String getDefaultDeptId() {
		return defaultDeptId;
	}

	public void setDefaultDeptId(String defaultDeptId) {
		this.defaultDeptId = defaultDeptId;
	}

	public String getDefaultDeptFlag() {
		return defaultDeptFlag;
	}

	public void setDefaultDeptFlag(String defaultDeptFlag) {
		this.defaultDeptFlag = defaultDeptFlag;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getTempFilePath() {
		return tempFilePath;
	}

	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}
	
	public String getFavoriteFlag() {
		return favoriteFlag;
	}

	public void setFavoriteFlag(String favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	
	public String getCreateDateFormat() {
		return createDateFormat;
	}

	public void setCreateDateFormat(String createDateFormat) {
		this.createDateFormat = createDateFormat;
	}

	public boolean isDeleteShow() {
		return isDeleteShow;
	}

	public void setDeleteShow(boolean isDeleteShow) {
		this.isDeleteShow = isDeleteShow;
	}

	public boolean isTypeDoc() {
		return SpSquareConst.FILE_TYPE_DOC.equals(this.fileType);
	}
	
	public boolean isTypeImage() {
		return SpSquareConst.FILE_TYPE_IMG.equals(this.fileType);
	}
	
	public boolean isTypeMov() {
		return SpSquareConst.FILE_TYPE_MOV.equals(this.fileType);
	}

	public void setTxNone() {
		this.transactionMode = SpSquareConst.TX_FILE_NONE;
	}
	
	public void setTxDelete() {
		this.transactionMode = SpSquareConst.TX_FILE_DELETE;
	}
	
	public void setTxCreate() {
		this.transactionMode = SpSquareConst.TX_FILE_CREATE;
	}
	
	public boolean isTxDelete() {
		return SpSquareConst.TX_FILE_DELETE.equals(this.transactionMode);
	}
	
	public boolean isTxCreate() {
		return SpSquareConst.TX_FILE_CREATE.equals(this.transactionMode);
	}
	
	public boolean isTxNone() {
		return SpSquareConst.TX_FILE_NONE.equals(this.transactionMode);
	}
	
	public boolean isFavorite() {
		return SpSquareConst.TRUE_CH.equals(this.favoriteFlag);
	}
	
	public boolean isCreateState() {
		return createState;
	}

	public void setCreateState(boolean createState) {
		this.createState = createState;
	}
	
	public Object clone() {
		try {
			SpAttachVO objReturn = (SpAttachVO)super.clone();

			if (createDate != null) {
				objReturn.createDate = (Date)createDate.clone();
			} else {
				objReturn.createDate = null;
			}

			return objReturn;
		} catch (CloneNotSupportedException e) {
			Debug.trace(e);
			return null;
		}
	}

	@Override
	public String toString() {
		return "{" + attachId + ", " + fileName + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SpAttachVO)) {
			return false;
		}

		final SpAttachVO attachVO = (SpAttachVO) o;

		if (attachId == null || attachVO.getAttachId() == null) {
			return false;
		}

		return attachId.equals(attachVO.getAttachId());
	}
}