package com.ch.entity;

public class CategoryBean {
	
	public static final String KEY_STRING_UUID="uuid";
	
	public static final String KEY_INT_CATEGORYID = "categoryid";
	
	public static final String KEY_STRING_NAME=  "name";
	
	private String UUID = "";
	
	private int mCategoryID = 0;
	
	private String mName = "";

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public int getCategoryID() {
		return mCategoryID;
	}

	public void setCategoryID(int categoryID) {
		mCategoryID = categoryID;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}
	
	
}
