package com.cdutcm.tcms.sys.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.log.SysServiceLog;
import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.mapper.UserMapper;
import com.cdutcm.tcms.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(userId);
	}

	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		int count = userMapper.getCountByName(user);
		if (count > 0) {
			return false;
		} else {
			userMapper.insertUser(user);
			return true;
		}

	}

	public User getUserByAccount(String account) {
		// TODO Auto-generated method stub
		return userMapper.getUserByAccount(account);
	}

	public List<User> listPageUser(User user) {

		if (user.getLastupdate() == null) {
			return userMapper.listPageUser(user);
		} else {
			user.setLastupdate(getNextDay(user.getLastupdate()));
			return userMapper.listPageUser(user);
		}
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, +23);// +1今天的时间加23小时
		calendar.add(Calendar.MINUTE, +59);
		date = calendar.getTime();
		return date;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}

	public SysMsg updateUserBaseInfo(User user) {
		SysMsg sysMsg = new SysMsg();
		int count = userMapper.getCountByName(user);
		if (count > 0) {
			sysMsg.setContent("用户名已拥有");
			sysMsg.setStatus("F");
		} else {
			userMapper.updateUserBaseInfo(user);
			sysMsg.setContent("修改成功");
			sysMsg.setStatus("T");
		}

		return sysMsg;
	}

	public void updateUserRights(User user) {
		userMapper.updateUserRights(user);
	}

	@SysServiceLog(description = "service登录")
	public User getUserByNameAndPwd(String loginname, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setAccount(loginname);
		user.setPassword(password);
		return userMapper.getUserInfo(user);
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void deleteUser(Long userId) {
		userMapper.deleteUser(userId);
	}

	public User getUserAndRoleById(Long userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserAndRoleById(userId);
	}

	public void updateLastLogin(User user) {
		// TODO Auto-generated method stub
		userMapper.updateLastLogin(user);
	}

	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userMapper.listAllUser();
	}

	public User selectUserByUserAndPass(Map<String, String> map) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByUserAndPass(map);
	}

}
