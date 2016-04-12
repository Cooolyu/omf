package com.gmsz.om.web.thirdpart.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.gmsz.om.common.beans.Assets;
import com.gmsz.om.common.constant.StateDefine;
import com.gmsz.om.common.utils.Constant;
import com.gmsz.om.common.utils.TimeUtil;
import com.gmsz.om.web.assets.dao.AssetsMapper;
import com.gmsz.om.web.checkinHistory.dao.CheckinHistoryMapper;
import com.gmsz.om.web.dictionary.dao.DictionaryMapper;

@Component
public class ELIDDoorServiceImp implements ELIDDoorService {
	
	private static final Logger infoLogger = Logger.getLogger(StateDefine.INFO_LOG);
	private static final Logger sysLogger = Logger.getLogger(StateDefine.SYS_LOG);
	
	public static String Session_Code = "";
	
	@Resource
	private DictionaryMapper dictionaryMapper;
	
	@Resource
	private AssetsMapper assetsMapper;
	
	@Resource
	private CheckinHistoryMapper checkMapper;

	@Override
	public String getStatus(Assets assets) {
		

		
		String status = "open";
		String uploadStstus = "1";
		if(this.Session_Code.equals("")) {
			Boolean iflogin =  this.login();
			if(!iflogin) {
				infoLogger.error("------ELIDDoorServiceImp.getStatus login failed-------");
				return "failure";
			}
		}
		byte[] retVal = new byte[20];
		long dicId = this.dictionaryMapper.getDictionaryIdByCode("doorCode");
		Map<String,Long> param = new HashMap<>();
		param.put("assetsId", assets.getId());
		param.put("dicId", dicId);
		String doorCode = this.assetsMapper.getAssetsPropByDictionay(param);
		int resStatus = ElidSDK.INSTANCE.WPI_DoorStatus(getByte(Session_Code.substring(5, 13)),getByte(doorCode), retVal);
		String statusStr = new String(retVal).trim();
		if(resStatus == 0) {
			if(statusStr.equals("0002")) {
				this.login();
				infoLogger.error("------ELIDDoorServiceImp.getStatus ElidSDK.INSTANCE.WPI_DoorStatus return 0002-------");
				return "failure";
			}else if(statusStr.equals("0003")) {
				infoLogger.error("------ELIDDoorServiceImp.getStatus ElidSDK.INSTANCE.WPI_DoorStatus return 0003-------");
				sysLogger.error("门禁编号有误");
				return "failure";
			}else {
				if("C".equals(statusStr.substring(6, 7))) {
					status = "close";
				}
				if("A".equals(statusStr.substring(7, 8))) {
					uploadStstus = "2";
					status = "warn";
				}
				if("D".equals(statusStr.substring(8, 9))) {
					uploadStstus = "0";
					status = "warn";
				}
				String resString = "{'assetsId':'"+assets.getId()+"','SerialNumber':'"+assets.getSerialNumber()+"','status':'"+status+"','PointStatus':'"+uploadStstus+""
						+ "'}";
				return resString;
			}
		}
		return "failure";
	}

	@Override
	public String getHistory(Assets assets,String startTime) {
		infoLogger.info("------ELIDDoorServiceImp.getHistory start-------");
		if(this.Session_Code.equals("")) {
			Boolean iflogin =  this.login();
			if(!iflogin) {
				return "failure";
			}
		}
		String resString = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		String endTime = df.format(new Date());
		byte[] retVal = new byte[1024000];
		long dicId = this.dictionaryMapper.getDictionaryIdByCode("doorCode");
		Map<String,Long> param = new HashMap<>();
		param.put("assetsId", assets.getId());
		param.put("dicId", dicId);
		String doorCode = this.assetsMapper.getAssetsPropByDictionay(param);
		if(doorCode != null && !"".equals(doorCode)) {
			int resTran = ElidSDK.INSTANCE.WPI_GetDTrans(getByte(Session_Code.substring(5, 13)),getByte(doorCode),getByte(startTime),getByte(endTime),retVal);
			if(resTran == 0) {
				if("0002".equals(new String(retVal))) {
					this.login();
					return "failure";
				}else {
					String historyStr = new String(retVal);
					if(!"".equals(historyStr))
						resString = this.espHistoryStr(historyStr, assets);
				}
			}else {
				return "failure";
			}
		}
		return resString;
	}
	
	public String espHistoryStr(String str,Assets assets) {
		JSONArray array = new JSONArray();
		Map<String,String> resMap = new HashMap<>();
		resMap.put("0", "刷卡进入");
		resMap.put("5", "脉冲开门");
		int count = Integer.parseInt(str.substring(0, 10));
		if(count == 0)
			return "";
		for(int i = 0;i<count;i++) {
			JSONObject obj = new JSONObject();
			String checkTime = str.substring(i*19+10,i*19+14) + "-" + str.substring(i*19+14,i*19+16) + "-" + str.substring(i*19+16,i*19+18)
					+ " " + str.substring(i*19+18,i*19+20) + ":" + str.substring(i*19+20,i*19+22);
			String carId = str.substring(i*19+22,i*19+28);
			String name = "";
			if(!"      ".equals(carId))
				name = this.getCardName(carId);
			obj.put("assetsId", assets.getId());
			obj.put("checkTime", checkTime);
			obj.put("userName", name);
			array.put(obj);
		}
		return array.toString();
	}
	
	
	
	public String getCardName(String card) {
		String name = "";
		if(this.Session_Code.equals("")) {
			Boolean iflogin =  this.login();
			if(!iflogin) {
				return "failure";
			}
		}
		byte[] retVal = new byte[20];
		int resname = ElidSDK.INSTANCE.WPI_GetCName(getByte(Session_Code.substring(5, 13)), getByte(card), retVal);
		if(resname == 0) {
			if("0002".equals(new String(retVal))) {
				this.login();
				this.getCardName(card);
			}else {
				try {
					name = new String(retVal,"GBK");
					name = name.substring(5,name.length()-1);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return name;
	}
	
	public Boolean login() {
		infoLogger.info("---------WPI_Login start-------------");
		byte[] retVal = new byte[1024];
		String ps = Constant.ElidSDK;
		int res = ElidSDK.INSTANCE.WPI_Init(getByte(ps),retVal);
		byte[] scode = new byte[1024];
		int  reslog = ElidSDK.INSTANCE.WPI_Login(getByte(Constant.loginname), getByte(Constant.password), scode);
		if ("0001".equals(new String(scode)) ) {
			infoLogger.info("---------Invalid ID/Password-------------");
			return false;
		} else if ("0002".equals(new String(scode))) {
			infoLogger.info("---------No license detected-------------");
			return false;
		} else {
			try {
				Field f = this.getClass().getDeclaredField("Session_Code");
				f.set(this, new String(scode));
				return true;
			} catch (NoSuchFieldException | SecurityException e) {
				sysLogger.error(e);
			} catch (IllegalArgumentException e) {
				sysLogger.error(e);
			} catch (IllegalAccessException e) {
				sysLogger.error(e);
			}
		}
		return false;
	}
	
	public static byte[] getByte(String str){
		String resString = str+"@";
		byte[] res = resString.getBytes();
		res[resString.length() -1] = 0;
		return res;
	}
	
	public static void main(String[] args) {
		byte[] retVal = new byte[1024];
		byte[] retVal2 = new byte[1024];
		byte[] retVal3 = new byte[1024];
		byte[] retVal4 = new byte[1024];
		byte[] retVal5 = new byte[1024];
		byte[] retVal6 = new byte[1024];
		String ps = "C:\\temp1\\WinPro\\@";
		byte[] path = ps.getBytes();
		String uname = "wpai@";
		String psw = "12345678@";
		byte[] username = uname.getBytes();
		byte[] paw = psw.getBytes();
		username[uname.length() -1] =0;
		paw[psw.length() - 1] = 0;
		path[ps.length() -1] = 0;
		int res = ElidSDK.INSTANCE.WPI_Init(path,retVal);
		byte[] scode = new byte[14];
		int reslog = ElidSDK.INSTANCE.WPI_Login(username,paw, scode);
		String ssesion = new String(scode);
		int resname = ElidSDK.INSTANCE.WPI_GetCName(getByte(ssesion.substring(5, 13)), getByte("064530"), retVal2);
		int resTran = ElidSDK.INSTANCE.WPI_GetDTrans(getByte(ssesion.substring(5, 13)),getByte("1"),getByte("201509090000"),getByte("201509101300"),retVal);
		int resStatus = ElidSDK.INSTANCE.WPI_DoorStatus(getByte(ssesion.substring(5, 13)),getByte("1"), retVal3);
		int cards = ElidSDK.INSTANCE.WPI_GetDCards(getByte(ssesion.substring(5, 13)), getByte("1"), retVal4);
		int unlock = ElidSDK.INSTANCE.WPI_DUnlock(getByte(ssesion.substring(5, 13)), getByte("1"), retVal5);
		int alarm = ElidSDK.INSTANCE.WPI_GetAlarmTrans(getByte(ssesion.substring(5, 13)),getByte("1"),getByte("201509090000"),getByte("201509101300"),retVal6);
		System.out.println(res);
		System.out.println("retVal:"+new String(retVal));
		try {
			System.out.println("retVal2:"+new String(retVal2,"GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("retVal3:"+new String(retVal3));
		System.out.println("retVal4:"+new String(retVal4));
		System.out.println("retVal5:"+new String(retVal5));
		System.out.println("retVal6:"+new String(retVal6));
		System.out.println(resStatus);
		System.out.println(new String(scode));
	}

}
