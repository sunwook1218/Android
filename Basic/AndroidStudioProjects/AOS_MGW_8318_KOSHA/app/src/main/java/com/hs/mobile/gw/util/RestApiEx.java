package com.hs.mobile.gw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.openapi.squareplus.SpSquarePlusOpenApiEx.DataType;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.content.Context;
import android.text.TextUtils;

/**
 * RestAPI 베이스 클래스. 각 RestAPI 클래스를 만들때 상속받아 사용한다.
 * 
 * @author Changhoon Lee
 * @since 2014. 08. 05
 * @param <J>
 * 
 */
public abstract class RestApiEx<J> {
	private String mUuid;
	private boolean mBCancel = false;
	private static RestApiProgress mProgress;
	private boolean mBLoadingProgress;
	private boolean mBRunning = false;

	public synchronized boolean isRunning() {
		return mBRunning;
	}

	protected synchronized void setRunning(boolean b) {
		mBRunning = b;
	}

	protected abstract String getHostUrl();

	protected abstract String getEndPoint();

	public String getUrl() {
		return getHostUrl() + getEndPoint();
	}

	public static String printPostParam(ArrayList<BasicNameValuePair> param) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; param != null && i < param.size(); ++i) {
			sb.append(param.get(i).getName()).append('=').append(param.get(i).getValue());
			sb.append('&');
		}
		if (sb.length() > 0) {
			sb.replace(sb.length() - 1, sb.length(), "");
		}
		return sb.toString();
	}

	protected boolean isCanceled() {
		return mBCancel;
	}

	protected String getTag() {
		if (TextUtils.isEmpty(mUuid)) {
			throw new NullPointerException("tag value is null.");
		}
		return mUuid;
	}

	/**
	 * RestApiProgress 클래스를 가지고 있는지 검사하는 메소드
	 * 
	 * @return true면 RestApiProgress 있음 아니면 없음
	 */
	protected boolean hasProgress() {
		return mProgress == null ? false : true;
	}

	protected RestApiProgress getRestApiProgress() {
		return mProgress;
	}

	protected void setLoadingProgress(boolean b) {
		mBLoadingProgress = b;
	}

	protected boolean isLoadingProgress() {
		return mBLoadingProgress;
	}

	public RestApiEx() {
		// RestAPI클래스가 생성 될 때 이 호출의 고유 값을 생성 해서 취소시 사용한다.
		mUuid = UUID.randomUUID().toString();
	}

	public RestApiEx(RestApiProgress progress) {
		mProgress = progress;
	}

	public void cancel() {
		Debug.trace(mUuid, "요청 취소.");
		if (!mBCancel) {
			mBCancel = true;
		}
	}

	public void setRestApiProgress(RestApiProgress progress) {
		mProgress = progress;
	}

	public static void setLoadingProgressHandler(RestApiProgress loadingProgressHandler) {
		mProgress = loadingProgressHandler;
	}

	public RestApiEx(Context context) {
		// RestAPI클래스가 생성 될 때 이 호출의 고유 값을 생성 해서 취소시 사용한다.
		mUuid = UUID.randomUUID().toString();
		m_aq = new AQuery(context);
		headers.put("User-Agent", HMGWServiceHelper.USER_AGENT);
		headers.put("Accept", "application/json");
		headers.put("Accept-Language", Locale.getDefault().getLanguage());
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	}

	// 스퀘어 플러스용 멀티파트 전송을 처리하기 위해 사용한다.
	public RestApiEx(Context context, DataType type) {
		// RestAPI클래스가 생성 될 때 이 호출의 고유 값을 생성 해서 취소시 사용한다.
		mUuid = UUID.randomUUID().toString();
		m_aq = new AQuery(context);

		if (type == DataType.JSON) {
			headers.put("User-Agent", HMGWServiceHelper.USER_AGENT);
			headers.put("Accept", "application/json");
			headers.put("Accept-Language", Locale.getDefault().getLanguage());
			headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		} else if (type == DataType.MULTIPART) {
//			headers.put("User-Agent", HMGWServiceHelper.USER_AGENT);
//			headers.put("Accept", "multipart/form-data");
//			headers.put("Accept-Language", Locale.getDefault().getLanguage());
//			headers.put("Content-Type", "multipart/form-data; charset=UTF-8");
		}
	}

	public AQuery getAq() {
		return m_aq;
	}

	private AQuery m_aq;
	private AjaxCallback<J> m_ajaxCallBack;

	public void setAjaxCallBack(AjaxCallback<J> callBack) {
		m_ajaxCallBack = callBack;
	}

	public AjaxCallback<J> getAjaxCallback() {
		return m_ajaxCallBack;
	}

	private HashMap<String, String> headers = new HashMap<String, String>();

	public void load(Context context, AjaxCallback<J> callBack, Map<String, String> params) {
		callBack.timeout(Define.NETWORK_TIMEOUT);
		callBack.headers(headers);
		if (params != null) {
			callBack.params(params);
		}
		if (HMGWServiceHelper.cookies != null && HMGWServiceHelper.cookies.size() > 0) {
			for (Cookie cookie : HMGWServiceHelper.cookies) {
				callBack.cookie(cookie.getName(), cookie.getValue());
			}
		}
	}

	public void loadMultipart(Context context, AjaxCallback<J> callBack, Map<String, Object> params) {
		callBack.timeout(Define.NETWORK_TIMEOUT);
		callBack.headers(headers);
		if (params != null) {
			callBack.params(params);
		}
		if (HMGWServiceHelper.cookies != null && HMGWServiceHelper.cookies.size() > 0) {
			for (Cookie cookie : HMGWServiceHelper.cookies) {
				callBack.cookie(cookie.getName(), cookie.getValue());
			}
		}
	}
}
