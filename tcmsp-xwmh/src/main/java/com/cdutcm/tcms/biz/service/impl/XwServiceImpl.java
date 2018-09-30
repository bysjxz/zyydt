package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.XwMapper;
import com.cdutcm.tcms.biz.model.Xw;

import com.cdutcm.tcms.biz.service.XwService;

@Service
public class XwServiceImpl implements XwService {

	@Autowired
	private XwMapper xwMapper;
	

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return xwMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Xw record) {
		// TODO Auto-generated method stub
		return xwMapper.insert(record);
	}

	@Override
	public int insertSelective(Xw record) {
		// TODO Auto-generated method stub
		return xwMapper.insertSelective(record);
	}

	@Override
	public Xw selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return xwMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Xw record) {
		// TODO Auto-generated method stub
		return xwMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Xw record) {
		// TODO Auto-generated method stub
		return xwMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Xw> findXwByNameOrPinYin(Xw record) {
		// TODO Auto-generated method stub
		return xwMapper.findXwByNameOrPinYin(record);
	}

}
