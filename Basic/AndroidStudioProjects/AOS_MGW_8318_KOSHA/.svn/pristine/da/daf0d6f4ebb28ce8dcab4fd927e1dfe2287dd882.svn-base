package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.Serializable;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class SqDept extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;
	private SqOrgID sqOrgId;
	private String name;

	public SqDept() {
		super(null);
	}

	public SqDept(JSONObject json) {
		super(json);
	}

	public SqOrgID getSqOrgId() {
		if (sqOrgId != null) {
			return (SqOrgID)sqOrgId.clone();
		} else {
			return null;
		}
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
}
