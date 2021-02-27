package com.hs.mobile.gw.openapi.vo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class AdditionalOfficerVO extends DefaultVO<JSONArray> {
	public List<AdditionalOfficerItemVO> additionalofficer = new ArrayList<AdditionalOfficerItemVO>();

	public int length() {
		return additionalofficer == null ? 0 : additionalofficer.size();
	}

	public AdditionalOfficerVO(JSONArray json) {
		super(json);
		for (int i = 0; i < getJson().length(); ++i) {
			additionalofficer.add(new AdditionalOfficerItemVO(getJson().optJSONObject(i)));
		}
	}
}
