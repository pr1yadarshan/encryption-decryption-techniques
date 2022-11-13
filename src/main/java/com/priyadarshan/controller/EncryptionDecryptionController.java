package com.priyadarshan.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.priyadarshan.service.EncryptionDecryptionService;

@RestController
public class EncryptionDecryptionController {
	
	@Autowired
	EncryptionDecryptionService encryptionDecryptionService;

	@GetMapping("/AesEncrypt/{input}")
	public String getAesEncryptedData(@PathVariable String input) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		return encryptionDecryptionService.getAesEncryptedData(input);
	}
	
	@GetMapping("/RsaEncrypt/{input}")
	public String getRsaEncryptedData(@PathVariable String input) {
		
		return encryptionDecryptionService.getRsaEncryptedData(input);
	}
}
