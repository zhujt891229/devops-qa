package com.zjt.qas.utils;

public class MD5Util {
    public static String md5Digest(String source ,Integer salt){
        char[] ca = source.toCharArray();
        for(int i = 0 ; i < ca.length ;i++){
            ca[i] = (char) (ca[i] + salt);
        }
        String target = new String(ca);
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(target);
    }

    public static String md5encrypt(String password,String salt){
//        for (int i = 0; i < 3; i++) {
            password = org.springframework.util.DigestUtils.md5DigestAsHex((salt+password+salt).getBytes())
                    .toUpperCase();
//        }
        return password;
    }
}
