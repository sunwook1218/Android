package com.hs.mobile.gw.openapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;
import com.hs.mobile.gw.view.SelectedListItem;

public class SearchAddressResult extends DefaultCallback {
	public ArrayList<SelectedListItem> data = new ArrayList<SelectedListItem>();

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		JSONArray jarr;
		try {
			jarr = new JSONArray(strRet);
			Debug.trace(jarr.toString(5));
			for (int i = 0; i < jarr.length(); ++i) {
				if (jarr.optJSONObject(i) != null) {
					JSONObject jsonObj = jarr.optJSONObject(i);
					String id = jsonObj.optString("id",jsonObj.optString("Id"));
					String email = jsonObj.optString("email");
					String objectType = jsonObj.optString("type");
					String name = jsonObj.optString("name");
					String dept_name = jsonObj.optString("dept_name");
					
					data.add(new SelectedListItem(id, email, objectType, name, dept_name));
				}
			}
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
	}
}
