package com.gmsz.om.common.beans;

import java.util.Date;

public class CheckinHistory {
	
	private long id;
	
	private long assetsId;
	
	private String userName;
	
	private String assetsName;
	
	private String assetsLocation;
	
	private Date checkTime;
	
	private String checkTimeStr;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public String getAssetsLocation() {
		return assetsLocation;
	}

	public void setAssetsLocation(String assetsLocation) {
		this.assetsLocation = assetsLocation;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckTimeStr() {
		return checkTimeStr;
	}

	public void setCheckTimeStr(String checkTimeStr) {
		this.checkTimeStr = checkTimeStr;
	}

	public long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(long assetsId) {
		this.assetsId = assetsId;
	}
	
	

}
