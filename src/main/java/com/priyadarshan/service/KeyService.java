package com.priyadarshan.service;

import java.security.NoSuchAlgorithmException;

import com.priyadarshan.entity.AesKey;
import com.priyadarshan.entity.KeyPair;

public interface KeyService {
	
	public KeyPair getRSAKeyPair() throws NoSuchAlgorithmException;
	public AesKey getAesKey() throws NoSuchAlgorithmException;

}
