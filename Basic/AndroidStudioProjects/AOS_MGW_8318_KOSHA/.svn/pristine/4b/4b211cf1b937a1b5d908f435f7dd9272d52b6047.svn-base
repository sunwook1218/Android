package com.hs.mobile.gw.openapi.squareplus;

import java.io.InputStream;
import java.util.Map;

import com.androidquery.callback.AjaxCallback;

import android.content.Context;

public class SpImageThumbnail extends SpSquarePlusThumnailOpenApiEx {

	public SpImageThumbnail(Context context) {
		super(context);
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/imageThumbnail.do";
	}

	@Override
	public void load(Context context, AjaxCallback<InputStream> callBack, Map<String, String> params) {
		super.load(context, callBack, params);
		getAq().ajax(getUrl(), params, InputStream.class, callBack);
	}

	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}

}
