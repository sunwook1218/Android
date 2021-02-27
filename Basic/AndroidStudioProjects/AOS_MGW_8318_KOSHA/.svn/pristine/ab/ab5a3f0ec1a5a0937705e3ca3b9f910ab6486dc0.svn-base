package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpSquareVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Status {
		IN_PROGRESS, END, BLIND, NONE
	}

	private String communityId;
	private String squareId;
	private String authorId;
	private String authorName;
	private String authorPositionName;
	private String authorDutyName;
	private String authorDeptId;
	private String authorDeptName;	
	private String description;
	private String title;
	private long createDate;
	private long modifyDate;
	private long endDate;
	private long recentlyDate;
	private String securityFlag;
	private String defaultDeptFlag;
	private String status;

	// NOT IN SQ_SQUARE DB
	private List<SpSquareMemberVO> squareMemberList;		// 부서가 모두 풀린 리스트
	private List<SpSquareMemberVO> viewMemberList;		// 보여지는 용도의 리스트
	private List<SpSquareMemberVO> dbMemberList;			// 실제 DB에 저장된 리스트
	private List<SpAttachVO> attachList;
	private String favoriteFlag;
	private int orderCount;
	private int newCount;
	private String newSquareFlag;
	private String folderId;
	private String folderFlag;
	private String selectedMember;
	private String createDateFormat;
	private String modifyDateFormat;
	private String dueDateFormat;
	private String endDateFormat;
	private int memberCount;
	private String adminFlag;
	private boolean isSystemAdmin;
	private boolean isMySquareMember;
	private String memberRights;
	private String defaultDeptId;

	// ignore VO
	private boolean defaultDept;
	private boolean ing;
	private boolean end;
	private boolean favorite;
	private boolean admin;
	private boolean remove;
	private boolean folder;
	private boolean memberAdmin;
	private boolean blind;
	private boolean newSquare;
	private boolean security;
	private boolean isSelected = false;

	public SpSquareVO() {
		super(null);
	}

	public SpSquareVO(JSONObject json) {
		super(json);
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String commnunityId) {
		this.communityId = commnunityId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(long modifyDate) {
		this.modifyDate = modifyDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public long getRecentlyDate() {
		return recentlyDate;
	}

	public void setRecentlyDate(long recentlyDate) {
		this.recentlyDate = recentlyDate;
	}

	public String getSecurityFlag() {
		return securityFlag;
	}

	public void setSecurityFlag(String securityFlag) {
		this.securityFlag = securityFlag;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getDefaultDeptFlag() {
		return defaultDeptFlag;
	}

	public void setDefaultDeptFlag(String defaultDeptFlag) {
		this.defaultDeptFlag = defaultDeptFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Status getStatusEnum() {
		return TextUtils.equals(getStatus(), "0") ? Status.IN_PROGRESS : TextUtils.equals(getStatus(), "1")? Status.END:Status.BLIND;
	}

	public List<SpSquareMemberVO> getSquareMemberList() {
		return squareMemberList;
	}

	public ArrayList<SpSquareMemberVO> getSquareMemberArrayList() {
		ArrayList<SpSquareMemberVO> squareMemberArrayList = new ArrayList<SpSquareMemberVO>();

		for (SpSquareMemberVO squareMemberVO : squareMemberList) {
			squareMemberArrayList.add(squareMemberVO);
		}

		return squareMemberArrayList;
	}

	public void setSquareMemberList(List<SpSquareMemberVO> squareMemberList) {
		this.squareMemberList = squareMemberList;
	}

	public List<SpSquareMemberVO> getViewMemberList() {
		return viewMemberList;
	}

	public void setViewMemberList(List<SpSquareMemberVO> viewMemberList) {
		this.viewMemberList = viewMemberList;
	}

	public List<SpSquareMemberVO> getDbMemberList() {
		return dbMemberList;
	}

	public void setDbMemberList(List<SpSquareMemberVO> dbMemberList) {
		this.dbMemberList = dbMemberList;
	}

	public List<SpAttachVO> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<SpAttachVO> attachList) {
		this.attachList = attachList;
	}

	public String getFavoriteFlag() {
		return favoriteFlag;
	}

	public void setFavoriteFlag(String favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
		this.favorite = SpSquareConst.TRUE_CH.equals(favoriteFlag) ? true : false;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public String getNewSquareFlag() {
		return newSquareFlag;
	}

	public void setNewSquareFlag(String newSquareFlag) {
		this.newSquareFlag = newSquareFlag;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getFolderFlag() {
		return folderFlag;
	}

	public void setFolderFlag(String folderFlag) {
		this.folderFlag = folderFlag;
	}

	public String getSelectedMember() {
		return selectedMember;
	}

	public void setSelectedMember(String selectedMember) {
		this.selectedMember = selectedMember;
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

	public String getDueDateFormat() {
		return dueDateFormat;
	}

	public void setDueDateFormat(String dueDateFormat) {
		this.dueDateFormat = dueDateFormat;
	}

	public String getEndDateFormat() {
		return endDateFormat;
	}

	public void setEndDateFormat(String endDateFormat) {
		this.endDateFormat = endDateFormat;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	public boolean isSystemAdmin() {
		return isSystemAdmin;
	}

	public void setSystemAdmin(boolean isSystemAdmin) {
		this.isSystemAdmin = isSystemAdmin;
	}

	public boolean isMySquareMember() {
		return isMySquareMember;
	}

	public void setMySquareMember(boolean isMySquareMember) {
		this.isMySquareMember = isMySquareMember;
	}

	public String getMemberRights() {
		return memberRights;
	}

	public void setMemberRights(String memberRights) {
		this.memberRights = memberRights;
	}

	public MemberRights getMemberRightsEnum() {
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

	public boolean isDefaultDept() {
		return defaultDept;
	}

	public void setDefaultDept(boolean defaultDept) {
		this.defaultDept = defaultDept;
	}

	public boolean isIng() {
		return ing;
	}

	public void setIng(boolean ing) {
		this.ing = ing;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public boolean isFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	public boolean isMemberAdmin() {
		return memberAdmin;
	}

	public void setMemberAdmin(boolean memberAdmin) {
		this.memberAdmin = memberAdmin;
	}

	public boolean isBlind() {
		return blind;
	}

	public void setBlind(boolean blind) {
		this.blind = blind;
	}

	public boolean isNewSquare() {
		return newSquare;
	}

	public void setNewSquare(boolean newSquare) {
		this.newSquare = newSquare;
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
	}

	public boolean equals(Object o) {
		if (!(o instanceof SpSquareVO)) {
			return false;
		}

		return this.squareId.equals(((SpSquareVO)o).getSquareId());
	}

	@Override
	public String toString() {
		return "{" + squareId + ", " + title + "}";
	}
}