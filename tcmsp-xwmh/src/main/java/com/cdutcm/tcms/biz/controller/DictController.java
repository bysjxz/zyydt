package com.cdutcm.tcms.biz.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.biz.model.BaseRecipelItem;
import com.cdutcm.tcms.biz.model.Dict;
import com.cdutcm.tcms.biz.service.DictService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value ="dict")
public class DictController {
	@Autowired
	private DictService dictService;
	@RequestMapping(value ="/dictTable")
	public ModelAndView listPageDict(Dict record,Long pid){
		ModelAndView m = new ModelAndView("/dict/dict_table.html");	
		List<Dict> d=dictService.listPageDict(record);	
	    m.addObject("dict", record);
		m.addObject("datas", d);
		return m;
	
	}
	//字典详细信息000000
	@RequestMapping(value ="/dictItem")
	public ModelAndView dictItem(Long pid){
		ModelAndView m = new ModelAndView("/dict/dictItem_table.html");
		List<Dict> dictItem=dictService.selectByPid(pid);
		m.addObject("items", dictItem);
		return m;		
	}
	//删除父类字典
	@RequestMapping(value ="/delDict")
	public SysMsg deleDict(Dict record){
	
		return dictService.deleteByPrimaryKey(record.getId());
	}
	//修改名称
	@RequestMapping(value ="/editDict")
	public SysMsg editDict(Dict record){		
		return dictService.editname(record);
		
	}
 //保存字典
	@RequestMapping(value = "/saveDict", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public SysMsg saveDict(HttpServletRequest request) throws Exception{
		SysMsg m=new SysMsg();
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream, "utf-8"));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		body = stringBuilder.toString();
		Dict d=new Gson().fromJson(body,Dict.class);
		IdWorker w = new IdWorker();
		Long dictid=w.nextId();
		d.setId(dictid);//生成ID
		d.setNum(0);
		d.setPid(0L);
		dictService.insert(d); //插入父类字典
		List<Dict> items=d.getDictItem();
		for(Dict item:items){
			IdWorker k = new IdWorker();
			item.setId(k.nextId());
			item.setPid(dictid);
		int i=	dictService.insert(item); //插入子类字典
		if (i > 0) {
			m.setStatus("TS");
			
		} else {
			m.setStatus("FS");	
				}
		}
		return m;
	}
	//修改和添加字典子类
	@RequestMapping(value = "/editItem", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public SysMsg editItem(HttpServletRequest request) throws Exception{
		SysMsg m=new SysMsg();
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream, "utf-8"));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		body = stringBuilder.toString();
		
		List<Dict> items=(List<Dict>)JSONArray.toList(JSONArray.fromObject(body), Dict.class); 
		for(Dict item:items){
			if (item.getId()==null){
				IdWorker w = new IdWorker();
				item.setId(w.nextId());
				dictService.insert(item);
			}else{
				int i=dictService.updateByPrimaryKey(item);
				if (i > 0) {
					m.setStatus("TS");					
				} else {
					m.setStatus("FS");	
				}
			}
			
		}
		return m;
	
	}
}
