package com.cdutcm.tcms.biz.service.impl;

import com.cdutcm.core.page2.Page;
import com.cdutcm.tcms.biz.mapper.PatientRegistMapper;
import com.cdutcm.tcms.biz.model.PatientRegist;
import com.cdutcm.tcms.biz.service.PatientRegistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: Mxq
 * @Date: 2018/8/6 15:42
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientRegistServiceImplTest {

    @Autowired
    private PatientRegistService registService;

    @Autowired
    private PatientRegistMapper registMapper;




}