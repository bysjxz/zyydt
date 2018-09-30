package com.cdutcm.tcms.sys.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.StringUtil;
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
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public ModelAndView userlogin(HttpSession session, @RequestParam String account, @RequestParam String password,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String errInfo = "";
		User user = userService.getUserByNameAndPwd(account, password);
		if (user != null) {
			user.setLastupdate(new Date());
			session.setAttribute(Const.SESSION_USER, user);
			session.removeAttribute(Const.SESSION_SECURITY_CODE);
			userService.updateLastLogin(user);
		} else {
			errInfo = "用户名或密码有误！";
		}
		if (StringUtil.isEmpty(errInfo)) {
			mv.setViewName("redirect:/personalCenter");
		} else {

			// mv.addObject("errInfo", errInfo);
			// mv.addObject("account", account);
			// mv.addObject("password", password);
			mv.setViewName("redirect:/login?code=error");

		}
		return mv;
	}

}