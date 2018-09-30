package com.cdutcm.tcms.util;


import java.util.List;

import com.cdutcm.tcms.DTO.RecipelDTO;
import com.cdutcm.tcms.DTO.ResultDTO;
import com.cdutcm.tcms.DTO.UserInfoDTO;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.model.Xw;

public class ResultUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultDTO success(UserInfoDTO userInfoDTO){
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(0);
		resultDTO.setMsg("success!");
		resultDTO.setData(userInfoDTO);
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public ResultDTO success(List<Xw> xw){
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(0);
		resultDTO.setMsg("success!");
		resultDTO.setData(xw);
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultDTO successRecipelItem(List<RecipelItem> Recipe){
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(0);
		resultDTO.setMsg("success!");
		resultDTO.setData(Recipe);
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultDTO successRecipelDTO(List<RecipelDTO> Recipe){
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(0);
		resultDTO.setMsg("success!");
		resultDTO.setData(Recipe);
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO success(){
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(0);
		resultDTO.setMsg("success!");
		return resultDTO;
		
	} 
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_userInfo(){
		ResultDTO resultDTO = new ResultDTO();
		//用户信息不存在
		resultDTO.setCode(1002);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	} 
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_user(){
		ResultDTO resultDTO = new ResultDTO();
		//用户不存在
		resultDTO.setCode(1001);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_updataUser(){
		ResultDTO resultDTO = new ResultDTO();
		//修改个人信息失败
		resultDTO.setCode(1003);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_xwFind(){
		ResultDTO resultDTO = new ResultDTO();
		//穴位查询失败
		resultDTO.setCode(1004);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_xw(){
		ResultDTO resultDTO = new ResultDTO();
		//未指定穴位
		resultDTO.setCode(1005);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_recipeFind(){
		ResultDTO resultDTO = new ResultDTO();
		//处方不存在
		resultDTO.setCode(1006);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_add(){
		ResultDTO resultDTO = new ResultDTO();
		//无增加信息
		resultDTO.setCode(1007);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error_updata(){
		ResultDTO resultDTO = new ResultDTO();
		//无更改处方信息
		resultDTO.setCode(1008);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public ResultDTO error(){
		ResultDTO resultDTO = new ResultDTO();
		//修改个人信息失败
		resultDTO.setCode(0003);
		resultDTO.setMsg("error!");
		return resultDTO;
		
	}

}
