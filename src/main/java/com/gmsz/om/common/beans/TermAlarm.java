/*
 *******************************************************************************
 * All rights Reserved, Copyright (C) www.gm-sz.com 2014
 * FileName: MonitorPoint.java
 * Modify record:
 * NO. |     Date       |    Version      |    Name         |      Content
 * 1   | 2014年6月6日        |      1.0        | GMSZ)ZhouYunlong | original version
 *******************************************************************************
 */
package com.gmsz.om.common.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 终端报警
 * @author zhouyunlong
 *
 */
public class TermAlarm {
	/**
	 * 1：系统报警
	 */
	public static final int OBTAINPATWAY_SYSTEM = 1;
	/**
	 * 2：客户报修 
	 */
	public static final int OBTAINPATWAY_CUSTOMER = 2;
	/**
	 * 3：巡检发现
	 */
	public static final int OBTAINPATWAY_REGULAR_CHECK = 3;
	/**
	 * 4：维护发现
	 */
	public static final int OBTAINPATWAY_REPAIR_CHECK = 4;
	
	public static final Map<Integer,String> obtainpatwayMap = new HashMap<Integer,String>(){
		private static final long serialVersionUID = 7554309343270607022L;
	{
	    put(OBTAINPATWAY_SYSTEM, "系统报警");
	    put(OBTAINPATWAY_CUSTOMER, "客户报修");
	    put(OBTAINPATWAY_REGULAR_CHECK, "巡检发现");
	    put(OBTAINPATWAY_REPAIR_CHECK, "维护发现");
	}};
	
	public static final Map<Integer,String> obtainpatwayMapActor = new HashMap<Integer,String>(){
		private static final long serialVersionUID = 1L;

	{
	    put(OBTAINPATWAY_REGULAR_CHECK, "巡检发现");
	    put(OBTAINPATWAY_REPAIR_CHECK, "维护发现");
	}};
	
	/**
	 * 报警级别（高）
	 */
	public static final int ALARM_LEVEL_HIGHT = 1;
	/**
	 * 报警级别（中）
	 */
	public static final int ALARM_LEVEL_MIDDLE = 2;
	/**
	 * 报警级别（低）
	 */
	public static final int ALARM_LEVEL_LOW = 3;
	
	public static final Map<Integer,String> alarmLeveMap = new HashMap<Integer,String>(){
		private static final long serialVersionUID = 2336027209986238904L;
	{
	    put(ALARM_LEVEL_HIGHT, "高");
	    put(ALARM_LEVEL_MIDDLE, "中");
	    put(ALARM_LEVEL_LOW, "低");
	}};
	
	/**
	 * 自动恢复(未恢复) 
	 */
	public static final int AUTORECOVERY_UNRECOVERY = 1;
	/**
	 * 自动恢复(恢复) 
	 */
	public static final int AUTORECOVERY_RECOVERY = 2;
	/**
	 * 1：未确认
	 */
	public static final int STATUS_WAITING_FOR_MAINTAIN = 1;
	/**
	 * 2：已确认
	 */
	public static final int STATUS_OVER = 2;
	
	
	public static final int SEND_STATUS_UNSEND = 1;
	
	public static final int SEND_STATUS_SENDING = 2;
	
	public static final int SEND_STATUS_SENDED = 3;
	
	
	public static final Map<Integer,String> statusAlarmMap = new HashMap<Integer,String>(){
		private static final long serialVersionUID = -4056972368037030534L;
	{
	    put(STATUS_WAITING_FOR_MAINTAIN, "未确认");
	    put(STATUS_OVER, "已确认");
	}};
	
	private long id;
	private long monitorPointId;
	private Date alarmTime;  
	private int obtainPathway;   
	private Long categoryId;                              
	private int alarmLevel;     
	private String alarmSource;                                     
	private int autoRecovery;
	private Date recoveryTime;                                   
	private String content;                         
	private String reportUser;                                   
	private Date reportTime;                                       
	private String reportUserPhone;                                   
	private int status;
	private String watchPoint;
	private Long assetsId;
	private int sendStatus;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getMonitorPointId() {
		return monitorPointId;
	}
	public void setMonitorPointId(long monitorPointId) {
		this.monitorPointId = monitorPointId;
	}
	public Date getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	public int getObtainPathway() {
		return obtainPathway;
	}
	public void setObtainPathway(int obtainPathway) {
		this.obtainPathway = obtainPathway;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public int getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getAlarmSource() {
		return alarmSource;
	}
	public void setAlarmSource(String alarmSource) {
		this.alarmSource = alarmSource;
	}
	public int getAutoRecovery() {
		return autoRecovery;
	}
	public void setAutoRecovery(int autoRecovery) {
		this.autoRecovery = autoRecovery;
	}
	public Date getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReportUser() {
		return reportUser;
	}
	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportUserPhone() {
		return reportUserPhone;
	}
	public void setReportUserPhone(String reportUserPhone) {
		this.reportUserPhone = reportUserPhone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getWatchPoint() {
		return watchPoint;
	}
	public void setWatchPoint(String watchPoint) {
		this.watchPoint = watchPoint;
	}
	public Long getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}
	public int getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	
}
