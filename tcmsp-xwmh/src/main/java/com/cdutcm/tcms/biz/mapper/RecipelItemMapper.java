package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdutcm.tcms.DTO.RecipelDTO;
import com.cdutcm.tcms.biz.model.RecipelItem;

public interface RecipelItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RecipelItem record);
    
    int insertDTO(RecipelDTO record);

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