package com.gmsz.om.common.beans;

import java.util.Date;

public class UploadStatus {

	private long id;
	
	private Date addTime;
	
	private String uploadJsonStr;
	
	private long status;
	
	private String ifType;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getUploadJsonStr() {
		return uploadJsonStr;
	}
	public void setUploadJsonStr(String uploadJsonStr) {
		this.uploadJsonStr = uploadJsonStr;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	
	public String getIfType() {
		return ifType;
	}
	public void setIfType(String ifType) {
		this.ifType = ifType;
	}

	public static final long STATUS_NOT_SEND = 1L;
	public static final long STATUS_SENDING = 2L;
}
