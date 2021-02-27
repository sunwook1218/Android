package com.hs.mobile.gw.ext;

import android.app.Activity;

/**
 * 외부 솔루션 연동시 상수값 정의 클래스 tkofs
 */
public class ViewModel extends BaseModel {

    /**
     * OTP 관련
     */
    public static String otp_id;
    public static boolean otpComplete = false;
    public static boolean backKey_Lock = false;

    public static boolean isLiveMainAct = false;
    public static boolean isLiveSubAct = false;
    public static boolean isLiveLnbAct = false;
    public static boolean isLivePermAct = false;
	public static final int OTP_REQUEST_CODE = 1812;
	// OTP 자동로그인 기능 사용 여부
	public static final boolean USE_OTP_AUTO_LOGIN = false;
	public static final String OTP_SUCCESS_CODE = "0000";


	//백신관련
	public static final int VAC_REQUEST_CODE = 777;
	public static final int VAC_MESSAGE_ID = 12345;
	public static final int VAC_MESSAGE_ID1 = 123456;

    // forground background 여부
    public static boolean isBackground = true;
    public static Activity mainActivity = null;

    /**
     * @name apiName
     * @return
     * @description - 인증 패스 여부 { true:패스, false:인증 }
     */
    public enum apiName {
        OTP("OTP", true),
        MDM("MDM", true),
        MIS("MIS", true),
        VPN("VPN", true),
        VAC("VAC", true)
        ;

        private String apikeyName;
        private boolean isPass;

        apiName(String name, Boolean b) {
            apikeyName = name;
            isPass = b;
        }

        public String isApiName() {
            return apikeyName;
        }

        public boolean isApiPass() {
            return isPass;
        }
    }

    /**
     * 메뉴 레이아웃 타입 tkofs
     */
    public static int menuLayoutType = 1;  // 0 : list 형, 1 : grid 형,  hsuco 경우 grid 메뉴 사용.

    public static boolean isAllLiveAct(){
        if(isLiveMainAct || isLiveSubAct || isLiveLnbAct || isLivePermAct){
            return true;
        }
        return false;
    }
}

