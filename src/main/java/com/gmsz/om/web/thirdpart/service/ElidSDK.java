package com.gmsz.om.web.thirdpart.service;

import com.gmsz.om.common.utils.Constant;
import com.sun.jna.Library;
import com.sun.jna.Native;

public interface ElidSDK extends Library{
	
	ElidSDK INSTANCE = (ElidSDK) Native.loadLibrary("C:\\temp1\\WinPro\\WPAPI.DLL",
			ElidSDK.class);
	
	public int WPI_Login(byte[] ID,byte[] PW,byte[] RetVal);
	
	public int WPI_DoorStatus(byte[] SCode,byte[] DoorTag,byte[] RetVal);
	
	public int WPI_GetDCards(byte[] SCode,byte[] DoorTag,byte[] RetVal);
	
	public int WPI_GetDTrans(byte[] Scode,byte[] DoorTag,byte[] StartDT,byte[] EndDT,byte[] RetVal);
	
	public int WPI_GetCName(byte[] SCode,byte[] cardNo,byte[] RetVal);
	
	public int WPI_SetCardAcc(byte[] Scode,byte[] CardNo,byte[] AccessTag,byte[] RetVal);
	
	public int WPI_DLock(byte[] Scode,byte[] DoorTag,byte[] RetVal);
	
	public int WPI_DUnlock(byte[] Scode,byte[] DoorTag,byte[] RetVal);
	
	public int WPI_DPulseOpen(byte[] Scode,byte[] DoorTag,byte[] RetVal);
	
	public int WPI_GetAlarmTrans(byte[] Scode,byte[] DoorTag,byte[] StartDT,byte[] EndDT,byte[] RetVal);
	
	public int WPI_Init(byte[] path,byte[] RetVal);
	
	

}	
