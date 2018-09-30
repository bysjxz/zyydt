package com.cdutcm.tcms.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.IllnessHistoryMapper;
import com.cdutcm.tcms.biz.model.IllnessHistory;
import com.cdutcm.tcms.biz.service.IllnessHistoryService;

@Service
public class IllnessHistoryServiceImpl implements IllnessHistoryService {

	@Autowired
	private IllnessHistoryMapper illnessHistoryMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return illnessHistoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(IllnessHistory record) {
		// TODO Auto-generated method stub
		return illnessHistoryMapper.insert(record);
	}

	@Override
	public int insertSelective(IllnessHistory record) {
		// TODO Auto-generated method stub
		return illnessHistoryMapper.insertSelective(record);
	}

	@Override
	public IllnessHistory selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return illnessHistoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(IllnessHistory record) {
		// TODO Auto-generated method stub
		return illnessHistoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(IllnessHistory record) {
		// TODO Auto-generated method stub
		return illnessHistoryMapper.updateByPrimaryKey(record);
	}

}
