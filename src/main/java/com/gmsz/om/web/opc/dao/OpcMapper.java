package com.gmsz.om.web.opc.dao;

import java.util.List;

import com.gmsz.om.common.beans.MonitorStatus;
import com.gmsz.om.common.beans.OpcMap;
import com.gmsz.om.common.beans.ServerConf;
import com.gmsz.om.common.beans.UploadStatus;
import com.gmsz.om.web.opc.bean.OpcResult;

public interface OpcMapper {
	
	public List<ServerConf> queryServers();
	
	public List<OpcResult> queryOpcResult(OpcMap opc);
	
	public List<OpcMap> queryOpcMap(Long serverId);
	
	public Long getDictionaryIdByCode(String code);
	
	public Long updateStatus(MonitorStatus status);
	
	public void insertStatus(MonitorStatus status);
	
	public void insertUploadStatus(UploadStatus status);

}
