package com.cdutcm.tcms.biz.controller;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.service.PatientRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: Mxq
 * @Date: 2018/8/6 16:16
 * @description:  病人挂号信息 00000000
 */
@Controller
public class patientRegistController {

    @Autowired
    private PatientRegistService patientRegistService;

    @RequestMapping("/userInfo")
    public ModelAndView userInfo(@RequestParam(defaultValue = "1",value = "currentPage")Integer currentPage,
                                 @RequestParam(defaultValue = "#@",value = "key1")String key1,
                                 @RequestParam(defaultValue = "待诊",value = "key2")String key2){
        int pageSize = 10;//每页显示的数量
        ModelAndView mv = new ModelAndView("/zj/userInfo.html");
        if (key1.equals("#@")){
            key1 = null;
        }
        if (key2.equals("search")){
            key2 = null;
        }
//        Page page = patientRegistService.pageRegist(currentPage,pageSize,key1,null);
//        mv.addObject("rs",page);
        return mv;
    }
}


