package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.biz.mapper.DictMapper;
import com.cdutcm.tcms.biz.model.Dict;
import com.cdutcm.tcms.biz.service.DictService;
@Service
public class DictServiceImpl implements DictService{

	@Autowired 
	private DictMapper dictMapper;
	@Override
	public Dict selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return dictMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Dict> listPageDict(Dict record) {
		// TODO Auto-generated method stub
		return dictMapper.listPageDict(record);
	}

	@Override
	public SysMsg deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		SysMsg m=new SysMsg();
		int i=dictMapper.deleteByPrimaryKey(id);
		if (i > 0) {
			m.setStatus("TS");
			m.setContent("成功删除" + i + "条数据");
		} else {
			m.setStatus("FS");
			m.setContent("删除失败!");
		}
		return m;
	}

	@Override
	public int insert(Dict record) {
		// TODO Auto-generated method stub
		
		return dictMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(Dict record) {
	
	
		
		
		return dictMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Dict> selectByPid(Long pid) {
		// TODO Auto-generated method stub
		return dictMapper.selectByPid(pid);
	}

	@Override
	public List<Dict> selectForCheck(Dict record) {
		// TODO Auto-generated method stub
		return dictMapper.selectForCheck(record);
	}

	@Override
	public SysMsg editname(Dict record) {
		// TODO Auto-generated method stub
		SysMsg m=new SysMsg();
		List<Dict> d=dictMapper.selectForCheck(record);
		if(d.size()==0){
			int i= dictMapper.editname(record);
			if(i>0){
				m.setStatus("TS");
				m.setContent("修改成功");
			      }else{
			    	  m.setStatus("FS");
				 }	
		}else{
			m.setStatus("FS");
			m.setContent("名称重复");
		}
		return m;
	}

}
