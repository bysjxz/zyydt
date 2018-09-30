package com.cdutcm.tcms.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.page2.Page;
import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.biz.DTO.EeveryStatusNum;
import com.cdutcm.tcms.biz.constant.StatusConstant;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.model.PatientRegist;
import com.cdutcm.tcms.biz.model.UserProfile;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.PatientRegistService;
import com.cdutcm.tcms.biz.service.PatientService;
import com.cdutcm.tcms.biz.service.UserProfileService;
import com.cdutcm.tcms.redis.service.IRedisService;
import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RestController
public class MainPageController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private UserService userService;

	@Autowired
	private IRedisService redisService;
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private EmrService emrService;
	@Autowired
	private PatientRegistService patientRegistService;

	/**
	 * 判断权限，跳转到不同的首页 系统管理员：用户权限设置.... 开单医生：开单界面 执行医师：执行界面
	 *
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(@ModelAttribute("user") User user, String type, Patient patient, HttpSession session) {
		ModelAndView mv = new ModelAndView("/index.html");
		user = (User) session.getAttribute(Const.SESSION_USER);
		Long userId = user.getUserId();
		UserProfile userprofile = userProfileService.selectByUserId(userId);
		mv.addObject("userp", userprofile);
		return mv;
	}

	/* 跳转到修改医生信息的页面 */
	@RequestMapping(value = "/toEditUser")
	public ModelAndView toEditUser(Long id) {
		ModelAndView mv = new ModelAndView("/editUser.html");
		UserProfile user = userProfileService.selectByPrimaryKey(id);
		mv.addObject("user", user);
		return mv;
	}

	/* 敏化检测折线图界面 */
	@RequestMapping(value = "/toDiagnosis")
	public ModelAndView toDiagnosis() {
		ModelAndView mv = new ModelAndView("/Diagnosis.html");
		return mv;
	}

	/* 敏化治疗折线图界面 */
	@RequestMapping(value = "/toDiagnosis1")
	public ModelAndView toDiagnosis1() {
		ModelAndView mv = new ModelAndView("/Diagnosis1.html");
		return mv;
	}

	/**
	 * 个人中心
	 *
	 * @return
	 */
	@RequestMapping(value = "/personalCenter")
	public ModelAndView personalCenter(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		// 计算几种诊断状态的数量
		EeveryStatusNum num = patientRegistService.totalNumByStatus(user.getAccount());
		mv.addObject("nrs", num);

		// 取出所有待诊信息的病人
		List<PatientRegist> registList = patientRegistService.selectByStatus(StatusConstant.WAITING, user.getAccount());
		mv.addObject("prs", registList);

		// 计算疾病数量比较多的4种情况
		List<Emr> emrList = emrService.countByDisease(user.getAccount());
		mv.addObject("ers", emrList);

		mv.setViewName("/personalCenter.html");
		return mv;
	}

	/**
	 * @Author :Mxq 2018-08-09 就诊记录
	 */
	@RequestMapping("/patientRecord")
	public ModelAndView patientRecord(HttpSession session,
									  @RequestParam(defaultValue = "1", value = "currentPage") Integer currentPage,
									  @RequestParam(defaultValue = "#@", value = "key1") String key1,
									  @RequestParam(defaultValue = "all_patient", value = "key2") String key2) {
		ModelAndView mv = new ModelAndView("/record.html");
		int pageSize = 5;// 每页显示的数量
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String key3 = user.getAccount();
		if (key1.equals("#@") || key1.equals("")) {
			key1 = null;
		}
		if (key2.equals("all_patient")) {
			key2 = null;
		}
		Page page = emrService.pageEmr(currentPage, pageSize, key1, key2, key3);
		mv.addObject("rs", page);
		return mv;
	}

	/**
	 * 我的患者
	 *
	 * @return
	 */
	@RequestMapping("/Mypatient")
	public ModelAndView Mypatient(HttpSession session,
								  @RequestParam(defaultValue = "1", value = "currentPage") Integer currentPage,
								  @RequestParam(defaultValue = "#@", value = "key1") String key1,
								  @RequestParam(defaultValue = "all_patient", value = "key2") String key2) {
		int pageSize = 5;// 每页显示的数量
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String key3 = user.getAccount();
		ModelAndView mv = new ModelAndView("/Mypatient.html");
		if (key1.equals("#@") || key1.equals("")) {
			key1 = null;
		}
		if (key2.equals("search")) {
			key2 = null;
		}
		if (key2.equals("all_patient")) {
			key2 = null;
		}
		Page page = patientRegistService.pageRegist(currentPage, pageSize, key1, key2, key3);
		mv.addObject("rs", page);
		return mv;
	}

	/**
	 * 注册一
	 *
	 * @return
	 */
	@RequestMapping(value = "/regist")
	public ModelAndView regist(@ModelAttribute("user") String user, String type, Patient patient, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/regist.html");
		return mv;
	}

	/**
	 * 注册2
	 *
	 * @return
	 */
	@RequestMapping(value = "/registStep2")
	public ModelAndView registStep2(@ModelAttribute("user") String user, String type, Patient patient,
									HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/registStep2.html");
		return mv;
	}

	/**
	 * 注册2
	 *
	 * @return
	 */
	@RequestMapping(value = "/registStep3")
	public ModelAndView registStep3(@ModelAttribute("user") String user, String type, Patient patient,
									HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/registStep3.html");
		return mv;
	}

	/**
	 * 登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(defaultValue = "success", value = "code") String code) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errInfo", code);
		mv.setViewName("/login.html");
		return mv;
	}

	/**
	 * 后台登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/login2")
	public ModelAndView login2(@RequestParam(defaultValue = "success", value = "code") String code) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errInfo", code);
		mv.setViewName("/login2.html");
		return mv;
	}

	/**
	 * 判断权限，跳转到不同的首页 系统管理员：用户权限设置.... 开单医生：开单界面 执行医师：执行界面
	 *
	 * @return
	 */
	@RequestMapping(value = "/cure_step1")
	public ModelAndView cure_step1(@ModelAttribute("user") String user, String type, Patient patient,
								   HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cure_step1.html");
		return mv;
	}

	/**
	 *
	 *
	 * @return
	 */
	@RequestMapping(value = "/cure_step2")
	public ModelAndView cure_step2(@ModelAttribute("user") String user, String type, Patient patient,
								   HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cure_step2.html");
		return mv;
	}

	/**
	 *
	 *
	 * @return
	 */
	@RequestMapping(value = "/cure_step3")
	public ModelAndView cure_step3(@ModelAttribute("user") String user, String type, Patient patient,
								   HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cure_step3.html");
		return mv;
	}

	/**
	 * 查看模板
	 *
	 * @return
	 */
	@RequestMapping(value = "/baserecipel")
	public ModelAndView baseRecipel() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/tjmb/baserecipel_nav.html");
		return mv;
	}

	// /**
	// * 查看患者信息
	// *
	// * @return
	// */
	// @RequestMapping(value = "/patientinfo")
	// public ModelAndView patientinfo() {
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("/kdys/patient_info.html");
	// return mv;
	// }

	/**
	 * 开单医生界面
	 *
	 * @return
	 */
	@RequestMapping(value = "/kdys")
	public ModelAndView kdys(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String emrVisitNo = (String) session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO);
		if (StringUtil.notEmpty(emrVisitNo)) {
			Emr emr = redisService.get(emrVisitNo, Emr.class);
			if (!StringUtil.isEmptyList((emr.getRecipels()))) {
				if (emr.getRecipels().size() > 0) {
					try {
						mv.addObject("recipels", new ObjectMapper().writeValueAsString(emr.getRecipels().get(0)));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		mv.setViewName("/kdys/right_nav.html");
		return mv;
	}

	/**
	 * 执行医生界面
	 *
	 * @return
	 */
	@RequestMapping(value = "/zxys")
	public ModelAndView zxys() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/zxys/right_nav.html");
		return mv;
	}
	
	@RequestMapping(value = "/forgetPass1")
	public ModelAndView forgetPass1() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/forgetPass1.html");
		return mv;
	}
	
	@RequestMapping(value = "/forgetPass2")
	public ModelAndView forgetPass2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/forgetPass2.html");
		return mv;
	}

	/**
	 * 开单医生分类界面跳转
	 *
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/kdys/{type}/{name}")
	public ModelAndView kdysType(@PathVariable String type, @PathVariable String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/kdys/" + type + ".html");
		mv.addObject("name", name);
		return mv;
	}

	// @RequestMapping(value = "/sendrecipel")
	// public String sendRecipel(String recipel) {
	// System.out.println(recipel);
	// Recipel r = new Recipel();
	// try {
	// r = new ObjectMapper().readValue(recipel, Recipel.class);
	// // auto插入到数据库
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println(r);
	// return "发送成功";
	// }
}