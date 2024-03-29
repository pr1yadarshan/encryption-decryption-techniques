package com.priyadarshan.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

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
	public String getAesEncryptedData(String toEncrypt) throws Exception {

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

	@Override
	public String getRsaEncryptedData(String toEncrypted, PublicKey publicKey) throws Exception {

		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
		OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
				PSource.PSpecified.DEFAULT);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);
		byte[] encrypted = cipher.doFinal(toEncrypted.getBytes());

		return Base64.encodeBase64String(encrypted);
	}

	@Override
	public String getRsaDecryptedData(String encrypted, PrivateKey privateKey) throws Exception {

		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
		OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256,
				PSource.PSpecified.DEFAULT);
		cipher.init(Cipher.DECRYPT_MODE, privateKey, oaepParams);
		byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encrypted));

		return new String(decrypted);
	}

	@Override
	public String getJweEncryptedData(String toBeEncrypted, PublicKey publicKey) throws Exception {

		CryptLibUtil cryptLib = new CryptLibUtil();
		String header = "eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkEyNTZHQ00ifQ";
		SecretKey aesKey = keyService.getAesSecretKey();
		String iv = Base64.encodeBase64String(CryptLibUtil.getRandomNonce(16));
		// String iv =
		// Base64.encodeBase64URLSafeString(CryptLibUtil.getRandomNonce(16));
		String aesEncryptedString = cryptLib.encryptAESGCM(toBeEncrypted, aesKey, Base64.decodeBase64(iv));
		String encryptedAesKey = cryptLib.encryptRsaOeap(toBeEncrypted, publicKey);

		String jweString = header + "." + encryptedAesKey + "." + iv + "." + aesEncryptedString;

		return jweString;
	}

}
