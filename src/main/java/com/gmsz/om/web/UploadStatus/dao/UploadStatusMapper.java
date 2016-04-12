package com.gmsz.om.web.UploadStatus.dao;

import java.util.List;

import com.gmsz.om.common.beans.UploadStatus;

public interface UploadStatusMapper {
	
	public long insertStatus(UploadStatus status);
	
	public long deleteStatus(UploadStatus status);
	
	public int queryUnsendCount();
	
	public long updateStatus(UploadStatus status);
	
	public List<UploadStatus> findLasetestStatusList();
	
	public List<UploadStatus> querySendingStatus();
	
	public void deleteData(String dateString);

}
