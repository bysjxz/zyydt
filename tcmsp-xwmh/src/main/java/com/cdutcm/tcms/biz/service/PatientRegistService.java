package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.DTO.EeveryStatusNum;
import com.cdutcm.tcms.biz.model.PatientRegist;

public interface PatientRegistService {
	int deleteByPrimaryKey(Long id);

	int insert(PatientRegist record);

	int insertSelective(PatientRegist record);

	PatientRegist selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(PatientRegist record);

	int updateByPrimaryKey(PatientRegist record);

	/** 根据visit_no删除 */
	int deletePatientRegistByVisitNo(String visitNo);

	List<PatientRegist> listPagePatientByPatientRegist(PatientRegist P);

	//分页或者根据查询结果分页
	Page<PatientRegist>pageRegist(int currentPage, int pageSize, String key1, String key2,String key3);

	/**
	 * @param 计算所有挂号信息状态的数量
	 * @return
	 */
	EeveryStatusNum totalNumByStatus(String userId);

	List<PatientRegist> selectByStatus(String status,String userId);
}