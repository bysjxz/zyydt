package com.cdutcm.tcms.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cdutcm.core.page2.Page;
import com.cdutcm.core.page2.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.EmrMapper;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.service.EmrService;

@Service
public class EmrServiceImpl implements EmrService {
	@Autowired
	private EmrMapper emrMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return emrMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.insert(record);
	}

	@Override
	public int insertSelective(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.insertSelective(record);
	}

	@Override
	public Emr selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return emrMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Emr> listPagefindEmrByNo(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.listPagefindEmrByNo(record);
	}

	@Override
	public int deleteEmrAndRecipel(long id) {
		// TODO Auto-generated method stub
		return emrMapper.deleteEmrAndRecipel(id);
	}

	@Override
	public List<Emr> findAllEmr() {
		// TODO Auto-generated method stub
		return emrMapper.findAllEmr();
	}
	@Override
	public Emr selectByPatientNo(String patientNo) {
		// TODO Auto-generated method stub
		return emrMapper.selectByPatientNo(patientNo);
	}

	@Override
	public List<Emr> listPagefindEmrBydoctorId(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.listPagefindEmrBydoctorId(record);
	}

	@Override
	public Emr selectEmrAndPatient(Long id) {
		// TODO Auto-generated method stub
		return emrMapper.selectEmrAndPatient(id);
	}

	/**
	 * 计算数量
	 * @return
	 */
	@Override
	public List<Emr> countByDisease(String userId) {
		List<Emr>emrList = emrMapper.countDisease(userId);
		int temp = emrList.size();
		List<Emr> new_emrList = new ArrayList<>();
		if (temp>=4){
			new_emrList = emrList.subList(0,4);
		}else {
			new_emrList = emrList.subList(0,temp);
		}
		return new_emrList;
	}

	@Override
	public Page<Emr> pageEmr(int currentPage, int pageSize, String key1, String key2, String key3) {
		Page page = PageUtil.queryPage(currentPage,emrMapper.totalCount(key1,key2,key3),pageSize);
		page.setKeyWord_1(key1);//搜索关键字
		page.setKeyWord_2(key2);//
		page.setKeyWord_3(key3);//医生的编号
		List<Emr> emrList = emrMapper.pageEmr(page);
		page.setList(emrList);
		return page;
	}

	@Override
	public List<Emr> selectIdByDisease(String Disease) {
		// TODO Auto-generated method stub
		return emrMapper.selectIdByDisease(Disease);
	}
}
