package com.news.Tool;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class Md5Util {
    /**
     * 将数据进行 MD5 加密，并以16进制字符串格式输出
     * @param data 输入的数据
     * @return 加密后的字符串
     */
    public static String md5(String data) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(data.getBytes(StandardCharsets.UTF_8));

            // 将字节数据转换为十六进制
            for (byte b : md5) sb.append(Integer.toHexString(b & 0xff));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
