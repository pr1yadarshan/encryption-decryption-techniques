package com.priyadarshan.entity;

public class KeyPair {
	
	private String keyType;
	private String timeStamp;
	private String keySize;
	private String publicKey;
	private String privateKey;
	
	
	public KeyPair(String keyType, String timeStamp, String keySize, String publicKey, String privateKey) {
		super();
		this.keyType = keyType;
		this.timeStamp = timeStamp;
		this.keySize = keySize;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		
	}
	
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getKeySize() {
		return keySize;
	}

	public void setKeySize(String keySize) {
		this.keySize = keySize;
	}

	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
