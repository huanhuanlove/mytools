package com.kang.mytools.md5util;

import java.security.MessageDigest;

/**
 * MD5加密加密工具类
 */
public class MD5Util {

    /**
     * 加密为16位--加密单次
     * @param inStr  需加密的字符串
     * @return 加密后得到的字符串
     */
    public static String digest16(String inStr) {
        return digest(inStr, 16);
    }

    /**
     * 加密为16位--加密多次
     * @param inStr  需加密的字符串
     * @param count  加密次数
     * @return  加密后得到的字符串
     */
    public static String digest16ByManyCount(String inStr, int count){
        for (int i = 0; i < count; i++) {
            inStr = digest(inStr);
        }
        return inStr;
    }

    /**
     * 加密为32位--加密单次
     * @param inStr  需加密的字符串
     * @return 加密后得到的字符串
     */
    public static String digest(String inStr) {
        return digest(inStr, 32);
    }

    /**
     * 加密为32位--加密多次
     * @param inStr  需加密的字符串
     * @param count  加密次数
     * @return  加密后得到的字符串
     */
    public static String digest32ByManyCount(String inStr, int count){
        for (int i = 0; i < count; i++) {
            inStr = digest(inStr);
        }
        return inStr;
    }

    private static String digest(String inStr, int rang) {
        MessageDigest md5 = null;
        if (StringUtil.isEmpty(inStr)) {
            return "";
        }

        try {
            md5 = MessageDigest.getInstance("MD5"); // 取得算法
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray); // 加密

        StringBuilder hexValue = new StringBuilder();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        if (rang == 32) {
            return hexValue.toString();
        } else {
            return hexValue.toString().substring(8, 24);// 转换为32位字符串
        }
    }

}
