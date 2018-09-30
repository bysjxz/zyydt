package com.cdutcm.tcms.biz.DTO;

import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;

import java.util.List;

/**
 * @Auther: Chandler
 * @Date: 2018/7/22 15:52
 * @Description:
 */
public class PatientRegistDTO {
    private Long id;
    /**
     * 医生
     */
    private String ys;

    /**
     * 医生编码
     */
    private String ysbm;

    /**
     * 挂号科室
     */
    private String ghks;

    /**
     * 科室编码
     */
    private String ghksbm;

    /**
     * 病人卡号
     */
    private String patientNo;

    /**
     * 挂号序号/入院号
     */
    private String visitNo;

    /**
     * 挂号时间/入院时间
     */
    private String kssj;

    /**
     * 门诊或住院 默认为门诊 1:门诊 2：住院
     */
    private String io;

    private String disease;
    private String status;
    /**
     * 基本信息
     */
    private Patient basicInfo;
    /**
     * 病历 可不要
     */
    private List<Emr>emrs;

    public Patient getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(Patient basicInfo) {
        this.basicInfo = basicInfo;
    }

    public List<Emr> getEmrs() {
        return emrs;
    }

    public void setEmrs(List<Emr> emrs) {
        this.emrs = emrs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getYsbm() {
        return ysbm;
    }

    public void setYsbm(String ysbm) {
        this.ysbm = ysbm;
    }

    public String getGhks() {
        return ghks;
    }

    public void setGhks(String ghks) {
        this.ghks = ghks;
    }

    public String getGhksbm() {
        return ghksbm;
    }

    public void setGhksbm(String ghksbm) {
        this.ghksbm = ghksbm;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}