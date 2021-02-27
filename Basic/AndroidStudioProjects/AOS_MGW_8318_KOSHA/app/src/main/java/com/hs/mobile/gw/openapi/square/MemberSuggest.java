package com.hs.mobile.gw.openapi.square;

import java.io.IOException;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.Response;

import android.content.Context;

public class MemberSuggest extends SquareOpenApi {

	@Override
	protected String[] getParams() {
		return new String[] { "squareId", "searchKey" };
	}

	@Override
	public Response load(Context context, String... params) {
		try {
			return NetUtils.requestPost(context, getOkhttpClient(), getUrl(), getTag(), makePostParams(params));
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}

	@Override
	protected String getOpenApiPath() {
		return "/square/memberSuggest.do";
	}

	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}

}
