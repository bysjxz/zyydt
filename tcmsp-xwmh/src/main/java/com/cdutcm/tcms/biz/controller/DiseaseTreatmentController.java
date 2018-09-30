/**  
 * @Title: DiseaseTreatment.java
 * @Package com.cdutcm.tcms.biz.controller
 * @Description: TODO
 * @author 魏浩
 * @date 2018年7月3日
 */
package com.cdutcm.tcms.biz.controller;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.biz.model.*;
import com.cdutcm.tcms.biz.service.*;
import com.cdutcm.tcms.sys.entity.User;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 魏浩
 * @date 2018年7月3日
 * @ClassName: DiseaseTreatment
 * @Description: 疾病诊疗
 * 
 */
@RestController
@RequestMapping(value = "/treatment")
public class DiseaseTreatmentController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private EmrService emrService;
	@Autowired
	private IllnessHistoryService illnessHistoryService;
	@Autowired
	private XwService xwService;
	@Autowired
	private RecipelItemService recipelItemService;
	@Autowired
	private RecipelService recipelService;
	@Autowired
	private BaseRecipelService baseRecipelService;
	@Autowired
	private BaseRecipelItemService baseRecipelItemService;

	/**
	 * 通过病名查询智能处方---------zhongyj
	 * @param diseaseName	病名
	 * @return	返回处方的信息集合
	 */
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public List<BaseRecipelItem> findBaseRecipelItemByName(String diseaseName) {
		List<BaseRecipelItem> list = new ArrayList<BaseRecipelItem>();
		System.out.println("智能推荐:"+diseaseName);
		//diseaseName = "肩周炎";		//测试用****************************************************************
		//调用service层查询方法
		diseaseName = "%"+diseaseName+"%";
		Long recipelId = baseRecipelService.findIdByName(diseaseName);
		//根据返回的病名id去查询对应的治疗方案
		list = baseRecipelItemService.findBaseRecipelItemByRecipelId(recipelId);
		return list;
	}

	/**
	 * 通过patientNo获取病人信息
	 *  @param patientNo 卡号
	 *  @param visitNo	挂号序号   0000
	 *  @return
	 */
	@RequestMapping(value = "/patient", method = RequestMethod.GET)
	public ModelAndView findPatient(String patientNo, String visitNo) {
		ModelAndView mv = new ModelAndView();
		Patient patient = new Patient();
		patient.setPatientNo(patientNo);
		patient.setVisitNo(visitNo);
		if (!patientNo.equals(null) && !visitNo.equals(null)) {
			patient = patientService.findPatientsByNo3(patient);
		}
		if (patient != null) {
			mv.addObject("patient", patient);
			mv.setViewName("/cure_step1.html");
			return mv;
		}
		return null;
	}

	/**
	 * 	新增病例信息
	 *  @param emr	主诉、症状、诊断信息  （拿到病人就诊卡号码）
	 *  @param bs  病史信息
	 *  @return
	 */
	@RequestMapping(value = "/case", method = RequestMethod.POST)
	public ModelAndView insertCase(Emr emr, String bs, HttpSession session) {
		// 还有获取 病人信息
		// 拿到医生信息
		User doctor = (User) session.getAttribute(Const.SESSION_USER);

		/*User doctor = new User();
		doctor.setAccount("0026");
		doctor.setUsername("华佗");*/

		IdWorker idWorker = new IdWorker();
		ModelAndView mv = new ModelAndView();
		IllnessHistory illnessHistory = new IllnessHistory();
		illnessHistory.setChiefComplaint(bs);
		long id = idWorker.nextId();
		illnessHistory.setId(id);
		// 电子病历赋值
		emr.setId(id);
		emr.setIllnessHistoryId(id);
		emr.setDoctorName(doctor.getUsername());
		emr.setDoctorId(doctor.getAccount());
		emr.setCreateTime(new Date());
		// 把填过的数据先存进session (包含医生病人)
		session.removeAttribute("illnessHistory");
		session.removeAttribute("emr");
		session.setAttribute("illnessHistory", illnessHistory);
		session.setAttribute("emr", emr);
		mv.setViewName("/cure_step2.html");
		return mv;
	}

	/**
	 * 查询穴位
	 *  @param request
	 *  @return
	 */
	@RequestMapping(value = "/serach")
	@ResponseBody
	public List<Xw> serach(HttpServletRequest request) {
		Xw x = new Xw();
		x.setPinyin(request.getParameter("q"));
		x.setName(request.getParameter("q"));
		return xwService.findXwByNameOrPinYin(x);
	}

	/**
	 * 处方用穴，保存诊断及治疗信息到数据库中------zhongyj
	 * @param recipelItems
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cfyx", method = RequestMethod.POST)
	public SysMsg recipelYx(String recipelItems, HttpSession session,HttpServletRequest request) {
//		String re = request.getParameter("recipelItems");
		//System.out.println(recipelItems);
		JSONArray recipelJson = JSONArray.fromObject(recipelItems);
		System.out.println(recipelJson);
		List<RecipelItem> recipelItemList = (List)JSONArray.toList(recipelJson, RecipelItem.class);
		/*Iterator it = list.iterator();
		while(it.hasNext()){
			RecipelItem p = (RecipelItem)it.next();
			System.out.println(p.toString());
		}*/
		Recipel recipel = new Recipel();
		IdWorker idWorker = new IdWorker();
		long recipelId = idWorker.nextId();		//生成随机的处方id
		//拿到保存的诊断记录
		IllnessHistory illnessHistory = (IllnessHistory) session.getAttribute("illnessHistory");
		Emr emr = (Emr) session.getAttribute("emr");
		recipel.setId(recipelId);
		recipel.setEmrId(emr.getId());

		//插入诊断记录
		illnessHistoryService.insertSelective(illnessHistory);
		//插入病人诊断基本记录
		emrService.insertSelective(emr);
		//插入处方基本信息
		recipelService.insertSelective(recipel);
		//插入处方诊断治疗信息
		for (RecipelItem recipelItem : recipelItemList) {
			long recipelItemId = idWorker.nextId();
			recipelItem.setRecipelId(recipelId);
			recipelItem.setId(recipelItemId);
			recipelItemService.insertSelective(recipelItem);
			System.out.println(recipelItem.getName());
		}
		//System.out.println(recipel.getJSONObject(1).get("name").toString());

		SysMsg msg = new SysMsg();
		msg.setStatus("TF");
		msg.setContent("新增处方用穴成功");
		return msg;
	}

}
