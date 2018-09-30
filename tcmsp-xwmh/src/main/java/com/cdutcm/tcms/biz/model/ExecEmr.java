package com.cdutcm.tcms.biz.model;

import java.util.List;

import com.cdutcm.core.util.JsonIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ExecEmr {
	// emrId
	@JsonSerialize(using = JsonIDSerializer.class)
	private long id;
	// 病人姓名
	private String patientName;
	// 病人号
    private String patientNo;
    // 病人挂号序号
    private String visitNo;
    // 开单医生id
	private String doctorId;
	// 开单医生名称
	private String doctorName;
	// 部门id
    private String deptId;
    // 部门名称
    private String deptName;
    // xw项目分类
    private String ctype;
    // 穴位的集合
    private List<ExecRecipelItem> ExecRecipelItems;
    // 病名
    private String disease;
    // 执行医生穴位操作日志
    private List<ExecRecipel> execRecipel;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public List<ExecRecipelItem> getExecRecipelItems() {
		return ExecRecipelItems;
	}
	public void setExecRecipelItems(List<ExecRecipelItem> execRecipelItems) {
		ExecRecipelItems = execRecipelItems;
	}
	public List<ExecRecipel> getExecRecipel() {
		return execRecipel;
	}
	public void setExecRecipel(List<ExecRecipel> execRecipel) {
		this.execRecipel = execRecipel;
	}
    
}
