package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.BaseRecipelItemMapper;
import com.cdutcm.tcms.biz.mapper.BaseRecipelMapper;
import com.cdutcm.tcms.biz.model.BaseRecipel;
import com.cdutcm.tcms.biz.model.BaseRecipelItem;
import com.cdutcm.tcms.biz.service.BaseRecipelItemService;
import com.cdutcm.tcms.biz.service.BaseRecipelService;

@Service
public class BaseRecipelItemServiceImpl implements BaseRecipelItemService {
	@Autowired
	private BaseRecipelItemMapper baseRecipelitemMapper;

	@Override
	public List<BaseRecipelItem> findBaseRecipelItemByRecipelId(Long recipelId) {
		return baseRecipelitemMapper.findBaseRecipelItemByRecipelId(recipelId);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.deleteByPrimaryKey(id);
	}

	

	@Override
	public int insert(BaseRecipelItem item) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.insert(item);
	}

	@Override
	public int insertSelective(BaseRecipelItem record) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.insertSelective(record);
	}

	@Override
	public List<BaseRecipelItem> listBRItem(Long id) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.listBRItem(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseRecipelItem record) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BaseRecipelItem record) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.updateByPrimaryKey(record);
	}



	@Override
	public BaseRecipelItem selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.selectByPrimaryKey(id);
	}



	@Override
	public int replaceByPrimaryKey(BaseRecipelItem record) {
		// TODO Auto-generated method stub
		return baseRecipelitemMapper.replaceByPrimaryKey(record);
	}


}
