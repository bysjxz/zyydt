package com.cdutcm.tcms.biz.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.biz.mapper.PatientCardMapper;
import com.cdutcm.tcms.biz.model.PatientCard;
import com.cdutcm.tcms.biz.service.PatientCardService;
import com.cdutcm.tcms.sys.entity.Role;
import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.mapper.RoleMapper;
import com.cdutcm.tcms.sys.mapper.UserMapper;

@Service
public class PatientCardServiceImpl implements PatientCardService{

	@Autowired 
	private PatientCardMapper patientCardMapper;
	@Autowired 
	private UserMapper userMapper;
	@Autowired 
	private RoleMapper roleMapper;
	@Override
	public SysMsg deleteByPrimaryKey(Long id) {
		SysMsg m = new SysMsg();
		int i=patientCardMapper.deleteByPrimaryKey(id);
		if (i > 0) {	
			m.setStatus("TS");
		} else {
			m.setStatus("FS");	
		}
		return m;
	}

	@Override
	public SysMsg insert(PatientCard record) {
		User user=new User();
		SysMsg msg = new SysMsg();
		//获取患者角色的ID
		
		Role role=new Role();
		 role =roleMapper.getIdByName(role);
		Long roleID=role.getRoleId();
		//插入到User表中
		IdWorker wk = new IdWorker();		
		user.setUserId(wk.nextId());
		user.setAccount(record.getTelephone());
		user.setUsername(record.getName());
		
		user.setRoleId(roleID);//插入角色ID
		
		record.setUserId(user.getUserId()); //插入user表的ID
		IdWorker w = new IdWorker();
		java.sql.Date time= new java.sql.Date(new Date().getTime());
		record.setLastupdate(time);//插入修改时间
		user.setCreattime(time);
		
		long pcId=w.nextId();
		record.setId(pcId);
		
		int count = userMapper.getCountByName(user);//判断登录账号（电话号码）是否重复
		if(count>0){
			msg.setStatus("FS");
		    msg.setContent("电话已注册");
		}else{
			msg.setStatus("TS");
			userMapper.insertUser(user);
			int i=patientCardMapper.insert(record);
			if (i > 0) {	
				msg.setStatus("TS");
			} else {
				msg.setStatus("FS");	
			}	
		}
		return msg;
	}

	@Override
	public PatientCard selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return patientCardMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysMsg updateByPrimaryKey(PatientCard record) {
		 SysMsg m=new SysMsg();
		 java.sql.Date time= new java.sql.Date(new Date().getTime());
		 record.setLastupdate(time);
		 int i=patientCardMapper.updateByPrimaryKey(record);
		 if (i > 0) {	
				m.setStatus("TS");
			} else {
				m.setStatus("FS");	
			}
		return m;
	}

	@Override
	public List<PatientCard> listPagePatientCard(PatientCard record) {
		// TODO Auto-generated method stub
		return patientCardMapper.listPagePatientCard(record);
	}

	@Override
	public PatientCard selectBySelective(Long id) {
		// TODO Auto-generated method stub
		return patientCardMapper.selectBySelective(id);
	}

}
