package com.priyadarshan.entity;

public class AesKey {
	
	private String keyType;
	private String timeStamp;
	private String keySize;
	private String aesKey;
	
	public AesKey(String keyType, String timeStamp, String keySize, String aesKey) {
		super();
		this.keyType = keyType;
		this.timeStamp = timeStamp;
		this.keySize = keySize;
		this.aesKey = aesKey;
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

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	
	
	
	
	

}
