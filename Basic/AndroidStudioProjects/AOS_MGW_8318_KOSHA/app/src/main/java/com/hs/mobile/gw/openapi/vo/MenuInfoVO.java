package com.hs.mobile.gw.openapi.vo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class MenuInfoVO extends DefaultVO<JSONArray> {
	public List<MenuInfoItemVO> menuInfo = new ArrayList<MenuInfoItemVO>();

	public MenuInfoVO(JSONArray jarr) {
		super(jarr);
		if (jarr != null) {
			for (int i = 0; i < jarr.length(); ++i) {
				menuInfo.add(new MenuInfoItemVO(jarr.optJSONObject(i)));
			}
		}
	}
}
