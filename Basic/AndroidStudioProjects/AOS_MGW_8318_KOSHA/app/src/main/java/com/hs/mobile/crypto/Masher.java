package com.hs.mobile.crypto;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.hs.mobile.gw.util.Debug;

/**
 * MD5 메시지 축약 알고리즘을 이용한 Masher 클래스
 * 사용법 : 
 * <ul>
 * <li>Masher.masher("Hellow World");
 * <li>Masher.masherFile("/home/userid/log.txt");
 * </ul>
 * 
 * @author YunChang.Lee
 */
 
public class Masher{
    /**
     * 스트링 문자열을 메시지 축약하는 메서드
     * @param   ID  축약을 원하는 문자열
     * @return  String 축약된 문자열
     */
    public static String masher(String ID) {
       try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(ID.getBytes());
            byte[] raw = md.digest();            
            String base64 = BASE64Encoder.encode(raw);
            return base64;
        }catch(NoSuchAlgorithmException e){
        	Debug.trace(e.getMessage());
            return "";
        }catch(NullPointerException e){
        	Debug.trace(e.getMessage());
            return "";
        }
	}

    /**
     * 파일을 메시지 축약하는 메서드
     * @param   fileName    축약을 원하는 파일
     * @return  String 축약된 문자열
     */    
//    public static String masherFile(String fileName){
//    	String strRet = "";
//    	FileInputStream in = null;
//        try{
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            in = new FileInputStream(fileName);
//            byte[] buffer = new byte[8192];
//            int length;
//
//            while ((length = in.read(buffer)) != -1){
//                    md.update(buffer, 0, length);
//            }
//            byte[] raw = md.digest();            
//            strRet = BASE64Encoder.encode(raw);
//        }catch(Exception e){
//        	Debug.trace(e.getMessage());
//        	strRet = "";
//        }
//        finally{
//        	try{
//        		if(in!= null) in.close();
//        	}
//        	catch(Exception e){
//        		Debug.trace(e.getMessage());
//        		strRet = "";
//        	}
//        }
//        return strRet;
//	}
}