package com.priyadarshan.service;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import com.priyadarshan.entity.AesKey;
import com.priyadarshan.entity.KeyPair;

public interface KeyService {

	public KeyPair getRSAKeyPair() throws NoSuchAlgorithmException;

	public AesKey getAesKey() throws Exception;

	PublicKey getPublicKeyFromString(String publicKey) throws Exception;

	PrivateKey getPrivateKeyFromString(String privateKey) throws Exception;

	public SecretKey getAesSecretKey() throws Exception;

}
