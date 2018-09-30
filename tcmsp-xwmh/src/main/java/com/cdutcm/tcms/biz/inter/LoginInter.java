package com.cdutcm.tcms.biz.inter;
import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.tcms.biz.DTO.ResultDTO;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RequestMapping("/io")
@RestController
public class LoginInter {
	@Autowired
	UserService userService;

	/**
	 * 登陆接口
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	public ResultDTO login(@RequestParam("account") String account, @RequestParam("password") String password) {
		User user = userService.getUserByNameAndPwd(account, password);
		ResultDTO resultDTO = new ResultDTO();
		if (user != null) {
			resultDTO.setCode("0");
			resultDTO.setMsg("登录成功");

		}else {
			resultDTO.setCode("-1");
			resultDTO.setMsg("用户名或密码错误");
		}
		return resultDTO;

	}

	/**
	 * 注册第一步接口
	 * @return
	 */
	@RequestMapping("/registStep1")
	public ResultDTO registStep1(HttpSession session, @RequestParam("account") String account, @RequestParam("password") String password){
		ResultDTO resultDTO=new ResultDTO();
		User user1 = userService.getUserByAccount(account);
		if (user1 == null) {
			resultDTO.setCode("0");
			resultDTO.setMsg("成功");
			session.setAttribute("account1", account);
			session.setAttribute("password1", password);
		} else {
			resultDTO.setCode("-1");
			resultDTO.setMsg("用户名已被注册");
		}
		return resultDTO;
	}

	/**
	 *注册第二步接口
	 * @param session
	 * @param registStep2Json
	 * @return
	 */
	@RequestMapping("/registStep2")
	public ResultDTO registStep2(HttpSession session,@RequestParam("username") String username){
		ResultDTO resultDTO=new ResultDTO();
				resultDTO.setCode("0");
				resultDTO.setMsg("成功");
				session.setAttribute("username", username);
		return resultDTO;
	}

	/**
	 * 注册第三部接口
	 * @param session
	 * @param registStep3Json
	 * @return
	 */
	@RequestMapping("/registStep3")
	public ResultDTO registStep3(HttpSession session,String registStep3Json){
		ResultDTO resultDTO=new ResultDTO();
		String account=(String)session.getAttribute("account1");
		String password=(String)session.getAttribute("password1");
		String username=(String)session.getAttribute("username");
		JSONObject json3=JSONObject.fromObject(registStep3Json);
		User user=new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setUsername(username);
		user.setCreatetime(new Date());
		user.setRoleId(1);
		user.setStatus(0);
		user.setUserId((long) (Math.random() * 100000));
		userService.insertUser(user);
		resultDTO.setCode("0");
		resultDTO.setMsg("成功");

		return resultDTO;
	}
}
