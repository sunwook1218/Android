package com.hs.mobile.crypto;

import com.hs.mobile.gw.util.Debug;

/**
 * 바이트 변환 암호화
 * 이 클래스는 기초적인 Caesar 암호화 알고리즘을 사용하여 바이트를 변환한다. 
 *
 * @author YunChang.Lee
 */

public class Caesar {
    /**
     * 연산에 적용할 변경 바이트 값
     */
    static final int defaultKey = -3;

    /**
     * 디폴트 값으로 인코딩하는 메서드 
     * @param s 인코딩할 문자열
     * @return  인코딩된 문자열
     */    
    public static String Encrypt(String s) {
        return Encrypt(s, defaultKey);
    }

    /**
     * 디폴트 값으로 디코딩하는 메서드 
     * @param s  디코딩할 문자열
     * @return  디코딩된 문자열
     */
    public static String Decrypt(String s) {
        return Decrypt(s, defaultKey);
    }

    /**
     * 주어진 파라미터 key 값으로 인코딩하는 메서드 
     * @param s  인코딩할 문자열
     * @param key
     * @return  인코딩된 문자열
     */    
	public static String Encrypt(String s, int key) {
		String rtn = "";
        byte[] cc = s.getBytes();
        for(int i=0; i< cc.length; i++){
            cc[i] = (byte)(cc[i]-key);
        }
        rtn = new String(cc);
        return rtn;
    }

    /**
     * 주어진 파라미터 key 값으로 디코딩하는 메서드 
     * @param s  디코딩할 문자열
     * @param key
     * @return  디코딩된 문자열
     */
    public static String Decrypt(String s, int key){
		String rtn = "";
        byte[] cc = s.getBytes();
        for(int i=0; i< cc.length; i++){
            cc[i] = (byte)(cc[i]+key);
        }
        rtn = new String(cc);
        return rtn;
    }
}