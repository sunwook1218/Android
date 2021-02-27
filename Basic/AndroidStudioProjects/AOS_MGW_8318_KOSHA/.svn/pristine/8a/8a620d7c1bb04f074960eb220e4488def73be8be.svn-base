package com.hs.mobile.gw.openapi.square.vo;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.util.Debug;

public class SquareDefaultVO extends DefaultVO<JSONObject> {
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	public static final int SESSION_EXPIRE = 20;
	public static final int REQUEST_WORNG = 9999;
	public ResponseData responseData;
	public ResponseHeader responseHead;

	public SquareDefaultVO(JSONObject json) {
		super(json);
		if (getJson() != null) {
			if (getJson().has("responseHead")) {
				responseHead = new ResponseHeader(getJson().optJSONObject("responseHead"));
				if (responseHead != null && responseHead.resultCode == SUCCESS) {
					responseData = new ResponseData(getJson().optJSONObject("responseData"));
				} else {
					Debug.trace("Error", responseHead.resultCode, responseHead.resultMessage);
				}
			} else {
				Debug.trace("Error", getJson().toString());
			}
		}
	}
}
