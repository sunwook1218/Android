package com.hs.mobile.gw.util;

import java.util.Map;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;
import android.os.AsyncTask;

public class ApiLoaderEx<J> extends AsyncTask<Object, Object, String> {
	private Context mContext;
	private RestApiEx<J> mRestApi;
	private AjaxCallback<J> mAjaxCallBack;
	private Map<String, String> mHashParams;

	public Context getContext() {
		return mContext;
	}

	@Override
	protected String doInBackground(Object... arg0) {
		Debug.trace("Load", mRestApi.getClass().getName());
		mRestApi.load(mContext, mAjaxCallBack, mHashParams);

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
	}
	
	public ApiLoaderEx(RestApiEx<J> api, Context c, AjaxCallback<J> callBack, Map<String, String> params) {
		mRestApi = api;
		mContext = c;
		mAjaxCallBack = callBack;
		mHashParams = params;
	}

}
