package com.cdutcm.tcms.biz.model;

import java.util.Date;
import java.util.List;

import com.cdutcm.core.page.Page;
import com.cdutcm.core.util.JsonIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Emr {
	@JsonSerialize(using = JsonIDSerializer.class)
    private Long id;

    private String disease;

    private String symptommould;

    private String symptom;

    private String fsCode;

    private String chiefComplaint;

    private Long illnessHistoryId;

    private String bw;

    private String bx;

    private String therapy;

    private Date createTime;

    private String lunarTime;

    private String patientNo;

    private String visitNo;

    private String patientName;

    private String doctorId;

    private String doctorName;

    private String deptId;

    private String deptName;

    private Date endTime;
    /**
     * 西医诊断
     */
    private String diseasewest;
    
    private List<Recipel> r;
    
    public List<Recipel> getR() {
		return r;
	}

	public void setR(List<Recipel> r) {
		this.r = r;
	}

	public String getDiseasewest() {
		return diseasewest;
	}

	public void setDiseasewest(String diseasewest) {
		this.diseasewest = diseasewest;
	}

	/**
     * 用于封装病历的病人基本信息
     */
    private Patient patient;
     
    /**
     * 病例
     */

   private List<Recipel> recipels;
    /**
     * 统计病例的数量
     * @return
     */
    private  Integer counter;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }


	public List<Recipel> getRecipels() {
		return recipels;
	}

	public void setRecipels(List<Recipel> recipels) {
		this.recipels = recipels;
	}

	private Page page;
    
    
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	/**
     * 病人病史信息
     */
    private IllnessHistory illnessHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease == null ? null : disease.trim();
    }

    public String getSymptommould() {
        return symptommould;
    }

    public void setSymptommould(String symptommould) {
        this.symptommould = symptommould == null ? null : symptommould.trim();
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
    }

    public String getFsCode() {
        return fsCode;
    }

    public void setFsCode(String fsCode) {
        this.fsCode = fsCode == null ? null : fsCode.trim();
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint == null ? null : chiefComplaint.trim();
    }

    public Long getIllnessHistoryId() {
        return illnessHistoryId;
    }

    public void setIllnessHistoryId(Long illnessHistoryId) {
        this.illnessHistoryId = illnessHistoryId;
    }

    public String getBw() {
        return bw;
    }

    public void setBw(String bw) {
        this.bw = bw == null ? null : bw.trim();
    }

    public String getBx() {
        return bx;
    }

    public void setBx(String bx) {
        this.bx = bx == null ? null : bx.trim();
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy == null ? null : therapy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLunarTime() {
        return lunarTime;
    }

    public void setLunarTime(String lunarTime) {
        this.lunarTime = lunarTime == null ? null : lunarTime.trim();
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo == null ? null : patientNo.trim();
    }

    public String getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo == null ? null : visitNo.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId == null ? null : doctorId.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public IllnessHistory getIllnessHistory() {
		return illnessHistory;
	}

	public void setIllnessHistory(IllnessHistory illnessHistory) {
		this.illnessHistory = illnessHistory;
	}

	@Override
	public String toString() {
		return "Emr [id=" + id + ", disease=" + disease + ", symptommould="
				+ symptommould + ", symptom=" + symptom + ", fsCode=" + fsCode
				+ ", chiefComplaint=" + chiefComplaint + ", illnessHistoryId="
				+ illnessHistoryId + ", bw=" + bw + ", bx=" + bx + ", therapy="
				+ therapy + ", createTime=" + createTime + ", lunarTime="
				+ lunarTime + ", patientNo=" + patientNo + ", visitNo="
				+ visitNo + ", patientName=" + patientName + ", doctorId="
				+ doctorId + ", doctorName=" + doctorName + ", deptId="
				+ deptId + ", deptName=" + deptName + ", endTime=" + endTime
				+ ", diseasewest=" + diseasewest + ", patient=" + patient
				+ ", recipels=" + recipels + ", page=" + page
				+ ", illnessHistory=" + illnessHistory + "]";
	}
	
}