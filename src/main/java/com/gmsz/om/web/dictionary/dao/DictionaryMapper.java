package com.gmsz.om.web.dictionary.dao;

import java.util.Map;

import com.gmsz.om.common.beans.Dictionary;

public interface DictionaryMapper {
	
	public Long getDictionaryIdByCode(String code);
	
	public Dictionary getDict(Long id);

}
