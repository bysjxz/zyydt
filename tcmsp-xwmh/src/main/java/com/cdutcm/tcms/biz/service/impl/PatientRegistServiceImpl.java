package com.cdutcm.tcms.biz.service.impl;


import java.util.List;
import com.cdutcm.core.page2.Page;
import com.cdutcm.core.page2.PageUtil;
import com.cdutcm.core.util.DateUtil;
import com.cdutcm.tcms.biz.DTO.EeveryStatusNum;
import com.cdutcm.tcms.biz.DTO.PatientRegistDTO;
import com.cdutcm.tcms.biz.constant.StatusConstant;
import com.cdutcm.tcms.biz.convert.PatientRegist2PatinetRegistDTO;
import com.cdutcm.tcms.biz.mapper.PatientMapper;
import com.cdutcm.tcms.biz.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.PatientRegistMapper;
import com.cdutcm.tcms.biz.model.PatientRegist;
import com.cdutcm.tcms.biz.service.PatientRegistService;

@Service
public class PatientRegistServiceImpl implements PatientRegistService {
	@Autowired
	private PatientRegistMapper patientRegistMapper;
	@Override
	public List<PatientRegist> listPagePatientByPatientRegist(PatientRegist P) {
		// TODO Auto-generated method stub
		return patientRegistMapper.listPagePatientByPatientRegist(P);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return patientRegistMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(PatientRegist record) {
		// TODO Auto-generated method stub
		return patientRegistMapper.insert(record);
	}
	@Override
	public int insertSelective(PatientRegist record) {
		// TODO Auto-generated method stub
		return patientRegistMapper.insertSelective(record);
	}
	@Override
	public PatientRegist selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return patientRegistMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(PatientRegist record) {
		// TODO Auto-generated method stub
		return patientRegistMapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public int updateByPrimaryKey(PatientRegist record) {
		// TODO Auto-generated method stub
		return patientRegistMapper.updateByPrimaryKey(record);
	}
	@Override
	public int deletePatientRegistByVisitNo(String visitNo) {
		// TODO Auto-generated method stub
		return patientRegistMapper.deletePatientRegistByVisitNo(visitNo);
	}

	/**
	 * @Author :Mxq
	 */

	@Autowired
	private PatientMapper patientMapper;
	@Override
	public Page<PatientRegist> pageRegist(int currentPage, int pageSize,String key1,String key2,String key3) {
		Page page = PageUtil.queryPage(currentPage,patientRegistMapper.totalCount(key1,key2,key3),pageSize);
		page.setKeyWord_1(key1);//搜索关键字
		page.setKeyWord_2(key2);//病人转态关键字
		page.setKeyWord_3(key3);//医生的编号
		List<PatientRegist>regists = patientRegistMapper.pagePatientRegist(page);
		//regist转换为数据传输对象
		List<PatientRegistDTO>registDTOS = PatientRegist2PatinetRegistDTO.convert(regists);
		//遍历病人挂号信息
		for (PatientRegistDTO dto:registDTOS){

			//查出病人信息后，将生日转换年龄
			Patient patient  = patientMapper.selectByPatientNo(dto.getPatientNo());
			patient.setAge(DateUtil.getAge(patient.getBirthday()));
			//查询病人基本信息
			dto.setBasicInfo(patient);
			//查询病历信息
			//TODO
//            dto.setEmrs(emrMapper.selectByPatientNo(dto.getPatientNo()));
		}
		page.setList(registDTOS);
		return page;
	}

	@Override
	public EeveryStatusNum totalNumByStatus(String userId) {
		int booking = patientRegistMapper.totalCountByStatus(StatusConstant.BOOKING,userId);
		int waiting = patientRegistMapper.totalCountByStatus(StatusConstant.WAITING,userId);
		int doing  = patientRegistMapper.totalCountByStatus(StatusConstant.DOING,userId);
		EeveryStatusNum everyStatusNum = new EeveryStatusNum(booking,waiting,doing);
		return everyStatusNum;
	}

	@Override
	public List<PatientRegist> selectByStatus(String status,String userId) {
		return patientRegistMapper.selectByStatus(status,userId);
	}
}