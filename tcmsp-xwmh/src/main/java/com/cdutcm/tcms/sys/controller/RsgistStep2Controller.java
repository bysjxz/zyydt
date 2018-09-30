package com.cdutcm.tcms.sys.controller;

import javax.servlet.http.HttpServletRequest;
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
public class RsgistStep2Controller {

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
	@RequestMapping(value = "/userRegistStep2", method = RequestMethod.POST)
	@ResponseBody
	public void userRegist(HttpSession session, String account, String password, User user, HttpServletRequest req,
			String IDCard, String username, String birthday, Integer sex, String address) {

		session.setAttribute("username1", username);

		/*
		 * String account2 = (String) session.getAttribute("account1"); String
		 * password2 = (String) session.getAttribute("password1");
		 * user.setAccount(account2); user.setPassword(password2);
		 * user.setUsername(username); userService.insertUser(user);
		 */
		/*
		 * ModelAndView mv = new ModelAndView(); mv.addObject("account",
		 * account); mv.setViewName("/registStep2.html"); return mv;
		 */

		/*
		 * if(account != null && password != null && username == null){ long
		 * userId = (long) (Math.random()*100000); user.setUserId(userId);
		 * user.setAccount(account); user.setPassword(password);
		 * userService.insertUser(user); }
		 * 
		 * if(account == null && password == null && username != null){ User
		 * user1 = userService.getUserByAccount(account);
		 * user1.setUsername(username); userService.insertUser(user1); }
		 */

		/*
		 * System.out.println(user.getAccount());
		 * System.out.println(user.getPassword());
		 * System.out.println(user.getUsername());
		 */
		/*
		 * session.setAttribute("account1", account);
		 * session.setAttribute("password1", password);
		 * session.setAttribute("username1", username);
		 * 
		 * String account2 = (String) session.getAttribute("account1"); String
		 * password2 = (String) session.getAttribute("password1"); String
		 * username2 = (String) session.getAttribute("username1");
		 * System.out.println(account2); System.out.println(password2);
		 * System.out.println(username2);
		 */
		/*
		 * String account = req.getParameter("account"); String password =
		 * req.getParameter("password"); System.out.println(account);
		 * System.out.println(password); System.out.println(username);
		 */
		/*
		 * System.out.println(account); User user1 =
		 * userService.getUserByAccount(account); user1.setUsername(username);
		 * userService.insertUser(user1);
		 */
		/*
		 * System.out.println(account); System.out.println(password);
		 * System.out.println(username);
		 */
		/*
		 * if(account != null && password != null && username != null){ long
		 * userId = (long) (Math.random()*100000); user.setUserId(userId);
		 * user.setAccount(account); user.setPassword(password);
		 * user.setUsername(username); userService.insertUser(user); }
		 */

		/*
		 * System.out.println(IDCard); System.out.println(username);
		 * System.out.println(birthday); System.out.println(sex);
		 * System.out.println(address); System.out.println(account);
		 * System.out.println(password);
		 */

	}

}