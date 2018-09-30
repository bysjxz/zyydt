package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.RecipelMapper;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.RecipelService;

@Service
public class RecipelServiceImpl implements RecipelService {
	@Autowired
	private RecipelMapper recipelMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return recipelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Recipel record) {
		// TODO Auto-generated method stub
		return recipelMapper.insert(record);
	}

	@Override
	public int insertSelective(Recipel record) {
		// TODO Auto-generated method stub
		return recipelMapper.insertSelective(record);
	}

	@Override
	public Recipel selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return recipelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Recipel record) {
		// TODO Auto-generated method stub
		return recipelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recipel record) {
		// TODO Auto-generated method stub
		return recipelMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Recipel> listPagefindRecipelById(Recipel record) {
		// TODO Auto-generated method stub
		return recipelMapper.listPagefindRecipelById(record);
	}

	@Override
	public int delRecipelAndRecipelItem(long id) {
		return recipelMapper.delRecipelAndRecipelItem(id);
	}

	@Override
	public Recipel selectIdByEmrId(Long emrId) {
		// TODO Auto-generated method stub
		return recipelMapper.selectIdByEmrId(emrId);
	}


}
