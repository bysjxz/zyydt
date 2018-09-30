package com.cdutcm.tcms.sys.mapper;

import java.util.List;
import java.util.Map;

import com.cdutcm.tcms.sys.entity.User;

public interface UserMapper {

	List<User> listAllUser();

	User getUserById(Long userId);

	void insertUser(User user);

	void updateUser(User user);

	User getUserInfo(User user);

	User getUserByAccount(String account);

	int updateUserBaseInfo(User user);

	void updateUserRights(User user);

	int getCountByName(User user);

	void deleteUser(Long userId);

	int getCount(User user);

	List<User> listPageUser(User user);

	User getUserAndRoleById(Integer userId);

	void updateLastLogin(User user);

	User getUserAndRoleById(Long userId);

	User selectUserByUserAndPass(Map<String, String> map);
}
