package com.hs.mobile.gw.ext.vpn;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.VpnService;

import com.hs.mobile.gw.ext.BaseApplication;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.fragment.login.LoginController;
import com.hs.mobile.gw.util.PopupUtil;
import com.raonsecure.license.E;
import com.secui.sslvpnsdk.api.SSLVPNConst;
import com.secui.sslvpnsdk.api.SSLVPNLogin;
import com.secui.sslvpnsdk.interfaces.ISSLVPNLogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.core.app.ActivityCompat;

public class VPNConnectHelper implements ISSLVPNLogin{

    public Context context;
    public Activity ac;
    public String uid;
    public String upw;
    public boolean useLogin;
    public LoginController lc;

    private static SSLVPNLogin mSSLVPN;
    private boolean isInit = false;
    private boolean receiveCallback = false;
    private int receiveCode = 0;

    public VPNConnectHelper(Context ctx, Activity a, String id, String pw, LoginController lc) {
        this.context = ctx;
        this.ac = a;
        this.uid = id;
        this.upw = pw;
        this.useLogin = true;
        this.lc = lc;
    }

    public void init(){
        ViewModel.Log("VPNConnectHelper init", "tkofs_vpn");

        isInit = true;
        PopupUtil.showLoading(ac, "보안 연결 중입니다.");
        String lang = ac.getResources().getConfiguration().locale.getLanguage();
        ViewModel.Log("lang : " + lang, "tkofs_vpn");
        mSSLVPN = new SSLVPNLogin(this, context, lang, "2.0.0",true);

        int status = mSSLVPN.vpnGetStatus();
        ViewModel.Log("init status : " +  status, "tkofs_vpn");
        if(status == SSLVPNConst.SSLVPN_STATUS_CONNECTED){
            /*
            mSSLVPN.vpnDisconnect();
            mSSLVPN.vpnClose();
            mSSLVPN = new SSLVPNLogin(this, context, lang, "2.0.0",true);
             */
            PopupUtil.hideLoading(ac);
            // vpn 접속시 로그인 처리
            lc.doLogIn(uid, upw);
        }else{
            sslvpnConnect();
            isInit = false;
        }

    }

    public void sslvpnConnect() {
        ViewModel.Log("sslvpnConnect", "tkofs_vpn");
        mSSLVPN.vpnSimpleLogin(VpnConfig.VPN_SERVER_URL, uid, upw, VpnConfig.VPN_USE_PROXY);
        //mSSLVPN.vpnSimpleLogin(VpnConfig.VPN_SERVER_URL, "test4", "hsuco12!@", VpnConfig.VPN_USE_PROXY);
    }

    // SSLVPN Disconnect
    public static void sslvpnDisconnect() {
        ViewModel.Log("sslvpnDisconnect", "tkofs_vpn");
        if(mSSLVPN != null){
            int status = mSSLVPN.vpnGetStatus();
            ViewModel.Log("sslvpnDisconnect status : " + status, "tkofs_vpn");
            if(status == SSLVPNConst.SSLVPN_STATUS_CONNECTED){
                mSSLVPN.vpnDisconnect();
                //mSSLVPN.vpnClose();
            }
        }
    }

    @Override
    public void onErrorMessage(int code, String msg) {
        ViewModel.Log("onErrorMessage", "tkofs_vpn");
        ViewModel.Log("onErrorMessage msg: " + msg + "code : " + Integer.toHexString(code), "tkofs_vpn");
        PopupUtil.hideLoading(ac);
        //로그인 실패시
        if(code == 101){
            msg = VpnConfig.LOGIN_FAIL_MESSAGE;
        }
        //메시지 출력 후 종료
        PopupUtil.showDialog(ac, msg + "[" + Integer.toHexString(code) +"]", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if(mSSLVPN != null){
                    if(mSSLVPN.vpnGetStatus() != SSLVPNConst.SSLVPN_STATUS_NOTCONNECTED) {
                        mSSLVPN.vpnDisconnect();
                        mSSLVPN.vpnClose();
                    }
                }
                dialog.dismiss();
                ac.finishAffinity();
            }
        });
    }

    @Override
    public void onVpnPermission() {
        ViewModel.Log("onVpnPermission", "tkofs_vpn");
        PopupUtil.hideLoading(ac);
        Intent intent = VpnService.prepare(context);
        if (intent != null) {
            try {
                ac.startActivityForResult(intent, VpnConfig.VPN_REQUEST_PERMISSION);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPermissionDenied(List<String> list) {
        ViewModel.Log("onPermissionDenied", "tkofs_vpn");
        PopupUtil.hideLoading(ac);
        //ProgressManager.getInst().dismissProgress();
        ActivityCompat.requestPermissions(ac, list.toArray(new String[list.size()]), 0);
    }

    @Override
    public void onReceivePolicy(boolean b, int i, boolean b1, int i1, String s) {
        ViewModel.Log("onReceivePolicy", "tkofs_vpn");
        PopupUtil.hideLoading(ac);
    }

    @Override
    public void onChangePassword(int i, String s) {
        ViewModel.Log("onChangePassword", "tkofs_vpn");
        PopupUtil.hideLoading(ac);
    }

    @Override
    public void onAlreadyLogin(String msg) {
        ViewModel.Log("onAlreadyLogin", "tkofs_vpn");
        showAlreadyLoginDialog(msg);
    }

    /**
     * VPN Connection 이 성공했을때 호출
     *
     * @param ip : 할당된 대여 ip
     * @param postNoti : 서버에 설정된 접속 후 공지사항
     * @param redirectUrl : 서버에 설정된 자동 redirect url
     *       (vpn 접속 후 자동으로 intranet site 등의 접속이 필요한 경우 설정)
     * @param serverList : vpn 접속 후 접속 가능한 내부 server list
     *         SSLVPNConst.SSLVPN_SERVER_INFO_NAME : server name
     *         SSLVPNConst.SSLVPN_SERVER_INFO_TYPE : server type (1:host, 2:network, 5:domain)
     *         SSLVPNConst.SSLVPN_SERVER_INFO_IP : server ip
     *         SSLVPNConst.SSLVPN_SERVER_INFO_VER : ip version (1:v4, 2:v6, 3:공통)
     *         SSLVPNConst.SSLVPN_SERVER_INFO_URL : server url
     */
    @Override
    public void onVpnConnected(String ip,
                               String postNoti,
                               String redirectUrl,
                               ArrayList<HashMap<String, String>> serverList) {
        ViewModel.Log("onVpnConnected", "tkofs_vpn");
        PopupUtil.hideLoading(ac);
        // vpn 접속시 로그인 처리
        int status = mSSLVPN.vpnGetStatus();
        ViewModel.Log("init status2 : " +  status, "tkofs_vpn");
        lc.doLogIn(uid, upw);
    }

    @Override
    public void onVpnDisconnected(int i, String s) {
        ViewModel.Log("onVpnDisconnected", "tkofs_vpn");
        //init 시점에 onVpnDisconnected된 경우
        if(isInit){
            sslvpnConnect();
            isInit = false;
        }else{
            PopupUtil.hideLoading(ac);
            if(mSSLVPN != null){
                mSSLVPN.vpnClose();
            }
            BaseApplication.currActivity.finishAffinity();
        }
    }

    @Override
    public void onReceiveOtpCode(int i, String s) {
        ViewModel.Log("onReceiveOtpCode", "tkofs_vpn");
    }

    // 중복로그인 Alert Popup
    private void showAlreadyLoginDialog(String msg) {
        PopupUtil.hideLoading(ac);
        PopupUtil.showDialog(ac  , msg
                , "확인"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSSLVPN.vpnLoginForced(true);
                    }}
                , " 취소"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSSLVPN.vpnLoginForced(false);
                        ac.finishAffinity();
                    }});
    }


}
