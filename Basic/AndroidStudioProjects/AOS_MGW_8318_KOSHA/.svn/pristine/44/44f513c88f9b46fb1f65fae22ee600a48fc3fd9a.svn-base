package com.hs.mobile.gw.fragment;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.util.CommonController;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public abstract class CommonFragmentController<V extends CommonFragment, M> extends CommonController<V, M> {

	public CommonFragmentController(V view, M model) {
		super(view, model);
	}

	public Context getContenxt() {
		return getView().getActivity().getApplicationContext();
	}

	public Resources getResources() {
		return getView().getResources();
	}

	public String getString(int resId) {
		return getView().getString(resId);
	}
	
	public String getString(int resId, Object... formatArgs)
	{
		return getView().getString(resId, formatArgs);
	}

	public MainModel getMainModel() {
		return getView().getMainModel();
	}
	
	public Activity getActivity()
	{
		return getView().getActivity();
	}
	
	public Bundle getArguments()
	{
		return getView().getArguments();
	}

	public Drawable getDrawable(int nRes) {
		return getResources().getDrawable(nRes);
	}
	
}
