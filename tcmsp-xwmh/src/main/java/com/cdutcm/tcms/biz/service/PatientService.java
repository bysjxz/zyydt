package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.tcms.biz.model.Patient;

public interface PatientService {
	int deleteByPrimaryKey(Long id);

	int insert(Patient record);

	int insertSelective(Patient record);

	Patient selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Patient record);

	int updateByPrimaryKey(Patient record);

	List<Patient> listPagePatients(Patient patient);

	List<Patient> findPatientsByNo(Patient patient);
	
	List<Patient> findPatientsByNo2(String patientNo);
	
	Patient findPatientsByNo3(Patient patient);
	Patient selectByPatientNo(String patintNo);
}
