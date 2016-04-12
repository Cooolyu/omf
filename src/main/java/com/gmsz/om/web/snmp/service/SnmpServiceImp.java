package com.gmsz.om.web.snmp.service;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.gmsz.om.web.snmp.bean.SnmpResultForm;
import com.gmsz.om.web.snmp.dao.SnmpMapper;

@Component
public class SnmpServiceImp implements SnmpService {
	
	@Resource
	private SnmpMapper snmpMapper;

	@Override
	public String getAssetsMonitor(Long assetsId) {
		JSONObject object = new JSONObject();
		List<SnmpResultForm> resultList = this.snmpMapper.querySnmpPropByAssets(assetsId);
		for(SnmpResultForm res : resultList) {
			
		}
		return object.toString();
	}
	 

}
