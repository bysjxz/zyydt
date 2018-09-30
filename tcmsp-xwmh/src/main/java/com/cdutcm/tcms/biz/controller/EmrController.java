package com.cdutcm.tcms.biz.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cdutcm.core.util.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.IllnessHistoryService;
import com.cdutcm.tcms.biz.service.PatientService;
import com.cdutcm.tcms.biz.service.RecipelItemService;
import com.cdutcm.tcms.biz.service.RecipelService;
import com.cdutcm.tcms.log.SysControllerLog;
import com.cdutcm.tcms.redis.service.IRedisService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 病历0000000000000
 * 
 * @author fw
 * 
 */
@RestController
@Controller
@RequestMapping(value = "/emr")
public class EmrController {

	@Autowired
	private EmrService emrService;

	@Autowired
	private RecipelService recipelService;

	@Autowired
	private RecipelItemService recipelItemService;
	
	@Autowired
	private IRedisService redisService;
	@Autowired 
	private  PatientService patientService;
    @Autowired 
    private IllnessHistoryService illnessHistoryService;
    
	//删除emr
    @RequestMapping(value = "/delemr")
    public SysMsg delemr(Long id){
    	SysMsg sm=new SysMsg();
    	int i=emrService.deleteByPrimaryKey(id);
    	if(i>0){
    		sm.setStatus("TS");
    	}else{
    		sm.setStatus("FS");
    	}
		return sm;
    }
    //修改emr
    @RequestMapping(value = "/editemr")
    public SysMsg editemr(Emr record){
    	SysMsg sm=new SysMsg();
    	int i=emrService.updateByPrimaryKey(record);
    	if(i>0){
    		sm.setStatus("TS");
    	}else{
    		sm.setStatus("FS");
    	}
    	return sm;
    }
    //查看病人信息详细
    @RequestMapping(value = "/showEmrAndPatient")
    public ModelAndView showEmrAndPatient(Long id){
    	ModelAndView mv=new ModelAndView();
    	Emr emr=emrService.selectEmrAndPatient(id);
    	mv.addObject("emr", emr);
		return mv;
    }
    /**
	 * 发送
	 * 
	 * @param recipel
	 * @return
	 */
    @SysControllerLog(description="发送处方")
	@RequestMapping(value = "/addrecipel")
	public SysMsg sendRecipel(String recipel,HttpSession session) {
    	SysMsg msg = new SysMsg();
		System.out.println(recipel);
		Recipel r = new Recipel();
		Emr e = new Emr();
		try {
			r = new ObjectMapper().readValue(recipel, Recipel.class);
			// 默认都是新增
			IdWorker i = new IdWorker();
			List<RecipelItem> items = r.getRecipelItem();
			long recipelid = i.nextId();
			long emrid = i.nextId();
			long illnessHistoryId=i.nextId();
			r.setLastupdate(new Date());
			r.setId(recipelid);
			String emrVisitNo = (String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO);
			if(StringUtil.notEmpty(emrVisitNo)){
				e =redisService.get(emrVisitNo,Emr.class);
				if(e.getPatient()==null){
					msg.setStatus("FS");
					msg.setContent("发送失败,未选择病人!");
					return msg;	
				}
			}
			else{
				msg.setStatus("FS");
				msg.setContent("发送失败,没有诊断信息！");
				return msg;	
			}
			// 如果有emrId则为新增
			
			System.out.println(e);
			if(r.getEmrId() != 0l){
				r.setEmrId(r.getEmrId());
			}else{
				// 插入病历
				e.setId(emrid);
				e.setCreateTime(new Date());
				if(e.getIllnessHistory()!=null){
					e.setIllnessHistoryId(illnessHistoryId);
					e.getIllnessHistory().setId(illnessHistoryId);
					illnessHistoryService.insert(e.getIllnessHistory());
				}
			if (StringUtil.isEmpty(e.getDisease())){
				msg.setStatus("FS");
				msg.setContent("发送失败,没有病名！");
				return msg;	
			}
				emrService.insert(e);	
				r.setEmrId(emrid);
			}
			r.setStatus("已发送");
			recipelService.insert(r);
			for (RecipelItem item : items) {
				item.setId(i.nextId());
				item.setRecipelId(recipelid);
				item.setLastupdate(new Date());
				recipelItemService.insert(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			msg.setStatus("FS");
			msg.setContent("发送失败,系统内部错误！");
			return msg;	
		}
		e.getPatient().setStatus("已诊");
		
		patientService.updateByPrimaryKeySelective(e.getPatient());
		session.removeAttribute(Const.SESSION_CURRENT_EMR_VISITNO);		
		session.removeAttribute(Const.SESSION_CURRENT_EMR);		
		System.out.println(r);
		msg.setStatus("TS");
		msg.setContent("发送成功--"+recipel);
		return msg;
	}
    @SysControllerLog(description="处方编辑")
	@RequestMapping(value = "/editrecipel")
    public SysMsg editRecipel(String recipel,Long id,HttpSession session){
    	SysMsg msg = new SysMsg();
		Recipel r = new Recipel();
		try {
			IdWorker i = new IdWorker();
			r = new ObjectMapper().readValue(recipel, Recipel.class);
			//编辑处方
			//Auto  病历修改
		    Emr emr=redisService.get((String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO),Emr.class);
		    emrService.updateByPrimaryKey(emr);
		    illnessHistoryService.updateByPrimaryKey(emr.getIllnessHistory());
			// 更新处方和处方详情
			r.setLastupdate(new Date());
			recipelService.updateByPrimaryKey(r);
			List<RecipelItem> items = r.getRecipelItem();
			// 删除处方详情表数据
			recipelItemService.deleteByPrimaryRecipelId(r.getId());
			for (RecipelItem item : items) {
				item.setId(i.nextId());
				item.setRecipelId(r.getId());
			    item.setLastupdate(new Date());
				recipelItemService.insert(item);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setStatus("FS");
			msg.setContent("发送失败,系统内部错误！");
			return msg;
		}
		session.removeAttribute(Const.SESSION_CURRENT_EMR_VISITNO);		
		session.removeAttribute(Const.SESSION_CURRENT_EMR);	
		msg.setStatus("TS");
		msg.setContent("编辑成功--"+recipel);
		return msg;
    }
	@RequestMapping(value = "/findRecipel")
	public ModelAndView findRecipel(Patient patient) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/kdys/patient_reicpels_info.html");
		mv.addObject("name", "松果医院非药物处方");
		return mv;

	}
	/**
	 * 通过卡号查询病例
	 */
	@GetMapping("/emrDetail")
	public Emr findEmrByPatientNo(@RequestParam("patientNo") String patientNo){
		return emrService.selectByPatientNo(patientNo);
	}
	/**
	 * 导出病历
	 */
	@RequestMapping("/export")
	public ModelAndView export(HttpServletRequest request,@RequestParam("emrId")Long emrId){

		//查询病历的所有信息
		Emr emr = emrService.selectByPrimaryKey(emrId);
		//通过查询患者信息
		Patient patient = patientService.selectByPatientNo(emr.getPatientNo());
		ModelAndView mv = new ModelAndView();
		String fileName = "cdutcm"+RandomUtil.generate(6)+".doc";//生成的文件名字

		File dir = new File("D:/File/emr");
		if (!dir.exists()){
			dir.mkdir();//本地文件夹不存在，创建一个
		}
		String expFile = "D:/File/emr/"+ fileName;//生成文件的保存路径

		//读取模板路径(在本地读取）
		String tmpFile = "D:/File/template.doc";
		//读取模板路径(在服务器读取）
		String tmpFile_2 = request.getSession().getServletContext().getRealPath("template4.doc");
		//将数据写入模板
		Map<String, String> datas = new HashMap<String, String>();
		datas.put("number", "xxxx");
		datas.put("depart", "中医外科");
		datas.put("time", "2018-08-25");
		datas.put("name", "小明");

		datas.put("sex", patient.getSex());
		datas.put("age", patient.getAge().toString());
		datas.put("height" ,patient.getHeight());
		datas.put("weight", patient.getWeight());
		datas.put("phone",  patient.getTelephone());
		datas.put("address", patient.getAddress());
		try {
			BuildFileUtil.build(ResourceUtils.getFile(tmpFile_2),datas,expFile);
		}catch (Exception e){
			e.printStackTrace();
		}

		String redirectUrl  = "/emrfile/"+fileName;
		mv.setViewName("redirect:"+redirectUrl);
		return mv;
	}
}
