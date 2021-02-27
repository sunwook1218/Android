package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class SqUser extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;
	private SqOrgID sqOrgId;
	private String name;
	private String positionName;	// 사용자인경우, 직위명
	private String dutyName;		// 사용자인경우, 직책명
	private String deptName;		// 사용자인경우, 부서명
	private String deptId;			// 사용자인경우, 부서ID	
	private int seq;
	private int SancLevel;
	private String empCode;
	private String rankName;
	private int rankLevel;
	private String managerType;

	public SqUser() {
		super(null);
	}

	public SqUser(JSONObject json) {
		super(json);
	}

	public SqOrgID getSqOrgId() {
		return sqOrgId;
	}

	public void setSqOrgId(SqOrgID sqOrgId) {
		this.sqOrgId = sqOrgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getSancLevel() {
		return SancLevel;
	}

	public void setSancLevel(int sancLevel) {
		SancLevel = sancLevel;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public int getRankLevel() {
		return rankLevel;
	}

	public void setRankLevel(int rankLevel) {
		this.rankLevel = rankLevel;
	}

	public String getManagerType() {
		return managerType;
	}

	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
}