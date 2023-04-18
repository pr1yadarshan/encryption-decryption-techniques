package com.priyadarshan.service;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.priyadarshan.entity.AesKey;
import com.priyadarshan.entity.KeyPair;

public interface KeyService {
	
	public KeyPair getRSAKeyPair() throws NoSuchAlgorithmException;
	public AesKey getAesKey() throws NoSuchAlgorithmException;
	PublicKey getPublicKeyFromString(String publicKey) throws Exception;
	PrivateKey getPrivateKeyFromString(String privateKey) throws Exception;

}
