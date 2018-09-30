package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;

public interface RecipelService {
	int deleteByPrimaryKey(Long id);

	int insert(Recipel record);

	int insertSelective(Recipel record);

	Recipel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Recipel record);

	int updateByPrimaryKey(Recipel record);

	List<Recipel> listPagefindRecipelById(Recipel record);
	
	int delRecipelAndRecipelItem(long id);
	
	Recipel selectIdByEmrId(Long emrId);

}
