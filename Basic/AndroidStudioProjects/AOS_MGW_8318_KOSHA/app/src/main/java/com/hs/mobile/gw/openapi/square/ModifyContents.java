package com.hs.mobile.gw.openapi.square;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.NetUtils;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

public class ModifyContents extends SquareOpenApi implements IFileAddable {

	private ArrayList<String> m_filePaths;

	@Override
	protected String getOpenApiPath() {
		return "/square/modifyContents.do";
	}

	@Override
	protected String[] getParams() {
		return new String[] { "contentsId", "contentsType", "body", "title", "orgAttachIdList", "dueDate", "member" };
	}

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
							RequestBody.create(MediaType.parse(path), new File(path)));
				}
				mb.addFormDataPart("attachCount", String.valueOf(m_filePaths.size()));
			}

			mb.addFormDataPart("userID", HMGWServiceHelper.userId);
			mb.addFormDataPart("K", HMGWServiceHelper.key);
			mb.addFormDataPart("dataType", getDataType().getType());
			mb.addFormDataPart("openapipath", getOpenApiPath());

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
		return DataType.MULTIPART;
	}

}
