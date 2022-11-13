package com.priyadarshan.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.priyadarshan.service.EncryptionDecryptionService;
import com.priyadarshan.service.KeyService;

@RestController
public class EncryptionDecryptionServiceimpl implements EncryptionDecryptionService {

	@Autowired
	KeyService keyService;

	@Override
	public String getAesEncryptedData(String toEncrypted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		String aesKeyStr = keyService.getAesKey().getAesKey();
		byte[] aesKeyBytes = Base64.decodeBase64(aesKeyStr.getBytes());
		SecretKey aesKey = new SecretKeySpec(aesKeyBytes, 0, aesKeyBytes.length, "AES");

		byte[] iv = getRandomNonce(16);
	    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	    cipher.init(Cipher.ENCRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
	    byte[] encryptedText = cipher.doFinal(toEncrypted.getBytes());
	    String encryptedTextStr = Base64.encodeBase64String(encryptedText);
	
		return encryptedTextStr;
	}

	@Override
	public String getRsaEncryptedData(String toEncrypted) {
		
		return null;
	}
	
	public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

}
