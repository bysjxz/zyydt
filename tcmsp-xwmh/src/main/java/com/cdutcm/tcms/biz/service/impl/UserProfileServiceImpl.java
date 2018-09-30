package com.cdutcm.tcms.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.UserProfileMapper;
import com.cdutcm.tcms.biz.model.UserProfile;
import com.cdutcm.tcms.biz.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	private UserProfileMapper userProfileMapper;

	
	@Override
	public UserProfile selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return userProfileMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserProfile selectByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userProfileMapper.selectByUserId(userId);
	}

	@Override
	public int insertSelective(UserProfile record) {
		// TODO Auto-generated method stub
		return userProfileMapper.insertSelective(record);
	} 

	@Override
	public int updateByPrimaryKey(UserProfile record) {
		// TODO Auto-generated method stub
		return userProfileMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserProfile selectByUserName(String userName) {
		// TODO Auto-generated method stub
		return userProfileMapper.selectByUserName(userName);
	}

}
