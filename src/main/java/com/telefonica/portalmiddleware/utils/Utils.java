package com.telefonica.portalmiddleware.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.axis.encoding.Base64;

public class Utils {
	private static final String ALGORITHM="AES";
	private static final byte[] key="p0r7417313f0n1c4".getBytes();
    public static String encrypt(String plainText) throws Exception{
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return Base64.encode(cipher.doFinal(plainText.getBytes()));
    }
    public static void main(String args[]){
    	try {
			System.out.println(encrypt("hola"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
