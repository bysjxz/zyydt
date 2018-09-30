package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.UserProfile;

public interface UserProfileMapper {

	UserProfile selectByPrimaryKey(Long id);
	
	UserProfile selectByUserId(Long userId);
	
	int insertSelective(UserProfile record);
	
	int updateByPrimaryKey(UserProfile record);
	
	/*根据用户名查询用户数据*/
	UserProfile selectByUserName(String userName);
	
	int updateByUserName(String userName);
}
