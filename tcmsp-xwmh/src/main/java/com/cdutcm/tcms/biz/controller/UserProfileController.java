package com.cdutcm.tcms.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.biz.model.UserProfile;
import com.cdutcm.tcms.biz.service.UserProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "userProfile")
public class UserProfileController {
	@Autowired
	private UserProfileService userProfileService;
	
/*	@RequestMapping(value = "/userinfo")
	public ModelAndView userinfo(Long userId){
		ModelAndView mv=new ModelAndView("/index.html");
		userId=1L;
		UserProfile userprofile=userProfileService.selectByUserId(userId);
		mv.addObject("userp", userprofile);
		return mv;
	}*/
	@RequestMapping(value = "/editUser")
	public SysMsg editUser(HttpSession session, UserProfile record){
		SysMsg sm=new SysMsg();
		int i=userProfileService.updateByPrimaryKey(record);
		if(i>0){
			sm.setStatus("TS");
		}else{
			sm.setStatus("FS");
		}
		return sm;
	}
}
