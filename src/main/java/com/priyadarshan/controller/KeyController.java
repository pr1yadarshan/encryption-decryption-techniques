package com.priyadarshan.controller;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.HashMap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyController {

	@GetMapping("/generateRSAKeyPair")
	public HashMap<String, String> generateRSAKeyPair() throws NoSuchAlgorithmException {
		/* 
		 * generating RSA key-pair and sending response as hash map 
		*/
		
		String generatedTime = java.time.Clock.systemUTC().instant().toString();
		
		HashMap<String, String> keyPairHashMap = new HashMap<>();
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();
		
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		
		String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
		String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
		
		keyPairHashMap.put("timeStamp", generatedTime);
		keyPairHashMap.put("publicKey", publicKeyStr);
		keyPairHashMap.put("privateKey", privateKeyStr);
		
		return keyPairHashMap;
	}
}
