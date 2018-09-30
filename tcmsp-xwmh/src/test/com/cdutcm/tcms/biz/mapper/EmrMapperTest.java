package com.cdutcm.tcms.biz.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdutcm.tcms.biz.model.Emr;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmrMapperTest {

	@Autowired
	private EmrMapper mapper;
	
	@Test
	public void selectByPrimaryPatientNotest() {
		Emr emr = mapper.selectByPatientNo("20130110000066");
		System.out.println(emr.getDisease());
	}

}
