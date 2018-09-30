package com.cdutcm.tcms.biz.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.core.util.ZtreeUtil;
import com.cdutcm.tcms.biz.model.BaseRecipel;
import com.cdutcm.tcms.biz.model.BaseRecipelItem;
import com.cdutcm.tcms.biz.service.BaseRecipelItemService;
import com.cdutcm.tcms.biz.service.BaseRecipelService;
import com.cdutcm.tcms.sys.entity.ZtreeModal;
import com.google.gson.Gson;

/**
 * 
 * @author fw
 * @Description 模板
 * @Date 2018-01-19
 */
@RestController
@RequestMapping(value = "baserecipel")
public class BaseRecipelController {
	@Autowired
	private BaseRecipelService baseRecipelService;
	
	@Autowired
	private BaseRecipelItemService baseRecipelItemService;
    
	private List<BaseRecipelItem> baseRecipelItem;
	public List<BaseRecipelItem> getBaseRecipelItem() {
		return baseRecipelItem;
	}

	public void setBaseRecipelItem(List<BaseRecipelItem> baseRecipelItem) {
		this.baseRecipelItem = baseRecipelItem;
	}

	/**
	 * 根据模板名称生成树形菜单
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/getbaserecipelztree")
	public String getZtree(BaseRecipel record) {
		BaseRecipel base = baseRecipelService
				.listPagefindBaseRecipelById(record);
		ZtreeModal ztree = ZtreeUtil.ToZTree(base);
		JSONArray arr = JSONArray.fromObject(ztree);
		return arr.toString();
	}

	@RequestMapping(value = "/getbaserecipelnames")
	public ModelAndView getBaseRecipelNames(BaseRecipel record) {
		ModelAndView m = new ModelAndView();
		List<BaseRecipel> bs = baseRecipelService.findBaseRecipel(record);
		m.addObject("BaseReicpel", bs);
		m.setViewName("/tjmb/left_baserecipelname.html");
		return m;
	}
	
//经方列表
	@RequestMapping(value = "/baserecipeltable.html")
	public ModelAndView listPageBaseRecipel(BaseRecipel baserecipel) {
		
		ModelAndView m = new ModelAndView("/jfgl/baserecipel_table.html");
		List<BaseRecipel> d = baseRecipelService.listPageBaseRecipel(baserecipel);
		m.addObject("baserecipel", baserecipel);
		m.addObject("datas", d);
		return m;
	}
//经方查询列表
	@RequestMapping(value = "/baserecipelSerach.html")
	public ModelAndView SerachDisease(BaseRecipel baserecipel) {
		ModelAndView m = new ModelAndView("/jfgl/baserecipel_serach_table.html");
		List<BaseRecipel> d1 = baseRecipelService.selectBySelective(baserecipel);
		m.addObject("po", baserecipel);
		m.addObject("datas", d1);
		m.addObject("url", "/jfgl/baserecipelSerach.html");
		return m;
	}
	//经方详细列表
	@RequestMapping(value = "/listBRItem")
	public ModelAndView getAllBaseRecipelItem(Long id){
		ModelAndView m = new ModelAndView("/jfgl/britem_table.html");
		List<BaseRecipelItem> b= baseRecipelItemService.listBRItem(id);	
		m.addObject("po", id);
		m.addObject("datas", b);
		return m;		
	}
	
	//请求新增经方页面
	@RequestMapping(value="/addBaseRecipel")
	public ModelAndView addBaseRecipel(BaseRecipel br){
		ModelAndView m = new ModelAndView("/jfgl/baserecipel_info.html");	
		m.addObject("baserecipel", br);
		return m;	
	}
	//插入经方
	@RequestMapping(value = "/insertBaseRecipel", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public SysMsg insertBaseRecipel(HttpServletRequest request) throws Exception{
		
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
		BaseRecipel br = new Gson().fromJson(body, BaseRecipel.class);
		SysMsg m=new SysMsg();		
		BaseRecipel count=baseRecipelService.getCountByName(br);
		if(count==null){
			IdWorker w = new IdWorker();
			long brid=w.nextId();//赋予随机ID
			br.setId(brid);
		    int i=baseRecipelService.insert(br); 
		    List<BaseRecipelItem> items=br.getBaseRecipelItem();//插入经方详细列表
		    for(BaseRecipelItem item : items) {
		    	IdWorker k = new IdWorker();
		    	item.setId(k.nextId());
		    	item.setRecipelId(brid);
		    	item.setLastupdate(new Date());
		    	baseRecipelItemService.insert(item);
		         }
		   if (i > 0) {	
			  m.setStatus("TS");
	     	  } else {
			  m.setStatus("FS");	
		   }			
		}else{
			m.setStatus("FS");
			m.setContent("经方名称重复");
		}
		return m;
		
	}
	//修改时候新增经方详细列表
  @RequestMapping(value = "/insertItem",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public SysMsg inserBrItem(HttpServletRequest request) throws Exception{
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
		
		List<BaseRecipelItem> items=(List<BaseRecipelItem>)JSONArray.toList(JSONArray.fromObject(body), BaseRecipelItem.class); 
		
		SysMsg m = new SysMsg();	
		for(BaseRecipelItem item : items) {
				IdWorker w = new IdWorker();
				item.setId(w.nextId());
				int i=baseRecipelItemService.insert(item);
			if (i > 0) {
				m.setStatus("TS");
				
			} else {
				m.setStatus("FS");	
					}
			}
	
		return m;
	}
	//请求经方修改页面
	@RequestMapping(value = "/editBaseRecipel")
	public ModelAndView editBaeseRecipel(long id) {
		ModelAndView m = new ModelAndView("/jfgl/baserecipel_edit.html");
		BaseRecipel br = baseRecipelService.selectByPrimaryKey(id);		
		m.addObject("br",br );
		return m;
	}
	//修改经方
	@RequestMapping(value = "/updateBaseRecipel")
	public SysMsg updataBaseRecipel(BaseRecipel record){
		SysMsg m = new SysMsg();
		int i=baseRecipelService.updateByPrimaryKey(record);
		if (i > 0) {
			m.setStatus("TS");		
		} else {
			m.setStatus("FS");	
		}
		return m;
	}
	//请求经方详细列表修改页面
	@RequestMapping(value = "/editbrItem")
	public ModelAndView editBRItem(long id){
		ModelAndView m = new ModelAndView("/jfgl/britem_edit.html");
		List<BaseRecipelItem> britem= baseRecipelItemService.listBRItem(id);
		m.addObject("po", id);
		m.addObject("britems",britem);
		return m;	
	}

	
	//修改经方详细列表
	@RequestMapping(value = "/updatebrItem",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public SysMsg updatebrItem(HttpServletRequest request) throws Exception{
	
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
		
		List<BaseRecipelItem> items=(List<BaseRecipelItem>)JSONArray.toList(JSONArray.fromObject(body), BaseRecipelItem.class); 
		
		SysMsg m = new SysMsg();	
		for(BaseRecipelItem item : items) {
			if (item.getId()==null){
				IdWorker w = new IdWorker();
				item.setId(w.nextId());
				baseRecipelItemService.insert(item);
			}else{
			int i=baseRecipelItemService.updateByPrimaryKeySelective(item);
			
			if (i > 0) {
				m.setStatus("TS");
				
			} else {
				m.setStatus("FS");	
			}
			}
		}
		
		return m;
	}

	//删除
	@RequestMapping(value = "/delBaseRecipel")
	public SysMsg delBaseRecipel(BaseRecipel record) {
		SysMsg m = new SysMsg();
		int i = baseRecipelService.deleteByPrimaryKey(record.getId());
		if (i > 0) {
			m.setStatus("TS");
			m.setContent("成功删除" + i + "条数据");
		} else {
			m.setStatus("FS");
			m.setContent("删除失败!");
		}
		return m;
	};
	//删除经方详细列表
	@RequestMapping(value = "/delitem")
	public SysMsg delBRItem(BaseRecipelItem record) {
		SysMsg m = new SysMsg();
		int i =baseRecipelItemService.deleteByPrimaryKey(record.getId());
		if (i > 0) {
			m.setStatus("TS");
			m.setContent("成功删除" + i + "条数据");
		} else {
			m.setStatus("FS");
			m.setContent("删除失败!");
		}
		return m;
	};
	
}
