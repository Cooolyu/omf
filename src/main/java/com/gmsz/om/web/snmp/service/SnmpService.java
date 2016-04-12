package com.gmsz.om.web.snmp.service;

public interface SnmpService {
	/**
	 * 获取资产的监控json
	 * @param assetsId
	 * @return
	 */
	public String getAssetsMonitor(Long assetsId);

}
