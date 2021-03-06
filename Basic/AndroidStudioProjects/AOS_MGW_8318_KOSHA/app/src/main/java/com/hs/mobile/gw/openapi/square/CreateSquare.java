package com.hs.mobile.gw.openapi.square;

import java.io.IOException;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.Response;

import android.content.Context;

public class CreateSquare extends SquareOpenApi {

	@Override
	protected String getOpenApiPath() {
		return "/square/createSquare.do";
	}

	@Override
	protected String[] getParams() {
		return new String[] {"title","desc","dueDate","member"};
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
	public DataType getDataType() {
		return DataType.JSON;
	}

}
