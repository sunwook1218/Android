package com.hs.mobile.gw.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.hs.mobile.gw.service.HMGWServiceHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.regex.Pattern;

public class AndroidUtils {

	public static boolean isRooted() {
		boolean isRooted = true;

		try {
			Runtime.getRuntime().exec("su"); // $codepro.audit.disable
		} catch (IOException e) {
			Debug.trace(e.getMessage());
			isRooted = false;
		}
		return isRooted;
	}

	public static final String PREF_KEY_DEVICE_ID = "device_id";

	@SuppressLint("MissingPermission")
	public static String getDeviceID(Context context) {
		String deviceId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

		if (TextUtils.isEmpty(deviceId) || TextUtils.equals(deviceId, "9774d56d682e549c")) {
			SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
			deviceId = pref.getString(PREF_KEY_DEVICE_ID, "");
			if (TextUtils.isEmpty(deviceId)) {
				deviceId = UUID.randomUUID().toString();
				SharedPreferences.Editor prefEdit = pref.edit();
				prefEdit.putString(PREF_KEY_DEVICE_ID, deviceId);
				prefEdit.apply();
			}
		}
		Debug.trace("DeviceID = " + deviceId);
		return deviceId.toString();
	}

	@SuppressLint("MissingPermission")
	public static String getPhoneNumber(Context context) {
		TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String phone = "";
		try {
			if (tMgr.getLine1Number() != null) {
				phone = tMgr.getLine1Number();
			}
			if (phone.length() == 10)
				return phone;

			if (phone != null && phone.length() > 10) {
				phone = phone.substring(phone.length() - 10, phone.length());
				phone = "0" + phone;
			}
		} catch (Exception e) {
			Debug.trace(e.getMessage());
		}
		return phone;
	}

	public static boolean isEmail(String email) {
		if (TextUtils.isEmpty(email))
			return false;

		boolean ckEmail = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
		Debug.trace(ckEmail);
		return ckEmail;
	}

	public static boolean chekcGoogleAccount(Context context) {
		AccountManager mAccountManager = AccountManager.get(context);
		Account[] mAccount = mAccountManager.getAccounts();
		final int account_lengh = mAccount.length;
		Account acct = null;
		String tmpStr = "";

		for (int i = 0; i < account_lengh; i++) {
			acct = mAccount[i];
			if (TextUtils.equals(acct.type, "com.google")) {
				return true;
			}
		}
		return false;
	}

	public static String getGoogleAccountName(Context context) {
		AccountManager mAccountManager = AccountManager.get(context);
		Account[] mAccount = mAccountManager.getAccounts();
		final int account_lengh = mAccount.length;
		Account acct = null;
		String tmpStr = "";

		for (int i = 0; i < account_lengh; i++) {
			acct = mAccount[i];
			if (TextUtils.equals(acct.type, "com.google")) {
				return acct.name;
			}
		}
		return "";
	}

	/**
	 * <pre>
	 * 인터넷 연결 상태를 알려준다.
	 * 연결안됨, 3G연결, WiFi연결 3가지 상태를 int 타입으로 리턴함
	 * </pre>
	 * 
	 * @see
	 * @param
	 * @return int
	 */
	public static int connectionType(ConnectivityManager connectivityService) {
		int networkType = 0;
		int result = -1;
		NetworkInfo.State networkState = NetworkInfo.State.DISCONNECTED;

		if (connectivityService != null && connectivityService.getActiveNetworkInfo() != null) {
			networkState = connectivityService.getActiveNetworkInfo().getState();
			networkType = connectivityService.getActiveNetworkInfo().getType();
		}

		if (networkState == NetworkInfo.State.CONNECTED) {
			result = networkType;
		}

		return result;

	}

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager connectivityService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo.State networkState = NetworkInfo.State.DISCONNECTED;

		if (connectivityService != null && connectivityService.getActiveNetworkInfo() != null) {
			networkState = connectivityService.getActiveNetworkInfo().getState();
		}
		boolean bRet = false;
		if (networkState != null && networkState == NetworkInfo.State.CONNECTED) {
			bRet = true;
		} else {
			bRet = false;
		}
		return bRet;
	}

	/**
	 * <pre>
	 * 전화번호 포멧(Utility Sample)
	 * </pre>
	 * 
	 * @see
	 * @param
	 * @return String
	 */
	public static String phoneNumberPasing(String phoneNumber) {
		String fST = "";
		String sND = "";
		String tND = "";

		if (phoneNumber.length() > 7) {
			if (TextUtils.equals(phoneNumber.substring(0, 1), "0")) {
				if (TextUtils.equals(phoneNumber.substring(0, 2), "02")) {
					fST = phoneNumber.substring(0, 2);
					tND = phoneNumber.substring(phoneNumber.length() - 4);
					sND = phoneNumber.substring(2, phoneNumber.length() - 4);
				} else {
					fST = phoneNumber.substring(0, 3);
					tND = phoneNumber.substring(phoneNumber.length() - 4);
					sND = phoneNumber.substring(3, phoneNumber.length() - 4);
				}
				return fST + "-" + sND + "-" + tND;
			} else {
				sND = phoneNumber.substring(phoneNumber.length() - 4);
				fST = phoneNumber.substring(0, phoneNumber.length() - 4);
				return fST + "-" + sND;
			}
		} else {
			return phoneNumber;
		}
	}

	/**
	 * deleteSaveFolder: SD 영역에 저장한 파일을 삭제
	 * 
	 * Using example : String sdPath =
	 * Environment.getExternalStorageDirectory().getAbsolutePath(); String
	 * targetFolderPath = "/targetFolder"); File targetFolder = new File(sdPath
	 * + targetFolderPath);
	 * 
	 * Util.deleteSaveFolder(dir);
	 * 
	 * @param
	 * @return void
	 */
	public synchronized static boolean deleteSaveFolder(File targetFolder) {
		String storageState = Environment.getExternalStorageState();

		if (targetFolder == null) {
			String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			String downloadPath = sdPath + HMGWServiceHelper.attachDownFolder;
			targetFolder = new File(downloadPath);
		}

		if (TextUtils.equals(storageState, Environment.MEDIA_MOUNTED)) {
			
			if(!targetFolder.exists()) return false;
			
			if(targetFolder.listFiles() == null) return false;
			
			File[] childFile = targetFolder.listFiles();
			if(childFile==null) return false;
			boolean confirm = false;
			int size = childFile.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (childFile[i].isFile()) {
						confirm = childFile[i].delete();
					} else {
						deleteSaveFolder(childFile[i]);
					}
				}
			}

			targetFolder.delete();
			return (!targetFolder.exists());
		}

		return true;
	}

	public static boolean hasCamera(Context context) {
		int sdkInt = Build.VERSION.SDK_INT;
		boolean hasCamera = false;

		if (sdkInt < 9) {
			PackageManager pm = context.getPackageManager();
			if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				hasCamera = true;
			}
		} else {
			int numCameras = Camera.getNumberOfCameras();
			if (numCameras > 0) {
				hasCamera = true;
			}
		}

		return hasCamera;
	}

	/**
	 * getMaxWidth:
	 * 
	 * <pre>
	 * 단말기의 가로 / 세로 Pixels 값을 비교하여 가로(Portrait) 값을 리턴
	 * </pre>
	 * 
	 * @param
	 * @return int
	 */
	public static int getMaxWidth(Context context) {

		int deviceWidth = context.getResources().getDisplayMetrics().widthPixels;
		int deviceHeight = context.getResources().getDisplayMetrics().heightPixels;
		int maxWidth;

		if (deviceWidth > deviceHeight) {
			maxWidth = deviceHeight;
		} else {
			maxWidth = deviceWidth;
		}

		return maxWidth;
	}

	/**
	 * getMaxWidth:
	 * 
	 * <pre>
	 * 단말기의 가로 / 세로 Pixels 값을 비교하여 세로(Landscape) 값을 리턴
	 * </pre>
	 * 
	 * @param
	 * @return int
	 */
	public static int getMaxHeight(Context context) {

		int deviceWidth = context.getResources().getDisplayMetrics().widthPixels;
		int deviceHeight = context.getResources().getDisplayMetrics().heightPixels;
		int maxHeight;

		if (deviceWidth < deviceHeight) {
			maxHeight = deviceHeight;
		} else {
			maxHeight = deviceWidth;
		}

		return maxHeight;
	}

	/**
	 * getDrawableFromUri:
	 * 
	 * <pre>
	 * Uri 에 해당하는 컨텐츠를 Drawable로 리턴
	 * </pre>
	 * 
	 * @see
	 * @param
	 * @return Drawable
	 */

	private static Drawable getDrawableFromUri(Context context, Uri uri) {
		BitmapDrawable d = null;
		InputStream input = null;

		try {
			input = context.getContentResolver().openInputStream(uri);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);
			d = new BitmapDrawable(bitmap);
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Debug.trace(e.getMessage());
		} catch (Exception e) {
			Debug.trace(e.getMessage());
		}
		return d;
	}

	public static void clearFocus(ViewGroup vg) {
		if(vg != null)
		{
			for(int i =0; i< vg.getChildCount(); ++i)
			{
				if(vg.getChildAt(i) instanceof ViewGroup)
				{
					clearFocus((ViewGroup) vg.getChildAt(i));
				}
				vg.getChildAt(i).setFocusable(false);
				vg.getChildAt(i).clearFocus();
			}
		}
	}

}