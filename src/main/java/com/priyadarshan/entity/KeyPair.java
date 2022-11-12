package com.priyadarshan.entity;

public class KeyPair {
	
	private String keyIndex;
	private String timeStamp;
	private String publicKey;
	private String privateKey;
	
	
	public KeyPair(String keyIndex, String timeStamp, String publicKey, String privateKey) {
		super();
		this.keyIndex = keyIndex;
		this.timeStamp = timeStamp;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		
	}
	
	public String getKeyIndex() {
		return keyIndex;
	}
	public void setId(String keyIndex) {
		this.keyIndex = keyIndex;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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
