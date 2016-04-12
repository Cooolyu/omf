package com.gmsz.om.web.assets.dao;

import java.util.List;
import java.util.Map;

import com.gmsz.om.common.beans.Assets;

public interface AssetsMapper {
	
	public String getClickServiceNameByAssetsId (Long assetsId);
	
	public String getStatusServiceNameByAssetsId (Long assetsId);
	
	public List<Assets> getAssetsByCategory(Long categoryId);
	
	public Long getAssetsId(Map<String, Object> map);
	
	public Assets getAssetsById(Long assetsId);
	
	public String getAssetsPropByDictionay(Map<String, Long> param);
	
	public long getAssetsByProp(Map<String, String> param);

}
