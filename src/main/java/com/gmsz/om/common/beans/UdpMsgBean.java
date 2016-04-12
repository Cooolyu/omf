package com.gmsz.om.common.beans;

public class UdpMsgBean {
	
	public String serverIp;
	
	public byte[] udpMsgBytes;
	
	public Integer serverPort;

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public byte[] getUdpMsgBytes() {
		return udpMsgBytes;
	}

	public void setUdpMsgBytes(byte[] udpMsgBytes) {
		this.udpMsgBytes = udpMsgBytes;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	
	

}
