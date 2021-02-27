package com.hs.mobile.crypto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.hs.mobile.gw.util.Debug;

/**
 * 주어진 스트링과 파일을 비밀키 암호화 하는 클래스이다.
 * 
 * @author YunChang.Lee
 */

public class Crypto {
	private Cipher mAesCipher; 
	private SecretKey mSecretKey; 
	private IvParameterSpec mIvParameterSpec; 
	private final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding"; 
	private final String CIPHER_ALGORITHM = "AES";
	

	/**
	 * 파일암호화에 쓰이는 버퍼 크기 지정
	 */
	private final int kBufferSize = 8192;
	
	private static Crypto instance = null;
	
	public static Crypto getInstance() {
		if(instance == null) {
			instance = new Crypto();
		}
		return instance;
	}
	
	private Crypto() {
		try {
			mAesCipher = Cipher.getInstance(CIPHER_TRANSFORMATION); 
			
//			byte[] seedByte = new byte[16];
//			for (int i = 0; i < seedByte.length; i++)
//			{
//				seedByte[i] = (byte)i;
//			}
			
			byte[] seedByte = {
				(byte)0x03, (byte)0x0e, (byte)0x0f, (byte)0x09,
				(byte)0x02, (byte)0x06, (byte)0x05, (byte)0x03,
				(byte)0x05, (byte)0x08, (byte)0x09, (byte)0x07,
				(byte)0x09, (byte)0x03, (byte)0x02, (byte)0x03
			};
			
			mSecretKey = new SecretKeySpec(seedByte, "AES"); 
			mIvParameterSpec = new IvParameterSpec(seedByte);
		} catch (NoSuchAlgorithmException e) {
			Debug.trace(e.getMessage());
		} catch (NoSuchPaddingException e) {
			Debug.trace(e.getMessage());
		}
	}


	public byte[] encrypt(byte[] inputBytes) { 
		byte[] encryptedData = null;
		try {
			mAesCipher.init(Cipher.ENCRYPT_MODE, mSecretKey, mIvParameterSpec); 
			encryptedData = mAesCipher.doFinal(inputBytes); 
		} catch (InvalidKeyException e) {
			Debug.trace(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			Debug.trace(e.getMessage());
		} catch (BadPaddingException e) {
			Debug.trace(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			Debug.trace(e.getMessage());
		}
		return encryptedData;
	} 

	public byte[] decrypt(byte[] encryptedData){ 

		byte[] decryptedData = null;
		try {
			mAesCipher.init(Cipher.DECRYPT_MODE, mSecretKey,mIvParameterSpec); 
			decryptedData = mAesCipher.doFinal(encryptedData);
		} catch (InvalidKeyException e) {
			Debug.trace(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			Debug.trace(e.getMessage());
		} catch (BadPaddingException e) {
			Debug.trace(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			Debug.trace(e.getMessage());
		} 
		return decryptedData;
	} 

	
	/**
	 * 문자열 대칭 암호화
	 * 
	 * @param ID
	 *            비밀키 암호화를 희망하는 문자열
	 * @return String 암호화된 ID
	 * @exception Exception
	 */
	public String encrypt(String inputString) {
		if (inputString == null || inputString.length() == 0)
			return "";

		String encryptedString = null;
		try {
			byte[] inputBytes = inputString.getBytes("UTF8");
			byte[] outputBytes = encrypt(inputBytes);
			
			encryptedString = BASE64Encoder.encode(outputBytes);
			
		} catch (UnsupportedEncodingException e) {
			Debug.trace(e.getMessage());
		}
		return encryptedString;

	}




	/**
	 * 문자열 대칭 복호화
	 * 
	 * @param codedID
	 *            비밀키 복호화를 희망하는 문자열
	 * @return String 복호화된 ID
	 * @exception Exception
	 */
	public String decrypt(String codedID) {
		if (codedID == null || codedID.length() == 0)
			return "";

		String strResult = "";

			try {
				byte[] inputBytes1 = BASE64Decoder.decodeBuffer(codedID);		
				byte[] outputBytes2 = decrypt(inputBytes1);
				strResult = new String(outputBytes2, "UTF8");
			} catch (UnsupportedEncodingException e) {
				Debug.trace(e.getMessage());
			} catch (StringIndexOutOfBoundsException e) { 
				Debug.trace(e.getMessage());
				return codedID;
			} catch (NullPointerException e) {
				Debug.trace(e.getMessage());
				return codedID;
			}

		return strResult;
	}

	/**
	 * 파일 대칭 암호화
	 * 
	 * @param infile
	 *            암호화을 희망하는 파일명
	 * @param outfile
	 *            암호화된 파일명
	 * @exception Exception
	 */
	public void encryptFile(String infile, String outfile)
			throws Exception {

		FileInputStream in = new FileInputStream(infile);
		FileOutputStream out = new FileOutputStream(outfile);
		try {

			encryptInputStream(in, out);
		} finally {
			in.close();
			out.close();
		}

	}

	public void encryptInputStream(InputStream in, OutputStream out)
			throws Exception {


		CipherOutputStream cipherOut = null;
		try {
			cipherOut = new CipherOutputStream(out, mAesCipher);
			byte[] buffer = new byte[kBufferSize];
			int length;
			while ((length = in.read(buffer)) != -1)
				cipherOut.write(buffer, 0, length);
		} finally {
			cipherOut.close();
		}
	}

	/**
	 * 파일 대칭 복호화
	 * 
	 * @param infile
	 *            복호화을 희망하는 파일명
	 * @param outfile
	 *            복호화된 파일명
	 * @exception Exception
	 */
	public void decryptFile(String infile, String outfile)
			throws Exception {
		FileInputStream in = new FileInputStream(infile);
		FileOutputStream out = new FileOutputStream(outfile);
		try {
			decryptInputStream(in, out);
		} finally {
			in.close();
			out.close();
		}
	}

	public void decryptInputStream(InputStream in, OutputStream out) {
		CipherOutputStream chperOut = null;
		chperOut = new CipherOutputStream(out, mAesCipher);
		byte[] buffer = new byte[kBufferSize];
		int length;
		try {
			while ((length = in.read(buffer)) != -1)
				out.write(buffer, 0, length);
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		} catch(IndexOutOfBoundsException e){
			Debug.trace(e.getMessage());
		}finally {
			try {
				chperOut.close();
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			}
		}
	}
}