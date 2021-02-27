package com.hs.mobile.gw.ext;

import android.app.Activity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class BaseModel {

    public static Activity currentActivity;
    public static String setUpPolicyName = "";

    public static String EVENT_CERTICATION_MANAGER = "onCerticationComplete";

    public static boolean overlayPage = false;
    // false일경우 ViewModel.Log 출력 안함.
    public static boolean debugMode = true;

    /**
     * @actionCode
     * @descriptiond
     * - 이벤트명
     */
    public enum actionCode {
        onComplete,
        onError,
        onLoadInit,
        onLoadComplete,
        onLoadError,
        onStop,
        onStart,
        onConnected,
        onDisconnected,
        fail,
        onPushDBInsertSuccess,
        onPushDBInsertError
    }

    /**
     * @AppStatus
     * @description
     * - 앱상태값
     */
    public enum AppStatus {
        BACKGROUND,
        RETURNED_TO_FOREGROUND,
        FOREGROUND
    }

    protected static HashMap<String,Boolean> certState;

    public static void Log(Object object){
        if(!debugMode) return;
        try{
            Log.d("tkofs","======================================================================");
            Log.d("tkofs", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) );
            Log.d("tkofs", object.toString() );
            Log.d("tkofs","======================================================================");
        }catch(NullPointerException e){
            Log.d("tkofs","======================================================================");
            Log.d("tkofs", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) );
            Log.d("tkofs", "object is null" );
            Log.d("tkofs","======================================================================");
        }
    }

    public static void Log(Object object, String tag){
        if(!debugMode) return;
        try{
            Log.d(tag,"======================================================================");
            Log.d(tag, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) );
            Log.d(tag, object.toString() );
            Log.d(tag,"======================================================================");
        }catch(NullPointerException e){
            Log.d(tag,"======================================================================");
            Log.d(tag, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) );
            Log.d(tag, "object is null" );
            Log.d(tag,"======================================================================");
        }
    }
}
