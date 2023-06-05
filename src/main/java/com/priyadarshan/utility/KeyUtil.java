package com.priyadarshan.utility;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.priyadarshan.constant.CommonConstant;

public class KeyUtil {

	public SecretKey generateAesSecretKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(CommonConstant.AES);
		keyGen.init(CommonConstant.AES_KEY_SIZE, SecureRandom.getInstanceStrong());
		SecretKey key = keyGen.generateKey();
		return key;
	}
}
