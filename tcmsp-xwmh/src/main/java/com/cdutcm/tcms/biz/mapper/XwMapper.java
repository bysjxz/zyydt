package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.Xw;

public interface XwMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Xw record);

	int insertSelective(Xw record);

	Xw selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Xw record);

	int updateByPrimaryKey(Xw record);

	List<Xw> findXwByNameOrPinYin(Xw record);
}