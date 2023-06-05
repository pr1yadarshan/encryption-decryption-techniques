package com.priyadarshan.service.impl;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.*;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.priyadarshan.constant.CommonConstant;
import com.priyadarshan.entity.AesKey;
import com.priyadarshan.service.KeyService;
import com.priyadarshan.utility.*;

@Service
public class KeyServiceImpl implements KeyService {

	@Override
	public com.priyadarshan.entity.KeyPair getRSAKeyPair() throws NoSuchAlgorithmException {

		com.priyadarshan.entity.KeyPair keyPair;
		String generatedTime = java.time.Clock.systemUTC().instant().toString();

		KeyPairGenerator generator = KeyPairGenerator.getInstance(CommonConstant.RSA);
		generator.initialize(CommonConstant.RSA_KEY_SIZE);
		KeyPair pair = generator.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();

		String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
		String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
		keyPair = new com.priyadarshan.entity.KeyPair(CommonConstant.RSA_KEY, generatedTime,
				CommonConstant.RSA_KEY_SIZE + "", publicKeyStr, privateKeyStr);

		return keyPair;
	}

	@Override
	public AesKey getAesKey() throws Exception {

		AesKey aesKey;
		String generatedTime = java.time.Clock.systemUTC().instant().toString();
		SecretKey key = new KeyUtil().generateAesSecretKey();
		String aesKeyStr = Base64.encodeBase64String(key.getEncoded());
		aesKey = new AesKey(CommonConstant.AES_KEY, generatedTime, CommonConstant.AES_KEY_SIZE + "", aesKeyStr);
		return aesKey;
	}

	@Override
	public PublicKey getPublicKeyFromString(String publicKey) throws Exception {
		byte[] publicKeyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey key = kf.generatePublic(keySpec);
		return key;

	}

	@Override
	public PrivateKey getPrivateKeyFromString(String privateKey) throws Exception {
		byte[] privateKeyBytes = Base64.decodeBase64(privateKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey key = kf.generatePrivate(keySpec);
		return key;

	}

	@Override
	public SecretKey getAesSecretKey() throws Exception {
		return new KeyUtil().generateAesSecretKey();
	}

}
