package com.priyadarshan.utility;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class CryptLibUtil {
	
	public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
	
	public static String sha256UsingRandom(String message, byte[] random) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(random);
        byte[] bytes = md.digest(message.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
	}
	
	public String encryptAESGCM(String message, SecretKey key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	    cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(128, iv));
	    byte[] encryptedText = cipher.doFinal(message.getBytes());
	    String encryptedTextStr = Base64.encodeBase64String(encryptedText);
	    
		return encryptedTextStr;
	}
	
	public String decryptAESGCM(byte[] cipherText, SecretKey key, byte[] iv) throws Exception
    {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] decryptedText = cipher.doFinal(cipherText);
        
        return new String(decryptedText);
    }

}
