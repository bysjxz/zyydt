package com.cdutcm.tcms.biz.model;

import java.io.Serializable;
import java.util.Date;

import com.cdutcm.core.page.Page;

/**
 * @author zhufj
 * @Description 患者挂号信息
 * @Date 2017-7-5
 */
public class PatientRegist implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private Date kssj;

	/**
	 * 门诊或住院 默认为门诊 1:门诊 2：住院
	 */
	private String io;
	private String status;
	
	private String disease;
	
	private Patient patient;
	
	private Page page;
	
	
	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public PatientRegist() {
		this.id = null;
		this.ys = null;
		this.ysbm = null;
		this.ghks = null;
		this.ghksbm = null;
		this.patientNo = null;
		this.visitNo = null;
		this.kssj = null;
		this.io = null;
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

	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}