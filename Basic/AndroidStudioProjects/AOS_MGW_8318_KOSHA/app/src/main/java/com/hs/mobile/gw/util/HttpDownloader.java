package com.hs.mobile.gw.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.os.AsyncTask;
import android.text.TextUtils;

public class HttpDownloader implements HttpDownloaderImpl {
	private OnDownloadFileCompletedListener mOnDownloadFileCompletedListener;
	private OnDownloadProgressChangedListener mOnDownloadProgressChangedListener;
	private OnDownloadStringCompletedListener mOnDownloadStringCompletedListener;

	@Override
	public void downloadFileAsync(String address, String fileName, ArrayList<NameValuePair> nameValuePairs) {
		new DownloadFileAsyncTask(nameValuePairs).execute(address, fileName);
	}

	private class DownloadFileAsyncTask extends AsyncTask<String, Long, DownloadFileCompleted> {

		static final int MAX_BUFFER_SIZE = 4096;
		private WeakReference<ArrayList<NameValuePair>> nameValuePairs;

		DownloadFileAsyncTask(ArrayList<NameValuePair> nameValuePairs) {
			this.nameValuePairs = new WeakReference<ArrayList<NameValuePair>>(nameValuePairs);
		}

		@Override
		protected DownloadFileCompleted doInBackground(String... params) {
			DownloadFileCompleted event = new DownloadFileCompleted();

			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				HttpClient client = new DefaultHttpClient();
				HttpResponse response;

				if (nameValuePairs.get() == null) {
					HttpGet request = new HttpGet(params[0]);
					Cookie sessionInfo = null;
					for (Cookie cookie : HMGWServiceHelper.cookies) {
						sessionInfo = cookie;

						if (TextUtils.equals("JSESSIONID", sessionInfo.getName())) {
							String sessionCookie = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; path=" + sessionInfo.getPath();
							request.setHeader("Cookie", sessionCookie);
						}
					}

					response = client.execute(request);
				} else {
					HttpPost request = new HttpPost(params[0]);
					request.setEntity(new UrlEncodedFormEntity(nameValuePairs.get()));
					Cookie sessionInfo = null;
					for (Cookie cookie : HMGWServiceHelper.cookies) {
						sessionInfo = cookie;

						if (TextUtils.equals("JSESSIONID", sessionInfo.getName())) {
							String sessionCookie = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; path=" + sessionInfo.getPath();
							request.setHeader("Cookie", sessionCookie);
						}
					}

					response = client.execute(request);
				}

				final HttpEntity entity = response.getEntity();
				bis = new BufferedInputStream(entity.getContent());

				File f = new File(params[1]);
				if (!f.exists())
					f.createNewFile();
				bos = new BufferedOutputStream(new FileOutputStream(f));

				long totalBytes = entity.getContentLength();
				long readBytes = 0;
				int read = 0;

				byte[] buffer = new byte[MAX_BUFFER_SIZE];

				if (totalBytes > -1) {
					while (totalBytes > readBytes) {
						read = bis.read(buffer);
						readBytes += read;
						// 다운로드 프로그레스바용 설정
						// Debug.trace("HttpDownloader - readBytes = " +
						// readBytes);
						// publishProgress(readBytes, totalBytes);
						bos.write(buffer, 0, read);
					}
				} else if (totalBytes == -1) {
					while ((read = bis.read(buffer)) != -1) {
						readBytes += read;
						// 다운로드 프로그레스바용 설정
						// Debug.trace("HttpDownloader - readBytes = " +
						// readBytes);
						// publishProgress(readBytes, totalBytes);
						bos.write(buffer, 0, read);
					}
					// totalBytes는 readBytes의 최종 누적값이 들어가게 됩니다.
					totalBytes = readBytes;
					// 프로그레스로 보냄.
					publishProgress(readBytes, totalBytes);
				}
				bos.flush();
				event.file = f;
			} catch (IOException e) {
				Debug.trace(e.getMessage());
				event.exception = e;
			} finally {
				try {
					if (bos != null)
						bos.close();
					if (bis != null)
						bis.close();
				} catch (IOException e) {
					Debug.trace(e.getMessage());
				}
			}
			return event;
		}

		@Override
		protected void onProgressUpdate(Long... values) {
			invokeOnDownloadProgressChangedListener(values[0], values[1]);
		}

		@Override
		protected void onPostExecute(DownloadFileCompleted result) {
			invokeOnDownloadFileCompletedListener(result);
		}
	}

	@Override
	public String downloadString(String address, ArrayList<NameValuePair> nameValuePairs) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request;
		if (nameValuePairs == null) {
			request = new HttpGet(address);
		} else {
			HttpPost post = new HttpPost(address);
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			request = post;
		}
		ResponseHandler<String> handler = new BasicResponseHandler();
		return client.execute(request, handler);
	}

	@Override
	public void downloadStringAsync(String address, ArrayList<NameValuePair> nameValuePairs) {
		new DownloadStringAsyncTask(nameValuePairs).execute(address);
	}

	private class DownloadStringAsyncTask extends AsyncTask<String, Void, DownloadStringCompleted> {
		private WeakReference<ArrayList<NameValuePair>> nameValuePairs;

		DownloadStringAsyncTask(ArrayList<NameValuePair> nameValuePairs) {
			this.nameValuePairs = new WeakReference<ArrayList<NameValuePair>>(nameValuePairs);
		}

		@Override
		protected DownloadStringCompleted doInBackground(String... params) {
			DownloadStringCompleted event = new DownloadStringCompleted();
			try {
				event.text = downloadString(params[0], nameValuePairs.get());
			} catch (IOException e) {
				Debug.trace(e.getMessage());
				event.exception = e;
			}
			return event;
		}

		@Override
		protected void onPostExecute(DownloadStringCompleted result) {
			invokeOnDownloadStringCompletedListener(result);
		}
	}

	@Override
	public void setOnDownloadFileCompletedListener(OnDownloadFileCompletedListener listener) {
		mOnDownloadFileCompletedListener = listener;
	}

	void invokeOnDownloadFileCompletedListener(DownloadFileCompleted event) {
		if (mOnDownloadFileCompletedListener != null) {
			mOnDownloadFileCompletedListener.onDownloadFileCompleted(event);
		}
	}

	@Override
	public void setOnDownloadProgressChangedListener(OnDownloadProgressChangedListener listener) {
		mOnDownloadProgressChangedListener = listener;
	}

	void invokeOnDownloadProgressChangedListener(long bytesReceived, long totalBytesReceived) {
		if (mOnDownloadProgressChangedListener != null) {
			mOnDownloadProgressChangedListener.onDownloadProgressChanged(bytesReceived, totalBytesReceived);
		}
	}

	@Override
	public void setOnDownloadStringCompletedListener(OnDownloadStringCompletedListener listener) {
		mOnDownloadStringCompletedListener = listener;
	}

	void invokeOnDownloadStringCompletedListener(DownloadStringCompleted event) {
		if (mOnDownloadStringCompletedListener != null) {
			mOnDownloadStringCompletedListener.onDownloadStringCompleted(event);
		}
	}
}
