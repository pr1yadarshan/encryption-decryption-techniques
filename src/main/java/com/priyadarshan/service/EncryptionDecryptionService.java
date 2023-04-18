package com.priyadarshan.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface EncryptionDecryptionService {
	
	public String getAesEncryptedData(String toEncrypted)throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public String getRsaEncryptedData(String toEncrypted);
	public String getRsaEncryptedData(String toEncrypted, PublicKey publicKey) throws Exception;
	String getRsaDecryptedData(String encrypted, PrivateKey privateKey) throws Exception;

}
