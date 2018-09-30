package com.cdutcm.tcms.biz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.tcms.biz.model.Xw;
import com.cdutcm.tcms.biz.service.XwService;

@RestController
public class XwController {
	@Autowired
	private XwService xwService;

	@RequestMapping(value = "/serach")
	@ResponseBody
	public List<Xw> serach(HttpServletRequest request) {
		Xw x = new Xw();
		x.setPinyin(request.getParameter("q"));
		x.setName(request.getParameter("q"));
		return xwService.findXwByNameOrPinYin(x);
	}

}
