package com.priyadarshan.service.impl;

import java.nio.charset.StandardCharsets;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.priyadarshan.service.EncryptionDecryptionService;
import com.priyadarshan.service.KeyService;
import com.priyadarshan.utility.CryptLibUtil;

@RestController
public class EncryptionDecryptionServiceimpl implements EncryptionDecryptionService {

	@Autowired
	KeyService keyService;

	@Override
	public String getAesEncryptedData(String toEncrypt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		CryptLibUtil cryptLib = new CryptLibUtil();
		
		String aesKeyStr = keyService.getAesKey().getAesKey();
		byte[] aesKeyBytes = Base64.decodeBase64(aesKeyStr.getBytes());
		SecretKey aesKey = new SecretKeySpec(aesKeyBytes, 0, aesKeyBytes.length, "AES");
		logger.debug("Aes key -> " + aesKeyStr);

		byte[] iv = CryptLibUtil.getRandomNonce(16);
		String ivStr = Base64.encodeBase64String(iv);
		logger.debug("Random IV -> " + ivStr);
		
		String messageHash = CryptLibUtil.sha256UsingRandom(toEncrypt, iv);
		logger.debug("Message Hash -> " + messageHash);
		
	    String encryptedTextStr = cryptLib.encryptAESGCM(messageHash, aesKey, iv);
	    logger.debug("Encrypted Text -> " + encryptedTextStr);
	
		return encryptedTextStr;
	}
	

	public String getAesDecryptedData(String toDecrypt) {
		return null;
	}

	@Override
	public String getRsaEncryptedData(String toEncrypted) {
		
		return null;
	}

}
