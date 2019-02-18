package com.telefonica.portalmiddleware.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.axis.encoding.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;


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

        String encryptedValue = new String(Base64.encode(encrypted));
        //return encryptedValue;
        return URLEncoder.encode(encryptedValue, "UTF-8");
    }
    public static String decrypt(String plainText) throws Exception{
    	byte[] clean = Base64.decode(plainText);

        // Generating IV.
        byte[] iv = key.getBytes("UTF-8");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Hashing key.
        byte[] keyBytes = key.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Encrypt.
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(clean);

        return new String(decrypted);
    }
    public static void main(String args[]){
    	try {
			System.out.println(encrypt("8239159"));
			System.out.println(decrypt("QnoXWFHrPHv-p1h6CWB3yQ=="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
