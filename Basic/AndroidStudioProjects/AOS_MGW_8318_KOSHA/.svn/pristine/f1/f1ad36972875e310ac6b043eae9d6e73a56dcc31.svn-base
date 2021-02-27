package com.hs.mobile.gw.openapi.square;

import com.hs.mobile.gw.util.DownloadCallBack;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.Response;

import android.content.Context;

public class DownloadAttach extends SquareOpenApi {
	private DownloadCallBack m_downloadCallBack;

	@Override
	protected String getOpenApiPath() {
		return "/square/downloadAttach.do";
	}

	@Override
	protected String[] getParams() {
		return new String[] { "contentsId", "attachId" };
	}

	@Override
	public Response load(Context context, String... params) {
		NetUtils.requestDownloadByPost(context, getOkhttpClient(), getUrl(), m_downloadCallBack, makePostParams(params), getTag());
		return null;
	}

	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}

	public void setCallBack(DownloadCallBack downloadCallBack) {
		m_downloadCallBack = downloadCallBack;
	}

}
