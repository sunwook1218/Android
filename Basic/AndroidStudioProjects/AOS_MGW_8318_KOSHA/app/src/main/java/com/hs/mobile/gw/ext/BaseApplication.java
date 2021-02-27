package com.hs.mobile.gw.ext;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hs.mobile.gw.ext.mdm.MdmActivity;
import com.hs.mobile.gw.ext.mdm.MdmHelper;
import com.hs.mobile.gw.ext.otp.OtpActivity;

public class BaseApplication extends Application {
    private static BaseApplication mInstance = null;
    private AppStatus mAppStatus = AppStatus.FOREGROUND;

    public static Activity currActivity = null;

    @Override
    public void onCreate() {
        mInstance = this;
        super.onCreate();

        // Activity 라이프 사이클을 탐지하여 포그라운드와 백그라운드 체크
        registerActivityLifecycleCallbacks(new CActivityLifecycleCallbacks());
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public AppStatus getAppStatus() {
        return mAppStatus;
    }

    public enum AppStatus {
        BACKGROUND,
        RETURNED_TO_FOREGROUND, // 홈 버튼으로 내린후 다시 실행 된 경우, 처음 실행시
        FOREGROUND,
        MEMORY_OUT // 최근 앱 사용 목록에서 앱이 삭제 되었을 경우
    }

    public class CActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
        private int running = 0;

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            ViewModel.Log("APP onActivityCreated ===" + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            ViewModel.Log("APP onActivityStarted ===" + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
            if (++running == 1) {
                ViewModel.Log("APP onActivityStarted ===" + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
                mAppStatus = AppStatus.RETURNED_TO_FOREGROUND;
                if (!ViewModel.apiName.MDM.isApiPass()) {
                    Intent intent = new Intent(activity, MdmActivity.class);
                    activity.startActivity(intent);
                }
            } else if (running > 1) {
                mAppStatus = AppStatus.FOREGROUND;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            currActivity = activity;
            ViewModel.Log("APP onActivityResumed ===" + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            ViewModel.Log("APP onActivityPaused ===" + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            ViewModel.Log("APP onActivityStopped === " + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
            if (--running == 0) {
                mAppStatus = AppStatus.BACKGROUND;
            }
            if(getAppStatus() == BaseApplication.AppStatus.BACKGROUND) {
                if (!ViewModel.apiName.MDM.isApiPass()) {
                    MdmHelper.releaseMdm();
                }
            }
            ViewModel.Log("test running=== " + running, "tkofs_BaseApplication");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            ViewModel.Log("APP onActivitySaveInstanceState ===" + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            ViewModel.Log("APP onActivityDestroyed === " + activity.getClass().getCanonicalName() + " status : " + getAppStatus(), "tkofs_BaseApplication");
            mAppStatus = AppStatus.MEMORY_OUT;
            // 메인 Activity가 Destory 된다는 것은 '최근 앱 사용 목록'에서 지우기를 한것임
        }
    }
}
