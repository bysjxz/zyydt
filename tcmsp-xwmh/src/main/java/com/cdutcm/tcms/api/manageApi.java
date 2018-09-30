package com.cdutcm.tcms.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.tcms.DTO.RecipelDTO;
import com.cdutcm.tcms.DTO.ResultDTO;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.RecipelItemService;
import com.cdutcm.tcms.biz.service.RecipelService;
import com.cdutcm.tcms.util.ResultUtil;

@RestController
@RequestMapping("recipe")
public class manageApi {
	
	@Autowired
	private EmrService emrService;
	
	@Autowired
	private RecipelService recipelService;
	
	@Autowired
	private RecipelItemService recipelItemService;
	
	@SuppressWarnings("rawtypes")
	public static ResultDTO turn = null;
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("find")
	public ResultDTO find(String disease){
		//disease = "感冒";
		ResultUtil resultUtil = new ResultUtil();
		RecipelDTO RecipelDTO = new RecipelDTO();
		if(disease!=null||disease!=""){
		//根据病症查询EMR的多个id
		List<Emr> a = emrService.selectIdByDisease(disease);
		//System.out.println(a);
		//将List数据存入数组分配
		List<RecipelDTO> cc = new ArrayList<>();
		long[] arra = new long[a.size()];
		for(int i=0;i<a.size();i++){
			a.get(i).getId();
			Recipel b = recipelService.selectIdByEmrId(a.get(i).getId());
			arra[i] = b.getId();
		}
		for(int j=0;j<arra.length;j++){
		List<RecipelDTO> c= recipelItemService.findRecipelDTOByRecipelId(arra[j]);
		cc.addAll(c);
		//System.out.println(c);
		if(cc!=null){
			turn = resultUtil.successRecipelDTO(cc);
		}
		else{turn = resultUtil.error_recipeFind();}
		}
		}else{
				turn = resultUtil.error_recipeFind();
		}
		return turn;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("add")
	public ResultDTO add(RecipelDTO recipelDTO){
		ResultUtil resultUtil = new ResultUtil();
		if(recipelDTO.getName() == null||"".equals(recipelDTO.getName())){
			turn = resultUtil.error_add();
		}else{
			int code= recipelItemService.insert(recipelDTO);
			if(code == 1){
				turn = resultUtil.success();
			}else{
				turn = resultUtil.error();
			}
		}
		return turn;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("updata")
	public ResultDTO updata(RecipelDTO recipelDTO){
		recipelDTO.setRecipelId(1111111111);
		recipelDTO.setName("hhh");
		ResultUtil resultUtil = new ResultUtil();
		if(recipelDTO.getName()!=null){
		 int code = recipelItemService.updateByPrimaryKeyDTO(recipelDTO);
		 if(code == 1){
				turn = resultUtil.success();
			}else{
				turn = resultUtil.error();
			}
		}else{
			turn = resultUtil.error_updata();
		}
		return turn;
		
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("del")
	public ResultDTO del(@RequestParam("id") long id){
		ResultUtil resultUtil = new ResultUtil();
		int code = recipelItemService.deleteByPrimaryRecipelId(id);
		if(code == 0){
			turn = resultUtil.success();
		}else{
			turn = resultUtil.error();
		}
		return turn;
		
	}

}
