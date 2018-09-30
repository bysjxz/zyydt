package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.Dict;

public interface DictMapper {

	Dict selectByPrimaryKey(Long id);
	
	List<Dict> selectByPid(Long pid);
	
	List<Dict> listPageDict(Dict record);
	
	int deleteByPrimaryKey(Long id);
	
	int insert(Dict record);
	
	int updateByPrimaryKey(Dict record);
	
	int editname(Dict record);
	
	List<Dict> selectForCheck(Dict record);
}
