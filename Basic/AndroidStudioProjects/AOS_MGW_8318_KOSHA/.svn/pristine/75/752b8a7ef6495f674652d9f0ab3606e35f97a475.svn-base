package com.hs.mobile.gw.openapi.squareplus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.view.WindowManager.BadTokenException;

import com.androidquery.callback.AjaxStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.mobile.gw.SubActivity;
import com.hs.mobile.gw.openapi.GWDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.GWErrorCode;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;

public class SpSquarePlusDefaultAjaxCallBack<VO extends DefaultVO<?>> extends GWDefaultAjaxCallBack<VO, JSONObject> {
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	public static final int SESSION_EXPIRE = 20;
	public static final int REQUEST_WORNG = 9999;
	private Activity m_activity;
	public ResponseData responseData;
	public ResponseHeader responseHead;
	Class<VO> cls;
	
	public SpSquarePlusDefaultAjaxCallBack(Activity a, Class<VO> cls) {
		super(cls, JSONObject.class);
		m_activity = a;
		this.cls = cls;
	}
	public class ResponseHeader {

		public int resultCode = -1;
		public String resultMessage;

		public ResponseHeader(JSONObject json) {
			resultCode = Integer.valueOf(json.optString("resultCode"));
			resultMessage = json.optString("resultMessage");
		}

		public ResponseHeader(int nResultCode, String strResultMessage) {
			resultCode = nResultCode;
			resultMessage = strResultMessage;
		}
	}

	public class ResponseData {
		public int totalCount = 0;
		public int pageSize = 0;
		public int pageNum = 0;
		public String lastViewId = "";
		public List<VO> dataList;
		public VO item;

		public ResponseData(JSONObject json) {
			if (json == null) {
				return;
			}
			totalCount = json.optInt("totalCount", 0);
			pageSize = json.optInt("pageSize", 0);
			pageNum = json.optInt("pageNum", 0);
			lastViewId = json.optString("lastViewId");

			try {
				ObjectMapper objectMapper = new ObjectMapper();
				synchronized(objectMapper){
					if (json.has("dataList")) {
						if (dataList == null) {
							dataList = new ArrayList<VO>();
						}
						
						if (json.optJSONArray("dataList") != null) {
							for (int i=0; i<json.optJSONArray("dataList").length(); i++) {
								JSONObject item = json.optJSONArray("dataList").getJSONObject(i);
								if(item!= null) dataList.add(objectMapper.readValue(item.toString(), cls));
							}
						}
					} else {
						item = objectMapper.readValue(json.toString(), cls);
					}
				}
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			} catch (Exception e) {
				Debug.trace(e.getMessage());
			}
		}
	}
	@Override
	public void callback(String url, JSONObject json, AjaxStatus status) {
		super.callback(url, json, status);
		SquareDefaultVO vo = new SquareDefaultVO(json);
		if (vo.getJson() == null) {
			try {
				PopupUtil.showDialog(m_activity, GWErrorCode.NETWORK_ERROR.getErrorRes());
			} catch (BadTokenException e) {
				Debug.trace(e.getMessage());
			}
		} else {
			String strCode = null;
			if (vo.getJson().has("responseHead")) { 
				strCode = String.valueOf(vo.responseHead.resultCode);
			}
			
			if (vo.getJson().has("code")) {
				strCode = vo.getJson().optString("code");
			}
			
			GWErrorCode code = GWErrorCode.valueOfCode(strCode);
			if (code != null && code != GWErrorCode.SUCCESS_0) {
				
				if (code == GWErrorCode.SESSION_EXPIRED || code == GWErrorCode.ACCESS_DENIED) {
					if (m_activity.getClass().getName().contains(SubActivity.class.getName())) {
						m_activity.finish();
						return;
					}
				}
				PopupUtil.showDialog(m_activity, code.getErrorRes());
			}
			else if (getVO() != null) {
				if (json.has("responseHead")) {
					responseHead = new ResponseHeader(json.optJSONObject("responseHead"));
					if (responseHead != null && responseHead.resultCode == SUCCESS) {
						responseData = new ResponseData(json.optJSONObject("responseData"));
					} else if (responseHead != null) {
						Debug.trace("Error", responseHead.resultCode, responseHead.resultMessage);
						PopupUtil.showDialog(m_activity, responseHead.resultMessage);
						return;
					}
				}
			}
		}
	}
}
