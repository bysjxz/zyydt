package com.cdutcm.tcms.biz.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdutcm.tcms.biz.model.Recipel;
@SpringBootTest
@RunWith(SpringRunner.class)
public class RecipelMapperTest {

	@Autowired
	private RecipelMapper mapper;
	
	@Test
	public void test() {
		Recipel recipel = mapper.selectByPrimaryKey(134464853526970368L);
		System.out.println("查找到的处方是："+recipel.getName());
	}

}
