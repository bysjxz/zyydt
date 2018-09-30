package com.cdutcm.tcms.biz.mapper;

import com.cdutcm.tcms.biz.model.BaseRecipel;

import java.util.List;

public interface BaseRecipelMapper {

	Long findIdByName(String name);
	
	List<BaseRecipel> selectBySelective(BaseRecipel baserecipel);
	
	List<BaseRecipel> listPageBaseRecipel(BaseRecipel baserecipel);
	
	int deleteByPrimaryKey(Long id);

	int insert(BaseRecipel br);

	int insertSelective(BaseRecipel record);

	BaseRecipel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BaseRecipel record);

	int updateByPrimaryKey(BaseRecipel record);

	List<BaseRecipel> listPagefindBaseRecipelByType(BaseRecipel record);

	BaseRecipel listPagefindBaseRecipelById(BaseRecipel record);

	List<BaseRecipel> findBaseRecipel(BaseRecipel record);
    
	BaseRecipel getCountByName (BaseRecipel record);
}