package com.hs.mobile.gw.util;

import android.app.Activity;

/**
 * RestAPI 작업시 Progress기능을 지원하는 클래스
 * 
 * @author Changhoon Lee
 * @since 2014-08-26
 * 
 */
public class RestApiProgress {
	public interface IProgressListener {
		void onProgress(int n);

		void showProgress();

		void hideProgress();
	}

	private IProgressListener mListener;
	private Activity mActivity;

	public RestApiProgress(Activity activity, IProgressListener listener) {
		mActivity = activity;
		mListener = listener;
	}

	public Activity getActivity() {
		return mActivity;
	}
	
	public void setActivity(Activity a)
	{
		mActivity = a;
	}

	public IProgressListener getListener() {
		return mListener;
	}
}
