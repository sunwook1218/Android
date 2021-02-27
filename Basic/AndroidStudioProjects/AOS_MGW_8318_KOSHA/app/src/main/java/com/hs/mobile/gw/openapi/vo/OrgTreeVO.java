package com.hs.mobile.gw.openapi.vo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class OrgTreeVO extends DefaultVO<JSONArray> {
	private static final long serialVersionUID = 1L;
	public List<OrgTreeItemVO> dataList = new ArrayList<OrgTreeItemVO>();

	public OrgTreeVO(JSONArray jarr) {
		super(jarr);
		if (jarr != null) {
			for (int i = 0; i < jarr.length(); ++i) {
				dataList.add(new OrgTreeItemVO(jarr.optJSONObject(i)));
			}
		}
	}
}
