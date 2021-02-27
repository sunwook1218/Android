package com.hs.mobile.gw.openapi;

import java.io.IOException;
import java.net.CookieManager;

import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.text.TextUtils;

public class SendEmail extends GWOpenApi {

	private String[] m_params;

	@Override
	protected String getEndPoint() {
		return "/rest/openapi/bypass/multipart";
	}

	@Override
	protected String[] getParams() {
		return m_params.clone();
	}

	@Override
	public Response load(Context context, String... params) {
		getOkhttpClient().setCookieHandler(CookieManager.getDefault());
		MultipartBuilder mb = new MultipartBuilder();
		mb.type(MultipartBuilder.FORM);
		mb.addFormDataPart("openapipath", "/wma/wmasm.do");
		mb.addFormDataPart("openapi", "true");
		mb.addFormDataPart("key", HMGWServiceHelper.key);
		mb.addFormDataPart("autoarchivesent", "true");
		mb.addFormDataPart("ismassmail", "false");
		for (int i = 0; i < getParams().length; ++i) {
			if (!TextUtils.isEmpty(params[i])) {
				mb.addFormDataPart(getParams()[i], params[i]);
				Debug.trace(getParams()[i], ":", params[i]);
			}
		}

		RequestBody body = mb.build();
		// Buffer buffer = new Buffer();
		// try {
		// body.writeTo(buffer);
		// byte[] bt = new byte[(int) buffer.size()];
		// buffer.inputStream().read(bt);
		// String s = new String(bt);
		// Debug.trace(s);
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		try {
			return NetUtils.reqeustMultipartUpload(context, getOkhttpClient(), getUrl(), body, getTag());
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}

	public void setParams(String[] keys) {
//		m_params = keys;
		m_params = java.util.Arrays.copyOf(keys, keys.length);
	}

}
