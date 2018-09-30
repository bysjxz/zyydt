package com.cdutcm.tcms.biz.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.EmrToExecEmrUtil;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.ExecEmr;
import com.cdutcm.tcms.biz.model.ExecRecipel;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.ExecRecipelService;
import com.cdutcm.tcms.biz.service.RecipelItemService;
import com.cdutcm.tcms.biz.service.RecipelService;

@RestController
@RequestMapping(value ="exec")
public class ExecRecipelController {
	
	@Autowired
	private ExecRecipelService execRecipelService;
	
	@Autowired
	private RecipelService recipelService;
	
	@Autowired
	private RecipelItemService recipelItemService;

	@Autowired
	private EmrService emrService;
	
	@RequestMapping(value = "/findemr")
	@ResponseBody
	public List<ExecEmr> findEmr(){
		List<Emr> emrs = emrService.findAllEmr();
		for (Emr emr : emrs){
			Recipel r = new Recipel();
			r.setEmrId(emr.getId());
			List<Recipel> recipel = recipelService.listPagefindRecipelById(r);
			if(!StringUtil.isEmptyList(recipel)){
				emr.setRecipels(recipel);
			}
		}	
		return EmrToExecEmrUtil.toExecEmr(emrs);
	}
	@ResponseBody
	@RequestMapping(value ="recipel/{type}")
	public SysMsg execRecipelUpdate(ExecRecipel record,@PathVariable String type){
		SysMsg msg = new SysMsg();
		if("pause".equals(type)){
			record.setEndtime(new Date());
			execRecipelService.updateExecRecipelByRecipelIdAndRecipelItemId(record);
		}else {
			// 判断是否已经有值
			List<ExecRecipel> execs = execRecipelService.findByRecipelId(record);
			if(execs.size()>0) {
				record.setStarttime(new Date());
				execRecipelService.updateExecRecipelByRecipelIdAndRecipelItemId(record);
				return msg;
			}
			RecipelItem reItem = new  RecipelItem();
			reItem.setRecipelId(record.getRecipelId());
			List<RecipelItem> reis = recipelItemService.findRecipelItemByCtypeAndRecipelId(reItem);
			for (RecipelItem r : reis){			
				if(record.getRecipelItemId().equals(r.getId())){
					record.setStarttime(new Date());
				}else {
					record.setStarttime(null);
				}
				record.setEndtime(null);
				record.setXwname(r.getName());
				record.setRecipelItemId(r.getId());
			    execRecipelService.insert(record);
			}	
		}
		msg.setContent("");
		msg.setStatus("TS");
		return msg;	
	}
	@ResponseBody
	@RequestMapping(value ="recipel/saveStatus")
	public SysMsg execRecipelSaveStatus(ExecRecipel record){
		SysMsg msg = new SysMsg();
		int i;
		i = execRecipelService.updateExecRecipelStatus(record);
		// 自动添加结束时间
		
		if(i>0){
			msg.setContent("");
			msg.setStatus("TS");
		}else{
			msg.setStatus("FS");
		}
		return msg;
	}

}
