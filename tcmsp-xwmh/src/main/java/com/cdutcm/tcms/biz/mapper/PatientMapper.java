package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Patient record);

	int insertSelective(Patient record);

	Patient selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Patient record);

	int updateByPrimaryKey(Patient record);

	List<Patient> listPagePatients(Patient patient);

	List<Patient> findPatientsByNo(Patient patient);
	
	/**
	 * 通过病人卡号查询挂号信息
	 * @param patientNo
	 * @return
	 */
	List<Patient> findPatientsByNo2(String patientNo);
	
	/**
	 * 	通过病人卡号和挂号号码查询病人 patient表
	 *  @param patient
	 *  @return
	 */
	Patient findPatientsByNo3(Patient patient);

	/**
	 * 计数
	 */
	Patient selectByPatientNo(String No);
}