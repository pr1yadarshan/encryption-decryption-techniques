package com.priyadarshan.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import com.priyadarshan.entity.KeyPair;

public interface KeyService {
	
	public List<KeyPair> getRSAKeyPair() throws NoSuchAlgorithmException;

}
