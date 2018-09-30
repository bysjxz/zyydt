package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.biz.model.Dict;

public interface DictService {

	Dict selectByPrimaryKey(Long id);
	
	List<Dict> selectByPid(Long pid);
	
	List<Dict> listPageDict(Dict record);
	
	SysMsg deleteByPrimaryKey(Long id);
	
	int insert(Dict record);
	
	int updateByPrimaryKey(Dict record);
	
	SysMsg editname(Dict record);
	
	List<Dict> selectForCheck(Dict record);
}
