package com.cdutcm.tcms.biz.mapper;

import com.cdutcm.tcms.biz.model.BaseRecipelItem;

import java.util.List;

public interface BaseRecipelItemMapper {
    /**
     * 通过病名查询相应的推荐处方
     * @param recipelId
     * @return
     */
    List<BaseRecipelItem> findBaseRecipelItemByRecipelId(Long recipelId);

    int deleteByPrimaryKey(Long id);

    int insert(BaseRecipelItem item);

    int insertSelective(BaseRecipelItem record);

    BaseRecipelItem selectByPrimaryKey(Long id);
    
    List<BaseRecipelItem>  listBRItem(Long id);
    
    int updateByPrimaryKeySelective(BaseRecipelItem record);

    int updateByPrimaryKey(BaseRecipelItem record);
    
    int replaceByPrimaryKey(BaseRecipelItem record);
}