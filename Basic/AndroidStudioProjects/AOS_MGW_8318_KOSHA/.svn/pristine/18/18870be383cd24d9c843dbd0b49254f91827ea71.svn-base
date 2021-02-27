package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hs.mobile.gw.fragment.squareplus.ISpContent;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpContentsVO extends DefaultVO<JSONObject> implements Serializable, ISpContent {
	private static final long serialVersionUID = 1L;

	private String contentsId;
	private String squareId;
	private String parentId;			// 상위 콘텐츠 ID, 메인화면에서 콘텐츠에 연결된 의견 항목 추출을 위해 사용
	private String contentsType;
	private String messageType;
	private String authorId;
	private String authorName;
	private String authorPositionName;
	private String authorDutyName;
	private String authorDeptId;
	private String authorDeptName;
	private String title;
	private String body;
	private Date createDate;
	private Date modifyDate;
	private Date indexDate;
	private String indexId;
	private Date reportDate;
	private Date endDate;
	private int viewDepth;
	private Date lastCommentDate;		// 마지막으로 의견이 추가된 일시
	private String lastCommentParentId;	// 마지막으로 추가된 의견의 ParentId
	private String originalParentId;	// 원래 콘텐츠 ID, 상세보기에서 콘텐츠에 연결된 의견 항목 추출을 위해 사용
	private String securityFlag;
	private String temporaryFlag;		// 업무지시 상태
	private String noticeFlag;

	// NOT IN SQ_CONTENTS DB
	private List<SpOpenGraphVO> openGraphList;
	private List<SpAttachVO> attachList;
	private List<SpAttachVO> thumnailList;
	private List<SpAttachVO> fileList;
	private List<SpContentsMentionVO> contentsMentionList;
	private List<SpTagVO> tagList;
	private List<SpContentsVO> commentList;
	private List<SpFavorVO> favorList;
	private List<SpSquareMemberVO> systemMessageMemberList;
	private int commentCount;	// 의견갯수
	private int contentsCount;	// 의견갯수
	private String squareTitle;
	private String favoriteFlag;
	private String favorType;
	private int favorCount;
	private String createDateFormat;
	private String modifyDateFormat;
	private String reportDateFormat;
	private String endDateFormat;
	private String cloneFlag;
	private String forceFlag;
	private String orgContentsId;
	private String mySquareMemberFlag;
	private String defaultDeptId;
	private String defaultDeptFlag;
	private String parentBody;
	private String memberRights;
	private List<SpContentsMentionVO> parentMentionList;

	// ignore VO
	public boolean notice;
	public boolean contentsTypeSystemUser;
	public boolean contentsTypeComment;
	public boolean contentsTypeSystemSquare;
	public boolean contentsTypeTopic;
	public boolean contentsTypeMessage;
	public boolean contentsTypeFile;
	public boolean contentsTypeSystem;
	public boolean contentsTypeReport;
	public boolean temporaryContents;
	public boolean systemMessage;
	public boolean mySquareMember;
	public boolean contentsTypeSystemSquareIntro;
	public boolean contentsTypeNotice;
	public boolean defaultDept;
	public String favoriteType;

	public SpContentsVO() {
		super(null);
	}

	public SpContentsVO(JSONObject json) {
		super(json);
		this.messageType = SpSquareConst.CONTENTS_TYPE_SYSTEM_NONE;
		this.securityFlag = SpSquareConst.FALSE_CH;
		this.favoriteFlag = SpSquareConst.FALSE_CH;
		this.title = "";
		this.authorDutyName = "";
		this.authorPositionName = "";
		this.authorDeptId = "";
		this.authorDeptName = "";
		this.cloneFlag = SpSquareConst.FALSE_CH;
		this.forceFlag = SpSquareConst.TRUE_CH;
		this.temporaryFlag = SpSquareConst.FALSE_CH;
		this.noticeFlag = SpSquareConst.FALSE_CH;
		this.systemMessageMemberList = new ArrayList<SpSquareMemberVO>();
		this.favorType = SpSquareConst.FAVOR_TYPE_NONE;
	}

	public String getContentsId() {
		return contentsId;
	}

	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	public String getSquareId() {
		return squareId;
	}

	public void setSquareId(String squareId) {
		this.squareId = squareId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getContentsType() {
		return contentsType;
	}

	public ContentsType getContentsTypeEnum() {
		if (this.isContentsTypeTopic()) {
			return ContentsType.TOPIC;
		} else if (this.contentsTypeMessage) {
			return ContentsType.MESSAGE;
		} else if (this.contentsTypeFile) {
			return ContentsType.FILE;
		} else if (this.contentsTypeComment) {
			return ContentsType.COMMENT;
		} else if (this.contentsTypeReport) {
			return ContentsType.REPORT;
		} else if (this.contentsTypeSystemSquare) {
			return ContentsType.SYSTEM_SQUARE;
		} else if (this.contentsTypeSystemUser) {
			return ContentsType.SYSTEM_USER;
		} else if (this.contentsTypeSystemSquareIntro) {
			return ContentsType.SYSTEM_SQUARE_INTRO;
		} else {
			return ContentsType.UNKNOWN;
		}
	}

	public void setContentsType(String contentsType) {
		this.contentsType = contentsType;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public Date getModifyDate() {
		if (modifyDate != null) {
			return (Date)modifyDate.clone();
		} else {
			return null;
		}
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getIndexDate() {
		if (indexDate != null) {
			return (Date)indexDate.clone();
		} else {
			return null;
		}
	}

	public void setIndexDate(Date indexDate) {
		this.indexDate = indexDate;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public Date getReportDate() {
		if (reportDate != null) {
			return (Date)reportDate.clone();
		} else {
			return null;
		}
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getEndDate() {
		if (endDate != null) {
			return (Date)endDate.clone();
		} else {
			return null;
		}
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getViewDepth() {
		return viewDepth;
	}

	public void setViewDepth(int viewDepth) {
		this.viewDepth = viewDepth;
	}

	public String getSecurityFlag() {
		return securityFlag;
	}

	public void setSecurityFlag(String securityFlag) {
		this.securityFlag = securityFlag;
	}

	public Date getLastCommentDate() {
		if (lastCommentDate != null) {
			return (Date)lastCommentDate.clone();
		} else {
			return null;
		}
	}

	public void setLastCommentDate(Date lastCommentDate) {
		this.lastCommentDate = lastCommentDate;
	}

	public String getLastCommentParentId() {
		return lastCommentParentId;
	}

	public void setLastCommentParentId(String lastCommentParentId) {
		this.lastCommentParentId = lastCommentParentId;
	}

	public String getOriginalParentId() {
		return originalParentId;
	}

	public void setOriginalParentId(String originalParentId) {
		this.originalParentId = originalParentId;
	}

	public List<SpOpenGraphVO> getOpenGraphList() {
		if (openGraphList != null) {
			return new ArrayList<SpOpenGraphVO>(openGraphList);
		} else {
			return null;
		}
	}

	public void setOpenGraphList(List<SpOpenGraphVO> openGraphList) {
		this.openGraphList = openGraphList;
	}

	public List<SpAttachVO> getAttachList() {
		if (attachList != null) {
			return new ArrayList<SpAttachVO>(attachList);
		} else {
			return null;
		}
	}
	
	public void setAttachList(List<SpAttachVO> attachList) {
		this.attachList = attachList;
	}

	public void addAttach(SpAttachVO attach) {
		if (this.attachList == null) {
			this.attachList = new ArrayList<SpAttachVO>();
		}
		
		this.attachList.add(attach);
	}

	public void clearAttachList() {
		if (this.attachList != null) {
			this.attachList.clear();
		}
	}

	public List<SpAttachVO> getThumnailList() {
		return thumnailList;
	}

	public void setThumnailList(List<SpAttachVO> thumnailList) {
		this.thumnailList = thumnailList;
	}

	public void addThumnail(SpAttachVO attach) {
		if (this.thumnailList == null) {
			this.thumnailList = new ArrayList<SpAttachVO>();
		}
		
		this.thumnailList.add(attach);
	}

	public List<SpAttachVO> getFileList() {
		return fileList;
	}

	public void setFileList(List<SpAttachVO> fileList) {
		this.fileList = fileList;
	}

	public void addFile(SpAttachVO attach) {
		if (this.fileList == null) {
			this.fileList = new ArrayList<SpAttachVO>();
		}
		
		this.fileList.add(attach);
	}

	public void clearMentionList() {
		if (this.contentsMentionList != null) {
			this.contentsMentionList.clear();
		}
	}

	public void clearTagList() {
		if (this.tagList != null) {
			this.tagList.clear();
		}
	}

	public void addAttachList(List<SpAttachVO> attachList) {
		if (this.attachList == null) {
			this.attachList = new ArrayList<SpAttachVO>();
		}
		
		this.attachList.addAll(attachList);
	}

	public void addMentionList(List<SpContentsMentionVO> mentionList) {
		if (this.contentsMentionList == null) {
			this.contentsMentionList = new ArrayList<SpContentsMentionVO>();
		}
		
		this.contentsMentionList.addAll(mentionList);
	}

	public void addTagList(List<SpTagVO> tagList) {
		if (this.tagList == null) {
			this.tagList = new ArrayList<SpTagVO>();
		}
		
		this.tagList.addAll(tagList);
	}

	public List<SpContentsMentionVO> getMentionList() {
		if (contentsMentionList != null) {
			return new ArrayList<SpContentsMentionVO>(contentsMentionList);
		} else {
			return null;
		}
	}

	public void setMentionList(List<SpContentsMentionVO> contentsMentionList) {
		this.contentsMentionList = contentsMentionList;
	}

	public List<SpTagVO> getTagList() {
		if (tagList != null) {
			return new ArrayList<SpTagVO>(tagList);
		} else {
			return null;
		}
	}

	public void setTagList(List<SpTagVO> tagList) {
		this.tagList = tagList;
	}

	public List<SpContentsVO> getCommentList() {
		if (commentList != null) {
			return new ArrayList<SpContentsVO>(commentList);
		} else {
			return null;
		}
	}

	public void setCommentList(List<SpContentsVO> commentList) {
		this.commentList = commentList;
	}

	public List<SpFavorVO> getFavorList() {
		return favorList;
	}

	public void setFavorList(List<SpFavorVO> favorList) {
		this.favorList = favorList;
	}

	public List<SpSquareMemberVO> getSystemMessageMemberList() {
		return systemMessageMemberList;
	}

	public void setSystemMessageMemberList(List<SpSquareMemberVO> systemMessageMemberList) {
		this.systemMessageMemberList = systemMessageMemberList;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public int getContentsCount() {
		return contentsCount;
	}

	public void setContentsCount(int contentsCount) {
		this.contentsCount = contentsCount;
	}

	public String getSquareTitle() {
		return squareTitle;
	}

	public void setSquareTitle(String squareTitle) {
		this.squareTitle = squareTitle;
	}

	public String getFavoriteFlag() {
		return favoriteFlag;
	}

	public void setFavoriteFlag(String favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	
	public String getFavorType() {
		return favorType;
	}

	public void setFavorType(String favorType) {
		this.favorType = favorType;
	}

	public int getFavorCount() {
		return favorCount;
	}

	public void setFavorCount(int favorCount) {
		this.favorCount = favorCount;
	}

	public String getCreateDateFormat() {
		return createDateFormat;
	}

	public void setCreateDateFormat(String createDateFormat) {
		this.createDateFormat = createDateFormat;
	}

	public String getModifyDateFormat() {
		return modifyDateFormat;
	}

	public void setModifyDateFormat(String modifyDateFormat) {
		this.modifyDateFormat = modifyDateFormat;
	}

	public String getReportDateFormat() {
		return reportDateFormat;
	}

	public void setReportDateFormat(String reportDateFormat) {
		this.reportDateFormat = reportDateFormat;
	}

	public String getEndDateFormat() {
		return endDateFormat;
	}

	public void setEndDateFormat(String endDateFormat) {
		this.endDateFormat = endDateFormat;
	}

	public String getCloneFlag() {
		return cloneFlag;
	}

	public void setCloneFlag(String cloneFlag) {
		this.cloneFlag = cloneFlag;
	}
	
	public String getTemporaryFlag() {
		return temporaryFlag;
	}

	public void setTemporaryFlag(String temporaryFlag) {
		this.temporaryFlag = temporaryFlag;
	}

	public String getNoticeFlag() {
		return noticeFlag;
	}

	public void setNoticeFlag(String noticeFlag) {
		this.noticeFlag = noticeFlag;
	}

	public boolean isContentsTypeTopic() {
		return SpSquareConst.CONTENTS_TYPE_TOPIC.equals(this.contentsType);
	}
	
	public boolean isContentsTypeMessage() {
		return SpSquareConst.CONTENTS_TYPE_MESSAGE.equals(this.contentsType);
	}
	
	public boolean isContentsTypeFile() {
		return SpSquareConst.CONTENTS_TYPE_FILE.equals(this.contentsType);
	}
	
	public boolean isContentsTypeComment() {
		return SpSquareConst.CONTENTS_TYPE_COMMENT.equals(this.contentsType);
	}

	public boolean isContentsTypeSystem() {
		return SpSquareConst.CONTENTS_TYPE_SYSTEM.equals(this.contentsType);
	}
	
	public boolean isContentsTypeReport() {
		return SpSquareConst.CONTENTS_TYPE_REPORT.equals(this.contentsType);
	}
	
	public boolean isContentsTypeSystemSquare() {
		return SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE.equals(this.contentsType);
	}
	
	public boolean isContentsTypeSystemUser() {
		return SpSquareConst.CONTENTS_TYPE_SYSTEM_USER.equals(this.contentsType);
	}
	
	public boolean isNotice() {
		return SpSquareConst.TRUE_CH.equals(this.noticeFlag);
	}
	
	public String getFavoriteType() {
		if (this.contentsType.equals(SpSquareConst.CONTENTS_TYPE_COMMENT)) {
			return SpSquareConst.FAVORITE_TARGET_CONTENTS_COMMENT;
		} else if (this.contentsType.equals(SpSquareConst.CONTENTS_TYPE_FILE)) {
			return SpSquareConst.FAVORITE_TARGET_CONTENTS_FILE;
		} else if (this.contentsType.equals(SpSquareConst.CONTENTS_TYPE_MESSAGE)) {
			return SpSquareConst.FAVORITE_TARGET_CONTENTS_MESSAGE;
		} else if (this.contentsType.equals(SpSquareConst.CONTENTS_TYPE_REPORT)) {
			return SpSquareConst.FAVORITE_TARGET_CONTENTS_REPORT;
		} else if (this.contentsType.equals(SpSquareConst.CONTENTS_TYPE_TOPIC)) {
			return SpSquareConst.FAVORITE_TARGET_CONTENTS_TOPIC;
		} else {
			return SpSquareConst.FAVORITE_TARGET_NONE;
		}
	}

	public boolean canHaveSubContents() {
		return (isContentsTypeFile() || isContentsTypeReport() || isContentsTypeTopic() || isContentsTypeSystem());
	}
	
	public boolean canHaveParentContents() {
		return isContentsTypeComment();
	}
	
	public boolean canHaveBodyFile() {
		return (isContentsTypeReport() || isContentsTypeTopic());
	}
	
	public boolean canHaveAttach() {
		return (isContentsTypeComment() || isContentsTypeFile() || isContentsTypeReport() || isContentsTypeTopic());
	}
	
	public boolean isTemporaryContents() {
		return SpSquareConst.TRUE_CH.equals(this.temporaryFlag);
	}
	
	public boolean isSystemMessage() {
		return !this.messageType.equals(SpSquareConst.CONTENTS_TYPE_SYSTEM_NONE);
	}

	public void setForceFlag(String forceFlag) {
		this.forceFlag = forceFlag;
	}

	public boolean isForceFlag() {
		return SpSquareConst.TRUE_CH.equals(this.forceFlag);
	}

	public String getOrgContentsId() {
		return orgContentsId;
	}

	public void setOrgContentsId(String orgContentsId) {
		this.orgContentsId = orgContentsId;
	}

	public String getMySquareMemberFlag() {
		return mySquareMemberFlag;
	}

	public void setMySquareMemberFlag(String mySquareMemberFlag) {
		this.mySquareMemberFlag = mySquareMemberFlag;
	}

	public boolean isMySquareMember() {
		return SpSquareConst.TRUE_CH.equals(mySquareMemberFlag);
	}

	public String getMemberRights() {
		return memberRights;
	}

	public void setMemberRights(String memberRights) {
		this.memberRights = memberRights;
	}

	public MemberRights getMemberRightsEnum() {
		if (memberRights == null) {
			return MemberRights.NORMAL_USER;
		}

		if (memberRights.equals(SpSquareConst.SQ_MEMBER_RIGHTS_ADMIN)) {
			return MemberRights.ADMIN_USER;
		} else if (memberRights.equals(SpSquareConst.SQ_MEMBER_RIGHTS_MEMBER)) {
			return MemberRights.NORMAL_USER;
		} else {
			return MemberRights.OBSERVER_USER;
		}
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

	public boolean isDefaultDept() {
		return SpSquareConst.TRUE_CH.equals(defaultDeptFlag);
	}

	public String getParentBody() {
		return parentBody;
	}

	public List<SpContentsMentionVO> getParentMentionList() {
		return parentMentionList;
	}

	public void setParentBody(String parentBody) {
		this.parentBody = parentBody;
	}

	public void setParentMentionList(List<SpContentsMentionVO> parentMentionList) {
		this.parentMentionList = parentMentionList;
	}

	@Override
	public String toString() {
		return "{" + contentsId + ", " + authorName + ", " + body + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SpContentsVO)) {
			return false;
		}

		final SpContentsVO contentsVO = (SpContentsVO) o;

		if (contentsId == null || contentsVO.getContentsId() == null) {
			return false;
		}

		return contentsId.equals(contentsVO.getContentsId());
	}
}