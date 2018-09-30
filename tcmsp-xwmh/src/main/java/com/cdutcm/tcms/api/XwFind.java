package com.cdutcm.tcms.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.tcms.DTO.ResultDTO;
import com.cdutcm.tcms.biz.model.Xw;
import com.cdutcm.tcms.biz.service.XwService;
import com.cdutcm.tcms.util.ResultUtil;

@RestController
@RequestMapping("xw")
public class XwFind {
	
	@Autowired
	private XwService xwService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("find")
	public ResultDTO xw(String xww){
		ResultUtil resultUtil = new ResultUtil();
		//根据名字和拼音查询穴位
		if(xww==null||"".equals(xww)){
		return resultUtil.error_xw();
		}else{
			Xw xw = new Xw();
			xw.setName(xww);
			xw.setPinyin(xww);
			List<Xw> x = xwService.findXwByNameOrPinYin(xw);
			//System.out.println(x);
			if(x==null||"".equals(x)||x.equals("")){
				return resultUtil.error_xwFind();
			}else{
				return resultUtil.success(x);
			}
		}
	}
	
	

}
