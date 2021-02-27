package com.hs.mobile.gw.ext.vac;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import com.TouchEn.mVaccine.b2b2c.activity.BackgroundScanActivity;
import com.TouchEn.mVaccine.b2b2c.activity.ScanActivity;
import com.TouchEn.mVaccine.b2b2c.receiver.ScanReceiver;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.secureland.smartmedic.SmartMedic;

public class VacHelper {
    protected static Context context;                //MainActivity Context
    public static Activity mainActivity = null;

    protected static BroadcastReceiver scanReceiver;
    protected static BroadcastReceiver codeReceiver;

    public VacHelper(Context context) {
        this.context = context;
    }

    public void inint() {
        /**
         * 백신은 라온제품을 그대로 사용할 가능성이있어 만약 사용한다면
         * 아래코드를 사용해도 무방하나, 확인은 필요
         */
        /* mVaccine, 사이트 ID, 라이선스key 설정 */
        com.secureland.smartmedic.core.Constants.site_id ="hsuco_mobile";
        com.secureland.smartmedic.core.Constants.license_key ="9bab25457c42e07549048c387f6845e0e8be2bec";
        //* mVaccine, 백신 엔진 초기화 *//*
        try {
            SmartMedic.init(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_INSTALL");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        scanReceiver = new ScanReceiver();
        context.registerReceiver(scanReceiver, intentFilter);
        //*----------------- CodeReceiver 동적 등록 방법 -------------------*//*
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(context.getPackageName()+".mVaccine.FIRE");
        intentFilter2.addAction("com.TouchEn.mVaccine.b2b2c.FIRE");
        codeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
				/*-------------------------------- 안내 ------------------------------------------
				본 파일은 mVaccine 제품에 대한 연동의 이해를 돕기위해 작성 된 샘플코드 입니다.
				백신 액티비티는 결과 값을 브로드캐스트로도 전달합니다.
				아래와 같이 브로드캐스트로 결과 값을 전달 받아 사용 하실 수 있습니다.
				com.secureland.smartmedic.core.Constants.EMPTY_VIRUS - 악성코드, 루팅여부 모두 정상
				com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE1 - 악성코드 탐지 후 사용자가 해당 악성코드 앱을 삭제
				com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE2 - 악성코드 탐지 후 사용자가 해당 악성코드 앱을 미삭제
				com.secureland.smartmedic.core.Constants.V_DB_FAIL - DB파일 무결성검증 실패
				com.secureland.smartmedic.core.Constants.ROOTING_EXIT_APP - 루팅 탐지 되었을 경우 ( mini 모드,  bg_rooting 옵션 true )
				----------------------------------------------------------------------------------*/

                Debug.trace("action : " + intent.getAction());
                // "com.TouchEn.mVaccine.b2b2c.FIRE"
                // 수신 된 Intent 처리

                int i = intent.getIntExtra("result", 0);
                ViewModel.Log("result = " + i, "tkofs_vaccine");
                switch (i)
                {
                    case com.secureland.smartmedic.core.Constants.EMPTY_VIRUS:				//1000
                        ViewModel.Log("CodeReceiver", "com.secureland.smartmedic.core.Constants.EMPTY_VIRUS");
                        break;

                    case com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE1:		//1010
                        ViewModel.Log("CodeReceiver", "com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE1");
                        ViewModel.Log("com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE1", "tkofs_vaccine");
                        PopupUtil.showDialog(mainActivity, "악성코드 탐지 후 사용자가 해당 악성코드 앱을 삭제.\n", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }
                        });
                        break;

                    case com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE2:		//1100
                        //종료로직 추가 필요
                        ViewModel.Log("CodeReceiver", "com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE2");
                        ViewModel.Log("com.secureland.smartmedic.core.Constants.EXIST_VIRUS_CASE2", "tkofs_vaccine");
                        PopupUtil.showDialog(mainActivity, "악성코드 탐지 후 사용자가 해당 악성코드 앱을 미삭제.\n앱을 종료합니다.", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                                mainActivity.finish();
                            }
                        });
                        break;
                    case com.secureland.smartmedic.core.Constants.ROOTING_EXIT_APP:		//1200
                        // 루팅 탐지 되었을 경우 ( mini 모드,  bg_rooting 옵션 true )
                        ViewModel.Log("CodeReceiver", "com.secureland.smartmedic.core.Constants.ROOTING_EXIT_APP");
                        ViewModel.Log("com.secureland.smartmedic.core.Constants.ROOTING_EXIT_APP", "tkofs_vaccine");
                        PopupUtil.showDialog(mainActivity, "루팅된 단말입니다.\n앱을 종료합니다.", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                                mainActivity.finish();
                            }
                        });
                        break;

                }
            }
        };
        context.registerReceiver(codeReceiver, intentFilter2);
    }

    public static void release() {
        NotificationManager mNotificationManager = (NotificationManager) mainActivity.getSystemService(mainActivity.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(ViewModel.VAC_MESSAGE_ID);
        mNotificationManager.cancel(ViewModel.VAC_MESSAGE_ID1);

        context.unregisterReceiver(scanReceiver);
        context.unregisterReceiver(codeReceiver);
    }

    public void check(){
        if(VacConfig.VAC_CHECK_TYPE == 1){
            mini();
        }else{
            full();
        }
    }

    private void mini() {
        Intent i = new Intent(context, BackgroundScanActivity.class);
        i.putExtra("useBlackAppCheck", true);
        i.putExtra("scan_rooting", true);
        i.putExtra("scan_package", true);
        i.putExtra("useDualEngine", true);
        i.putExtra("backgroundScan", true); // mini 전용
        i.putExtra("rootingexitapp", true);
        i.putExtra("show_update", false);
        i.putExtra("show_license", false);
        i.putExtra("show_notify", false); // mini 전용
        i.putExtra("show_toast", false);
        i.putExtra("show_warning", false);
        i.putExtra("show_scan_ui", true); // mini 전용
        mainActivity.startActivityForResult(i, ViewModel.VAC_REQUEST_CODE);
    }

    private void full() {
        Intent i = new Intent(context, ScanActivity.class);
        i.putExtra("useBlackAppCheck", true);
        i.putExtra("scan_rooting", true);
        i.putExtra("scan_package", true);
        i.putExtra("useDualEngine", true);
        i.putExtra("dualEngineBackground", true); // full 전용
        i.putExtra("backgroundJobForLongTime", true); // full 전용
        i.putExtra("rootingexitapp", true);
        i.putExtra("show_update", false);
        i.putExtra("show_license", false);
        i.putExtra("show_toast", false);
        i.putExtra("show_warning", true);
        mainActivity.startActivityForResult(i, ViewModel.VAC_REQUEST_CODE);
    }

}

