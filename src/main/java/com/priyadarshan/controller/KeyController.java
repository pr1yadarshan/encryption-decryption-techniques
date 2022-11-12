package com.priyadarshan.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyadarshan.service.KeyService;
import com.priyadarshan.entity.KeyPair;

@RestController
public class KeyController {
	
	@Autowired
	KeyService keyService;

	@GetMapping("/generateRSAKeyPair")
	public KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		/* 
		 * generating RSA key-pair and sending response as hash map 
		*/
		
		return keyService.getRSAKeyPair();
	}
}
