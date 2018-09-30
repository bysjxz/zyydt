package com.cdutcm.tcms.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class RsgistController {

	@Autowired
	private UserService userService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userRegist", method = RequestMethod.POST)

	public String userRegist(User user, HttpSession session, HttpServletRequest req, HttpServletResponse resp,
			String account, String password, ModelMap model) {
		session.setAttribute("account1", account);
		session.setAttribute("password1", password);
		String result = null;
		User user1 = userService.getUserByAccount(account);
		if (user1 == null) {
			result = "success";
		} else {
			result = "error";
		}
		return result;
	}

}