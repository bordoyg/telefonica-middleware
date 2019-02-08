package com.telefonica.portalmiddleware.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.axis.encoding.Base64;
import org.apache.commons.lang3.StringUtils;

public class Utils {
	private static final String key="p0r7417313f0n1c4";
    public static String encrypt(String plainText) throws Exception{
    	byte[] clean = plainText.getBytes();

        // Generating IV.
        byte[] iv = key.getBytes("UTF-8");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        byte[] keyBytes = key.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(clean);

        String encryptedValue = Base64.encode(encrypted);

        return StringUtils.replaceEach(encryptedValue, new String[] {"+", "/", "="}, new String[] {"_", "~", "*"});
    }
    public static void main(String args[]){
    	try {
			System.out.println(encrypt("5014697"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
