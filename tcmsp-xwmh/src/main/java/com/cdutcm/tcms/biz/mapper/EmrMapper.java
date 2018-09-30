package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.PatientRegist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmrMapper {
	/*查询医生诊治的病人列表*/
	List<Emr> listPagefindEmrBydoctorId(Emr record);
	/*	查看病人信息详细*/
	Emr selectEmrAndPatient(Long id);
	
	int deleteByPrimaryKey(Long id);

	int insert(Emr record);

	int insertSelective(Emr record);

	Emr selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Emr record);

	int updateByPrimaryKey(Emr record);

	List<Emr> listPagefindEmrByNo(Emr record);
	
	int deleteEmrAndRecipel(long id);
	
	List<Emr> findAllEmr();
	/**
	 * 通过病人卡号查询病例
	 * @param patientNo
	 * @return
	 */
	Emr selectByPatientNo(String patientNo);
	List<Emr> countDisease(String userId);
	/**
	 * 计数与分页
	 */
	int  totalCount(@Param(value = "key1") String keyWord_1, @Param(value = "key2") String keyWord_2, @Param(value = "key3") String keyWord_3);//计算总数
	List<Emr> pageEmr(Page page);
	
	
	List<Emr> selectIdByDisease(String Disease);
}