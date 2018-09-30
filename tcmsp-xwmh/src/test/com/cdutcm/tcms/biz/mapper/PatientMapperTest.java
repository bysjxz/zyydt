package com.cdutcm.tcms.biz.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdutcm.tcms.biz.model.Patient;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientMapperTest {

	@Autowired
	private PatientMapper mapper;
	
	//@Test
	public void test() {
		Patient patient = mapper.selectByPrimaryKey(8L);
		System.out.println("查找到的姓名是："+patient.getName());
	}
	@Test
	public void findPatientsByNo2() {
		List<Patient>plList = mapper.findPatientsByNo2("20130107000090");
//		System.out.println("查找到的姓名是："+patient.getName());
	}
	

}
