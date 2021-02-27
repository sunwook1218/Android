package com.hs.mobile.gw.util;

import java.io.IOException;
import java.util.Map;

import com.androidquery.callback.AjaxCallback;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.os.AsyncTask;

public class ApiLoader<J> extends AsyncTask<Object, Object, String> {
	private Callback mCallBack;
	private String[] mParams;
	private Context mContext;
	private RestAPI<J> mRestApi;
	private AjaxCallback<J> mAjaxCallBack;
	private Map<String, String> mHashParams;

	@Override
	protected String doInBackground(Object... arg0) {
		if (mAjaxCallBack != null) {
			Debug.trace("Load", mRestApi.getClass().getName());
			mRestApi.load(mContext, mAjaxCallBack, mHashParams);
		} else {
			Response response = mRestApi.load(mContext, mParams);
			if (response != null && response.isSuccessful()) {
				try {
					return response.body().string();
				} catch (IOException e) {
					Debug.trace(e.getMessage());
					return null;
				}
			} else {
				if (response != null) {
					Debug.trace("Code: ", response.code(), "Message: ", response.message());
				}
				return null;
			}
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (mRestApi.hasProgress() && !mRestApi.isLoadingProgress()) {
			mRestApi.getRestApiProgress().getListener().showProgress();
			mRestApi.setLoadingProgress(true);
		}
		mRestApi.setRunning(true);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (mRestApi.hasProgress() && mRestApi.isLoadingProgress()) {
			mRestApi.getRestApiProgress().getListener().hideProgress();
			mRestApi.setLoadingProgress(false);
		}
		mRestApi.setRunning(false);
		if (mAjaxCallBack == null && mCallBack != null) {
			if (result == null) {
				mCallBack.onFailure("");
			} else {
				mCallBack.onResponse(result);
			}
		}
	}

	public ApiLoader(RestAPI api, Context c, Callback listener, String... params) {
		mRestApi = api;
		mContext = c;
		mCallBack = listener;
		mParams = params;
	}

	public ApiLoader(RestAPI<J> api, Context c, AjaxCallback<J> callBack, Map<String, String> params) {
		mRestApi = api;
		mContext = c;
		mAjaxCallBack = callBack;
		mHashParams = params;
	}

}
