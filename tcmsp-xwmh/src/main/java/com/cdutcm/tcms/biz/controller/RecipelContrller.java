package com.cdutcm.tcms.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.core.util.ResultVO;
import com.cdutcm.core.util.ResultVOUtil;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.RecipelItemService;
import com.cdutcm.tcms.biz.service.RecipelService;


@RequestMapping("/recipel")
@RestController
public class RecipelContrller {
	
	
	@Autowired
	private RecipelService recipelService;
	
	@Autowired
	private RecipelItemService recipelItemService;

	/**
	 * 1.通过id查询处方
	 */
	@GetMapping("/recipelMaster")
	public Recipel findRecipel(@RequestParam("recipelId")Long recipelId){
		return recipelService.selectByPrimaryKey(recipelId);
	}
	/**
	 * 2.查询处方明细
	 */
	@GetMapping("/recipelDetail")
	public ResultVO<List<RecipelItem>> findRecipelItem2(){
		List<RecipelItem>recipelItems = recipelItemService.findRecipelItemByRecipelId(134464853526970368L);
		return ResultVOUtil.success(recipelItems);
	}
}
