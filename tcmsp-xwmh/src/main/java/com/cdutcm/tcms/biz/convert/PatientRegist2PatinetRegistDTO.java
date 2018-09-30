package com.cdutcm.tcms.biz.convert;

import com.cdutcm.tcms.biz.DTO.PatientRegistDTO;
import com.cdutcm.tcms.biz.model.PatientRegist;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Chandler
 * @Date: 2018/7/22 16:17
 * @Description:
 */
public class PatientRegist2PatinetRegistDTO {
    /**
     * 个体间转换
     */
    public static PatientRegistDTO convert(PatientRegist obj){
        PatientRegistDTO dto = new PatientRegistDTO();
        BeanUtils.copyProperties(obj,dto);
        return dto;
    }

    /**
     * 集合间转换 lambda表达式 jdk1.8及以上
     */
    public static List<PatientRegistDTO>convert(List<PatientRegist>objList){
        return objList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}