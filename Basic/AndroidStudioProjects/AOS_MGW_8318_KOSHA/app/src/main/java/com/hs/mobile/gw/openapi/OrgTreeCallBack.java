package com.hs.mobile.gw.openapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.hs.mobile.gw.openapi.vo.OrgTreeItemVO;
import com.hs.mobile.gw.openapi.vo.OrgTreeVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;

public class OrgTreeCallBack extends DefaultCallback {

	public ArrayList<OrgTreeItemVO> data = new ArrayList<OrgTreeItemVO>();

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		JSONArray jarr;
		try {
			jarr = new JSONArray(strRet);
			
			OrgTreeVO vo = new OrgTreeVO(jarr);
			if (vo.dataList != null) {
				data.clear();
				for(OrgTreeItemVO item : vo.dataList) {
					data.add(item);
				}
			}
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
	}
	
}
