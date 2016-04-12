package com.gmsz.om.web.snmp.dao;

import java.util.List;

import com.gmsz.om.common.beans.SnmpRecord;
import com.gmsz.om.web.snmp.bean.SnmpResultForm;

public interface SnmpMapper {
	
	public List<SnmpResultForm> querySnmpPropByAssets(Long assetsId);
	
	public void insertRecord(SnmpRecord record);

}
