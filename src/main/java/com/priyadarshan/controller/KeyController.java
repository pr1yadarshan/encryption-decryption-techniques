package com.priyadarshan.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyadarshan.service.KeyService;
import com.priyadarshan.entity.AesKey;
import com.priyadarshan.entity.KeyPair;

@RestController
public class KeyController {
	
	@Autowired
	KeyService keyService;

	@GetMapping("/generateRsaKeyPair")
	public KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		
		return keyService.getRSAKeyPair();
	}
	
	@GetMapping("/generateAesKey")
	public AesKey generateAesKy() throws NoSuchAlgorithmException {
		
		return keyService.getAesKey();
	}
	
}
