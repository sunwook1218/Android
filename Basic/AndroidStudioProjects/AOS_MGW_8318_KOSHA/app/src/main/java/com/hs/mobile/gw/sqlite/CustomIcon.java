package com.hs.mobile.gw.sqlite;


public class CustomIcon {

	String name;
	int version;
	byte[] byteArray;	

	public CustomIcon() {
	}

	public CustomIcon(String name, int version, byte[] byteArray) {
		super();
		this.version = version;
		this.name = name;
		this.byteArray = byteArray;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

}
