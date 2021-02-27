package com.hs.mobile.gw.openapi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.util.Debug;

import android.text.TextUtils;

/**
 * 
 * @author handy
 *
 * @param <VO>
 *            리턴을 처리할 VO객체
 * @param <J>
 *            서버로 부터 받게 되는 데이터 타입 예: JSONObject or JSONArray
 */
public class GWDefaultAjaxCallBack<VO extends DefaultVO<?>, J> extends AjaxCallback<J> {
	private Class<VO> typeArgumentClass;
	private VO mVO;
	private Class<J> jsonType;

	public GWDefaultAjaxCallBack(Class<VO> cls, Class<J> jsonType) {
		typeArgumentClass = cls;
		this.jsonType = jsonType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void callback(String url, J json, AjaxStatus status) {
		super.callback(url, json, status);
		Debug.trace(typeArgumentClass.getName(), "-> 응답 시간:", status.getDuration(), "ms");
		// 200 응답 코드가 떨어진 시점에서 jsonObject를 사용할 수 있다.
		if (status.getCode() == 200 && json != null) {
			try {
				Class<VO> cls = (Class<VO>) Class.forName(typeArgumentClass.getName());
				Class<J> jt = (Class<J>) Class.forName(jsonType.getName());

				Constructor<VO> constructor = (Constructor<VO>) cls.asSubclass(DefaultVO.class).getConstructor(new Class[] { jt });
				mVO = constructor.newInstance(json);
				if(mVO.getJson() != null && mVO.getJson() instanceof JSONObject)
				{
					JSONObject jObj = (JSONObject) mVO.getJson();
					if(jObj.has("message") && jObj.has("code") && GWErrorCode.valueOfCode(jObj.getString("code")) != null)
					{
						Debug.trace(jObj);
						if(TextUtils.equals("1003",jObj.getString("code"))){
							if(MainFragment.getController() != null){
								MainFragment.getController().forceLogout(R.string.error_session_expired);
							}else{
								throw new MGWException(GWErrorCode.valueOfCode(jObj.getString("1001")));
							}
							return;
						}
						if(TextUtils.equals("1004",jObj.getString("code"))){
							MainFragment.getController().forceLogout(R.string.error_access_denied);
							return;
						}
						throw new MGWException(GWErrorCode.valueOfCode(jObj.getString("code")));
					}else if(jObj.has("message") && jObj.has("code")){
						ViewModel.Log(jObj, "tkofs_new_error_code");
					}
				}				
			} catch (InstantiationException e) {
				Debug.trace(e.getMessage());
			} catch (IllegalAccessException e) {
				Debug.trace(e.getMessage());
			} catch (IllegalArgumentException e) {
				Debug.trace(e.getMessage());
			} catch (NoSuchMethodException e) {
				Debug.trace(e.getMessage());
			} catch (InvocationTargetException e) {
				Debug.trace(e.getMessage());
			} catch (ClassNotFoundException e) {
				Debug.trace(e.getMessage());
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			} catch (MGWException e) {
				Debug.trace(e.getMessage());
			}
		}
	}

	public void setVO(VO vo) {
		mVO = vo;
	}

	public VO getVO() {
		return mVO;
	}
}
