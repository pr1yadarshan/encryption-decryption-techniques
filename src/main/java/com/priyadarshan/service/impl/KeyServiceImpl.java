package com.priyadarshan.service.impl;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.priyadarshan.constant.CommonConstant;
import com.priyadarshan.entity.AesKey;
import com.priyadarshan.service.KeyService;

@Service
public class KeyServiceImpl implements KeyService {

	@Override
	public com.priyadarshan.entity.KeyPair getRSAKeyPair() throws NoSuchAlgorithmException {
		
		com.priyadarshan.entity.KeyPair keyPair;
		String generatedTime = java.time.Clock.systemUTC().instant().toString();

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(CommonConstant.RSA_KEY_SIZE);
		KeyPair pair = generator.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
		String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
		keyPair = new com.priyadarshan.entity.KeyPair(CommonConstant.RSA_KEY, generatedTime, CommonConstant.RSA_KEY_SIZE + "", publicKeyStr, privateKeyStr);
		
		return keyPair;
	}
	
	@Override
	public AesKey getAesKey() throws NoSuchAlgorithmException {
		
		AesKey aesKey;
		String generatedTime = java.time.Clock.systemUTC().instant().toString();
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(CommonConstant.AES_KEY_SIZE, SecureRandom.getInstanceStrong());
        SecretKey key =  keyGen.generateKey();
        
        String aesKeyStr = Base64.encodeBase64String(key.getEncoded());
        aesKey = new AesKey(CommonConstant.AES_KEY, generatedTime, CommonConstant.AES_KEY_SIZE + "", aesKeyStr);
        
	    return aesKey;
	}

}
