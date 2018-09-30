package com.cdutcm.tcms.biz.mapper;

import com.cdutcm.tcms.biz.model.IllnessHistory;

public interface IllnessHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IllnessHistory record);

    int insertSelective(IllnessHistory record);

    IllnessHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IllnessHistory record);

    int updateByPrimaryKey(IllnessHistory record);
}