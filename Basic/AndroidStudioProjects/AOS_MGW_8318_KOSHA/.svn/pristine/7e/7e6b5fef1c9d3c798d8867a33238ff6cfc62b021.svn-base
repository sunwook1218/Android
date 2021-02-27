package com.hs.mobile.gw.util;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import org.apache.cordova.file.FileExistsException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.hsuco.R;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import de.mindpipe.android.logging.log4j.LogConfigurator;

public class Debug {
	public static void configure(Context ctx) {
		// ApplicationInfo info = null;
		// if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
		// {
		// sBDebug = BuildConfig.DEBUG;
		// }else
		// {
		// try {
		// info =
		// ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(),
		// PackageManager.GET_META_DATA);
		// sBDebug = ((info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
		// } catch (NameNotFoundException e) {
		// logger.info(e.getMessage());
		// return;
		// }
		// }

		// path
		String appName = ctx.getString(R.string.app_name);
		String logPath = Environment.getExternalStorageDirectory() + File.separator + appName;

		// create directory, which directory is not exists
		File file = new File(logPath);
		logPath += File.separator + appName + ".log";

		if (sBDebug) {
			file.mkdirs();
			LogConfigurator configurator = new LogConfigurator();
			configurator.setFileName(logPath);
			configurator.setFilePattern("%d - [%p::%C] - %m%n"); // log pattern
			configurator.setMaxFileSize(512 * 1024); // file size(byte)
			configurator.setMaxBackupSize(0); // number of backup file

			configurator.setRootLevel(Level.DEBUG); // set log level
			configurator.setUseLogCatAppender(true); // and use Logcat

			// set log level of a specific logger
			configurator.setLevel("org.apache", Level.ERROR);
			configurator.configure();
		} 
		// Debug가 false면 기존의 로그를 지워준다.
		else {
			try {
				removeDirRecursively(file);
			} catch (FileExistsException e) {
				e.printStackTrace();
			}
		}
	}

	private static Logger logger = Logger.getLogger("DEBUG");
	// private static boolean sBDebug = BuildConfig.DEBUG;
	private static boolean sBDebug = Define.DEBUG;

	public static void trace(Object... objects) {
		if (!sBDebug) {
			return;
		}
		WeakReference<StringBuilder> sb = new WeakReference<StringBuilder>(new StringBuilder());
		for (Object obj : objects) {
			if (obj != null) {
				sb.get().append(obj).append(' ');
			} else if (obj instanceof List<?>) {
				int i = 0;
				sb.get().append("\n");
				for (Object item : (List<?>) obj) {
					sb.get().append('[');
					sb.get().append(i++);
					sb.get().append(']');
					sb.get().append(item);
					sb.get().append("\n");
				}
			} else {
				sb.get().append("null").append(' ');
			}
		}
//		logger.info(sb.get().toString());
		trace(sb.get().toString());
		
		sb.clear();
	}
	static String LOG_TAG = "mGW";
	private static void trace(String content)
	{
		StackTraceElement[] trace = new Throwable().getStackTrace();
		String msg = "";

		if (trace.length >= 2) {
			StackTraceElement elt = trace[2];

			msg = "[" + elt.getFileName() + " Line:" + elt.getLineNumber() + "] " + elt.getMethodName() + " " + content;
		}

		Log.i(LOG_TAG, msg);
	}

	public static boolean isDebug() {
		return sBDebug;
	}

	public static void memoryInfo() {
		trace("Used memory size", android.os.Debug.getNativeHeapAllocatedSize() / 1024L + "KB");
	}

	protected static boolean removeDirRecursively(File directory) throws FileExistsException {
		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				removeDirRecursively(file);
			}
		}

		if (!directory.delete()) {
			throw new FileExistsException("could not delete: " + directory.getName());
		} else {
			return true;
		}
	}
}
