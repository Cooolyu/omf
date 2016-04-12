package com.gmsz.om.web.opc.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.gmsz.om.common.beans.ServerConf;
import com.gmsz.om.web.opc.dao.OpcMapper;

public class OpcServer {
	
	@Resource
	private OpcMapper opcMapper;
	
	@PostConstruct
	public void init() {
		List<ServerConf> serverList = this.opcMapper.queryServers();
		for(ServerConf server : serverList) {
			SCADAThread thread = new SCADAThread(this.opcMapper);
			thread.setClsId(server.getClsId());
			thread.setHost(server.getHost());
			thread.setServerId(server.getId());
			thread.setUserName(server.getUserName());
			thread.setPassword(server.getPassword());
			new Thread(thread).start();
		}
	}

}
