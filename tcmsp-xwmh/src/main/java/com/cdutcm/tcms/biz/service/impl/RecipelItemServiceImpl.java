package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.DTO.RecipelDTO;
import com.cdutcm.tcms.biz.mapper.RecipelItemMapper;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.RecipelItemService;

@Service
public class RecipelItemServiceImpl implements RecipelItemService {
	@Autowired
	private RecipelItemMapper recipelItemMapper;


	@Override
	public int insert(RecipelItem record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.insert(record);
	}

	@Override
	public int insertSelective(RecipelItem record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.insertSelective(record);
	}

	@Override
	public RecipelItem selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return recipelItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RecipelItem record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RecipelItem record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryEmrId(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryRecipelId(long id) {
		return recipelItemMapper.deleteByPrimaryRecipelId(id);
	}

	@Override
	public List<String> findCtypeByRecipelId(RecipelItem record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.findCtypeByRecipelId(record);
	}

	@Override
	public List<RecipelItem> findRecipelItemByCtypeAndRecipelId(
			RecipelItem record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.findRecipelItemByCtypeAndRecipelId(record);
	}
	
	@Override
	public List<RecipelItem> findRecipelItemByRecipelId(Long recipelId) {
		// TODO Auto-generated method stub
		return recipelItemMapper.findRecipelItemByRecipelId(recipelId);
	}

	@Override
	public List<RecipelDTO> findRecipelDTOByRecipelId(Long recipelId) {
		// TODO Auto-generated method stub
		return recipelItemMapper.findRecipelDTOByRecipelId(recipelId);
	}

	@Override
	public int insert(RecipelDTO record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.insertDTO(record);
	}

	@Override
	public int updateByPrimaryKeyDTO(RecipelDTO record) {
		// TODO Auto-generated method stub
		return recipelItemMapper.updateByPrimaryKeyDTO(record);
	}

}
