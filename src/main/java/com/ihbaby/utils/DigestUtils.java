package com.ihbaby.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @author qiang on 2015/9/8.
 *         jinliqiang@ihbaby.com
 */
public class DigestUtils {
    private static final String salt = "p3XnKm";

    public static String digest(String pwd) {
    	return new SHA1().getDigestOfString(pwd.getBytes());
    }

    public static String digestSha1(String pwd) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(pwd);
    }

    public static String getDefalutPassword(){
        return new SHA1().getDigestOfString("123456".getBytes());
    }
    
    public static void main(String[] args) {
		System.out.println(digest("123456"));
	}
    public static String digest_erp(String pwd) {
       return Base64.encodeBase64String(
               org.apache.commons.codec.digest.DigestUtils.sha256(salt + pwd)
        );

    }
}
