package com.cdutcm.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Auther: Mxq
 * @Date: 2018/8/9 16:01
 * @description:
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BuildFileUtilTest {

    @Test
    public void test1(){
        String tmpFile = "D:/File/emr/template.doc";
        String expFile = "D:/File/result.doc";
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("title", "标题部份");
        datas.put("content", "这里是内容，测试使用POI导出到Word的内容！");
        datas.put("author", "知识林");
        datas.put("url", "http://www.zslin.com");
        try {
            BuildFileUtil.build(ResourceUtils.getFile(tmpFile),datas,expFile);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}