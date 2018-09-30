package com.cdutcm.tcms.biz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.internal.xml.PayloadType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.IllnessHistory;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.IllnessHistoryService;
import com.cdutcm.tcms.biz.service.PatientService;
import com.cdutcm.tcms.biz.service.RecipelService;
import com.cdutcm.tcms.log.SysControllerLog;
import com.cdutcm.tcms.redis.service.IRedisService;
import com.cdutcm.tcms.sys.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private EmrService emrService;

	@Autowired
	private RecipelService recipelService;

	@Autowired
	private IRedisService redisService;
	@Autowired 
	private IllnessHistoryService illnessHistoryService;

	/**
	 * 分页显示病人列表
	 * 
	 * @param patient
	 * @return
	 */
	@SysControllerLog(description="分页显示病人列表")
	@RequestMapping(value = "/listPagePatients")
	public ModelAndView listPagePatients(Patient patient,HttpSession session) {
		List<Patient> patients = patientService.listPagePatients(patient);
		ModelAndView mv = new ModelAndView();
		mv.addObject("patients", patients);
		String emrVisitNo = (String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO);
		if(StringUtil.notEmpty(emrVisitNo)){
			Emr emr=redisService.get(emrVisitNo,Emr.class);
			mv.addObject("Emr", emr);
		}
		mv.setViewName("/kdys/patient_info.html");
		return mv;
	}

	/**
	 * 分页显示病人就诊记录
	 * 
	 * @param patient
	 * @return
	 */
	@SysControllerLog(description="分页显示病人就诊记录")
	@RequestMapping(value = "/listPagePatientsMedicalRecords")
	public ModelAndView listPagePatientsMedicalRecords(Emr emr,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/kdys/patient_table.html");
		if(emr.getPage()==null){
			mv.setViewName("/kdys/patient_medicalrecords.html");
		}
		// 查询登陆医生的病人
		User user  =(User) session.getAttribute(Const.SESSION_USER);
		emr.setDoctorId(user.getAccount());
		List<Emr> emrs = emrService.listPagefindEmrByNo(emr);
		mv.addObject("emrsLeft", emrs);
		return mv;
	}
	/**
	 * 根据病人信息 查询就诊处方信息
	 * @param patient
	 * @param session
	 * @return
	 */
   @SysControllerLog(description="根据病人信息 查询就诊处方信息")
   @RequestMapping(value = "/findRecording")
   public ModelAndView findVistPatientRecording(Emr emr,HttpSession session){
	    ModelAndView mv = new ModelAndView("/kdys/patient_recording.html");
	    List<Recipel> rs = new ArrayList<Recipel>();
	    Recipel r = new Recipel();
	    List<Emr> emrs = emrService.listPagefindEmrByNo(emr);
	    mv.addObject("name", emr.getPatientName()); 
	    if(emrs.size()<=0){
	 	   return mv;
	 	}
	    r.setEmrId(emrs.get(0).getId());
	    rs = recipelService.listPagefindRecipelById(r);
		mv.addObject("emrsRight", emrs);
		mv.addObject("recipels", rs);
	    return mv;
	   
   }
	/**
	 * 通过emrId查询病人就诊记录
	 * 
	 * @return
	 */
    @SysControllerLog(description="通过emrId查询病人就诊记录")
	@RequestMapping(value = "/findbyemr")
	public ModelAndView findByEmr(Recipel r) {
		ModelAndView mv = new ModelAndView();
		List<Recipel> rs = recipelService.listPagefindRecipelById(r);
		mv.addObject("recipels", rs);
		mv.setViewName("/kdys/medicalrecords_recipelItem_info.html");
		return mv;
	}

	/**
	 * 获取某个病人所有历史的电子处方
	 * 
	 * @param patient
	 * @return
	 */
    @SysControllerLog(description="获取某个病人所有历史的电子处方")
	@RequestMapping(value = "/getPatientRecipels")
	public ModelAndView getPatientRecipels(Emr emr,HttpSession session) {
		//AUTO 我们系统发送成功的病历组成的病人列表
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cure_step3.html");
		if(emr.getPage()==null){
			mv.setViewName("cure_step3.html");
		}
		// 查询登陆医生的病人
//		User user  =(User) session.getAttribute(Const.SESSION_USER);
//		emr.setDoctorId(user.getAccount());
		emr.setDoctorId("00026");
		List<Emr> emrs = emrService.listPagefindEmrByNo(emr);
		mv.addObject("emrsLeft", emrs);
		return mv;
	}
  
    @RequestMapping(value = "/findrecipellist")
	public ModelAndView findReciplList(Emr emr) {
    	ModelAndView mv = new ModelAndView("/kdys/patient_recipellist.html");
 	    List<Recipel> rs = new ArrayList<Recipel>();
 	    Recipel r = new Recipel();
 	    List<Emr> emrs = emrService.listPagefindEmrByNo(emr);
 	    mv.addObject("name", emr.getPatientName());
 	    if(emrs.size()<=0){
 	       return mv;
 	    }
 	    r.setEmrId(emrs.get(0).getId());
 	    rs = recipelService.listPagefindRecipelById(r);
 		mv.addObject("emrsRight", emrs);
 		mv.addObject("recipels", rs);
 	    return mv;
	}
	/**
	 * 给病人下诊断
	 * 先读取redis中的病人，通过ID判断是否存在，如果存在，就替换，没有就存放进去
	 * 
	 * @param patient
	 * @return
	 */
    @SysControllerLog(description="给病人下诊断")
	@RequestMapping(value = "/writeDiagnose")
	public ModelAndView writeDiagnose(String visitNo,HttpSession session) {
		Emr emr = redisService.get(Const.CURRENTEMR_ + visitNo, Emr.class);
		if(emr == null) {
			emr = new Emr();
			setRedisPatient(visitNo, emr, session);
		}else{
			if(emr.getPatient()!=null && visitNo != emr.getPatient().getVisitNo()){
				setRedisPatient(visitNo, emr, session);
			}
		}
		session.setAttribute(Const.SESSION_CURRENT_EMR_VISITNO, Const.CURRENTEMR_ + visitNo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("Emr", emr);
		session.setAttribute(Const.SESSION_CURRENT_EMR, emr);
		mv.setViewName("/kdys/patient.html");
		return mv;
	}
	
	/**
	 * 给当前emr设置病人信息
	 * @param id
	 */
	private void setRedisPatient(String visitNo, Emr emr,HttpSession session){
		Patient p = new Patient();
		p.setVisitNo(visitNo);
		List<Patient> patients = patientService.findPatientsByNo(p);
		if(!StringUtil.isEmptyList(patients)){
			emr.setPatient(patients.get(0));
		}
		setPatientAndUserToEmr(emr, (User)session.getAttribute(Const.SESSION_USER));
		redisService.set(Const.CURRENTEMR_ + visitNo, emr);
	}
	
	/**
	 * 就诊病人 处方编辑
	 * @param r
	 * @param session
	 * @return
	 */
	@SysControllerLog(description="就诊病人 处方编辑")
	@RequestMapping(value = "/editRecipel")
    public ModelAndView editRecipel(Recipel r, Long emrId, HttpSession session){
    	ModelAndView mv = new  ModelAndView("/kdys/right_nav.html");
    	List<Recipel> recipels = recipelService.listPagefindRecipelById(r);
        Emr emr=emrService.selectByPrimaryKey(emrId);
        Patient patient=new Patient();
        patient.setVisitNo(emr.getVisitNo());  
        List<Patient> ppPatient=patientService.findPatientsByNo(patient);
        emr.setPatient(ppPatient.get(0));
        emr.setRecipels(recipels);
        //封装病史
        IllnessHistory illnessHistory=  illnessHistoryService.selectByPrimaryKey(emr.getIllnessHistoryId());
        emr.setIllnessHistory(illnessHistory);
        
        session.setAttribute(Const.SESSION_CURRENT_EMR_VISITNO, Const.CURRENTEMR_ + emr.getPatient().getVisitNo());
        session.setAttribute(Const.SESSION_CURRENT_EMR, emr);
        redisService.set(Const.CURRENTEMR_ + emr.getPatient().getVisitNo(), emr);
    	String data  = "";
    	try {
    /*		Emr emr = redisService.get("currentEmr_"+emrId, Emr.class);
			if(emr == null) {
				emr = new Emr();
				setRedisPatient(emrId, emr, session);
			}else{
				if(emr.getPatient()!=null && emrId != emr.getPatient().getId()){
					setRedisPatient(emrId, emr, session);
				}
			}
			session.setAttribute(Const.SESSION_CURRENT_EMR_VISITNO, "currentEmr_"+id);*/
			
			data = new ObjectMapper().writeValueAsString(recipels.get(0));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	mv.addObject("recipels", data);
    	return mv;
    }
	/**
	 * 删除处方
	 * @param r
	 * @param session
	 * @return
	 */
	@SysControllerLog(description="删除处方")
	@RequestMapping(value = "delRecipel")
	public SysMsg delRecipel(Recipel r, HttpSession session){
		SysMsg msg = new SysMsg();
		int i;
		try {
			i = recipelService.delRecipelAndRecipelItem(r.getId());
			if(i<=0){
				msg.setStatus("FS");
				msg.setContent("删除失败!");
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("FS");
			msg.setContent("系统内部错误!");
			return msg;
		}
		msg.setStatus("TS");
		msg.setContent("删除成功!");
		return msg;
	}
	/**
	 * 删除emr
	 * @param e
	 * @param session
	 * @return
	 */
	@SysControllerLog(description="删除病历")
	@RequestMapping(value = "delEmr")
	public SysMsg delEmr(Emr e,HttpSession session){
		SysMsg msg = new SysMsg();
		try {
		    int j=	illnessHistoryService.deleteByPrimaryKey(emrService.selectByPrimaryKey(e.getId()).getIllnessHistoryId());
			int i = emrService.deleteEmrAndRecipel(e.getId());
			
			if (i < 0&&j<0) {
				msg.setStatus("FS");
				msg.setContent("删除失败!");
				return msg;
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			msg.setStatus("FS");
			msg.setContent("系统内部错误!");
			return msg;

		}
		msg.setStatus("TS");
		msg.setContent("删除成功!");
		return msg;
		
	}
	/**
	 * 处方复用
	 * @param r
	 * @param session
	 * @return
	 */
	@SysControllerLog(description="处方复用")
	@RequestMapping(value = "reuseRecipel")
	public ModelAndView reuseRecipel(Recipel r, HttpSession session){
		ModelAndView mv = new  ModelAndView("/kdys/right_nav.html");
		Emr emr = new Emr();
		if(StringUtil.isEmpty((String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO))){
			return null;
		}else{
			emr = redisService.get((String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO), Emr.class);
			if(emr == null ){
				return null;
			}
			else{
				if(StringUtil.isEmpty(emr.getDisease())){
					mv.setViewName("/kdys/msg.html");
				    return mv;
				}
				List<Recipel> recipels = recipelService.listPagefindRecipelById(r);
				List<Recipel> res = new ArrayList<Recipel>();
				Emr e = new Emr();
				e.setVisitNo(emr.getVisitNo());
				String data  = "";
				List<RecipelItem> resItem = new ArrayList<RecipelItem>();
				// 通过visitNo 判断有无该病人的emr 如果有设置emrId值 反之重置emrId值
				// 判断是否时编辑后的复用处方
				if(!StringUtil.isEmptyList(emr.getRecipels())){
					if(emr.getRecipels().size() >= 0){
						// 设置处方id
						for(RecipelItem reitem : recipels.get(0).getRecipelItem()){
							reitem.setRecipelId(emr.getRecipels().get(0).getId());
							resItem.add(reitem);
						}
						recipels.get(0).setRecipelItem(resItem);
						recipels.get(0).setId(emr.getRecipels().get(0).getId());
						recipels.get(0).setStatus("已发送");
					}
				}
				else{					
					recipels.get(0).setStatus("");
				}
				if(StringUtil.isEmptyList(emrService.listPagefindEmrByNo(e))){
					// 设置emrId为空
					recipels.get(0).setEmrId(0l);
				}else{
					// 关联上这个病人的emrId
					for(Recipel re :recipels){
						re.setEmrId(emr.getId());
						res.add(re);
					}
					emr.setRecipels(res);
				}
				try {
					data = new ObjectMapper().writeValueAsString(recipels.get(0));
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
/*		    	session.setAttribute(Const.SESSION_CURRENT_EMR_VISITNO, Const.CURRENTEMR_ + emr.getPatient().getVisitNo());
		    	session.setAttribute(Const.SESSION_CURRENT_EMR, emr);
		    	redisService.set(Const.CURRENTEMR_ + emr.getPatient().getVisitNo(), emr);*/
		    	mv.addObject("recipels", data);
			}
		}
    	return mv;
	}
	@RequestMapping(value  = "addRecipel")
	public ModelAndView addRecipel(Emr emr, HttpSession session){
		ModelAndView mv = new  ModelAndView("/kdys/right_nav.html");
		String data = "";
	    //查询数据库的病人信息
		/*Patient patient=new Patient();
		patient.setVisitNo(emr.getVisitNo());
		List<Patient> patients=patientService.findPatientsByNo(patient);*/
		Emr emradd=emrService.selectByPrimaryKey(emr.getId());
		Patient patient=new Patient();
		patient.setVisitNo(emradd.getVisitNo());
		List<Patient> patients=patientService.findPatientsByNo(patient);
		emradd.setPatient(patients.get(0));
		emradd.setIllnessHistory(illnessHistoryService.selectByPrimaryKey(emradd.getIllnessHistoryId()));
		session.setAttribute(Const.SESSION_CURRENT_EMR_VISITNO, Const.CURRENTEMR_ +emr.getVisitNo());	
		session.setAttribute(Const.SESSION_CURRENT_EMR, emradd);
		redisService.set( Const.CURRENTEMR_ +emr.getVisitNo(), emradd);
			
 		try {
			data = new ObjectMapper().writeValueAsString(emr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		mv.addObject("emr", data);
		return mv;
	}
	/**
	 * 将病人，医生信息封装到病历对象中
	 * @param emr
	 * @param user
	 * @param patient
	 */
	private void setPatientAndUserToEmr(Emr emr,User user){
		Patient patient = emr.getPatient();
		emr.setPatientName(patient.getName());
		emr.setPatientNo(patient.getPatientNo());
		emr.setVisitNo(patient.getVisitNo());
		emr.setDoctorId(user.getAccount());
		emr.setDoctorName(user.getUsername());
	}
	
	/**
	 * 接收前台病人数据
	 */
	@SysControllerLog(description="保存诊断信息")
	@RequestMapping(value="/recievepatient")
	
	public String recieve(Emr emr,HttpSession session){
	   String emrVisitNo = (String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO);
	   Emr redisemr = redisService.get(emrVisitNo, Emr.class);
	   redisemr.setSymptom(emr.getSymptom());
	   redisemr.setSymptommould(emr.getSymptommould());
	   redisemr.setDiseasewest(emr.getDiseasewest());
	   redisemr.setDisease(emr.getDisease());
	   redisemr.setChiefComplaint(emr.getChiefComplaint());
	   emr.getIllnessHistory().setId(redisemr.getIllnessHistoryId());
	   redisemr.setIllnessHistory(emr.getIllnessHistory());
	   redisService.set(emrVisitNo, redisemr);
	   return "T";
		
	}
	/**
	 * 诊断按钮判断跳转页面
	 * 
	 */
	@SysControllerLog(description="获取当前病人进入诊断页面")
	@RequestMapping(value="/checkdisease")
	public ModelAndView checkdisease(HttpSession session){
		ModelAndView mv = new ModelAndView();
		String emrVisitNo = (String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO);
		if(StringUtil.notEmpty(emrVisitNo)){
			Emr emr = redisService.get(emrVisitNo, Emr.class);
			mv.addObject("Emr", emr);
		}
		mv.setViewName("/kdys/patient.html");
		return mv;
		
	}
	/**
	 *通过病人卡号查询挂号信息
	 */
	@GetMapping("/patientInfo")
	public List<Patient> findPatientsByNo2(@RequestParam("patientNo") String patintNo ){
	   return patientService.findPatientsByNo2(patintNo);
	}

}
