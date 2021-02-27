package com.hs.mobile.gw.openapi.vo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class MenuListVO extends DefaultVO<JSONArray> {
	public List<MenuInfoListItemVO> menuInfo = new ArrayList<MenuInfoListItemVO>();

	public MenuListVO(JSONArray jarr) {
		super(jarr);
		if (jarr != null) {
			for (int i = 0; i < jarr.length(); ++i) {
				menuInfo.add(new MenuInfoListItemVO(jarr.optJSONObject(i)));
			}
		}
	}
}
