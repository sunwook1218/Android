package com.hs.mobile.gw.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;

import com.hs.mobile.crypto.BASE64Decoder;
import com.hs.mobile.crypto.BASE64Encoder;

public class EncryptedProperties extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BASE64Decoder decoder = new BASE64Decoder();
	private static BASE64Encoder encoder = new BASE64Encoder();
	private Cipher encrypter, decrypter;
	private static byte[] salt = { (byte) 0x03, (byte) 0x07, (byte) 0x11, (byte) 0xFF }; // make
																							// up
																							// your
																							// own

	public EncryptedProperties(String password) throws Exception {
		PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20);
		SecretKeyFactory kf = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey k = kf.generateSecret(new javax.crypto.spec.PBEKeySpec(password.toCharArray()));
		encrypter = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
		decrypter = Cipher.getInstance("PBEWithMD5AndDES/CBC/PKCS5Padding");
		encrypter.init(Cipher.ENCRYPT_MODE, k, ps);
		decrypter.init(Cipher.DECRYPT_MODE, k, ps);
	}

	public String getProperty(String key) {
		
		if(super.getProperty(key)!=null) return decrypt(super.getProperty(key));
		return null;
		
	}

	public synchronized Object setProperty(String key, String value){
		return super.setProperty(key, encrypt(value));
	}

	private synchronized String decrypt(String str){
		byte[] dec;
		byte[] utf8;
		try {
			if (str == null) return null;
			dec = decoder.decodeBuffer(str);
			utf8 = decrypter.doFinal(dec);
			return new String(utf8, "UTF-8");
		} catch (IllegalBlockSizeException e) {
			Debug.trace(e.getMessage());
		} catch (BadPaddingException e) {
			Debug.trace(e.getMessage());
		} catch (UnsupportedEncodingException e){
			Debug.trace(e.getMessage());
		}
		return null;
		
	}

	private synchronized String encrypt(String str){
		byte[] utf8;
		byte[] enc;
		try {
			utf8 = str.getBytes("UTF-8");
			enc = encrypter.doFinal(utf8);
			return encoder.encode(enc);
		} catch (IllegalBlockSizeException e) {
			Debug.trace(e.getMessage());
		} catch (BadPaddingException e) {
			Debug.trace(e.getMessage());
		}catch (UnsupportedEncodingException e) {
			Debug.trace(e.getMessage());
		}
		return null;
		
	}
}
