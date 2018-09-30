package com.cdutcm.tcms.sys.service;

import java.util.List;
import java.util.Map;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.sys.entity.User;

public interface UserService {
	User getUserById(Long userId);

	boolean insertUser(User user);

	void updateUser(User user);

	User getUserByNameAndPwd(String username, String password);

	public SysMsg updateUserBaseInfo(User user);

	void updateUserRights(User user);

	void deleteUser(Long userId);

	List<User> listPageUser(User user);

	void updateLastLogin(User user);

	User getUserAndRoleById(Long userId);

	List<User> listAllUser();

	User getUserByAccount(String account);

	User selectUserByUserAndPass(Map<String, String> map);
}
