package com.hs.mobile.crypto;

/**
 * 각종암호화에서 byte[]값을 스트링형태로 변형시킬때 사용한다.
 * 이 클래스는 직접적으로 사용 할수 없고 BASE64Decoder, BASE64Encoder클래스를 통하여 사용된다.
 * 
 * @author YunChang.Lee
 */

public class Base64 {

    /**
     * 인코딩 메서드
     * @param raw String으로 변경하길 희망하는 바이트배열
     * @return 인코딩된 String
     */
    public static String encode(byte[] raw) {
        StringBuffer encoded = new StringBuffer();
        for (int i = 0; i < raw.length; i += 3) {
            encoded.append(encodeBlock(raw, i));
        }
        return encoded.toString();
    }

    /**
     * 인코딩
     * @param raw String으로 변경하길 희망하는 바이트배열
     * @param offset 옵셋
     * @return 인코딩된 char[]
     */
    protected static char[] encodeBlock(byte[] raw, int offset) {
        int block = 0;
        int slack = raw.length - offset - 1;
        int end = (slack >= 2) ? 2 : slack;
        for (int i = 0; i <= end; i++) {
            byte b = raw[offset + i];
            int neuter = (b < 0) ? b + 256 : b;
            block += neuter << (8 * (2 - i));
        }
        char[] base64 = new char[4];
        for (int i = 0; i < 4; i++) {
            int sixbit = (block >>> (6 * (3 - i))) & 0x3f;
            base64[i] = getChar(sixbit);
        }
        if (slack < 1) base64[2] = '=';
        if (slack < 2) base64[3] = '=';
        return base64;
    }

    /**
     * 인코딩
     * @param sixBit String으로 변경하길 희망하는 바이트배열
     * @return 인코딩된 char[]
     */
  
    protected static char getChar(int sixBit) {
        if (sixBit >= 0 && sixBit <= 25)
            return (char)('A' + sixBit);
        if (sixBit >= 26 && sixBit <= 51)
            return (char)('a' + (sixBit - 26));
        if (sixBit >= 52 && sixBit <= 61)
            return (char)('0' + (sixBit - 52));
        if (sixBit == 62) return '+';
        if (sixBit == 63) return '/';
        return '?';
    }

    /**
     * 디코딩 메서드
     * @param base64 바이트배열로 변경을 희망하는 인코딩된 String
     * @return 바이트배열
     */  
    public static byte[] decode(String base64) {
        int pad = 0;
        for (int i = base64.length() - 1; base64.charAt(i) == '='; i--)
            pad++;
        int length = base64.length() * 6 / 8 - pad;
        byte[] raw = new byte[length];
        int rawIndex = 0;
        for (int i = 0; i < base64.length(); i += 4) {
            int block = (getValue(base64.charAt(i)) << 18)
            + (getValue(base64.charAt(i + 1)) << 12)
            + (getValue(base64.charAt(i + 2)) << 6)
            + (getValue(base64.charAt(i + 3)));
            for (int j = 0; j < 3 && rawIndex + j < raw.length; j++)
                raw[rawIndex + j] = (byte)((block >> (8 * (2 - j))) & 0xff);
            rawIndex += 3;
        }
        return raw;
    }
  
    /**
     * 내부 사용 메서드
     * @param c
     * @return  변현값
     */
    protected static int getValue(char c) {
        if (c >= 'A' && c <= 'Z') return c - 'A';
        if (c >= 'a' && c <= 'z') return c - 'a' + 26;
        if (c >= '0' && c <= '9') return c - '0' + 52;
        if (c == '+') return 62;
        if (c == '/') return 63;
        if (c == '=') return 0;
        return -1;
    }
}