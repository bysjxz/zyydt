package com.cdutcm.tcms.sys.controller;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ForgetPassController2 {

	@Autowired
	private UserService userService;

	/**
	 * 访问登录页
	 * 
	 * @return
	 */

	
	@RequestMapping("/ForgetPass2")
	public String ForgetPass2(HttpSession session, String account, String password){
		
		User user = userService.getUserByAccount(account);
		user.setPassword(password);
		userService.insertUser(user);
		String result = "success";
		return result;
		
	}
	
	
}