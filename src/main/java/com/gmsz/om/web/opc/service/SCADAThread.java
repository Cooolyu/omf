package com.gmsz.om.web.opc.service;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.AddFailedException;
import org.openscada.opc.lib.da.Async20Access;
import org.openscada.opc.lib.da.AutoReconnectController;
import org.openscada.opc.lib.da.DuplicateGroupException;
import org.openscada.opc.lib.da.Server;
import org.springframework.stereotype.Component;

import com.gmsz.om.common.beans.OpcMap;
import com.gmsz.om.web.opc.dao.OpcMapper;

public class SCADAThread implements Runnable{
	
	private String host;
	private String clsId;
	private String userName;
	private String password;
	private Long serverId;
	
	private OpcMapper opcMapper;
	
	public SCADAThread(OpcMapper opcMapper) {
		this.opcMapper = opcMapper;
	}
	
	

	@Override
	public void run() {
		AutoReconnectController autos = null;
		  try {
		   JISystem.setAutoRegisteration(true);
		    
		   ConnectionInformation ci = new ConnectionInformation();
		   ci.setHost(host);
		   ci.setDomain("");
		   ci.setClsid(clsId);
		   ci.setUser(userName);
		   ci.setPassword(password);
		    
		    
		    Server s = new Server(ci,Executors.newSingleThreadScheduledExecutor());
		         autos = new AutoReconnectController(s);
		   autos.connect();Thread.sleep(100);	    
		    
		   final AccessBase access = new Async20Access(s,100,false);
		   
		   List<OpcMap> mapList = this.opcMapper.queryOpcMap(serverId);
			for (OpcMap map : mapList) {
				access.addItem(map.getItemName(), new DataCallbackDumper(this.opcMapper,map));
			}
			access.bind();
			Thread.sleep(1000 * 3600 * 24 * 365 * 10);
			access.unbind();
		            
		 
		  } catch (IllegalArgumentException e) {
		   e.printStackTrace();
		  } catch (UnknownHostException e) {   
		   e.printStackTrace();
		  } catch (JIException e) {  
		   e.printStackTrace();
		  } catch (NotConnectedException e) {  
		   e.printStackTrace();
		  } catch (DuplicateGroupException e) {             
		   e.printStackTrace();
		  } catch (AddFailedException e) {
		   e.printStackTrace();
		  } catch (InterruptedException e) {
		   e.printStackTrace();
		  }finally{
		   autos.disconnect();
		  }  
		
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getClsId() {
		return clsId;
	}

	public void setClsId(String clsId) {
		this.clsId = clsId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	
	

}
