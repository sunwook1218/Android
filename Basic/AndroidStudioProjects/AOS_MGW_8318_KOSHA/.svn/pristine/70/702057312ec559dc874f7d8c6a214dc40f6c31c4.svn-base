package com.hs.mobile.gw.openapi.square;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

public class AddComment extends SquareOpenApi implements IFileAddable {

	private ArrayList<String> m_filePaths;

	@Override
	protected String getOpenApiPath() {
		return "/square/addComment.do";
	}

	@Override
	protected String[] getParams() {
		return new String[] { "squareId", "originalParentContentsId", "comment" };
	}

	@Override
	public void setFilePath(ArrayList<String> imgPathArray) {
		m_filePaths = imgPathArray;
	}

	@Override
	public Response load(Context context, String... params) {
		try {
			MultipartBuilder mb = new MultipartBuilder();
			mb.type(MultipartBuilder.FORM);
			for (int i = 0; i < getParams().length; ++i) {
				if (!TextUtils.isEmpty(params[i])) {
					mb.addFormDataPart(getParams()[i], params[i]);
				}
			}
			if (m_filePaths != null && m_filePaths.size() > 0) {
				for (int i = 0; i < m_filePaths.size(); i++) {
					String path = m_filePaths.get(i);
					mb.addFormDataPart("att" + i, Uri.parse(path).getLastPathSegment(),
							RequestBody.create(MultipartBuilder.FORM, new File(path)));
				}
				mb.addFormDataPart("attachCount", String.valueOf(m_filePaths.size()));
			}

			mb.addFormDataPart("userID", HMGWServiceHelper.userId);
			mb.addFormDataPart("K", HMGWServiceHelper.key);
			mb.addFormDataPart("dataType", getDataType().getType());
			mb.addFormDataPart("openapipath", getOpenApiPath());
			// RequestBody rb = mb.build();
			// Buffer buffer = new Buffer();
			// rb.writeTo(buffer);
			// String str = new String(buffer.readByteArray());
			// Debug.trace(str);

			getOkhttpClient().setConnectTimeout(0, TimeUnit.MILLISECONDS);
			getOkhttpClient().setReadTimeout(0, TimeUnit.MILLISECONDS);

			return NetUtils.reqeustMultipartUpload(context, getOkhttpClient(), getUrl(), mb.build(), getTag());
		} catch (UnsupportedEncodingException e) {
			Debug.trace(e.getMessage());
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}

	@Override
	public DataType getDataType() {
		// TODO 의견 등록시 9999오류로 인해 DataType.MULTIPART의 사용을 일시적으로 회피함. 
		return DataType.JSON;
	}

}
