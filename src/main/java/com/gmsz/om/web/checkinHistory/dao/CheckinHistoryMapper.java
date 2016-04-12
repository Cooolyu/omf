package com.gmsz.om.web.checkinHistory.dao;

import java.util.Date;

import com.gmsz.om.common.beans.CheckinHistory;

public interface CheckinHistoryMapper {
	
	public void insertHistory(CheckinHistory check);
	
	public Date getLastTime();
	
	public void deleteOneMinute(String startTime);

}
