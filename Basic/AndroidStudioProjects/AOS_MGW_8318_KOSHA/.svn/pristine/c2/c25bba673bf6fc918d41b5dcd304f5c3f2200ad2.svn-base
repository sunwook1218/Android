package com.hs.mobile.gw.openapi.square;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.openapi.GWDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.GWErrorCode;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;

import android.app.Activity;
import android.view.WindowManager.BadTokenException;

public class SquareDefaultAjaxCallBack<VO extends DefaultVO<?>> extends GWDefaultAjaxCallBack<VO, JSONObject> {

	private Activity m_activity;

	public SquareDefaultAjaxCallBack(Activity a, Class<VO> cls) {
		super(cls, JSONObject.class);
		m_activity = a;
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
			String strCode = String.valueOf(vo.responseHead.resultCode);
			GWErrorCode code = GWErrorCode.valueOfCode(strCode);
			if (code != null && code != GWErrorCode.SUCCESS_0) {
				PopupUtil.showDialog(m_activity, code.getErrorRes());
			}
		}
	}
}
