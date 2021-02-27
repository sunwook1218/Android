package com.hs.mobile.crypto;

/**
 * 각종암호화에서 Decoding 클래스
 * 이 클래스는 Base64 클래스의 decode 메서드를 호출한다.
 *
 * @author YunChang.Lee
 */
import org.apache.commons.codec.binary.Base64;

import com.hs.mobile.gw.util.Debug;

public class BASE64Decoder {

	/**
	 * 디코딩 메서드
	 * 
	 * @param base64
	 *            인코딩된 스트링 문자열
	 * @return byte[] 디코딩된 바이트배열
	 */
	public static byte[] decodeBuffer(String base64) {
		byte[] buffer = null;
		buffer = Base64.decodeBase64(base64.getBytes());
		return buffer;
	}
}