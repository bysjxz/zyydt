package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.biz.mapper.BaseRecipelMapper;
import com.cdutcm.tcms.biz.model.BaseRecipel;
import com.cdutcm.tcms.biz.service.BaseRecipelService;

@Service
public class BaseRecipelServiceImpl implements BaseRecipelService {
	@Autowired
	private BaseRecipelMapper baseRecipelMapper;

	@Override
	public Long findIdByName(String name) {
		return baseRecipelMapper.findIdByName(name);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BaseRecipel br) {
		// TODO Auto-generated method stub
		
		return baseRecipelMapper.insert(br);
	}

	@Override
	public int insertSelective(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.insertSelective(record);
	}

	@Override
	public BaseRecipel selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BaseRecipel> listPagefindBaseRecipelByType(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.listPagefindBaseRecipelByType(record);
	}

	@Override
	public BaseRecipel listPagefindBaseRecipelById(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.listPagefindBaseRecipelById(record);
	}

	@Override
	public List<BaseRecipel> findBaseRecipel(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.findBaseRecipel(record);
	}

	@Override
	public List<BaseRecipel> listPageBaseRecipel(BaseRecipel baserecipel) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.listPageBaseRecipel(baserecipel);
	}

	@Override
	public List<BaseRecipel> selectBySelective(BaseRecipel baserecipel) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.selectBySelective(baserecipel);
	}

	@Override
	public BaseRecipel getCountByName(BaseRecipel record) {
		// TODO Auto-generated method stub
		return baseRecipelMapper.getCountByName(record);
	}

}
