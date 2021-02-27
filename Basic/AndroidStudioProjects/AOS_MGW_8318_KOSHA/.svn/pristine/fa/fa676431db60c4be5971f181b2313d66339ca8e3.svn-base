package com.hs.mobile.gw.util;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;


public class IntentUtils {

	public static void phoneCall(Context ctx, String URL) {
		URL = URL.replaceAll("#", "%23");
		Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(URL));
		ctx.startActivity(intent);
	}
	
	public static void sendSMS(Context ctx, String URL) {
		Intent sms_phone = new Intent(Intent.ACTION_SENDTO, Uri.parse(URL));
		ctx.startActivity(sms_phone);
	}
	
	public static void sendEmail(Context ctx, String[] toMail) {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
		emailIntent.setType("plain/text");   
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, toMail);    
		ctx.startActivity(Intent.createChooser(emailIntent, "E-Mail Send"));  
	}
	
	public static void webBrowser(Context ctx, String URL) {
		Intent web = new Intent(Intent.ACTION_VIEW , Uri.parse(URL));
		ctx.startActivity(web);
	}
	
	public static void startApp(Context ctx, String packageName) {
		Intent intent = ctx.getPackageManager().getLaunchIntentForPackage(packageName);
		if (intent != null)
			ctx.startActivity(intent);
	}
	
	//Intent를 받을 수있는 App이 있는지 검증 (true 일때만 실행 가능)
	public static boolean checkIntentApp(Context ctx, Intent intent) {
		PackageManager packageManager = ctx.getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
		boolean isIntentSafe = activities.size() > 0;
		return isIntentSafe;
	}
	
	//App Chooser를 보여주기 (디폴트 선택 무시됨)
	public static void chooserApp(Context ctx, Intent intent) {
		// Always use string resources for UI text. This says something like "Share this photo with"
		String title = "title";
		
		// Create and start the chooser
		Intent chooser = Intent.createChooser(intent, title);
		ctx.startActivity(chooser);
	}
}
