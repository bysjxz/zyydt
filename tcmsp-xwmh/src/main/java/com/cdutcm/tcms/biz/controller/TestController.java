package com.cdutcm.tcms.biz.controller;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.mapper.EmrMapper;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.service.EmrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Mxq
 * @Date: 2018/8/8  09:36
 * @description: 单元测试所用
 */
@Controller

public class TestController {

    @Autowired
    private EmrMapper emrMapper;

    @Autowired
    private EmrService emrService;


    @RequestMapping("test")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView();
        int currentPage = 1;
        int  pageSize = 5;
        String key3 = "00026";
        String key2=null;
        String key1 = null;
        Page page = emrService.pageEmr(currentPage,pageSize,key1,key2,key3);
        mv.addObject("rs",page);
        return mv;
    }
}
