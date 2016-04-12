package com.gmsz.om.web.thirdpart.service;

import com.gmsz.om.common.beans.Assets;

public interface ELIDDoorService{
	
	public String getStatus(Assets assets);
	
	public String getHistory(Assets assets,String startTime);

}
