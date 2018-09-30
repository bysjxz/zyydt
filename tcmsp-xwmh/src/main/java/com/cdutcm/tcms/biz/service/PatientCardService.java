package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.biz.model.PatientCard;

public interface PatientCardService {

	SysMsg deleteByPrimaryKey(Long id);
	
	SysMsg insert(PatientCard record);
	
	PatientCard selectByPrimaryKey(Long id);
	
	SysMsg updateByPrimaryKey(PatientCard record);
	
	List<PatientCard> listPagePatientCard(PatientCard record);
	
	PatientCard selectBySelective(Long id);
}
