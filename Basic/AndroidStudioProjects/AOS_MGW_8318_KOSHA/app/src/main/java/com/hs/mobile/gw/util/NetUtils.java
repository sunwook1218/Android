package com.hs.mobile.gw.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.message.BasicNameValuePair;

import com.hs.mobile.gw.Define;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Copyright 2014 Square, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * 
 * @author Changhoon Lee
 * 
 */
public class NetUtils {
	private static long TIMEOUT_MILLISECOUND = Define.NETWORK_TIMEOUT;

	/**
	 * 네트워크 요청 타임아웃을 설정한다.
	 * 
	 * @param nMillisecound
	 */
	public static void setTimeOut(long nMillisecound) {
		TIMEOUT_MILLISECOUND = nMillisecound;
	}

	/**
	 * Async
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param responseCallback
	 * @param strTag
	 * @param strGetParam
	 * @throws UnsupportedEncodingException
	 */
	public static void requestGet(Context context, OkHttpClient client, String url, Callback responseCallback, String strTag,
			String strGetParam) throws UnsupportedEncodingException {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		Request req = new Request.Builder().url(url + strGetParam).tag(strTag).build();
		client.newCall(req).enqueue(responseCallback);
	}

	/**
	 * Sync
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param responseCallback
	 * @param strTag
	 * @param strGetParam
	 * @return
	 * @throws IOException
	 */
	public static Response requestGet(Context context, OkHttpClient client, String url, String strTag, String strGetParam)
			throws IOException {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		Request req = new Request.Builder().url(url + strGetParam).tag(strTag).build();
		return client.newCall(req).execute();
	}

	/**
	 * Async
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param strTag
	 * @param arrayList
	 */
	public static void requestPost(Context context, OkHttpClient client, String url, Callback callBack, String strTag,
			ArrayList<BasicNameValuePair> arrayList) {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		FormEncodingBuilder fed = new FormEncodingBuilder();

		for (BasicNameValuePair item : arrayList) {
			if (!TextUtils.isEmpty(item.getValue())) {
				fed.add(item.getName(), item.getValue());
			}
		}
		Request req = new Request.Builder().url(url).tag(strTag).post(fed.build()).build();
		client.newCall(req).enqueue(callBack);
	}

	/**
	 * Async with Header
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param strTag
	 * @param arrayList
	 */
	public static void requestPost(Context context, OkHttpClient client, Headers headers, String url, Callback callBack, String strTag,
			ArrayList<BasicNameValuePair> arrayList) {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);

		FormEncodingBuilder fed = new FormEncodingBuilder();
		for (BasicNameValuePair item : arrayList) {
			if (!TextUtils.isEmpty(item.getValue())) {
				fed.add(item.getName(), item.getValue());
			}
		}
		Builder builder = new Request.Builder();
		Request req = builder.url(url).tag(strTag).post(fed.build()).headers(headers).build();
		client.newCall(req).enqueue(callBack);
	}

	/**
	 * Sync
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param strTag
	 * @param arrayList
	 * @return
	 * @throws IOException
	 */
	public static Response requestPost(Context context, OkHttpClient client, String url, String strTag,
			ArrayList<BasicNameValuePair> arrayList) throws IOException {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		FormEncodingBuilder fed = new FormEncodingBuilder();
		for (BasicNameValuePair item : arrayList) {
			if (!TextUtils.isEmpty(item.getValue())) {
				fed.add(item.getName(), item.getValue());
			}
		}
		Request req = new Request.Builder().url(url).tag(strTag).post(fed.build()).build();
		return client.newCall(req).execute();
	}

	/**
	 * Sync with Header
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param strTag
	 * @param arrayList
	 * @return
	 * @throws IOException
	 */
	public static Response requestPost(Context context, OkHttpClient client, Headers headers, String url, String strTag,
			ArrayList<BasicNameValuePair> arrayList) throws IOException {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		FormEncodingBuilder fed = new FormEncodingBuilder();
		for (BasicNameValuePair item : arrayList) {
			if (!TextUtils.isEmpty(item.getValue())) {
				fed.add(item.getName(), item.getValue());
			}
		}
		Request req = new Request.Builder().url(url).tag(strTag).post(fed.build()).headers(headers).build();
		return client.newCall(req).execute();
	}

	/**
	 * Async
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param strGetParam
	 * @param strTag
	 */
	public static void requestDownloadByGet(Context context, OkHttpClient client, String url, Callback callBack, String strGetParam,
			String strTag) {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		Request request = new Request.Builder()
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko").url(url + strGetParam)
				.tag(strTag).build();
		client.newCall(request).enqueue(callBack);
	}

	/**
	 * Async
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param arrayList
	 * @param strTag
	 */
	public static void requestDownloadByPost(Context context, OkHttpClient client, String url, Callback callBack,
			ArrayList<BasicNameValuePair> arrayList, String strTag) {
		Debug.trace("url:", url);
		client.setConnectTimeout(0, TimeUnit.MILLISECONDS);
		client.setReadTimeout(0, TimeUnit.MILLISECONDS);
		FormEncodingBuilder fed = new FormEncodingBuilder();		
		for (BasicNameValuePair item : arrayList) {
			fed.add(item.getName(), item.getValue());
		}
		Request req = new Request.Builder().url(url).post(fed.build()).tag(strTag).build();
		client.newCall(req).enqueue(callBack);
	}

	/**
	 * Async
	 * 
	 * @param context
	 * @param client
	 * @param url
	 * @param callBack
	 * @param requestBody
	 * @param strTag
	 * @throws IOException
	 */
	public static void reqeustMultipartUpload(Context context, OkHttpClient client, String url, Callback callBack, RequestBody requestBody,
			String strTag) throws IOException {
		Debug.trace("url:", url);
		client.setConnectTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		client.setReadTimeout(TIMEOUT_MILLISECOUND, TimeUnit.MILLISECONDS);
		Request request = new Request.Builder().url(url).post(requestBody).tag(strTag).build();
		client.newCall(request).enqueue(callBack);
	}

	/**
	 * Sync
	 * 
	 * @return
	 */
	public static Response reqeustMultipartUpload(Context context, OkHttpClient client, String url, RequestBody requestBody, String strTag)
			throws IOException {
		Debug.trace("url:", url);
		Request request = new Request.Builder().url(url).post(requestBody).tag(strTag).build();
		Debug.trace(request.body().contentType());
		return client.newCall(request).execute();
	}

	public static long downloadFile(Activity a, String strId, String strSrc, String strTargetFolder, String makeTempPath) {
		DownloadManager downloadManager = (DownloadManager) a.getSystemService(Context.DOWNLOAD_SERVICE);
		Uri urlToDownload = Uri.parse(makeTempPath);
		List<String> pathSegments = urlToDownload.getPathSegments();
		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(strSrc));
		// request.setDescription("항목 설명");
		request.setDestinationInExternalFilesDir(a, strId + File.separator + strTargetFolder, pathSegments.get(pathSegments.size() - 1));
		a.getExternalFilesDir(strId + File.separator + strTargetFolder).mkdirs();
		return downloadManager.enqueue(request);
	}
}
