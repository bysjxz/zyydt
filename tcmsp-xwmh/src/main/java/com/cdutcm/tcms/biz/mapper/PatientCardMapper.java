package com.cdutcm.tcms.biz.mapper;


import java.util.List;

import com.cdutcm.tcms.biz.model.PatientCard;

public interface PatientCardMapper {

	int deleteByPrimaryKey(Long id);
	
	int insert(PatientCard record);
	
	PatientCard selectByPrimaryKey(Long id);
	
	int updateByPrimaryKey(PatientCard record);
	
	List<PatientCard> listPagePatientCard(PatientCard record);
	
	PatientCard selectBySelective(Long id);
}
