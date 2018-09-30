package com.cdutcm.tcms.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.tcms.DTO.ResultDTO;
import com.cdutcm.tcms.DTO.UserInfoDTO;
import com.cdutcm.tcms.biz.model.UserProfile;
import com.cdutcm.tcms.biz.service.UserProfileService;
import com.cdutcm.tcms.util.ResultUtil;

@RestController
@RequestMapping("user")
public class UserInfoApi {
	
	@Autowired
	private UserProfileService userProfileService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("info")
	public ResultDTO userInfo(@RequestParam("userName") String userName){
		ResultUtil resultUtil = new ResultUtil();
		//根据用户名查询用户信息
		if(userName!=null||"".equals(userName)){
		UserProfile user = userProfileService.selectByUserName(userName);
		if(user!=null){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAddress(user.getAddress());
        userInfoDTO.setAge(user.getAge());
        userInfoDTO.setBirthday(user.getBirthday());
        userInfoDTO.setCzAddress(user.getCzAddress());
        userInfoDTO.setCzTime(user.getCzTime());
        userInfoDTO.setDeptname(user.getDeptname());
        userInfoDTO.setDocDesc(user.getDocDesc());
        userInfoDTO.setEducation(user.getEducation());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setIllness(user.getIllness());
        userInfoDTO.setProf(user.getProf());
        userInfoDTO.setSex(user.getSex());
        userInfoDTO.setSchool(user.getSchool());
        userInfoDTO.setTeacher(user.getTeacher());
        userInfoDTO.setTelephone(user.getTelephone());
        userInfoDTO.setTitle(user.getTitle());
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setWorkTime(user.getWorkTime());
		return resultUtil.success(userInfoDTO);
		}
		return resultUtil.error_userInfo();
		}
		return resultUtil.error_user();
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("info/updata")
	public ResultDTO userInfoUpdata(UserProfile userProfile){
		//根据用户名修改数据
		int turn = userProfileService.updateByPrimaryKey(userProfile);
		ResultUtil resultUtil = new ResultUtil();
		System.out.println(turn);
		if(turn == 0){
			return resultUtil.success();
		}
		return resultUtil.error_updataUser();
		
	}

}
