package com.hs.mobile.gw.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

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
public abstract class RestAPI<J> {
	private OkHttpClient mClient = new OkHttpClient();
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

	protected abstract String[] getParams();

	public String getUrl() {
		return getHostUrl() + getEndPoint();
	}

	/**
	 * 전달받은 가변인자를 QueryString 형태로 바꿔주는 메소드
	 * 
	 * @param params
	 *            각종 인자값들
	 * @return QueryString 형태의 String값
	 * @throws UnsupportedEncodingException
	 */
	protected String makeGetParams(String... params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append('?');
		for (int i = 0; params != null && i < getParams().length; ++i) {
			if (TextUtils.isEmpty(params[i])) {
				continue;
			}
			sb.append(getParams()[i]).append('=').append(TextUtils.isEmpty(params[i]) ? "" : URLEncoder.encode(params[i], "UTF-8"));
			sb.append('&');
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		Debug.trace("makeGetParams: ", sb.toString());
		return sb.toString();
	}

	/**
	 * 전달받은 가변 인자를 Post요청시 사용할 수 있도록 application/x-www-url-form-encoded 형태로 바꿔주는
	 * 메소드
	 * 
	 * @param params
	 *            각종 인자값
	 * @return key/value 쌍 ArrayList
	 */
	protected ArrayList<BasicNameValuePair> makePostParams(String... params) {
		ArrayList<BasicNameValuePair> ret = new ArrayList<BasicNameValuePair>();

		for (int i = 0; params != null && i < params.length; ++i) {
			if (TextUtils.isEmpty(params[i])) {
				continue;
			} else {
				BasicNameValuePair p = new BasicNameValuePair(getParams()[i], params[i]);
				ret.add(p);
			}
		}
		return ret;
	}

	protected Map<String, String> makePostParamsHashMap(String... params) {
		HashMap<String, String> ret = new HashMap<String, String>();

		for (int i = 0; params != null && i < params.length; ++i) {
			if (TextUtils.isEmpty(params[i])) {
				continue;
			} else {
				ret.put(getParams()[i], params[i]);
			}
		}
		return ret;
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

	protected OkHttpClient getOkhttpClient() {
		return mClient;
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

	public RestAPI() {
		// RestAPI클래스가 생성 될 때 이 호출의 고유 값을 생성 해서 취소시 사용한다.
		mUuid = UUID.randomUUID().toString();
	}

	public RestAPI(RestApiProgress progress) {
		mProgress = progress;
	}

	public abstract Response load(Context context, String... params);

	public void cancel() {
		Debug.trace(mUuid, "요청 취소.");
		if (!mBCancel) {
			mBCancel = true;
			mClient.cancel(mUuid);
		}
	}

	public void setRestApiProgress(RestApiProgress progress) {
		mProgress = progress;
	}

	public static void setLoadingProgressHandler(RestApiProgress loadingProgressHandler) {
		mProgress = loadingProgressHandler;
	}

	public RestAPI(Context context) {
		// RestAPI클래스가 생성 될 때 이 호출의 고유 값을 생성 해서 취소시 사용한다.
		mUuid = UUID.randomUUID().toString();
		m_aq = new AQuery(context);
		headers.put("User-Agent", HMGWServiceHelper.USER_AGENT);
		headers.put("Accept", "application/json");
		headers.put("Accept-Language", Locale.getDefault().getLanguage());
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
		callBack.headers(headers);
		if(params != null)
		{
			callBack.params(params);
		}
		if(HMGWServiceHelper.cookies != null &&HMGWServiceHelper.cookies.size() > 0)
		{
			for(Cookie cookie:HMGWServiceHelper.cookies)
			{
				callBack.cookie(cookie.getName(), cookie.getValue());
			}
		}
	};
}
