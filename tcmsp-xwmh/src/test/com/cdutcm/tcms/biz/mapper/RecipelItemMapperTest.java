package com.cdutcm.tcms.biz.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdutcm.tcms.biz.model.RecipelItem;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RecipelItemMapperTest {

	@Autowired
	private RecipelItemMapper mapper;
	
	@Test
	public void test() {
		List<RecipelItem> recipelItems = mapper.findRecipelItemByRecipelId(134464853526970368L);
	}

}
