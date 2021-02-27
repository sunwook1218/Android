package com.hs.mobile.gw.openapi.vo;

import java.util.ArrayList;

import org.json.JSONArray;

public class IconInfoVO extends DefaultVO<JSONArray> {
	public ArrayList<IconInfoItemVO> icons = new ArrayList<IconInfoItemVO>();

	public IconInfoVO(JSONArray json) {
		super(json);
		for (int i = 0; i < getJson().length(); ++i) {
			icons.add(new IconInfoItemVO(getJson().optJSONObject(i)));
		}
	}
}
