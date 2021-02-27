package com.hs.mobile.gw;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DownloadUtils;
import com.hs.mobile.gw.util.HttpDownloader;
import com.hs.mobile.gw.util.HttpDownloaderImpl.DownloadFileCompleted;
import com.hs.mobile.gw.util.HttpDownloaderImpl.OnDownloadFileCompletedListener;
import com.hs.mobile.gw.util.HttpDownloaderImpl.OnDownloadProgressChangedListener;
import com.hs.mobile.gw.util.PopupUtil;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class DownloadActivity extends Activity implements EasyPermissions.PermissionCallbacks {

	private Context uiContext;
	private ProgressDialog downloadProgress;

	private String url;
	private String fileName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiContext = this;

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
		layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
		layoutParams.dimAmount = 0.7f;
		getWindow().setAttributes(layoutParams);

		Bundle bun = getIntent().getExtras();
		try {
			url = bun.getString("url");
			fileName = bun.getString("fileName");
		} catch (NullPointerException e) {
			Debug.trace(e.getMessage());
			url = null;
			fileName = null;
		}

		Debug.trace("DownloadActivity - url[" + url + "] fineName[" + fileName + "]");

		if (url == null || fileName == null) {
			finish();
		} else {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
				onDownloadStart(url, fileName);
				return;
			}
			boolean hasWriteStoragePermission = hasWriteStoragePermission();
			if (hasWriteStoragePermission) {
				onDownloadStart(url, fileName);
			} else {
				requestWriteStoragePermission();
			}
		}
	}

	@Override
	public void onPause() {
		super.onPause();

		if (downloadProgress != null && downloadProgress.isShowing()) {
			downloadProgress.dismiss();
		}
	}

	private void linkFile(String downloadPath) {
		String fileType = downloadPath.substring(downloadPath.lastIndexOf(".") + 1);

		if (TextUtils.isEmpty(fileType)) {
			finish();
			return;
		}

		fileType = fileType.toLowerCase();

		if (TextUtils.equals("jpg", fileType) || TextUtils.equals("bmp", fileType) || TextUtils.equals("png", fileType)
				|| TextUtils.equals("gif", fileType) || TextUtils.equals("tif", fileType)) {

			Intent i = new Intent(this, ImageActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			i.putExtra("url", downloadPath);
			startActivity(i);
		} else {
			try {
				startActivity(DownloadUtils.createLaunchFileIntent(this, downloadPath));
			} catch (ActivityNotFoundException e) {
				Debug.trace(e.getMessage());
				PopupUtil.showToastMessage(this, "지원하는 뷰어가 설치되어있지 않습니다.");
			}  catch (Exception e){
				Debug.trace(e.getMessage());
				PopupUtil.showToastMessage(this, "알 수 없는 오류가 발생했습니다.");
			}
		}

		finish();
	}

	// 첨부 다운로드
	private void onDownloadStart(String url, String fileName) {
		String storageState = Environment.getExternalStorageState();
		String envPath = Environment.getExternalStorageDirectory().getAbsolutePath();

		final String downloadFolderPath = envPath + HMGWServiceHelper.attachDownFolder;
		final String downloadFilePath = envPath + HMGWServiceHelper.attachDownFolder + fileName;

		Debug.trace("DownloadActivity - downloadPath[" + downloadFolderPath + "]");

		if (storageState.equals(Environment.MEDIA_MOUNTED)) {

			HttpDownloader downloader = new HttpDownloader();

			File saveFolder = new File(downloadFolderPath);
			if (!saveFolder.exists()) {
				saveFolder.mkdirs();
			}

			downloader.downloadFileAsync(url, downloadFilePath, null);

			/*
			 * 추후 다운로드 프로그레스바용 설정
			 * 
			 * downloadProgress = new ProgressDialog(uiContext);
			 * downloadProgress.setIndeterminate(false);
			 * downloadProgress.setProgressStyle
			 * (ProgressDialog.STYLE_HORIZONTAL);
			 * downloadProgress.setTitle(R.string.message_save_file);
			 * downloadProgress.show();
			 */

			downloadProgress = new ProgressDialog(uiContext);
			downloadProgress.setCancelable(false);
			downloadProgress.setTitle(R.string.message_save_file);
			downloadProgress.setMessage(uiContext.getResources().getString(R.string.message_save_file_start));
			downloadProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			downloadProgress.show();

			downloader.setOnDownloadFileCompletedListener(new OnDownloadFileCompletedListener() {
				@Override
				public void onDownloadFileCompleted(DownloadFileCompleted event) {
					if (downloadProgress != null && downloadProgress.isShowing()) {
						downloadProgress.cancel();
					}
					// HMPMessageUtil.shortTostMessage(uiContext,
					// R.string.message_save_file_complete);
					linkFile(downloadFilePath);
				}
			});

			downloader.setOnDownloadProgressChangedListener(new OnDownloadProgressChangedListener() {
				@Override
				public void onDownloadProgressChanged(long bytesReceived, long totalBytesReceived) {

					Debug.trace("DownloadActivity - bytesReceived[" + bytesReceived + "] totalBytesReceived[" + totalBytesReceived + "]");

					if (downloadProgress.getMax() != (totalBytesReceived / 1024)) {
						downloadProgress.setMax((int) (totalBytesReceived / 1024));
					}
					downloadProgress.setProgress((int) (bytesReceived / 1024));
				}
			});
		} else {
			PopupUtil.showDialog(this, R.string.message_error_storage, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
					return;
				}
			});
		}
	}

    public boolean hasWriteStoragePermission() {
        return EasyPermissions.hasPermissions(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestWriteStoragePermission() {
        requestPermissions(
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                CommonFragment.RC_WRITE_STORAGE_PERM);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Debug.trace("onPermissionsGranted:" + requestCode + ":" + perms.size());
        if (requestCode == CommonFragment.RC_WRITE_STORAGE_PERM) {
            for (String permission : perms) {
                if (TextUtils.equals(permission, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    onDownloadStart(url, fileName);
                }
            }
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Debug.trace( "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
}