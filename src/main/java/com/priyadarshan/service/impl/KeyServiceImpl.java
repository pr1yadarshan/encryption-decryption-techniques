package com.priyadarshan.service.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.priyadarshan.constant.CommonConstant;
import com.priyadarshan.service.KeyService;

@Service
public class KeyServiceImpl implements KeyService {

	@Override
	public com.priyadarshan.entity.KeyPair getRSAKeyPair() throws NoSuchAlgorithmException {
		
		com.priyadarshan.entity.KeyPair keyPair;
		String generatedTime = java.time.Clock.systemUTC().instant().toString();

		List<com.priyadarshan.entity.KeyPair> keyPairList = new ArrayList<>();
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
		String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
		
		keyPair = new com.priyadarshan.entity.KeyPair(CommonConstant.RSA_KEY_INDEX, generatedTime, publicKeyStr, privateKeyStr);
		
		return keyPair;
	}

}
