package com.hs.mobile.gw.ext.vpn;

public class VpnConfig {

    protected static final String VPN_SERVER_URL = "https://121.166.27.18:8443/index.html";

    protected static boolean VPN_USE_PROXY = false;

    protected static final int VPN_CONNECT_CHECK_TIMEOUT = 60; //vpn접속체크 타임아웃시간(초)

    public static final int VPN_REQUEST_PERMISSION = 101;

    protected static final String LOGIN_FAIL_MESSAGE  = "VPN 로그인에 실패했습니다.";
}
