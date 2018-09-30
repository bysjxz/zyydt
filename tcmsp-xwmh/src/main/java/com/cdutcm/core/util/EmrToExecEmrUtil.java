package com.cdutcm.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.ExecEmr;
import com.cdutcm.tcms.biz.model.ExecRecipelItem;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;

public class EmrToExecEmrUtil {
	// 处方对象转开单医生处方对象
	public static List<ExecEmr> toExecEmr(List<Emr> emrs){
		List<ExecEmr> execEmrs  = new ArrayList<ExecEmr>();
		for (Emr e :emrs){
		   List<Recipel> recipels = e.getRecipels();
		   if(!StringUtil.isEmptyList(recipels)){
			   for (Recipel r: recipels){
				   // 先取出这个处方的类型集合
				   Set<String> ctypes = new HashSet<String>();
				   for(RecipelItem recipelitem: r.getRecipelItem()){
					  ctypes.add(recipelitem.getCtype());
				   }
				   // 根据处方的类型集合来筛选
				   for(String s :ctypes){
					   ExecEmr  execEmr = new ExecEmr();
					   List<ExecRecipelItem> execRecipelItems = new ArrayList<ExecRecipelItem>();
					    try {
						    execEmr = (ExecEmr) ClassUtil.copyObject(e, execEmr);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
				      for(RecipelItem recipelitem: r.getRecipelItem()){
						ExecRecipelItem execRecipelItem = new ExecRecipelItem();
						 if(s.equals(recipelitem.getCtype())){
							 execEmr.setCtype(s);
							 try {
								execRecipelItem = (ExecRecipelItem) ClassUtil.copyObject(recipelitem, execRecipelItem);
								// 已执行要设置值
								if("已执行".equals(execRecipelItem.getStatus())){
									execRecipelItem.setStatus("checkmark-circled");
								}else{
									execRecipelItem.setStatus("ios-play");
								}	
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							 execRecipelItems.add(execRecipelItem);
						 }
					   }			  
				      execEmr.setExecRecipelItems(execRecipelItems);
				      execEmrs.add(execEmr);
				   }
			   }
		   }
		}
		
		return execEmrs;
	}

}
