package com.gmsz.om.web.opc.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jinterop.dcom.common.JIException;
import org.json.JSONObject;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;

import com.gmsz.om.common.beans.MonitorStatus;
import com.gmsz.om.common.beans.OpcMap;
import com.gmsz.om.common.beans.UploadStatus;
import com.gmsz.om.common.utils.Constant;
import com.gmsz.om.web.opc.bean.OpcResult;
import com.gmsz.om.web.opc.dao.OpcMapper;


public class DataCallbackDumper implements DataCallback{
	
	private OpcMapper opcMapper;
	
	private OpcMap opcMap;
	
	public DataCallbackDumper(OpcMapper opcMapper,OpcMap opcMap){
		this.opcMapper = opcMapper;
		this.opcMap = opcMap;
	}

	@Override
	public void changed(Item item, ItemState arg1) {
		List<OpcResult> resultList = this.opcMapper.queryOpcResult(opcMap);
		for(OpcResult result : resultList) {
			JSONObject obj = new JSONObject();
			obj.put("MonitorPointId", Constant.monitorPoint);
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			obj.put("AddTime", df.format(new Date()));
			obj.put("serialNumber", result.getSerialNumber());

			String value = arg1.getValue().toString();
			value = value.substring(2, value.length()-2);
			obj.put(result.getMonitorCode(), value);
			String uploadJsonStr = obj.toString();
			UploadStatus status = new UploadStatus();
			status.setAddTime(new Date());
			status.setStatus(UploadStatus.STATUS_NOT_SEND);
			status.setUploadJsonStr(uploadJsonStr);
			status.setIfType("/information/status/push");
			this.opcMapper.insertUploadStatus(status);

			MonitorStatus mstatus = new MonitorStatus();
			Long dicId = this.opcMapper.getDictionaryIdByCode(result
					.getMonitorCode());
			mstatus.setDictionaryId(dicId);
			mstatus.setAssetsId(result.getAssetsId());
			mstatus.setCurStatus(value);
			mstatus.setCreateTime(new Date());
			Long count = this.opcMapper.updateStatus(mstatus);
			if (count < 1) {
				this.opcMapper.insertStatus(mstatus);
			}
		}
		
	}
	

}
