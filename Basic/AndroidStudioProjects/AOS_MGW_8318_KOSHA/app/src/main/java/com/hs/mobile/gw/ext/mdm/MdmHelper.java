package com.hs.mobile.gw.ext.mdm;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.util.PopupUtil;
import com.raonsecure.touchen_mguard_4_0.MDMAPI;
import com.raonsecure.touchen_mguard_4_0.MDMResultCode;
import com.raonsecure.touchen_mguard_4_0.MDMAPI.MGuardConnectionListener;

public class MdmHelper {

    private static Context cuurentContext;
    private static MDMAPI _mdm = null;
    protected static Activity mdmActivity;

    public static void setContext(Context context) {
        cuurentContext = context;
    }

    protected static void setMdmActivity(Activity activity) {
        mdmActivity = activity;
    }

    public static void applyMdm(){
        if(_mdm == null){
            _mdm = MDMAPI.getInstance();
        }

        ViewModel.Log("MdmHelper 정책 적용", "tkofs_mdm");
        initSDK();
    }

    public static void releaseMdm(){
        ViewModel.Log("MdmHelper 정책 해제", "tkofs_mdm");
        logoutOffice();
    }

    private static void initSDK(){
        ViewModel.Log("MdmHelper initSDK()", "tkofs_mdm");
        _mdm.RS_MDM_Init(cuurentContext, MdmConfig.MDM_SERVER_URL, new MGuardConnectionListener() {
            @Override
            public void onComplete(int resultCode) {
                ViewModel.Log("RS_MDM_Init resultCode :" + resultCode, "tkofs_mdm");
                switch (resultCode) {
                    case MDMResultCode.ERR_MDM_SUCCESS:
                        ViewModel.Log("bind Success", "tkofs_mdm");
                        checkAgent();
                        break;
                    case MDMResultCode.ERR_MDM_FAILED:
                        ViewModel.Log("bind fail", "tkofs_mdm");
                        //showDialogResultMsg(resultCode, null, "bind fail");
                        PopupUtil.showDialog(ViewModel.currentActivity, "MDM 연동에 실패했습니다.[" + resultCode + "]", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                                ViewModel.currentActivity.finishAffinity();
                            }
                        });
                        break;
                    case MDMResultCode.ERR_MDM_NOT_INSTALLED:
                        ViewModel.Log("MDM is not Installed", "tkofs_mdm");
                        agentNotInstalled();
                        break;
                    default:
                        ViewModel.Log("unknown fail", "tkofs_mdm");
                        //showDialogResultMsg(resultCode, null, "bind fail");
                        PopupUtil.showDialog(ViewModel.currentActivity, "MDM 연동에 실패했습니다.[" + resultCode + "]", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                                ViewModel.currentActivity.finishAffinity();
                            }
                        });
                        break;
                }
            }
        }, MdmConfig.ONE_GUARD_PACKAGE_NAME);
    }

    private static void checkAgent(){
        ViewModel.Log("checkAgent", "tkofs_mdm");
        //원가드 최신버전 및 위변조 유무를 확인한다.
        _mdm.RS_MDM_CheckAgent(cuurentContext, new MDMAPI.MGuardCallbackListener() {
            @Override
            public void onCompleted(int resultCode, String msg) {
                ViewModel.Log("RS_MDM_CheckAgent resultCode : " + resultCode, "tkofs_mdm");
                if (resultCode == MDMResultCode.ERR_MDM_NOT_INSTALLED) {
                    agentNotInstalled();
                } else if (resultCode == MDMResultCode.ERR_MDM_NOT_LOGIN) {
                    PopupUtil.showDialog(ViewModel.currentActivity, "MDM 클라이언트에 로그인 되지 않았습니다.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                            ViewModel.currentActivity.finishAffinity();
                        }
                    });
                } else if (resultCode == MDMResultCode.ERR_MDM_DEVICE_ROOTING) {
                    PopupUtil.showDialog(ViewModel.currentActivity, "루팅된 단말기 입니다.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                            ViewModel.currentActivity.finishAffinity();
                        }
                    });
                } else if (resultCode == MDMResultCode.ERR_MDM_AGENT_NOT_LAST_VERSION) {
                    //do something
                } else if (resultCode == MDMResultCode.ERR_MDM_FIND_HACKED_APP) {
                    PopupUtil.showDialog(ViewModel.currentActivity, "에이전트가 위조/변조 되었습니다.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                            ViewModel.currentActivity.finishAffinity();
                        }
                    });
                } else if (resultCode == MDMResultCode.ERR_MDM_SUCCESS) {
                    loginOffice();
                } else {
                    PopupUtil.showDialog(ViewModel.currentActivity, "에이전트 체크 오류가 발생했습니다.[" + resultCode + "]", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                            ViewModel.currentActivity.finishAffinity();
                        }
                    });
                }
            }
        });
    }

    //업무앱 로그인 mdm 정책 적용
    private static void loginOffice(){
        _mdm.RS_MDM_LoginOffice(new MDMAPI.MGuardCallbackListener() {
            @Override
            public void onCompleted(int resultCode, String msg) {
                ViewModel.Log("RS_MDM_LoginOffice resultCode : " + resultCode + " msg : " + msg, "tkofs_mdm");
                if (resultCode == MDMResultCode.ERR_MDM_ALLEADY_OFFICE_MODE) {
                    //showDialogResultMsg(resultCode, msg, "이미 업무앱 로그인 상태입니다.");
                } else {
                    //showDialogResultMsg(resultCode, msg);
                }
                MdmHelper.mdmActivity.finish();
            }
        });
    }

    //업무앱 로그아웃 mdm 정책 해제
    private static void logoutOffice(){
        if(_mdm != null){
            _mdm.RS_MDM_LogoutOffice(new MDMAPI.MGuardCallbackListener() {
                @Override
                public void onCompleted(int resultCode, String msg) {
                    ViewModel.Log("RS_MDM_LogoutOffice resultCode : " + resultCode + ", msg : " + msg, "tkofs_mdm");
                    if(resultCode == MDMResultCode.ERR_MDM_SUCCESS){
                        PopupUtil.showToastMessage(ViewModel.currentActivity, "정상적으로 보안정책이 해제되었습니다.");
                    } else if (resultCode == MDMResultCode.ERR_MDM_ALLEADY_PERSONAL_MODE) {
                        PopupUtil.showToastMessage(ViewModel.currentActivity, "이미 업무앱 로그아웃 상태입니다.");
                    }else{
                        PopupUtil.showToastMessage(ViewModel.currentActivity, msg + "["+ resultCode + "]");
                    }
                }
            });
        }
    }

    //mdm 클라이언트가 설치 안된경우
    private static void agentNotInstalled(){
        PopupUtil.showDialog(ViewModel.currentActivity, "MDM 에이전트가 설치되지 않았습니다.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse(MdmConfig.ONE_GUARD_INSTALL_URL));
                ViewModel.currentActivity.startActivity(intentWeb);
                ViewModel.currentActivity.finishAffinity();
            }
        });
    }
}
