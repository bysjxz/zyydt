package com.cdutcm.tcms.sys.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.service.UserService;

/**
 * /**
 * 
 * @author zhufj
 *
 */
@Controller
@RestController
public class RsgistStep3Controller {

	@Autowired
	private UserService userService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/userRegist", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public void userRegist(User user) { String account1 =
	 * user.getAccount(); System.out.println(account1); String account, String
	 * IDCard, String username, Date birthday, Integer sex, String address }
	 */
	@RequestMapping(value = "/userRegistStep3", method = RequestMethod.POST)
	@ResponseBody
	public String userRegist(User user, HttpServletResponse resp, HttpSession session, String number, String years,
			String name, String nameNumber, Integer variety, String mainDep, String skill, String depNature) {

		String account2 = (String) session.getAttribute("account1");
		String password2 = (String) session.getAttribute("password1");
		String username2 = (String) session.getAttribute("username1");
		user.setAccount(account2);
		user.setPassword(password2);
		user.setUsername(username2);
		user.setCreatetime(new Date());
		user.setRoleId(1);
		user.setStatus(0);
		user.setUserId((long) (Math.random() * 100000));
		userService.insertUser(user);
		session.invalidate();
		String result = "success";
		return result;

		/*
		 * System.out.println(account2); System.out.println(password2);
		 * System.out.println(username2); System.out.println(number);
		 * System.out.println(years); System.out.println(name);
		 * System.out.println(nameNumber); System.out.println(variety);
		 * System.out.println(mainDep); System.out.println(skill);
		 * System.out.println(depNature);
		 */
	}

}