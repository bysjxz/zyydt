package com.cdutcm.tcms.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.biz.model.PatientCard;
import com.cdutcm.tcms.biz.service.PatientCardService;

@RestController
@RequestMapping("patientCard") 
public class PatientCardController {

	@Autowired
	private PatientCardService patientCardService;
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public SysMsg savePatientCard(PatientCard card,String token){
		 return patientCardService.insert(card);
	}
	
	//修改
	 @RequestMapping(value="/edit",method = RequestMethod.PUT)
	 public SysMsg updatePatientCard(PatientCard record){
		
		 return patientCardService.updateByPrimaryKey(record);
		 
	 }
	 //删除
	 @RequestMapping(value="/delete",method = RequestMethod.DELETE)
	 public SysMsg deletePatientCard(Long id){
		
		 return patientCardService.deleteByPrimaryKey(id);
	 }

}
