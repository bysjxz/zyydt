package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.tcms.DTO.RecipelDTO;
import com.cdutcm.tcms.biz.model.RecipelItem;

public interface RecipelItemService {
	int deleteByPrimaryEmrId(Long id);

	int insert(RecipelItem record);
	
	int insert(RecipelDTO record);

	int insertSelective(RecipelItem record);

	RecipelItem selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RecipelItem record);

	int updateByPrimaryKey(RecipelItem record);
	
	int updateByPrimaryKeyDTO(RecipelDTO record);
	
    int deleteByPrimaryRecipelId(long id);
    
    List<String> findCtypeByRecipelId (RecipelItem record);
    
    List<RecipelItem> findRecipelItemByCtypeAndRecipelId(RecipelItem record);
    
   //查询处方明细
  	List<RecipelItem> findRecipelItemByRecipelId(Long recipelId);
  	
  	List<RecipelDTO> findRecipelDTOByRecipelId(Long recipelId);
}
