package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.model.PatientRegist;
import org.apache.ibatis.annotations.Param;

public interface PatientRegistMapper {

	int deleteByPrimaryKey(Long id);

	int insert(PatientRegist record);

	int insertSelective(PatientRegist record);

	PatientRegist selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(PatientRegist record);

	int updateByPrimaryKey(PatientRegist record);

	/** 根据visit_no删除0000 */
	int deletePatientRegistByVisitNo(String visitNo);

	List<PatientRegist> listPagePatientByPatientRegist(PatientRegist P);
	int  totalCount(@Param(value = "key1") String keyWord_1,@Param(value = "key2")String keyWord_2,@Param(value = "key3")String keyWord_3);//计算总数
	List<PatientRegist> pagePatientRegist(Page page);

	/**
	 * 根据诊断状态计算相应疾病状态的总数
	 */
	int totalCountByStatus(@Param(value = "status") String status,@Param(value = "userId")String userId);
	/**
	 * 根据诊断状态筛选出相应的挂号单
	 */
	List<PatientRegist> selectByStatus(@Param(value = "status") String status,@Param(value = "userId")String userId);
}
