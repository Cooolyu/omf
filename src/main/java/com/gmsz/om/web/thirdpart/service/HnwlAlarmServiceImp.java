package com.gmsz.om.web.thirdpart.service;

import org.apache.log4j.Logger;

import com.gmsz.om.common.beans.UdpMsgBean;
import com.gmsz.om.common.constant.StateDefine;
import com.gmsz.om.common.utils.Constant;
import com.gmsz.om.scoket.udp.UdpClient;

public class HnwlAlarmServiceImp implements HnwlAalarmService {

	private static final Logger infoLogger = Logger.getLogger(StateDefine.INFO_LOG);
	private static final Logger sysLogger = Logger.getLogger(StateDefine.SYS_LOG);
	@Override
	public String bufang(String value) {
		UdpClient client = new UdpClient();
		UdpMsgBean udpMsg = new UdpMsgBean();
		if("open".equals(value)) {
			byte[] order = {0x07,0x01,0x01,0x02,0x03,0x04,0x02,0x0b};
			udpMsg.setServerIp(Constant.HNWLIp);
			udpMsg.setServerPort(1868);
			udpMsg.setUdpMsgBytes(order);
		}
		if(("close").equals(value)) {
			byte[] order = {0x07,0x01,0x01,0x02,0x03,0x04,0x01,0x0b};
			udpMsg.setServerIp(Constant.HNWLIp);
			udpMsg.setServerPort(1868);
			udpMsg.setUdpMsgBytes(order);
		}
		
		try {
			sysLogger.info("### UDP SO_BROADCAST MSG ==> "+udpMsg);
			client.writeAndFlush(udpMsg);
		}  catch (Exception e) {
			sysLogger.error(e);
		}
		return "success";
	}

}
