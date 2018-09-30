package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.model.PatientRegist;

public interface EmrService {
	
	List<Emr> listPagefindEmrBydoctorId(Emr record);
	
	Emr selectEmrAndPatient(Long id);
	
	int deleteByPrimaryKey(Long id);

	int insert(Emr record);

	int insertSelective(Emr record);

	Emr selectByPrimaryKey(Long id);
	
	Emr selectByPatientNo(String patientNo);

	int updateByPrimaryKeySelective(Emr record);

	int updateByPrimaryKey(Emr record);

	List<Emr> listPagefindEmrByNo(Emr record);
	
	int deleteEmrAndRecipel(long id);
	
    List<Emr> findAllEmr();

    List<Emr>countByDisease(String userId);

	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param key1
	 * @param key2
	 * @param key3
	 * @return
	 */
	Page<Emr> pageEmr(int currentPage, int pageSize, String key1, String key2, String key3);
	
	List<Emr> selectIdByDisease(String Disease);
}
