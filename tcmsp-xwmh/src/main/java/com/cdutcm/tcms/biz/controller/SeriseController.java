package com.cdutcm.tcms.biz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;



@RestController
@RequestMapping(value="/toDiagnosis")
public class SeriseController {
	@RequestMapping(value="/getSeries")
	public ModelAndView sendSerie(String xwame){
		ModelAndView mv=new ModelAndView();
		List<String> list = new ArrayList<String>();
		
	    list =(List<String>) JSONArray.fromObject(xwame );
		System.out.println(list);
		/*list.add("四百");
		list.add("关元");
		list.add("迎春");*/
		mv.addObject("list", list);
		mv.setViewName("/Diagnosis.html");
		return mv;
	}
	@RequestMapping(value="/getSeries2")
	public ModelAndView sendSerie2(String s1,String s2){
		ModelAndView mv=new ModelAndView();
		System.out.println(s1+"=========="+s2);
		List<String> s1list = new ArrayList<String>();
		List<String> s2list = new ArrayList<String>();
		s1list = (List<String>)JSONArray.fromObject(s1);
		s2list = (List<String>)JSONArray.fromObject(s2);
		mv.addObject("s1list",s1list);
		mv.addObject("s2list",s2list);
        //mv.addObject("s1list",s1);
		//mv.addObject("s2list",s2);
		mv.setViewName("/Diagnosis1.html");
		return mv;
	}
}
