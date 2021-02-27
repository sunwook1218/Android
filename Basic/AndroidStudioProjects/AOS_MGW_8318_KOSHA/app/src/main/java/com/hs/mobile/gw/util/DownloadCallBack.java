package com.hs.mobile.gw.util;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class DownloadCallBack implements Callback {
	private RestAPI mRestApi;
	private String mStrTargetPath;
	private long mAvailable;

	public DownloadCallBack(RestAPI api, String strTargetPath) {
		mRestApi = api;
		mStrTargetPath = strTargetPath;
	}

	@Override
	public void onResponse(Response response){
		InputStream stream = null;
		BufferedInputStream input = null;
		FileOutputStream fos = null;
		try{
			if (response == null || !response.isSuccessful()) {
				throw new IOException("Unexpected code " + response);
			}
	
			if (mRestApi.getRestApiProgress() != null && mRestApi.getRestApiProgress().getListener() != null) {
				mRestApi.getRestApiProgress().getListener().showProgress();
			}
			stream = response.body().byteStream();
			input = new BufferedInputStream(stream);
			fos = new FileOutputStream(mStrTargetPath);
			byte[] data = new byte[1024];
	
			long total = 0;
	
			int count;
			while ((count = input.read(data)) != -1) {
				total += count;
				fos.write(data, 0, count);
			}
	
			fos.flush();
			if (mRestApi.getRestApiProgress() != null && mRestApi.getRestApiProgress().getListener() != null) {
				mRestApi.getRestApiProgress().getListener().hideProgress();
			}
		}catch (IOException e){
			Debug.trace(e.getMessage());
		}finally{
			try {
				if(stream!=null) stream.close();
				if(fos!=null) fos.close();
				if(input!=null) input.close();
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			}
		}
	}

	protected synchronized boolean isDownloadSuccess() {
		FileInputStream fis = null;
		try{
			File file = new File(mStrTargetPath);
			if (file.exists()) {
				fis = new FileInputStream(file);
				if (fis.available() < mAvailable) {
					onFailure(null, new IOException("File Download Fail"));
					fis.close();
					file.delete();
				} else {
					fis.close();
					return true;
				}
			}
		}catch (FileNotFoundException e){
			Debug.trace(e.getMessage());
		}catch (IOException e){
			Debug.trace(e.getMessage());
		}finally{
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			}
		}
		return false;
	}
}
