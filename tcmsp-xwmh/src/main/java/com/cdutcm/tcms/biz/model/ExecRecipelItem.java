package com.cdutcm.tcms.biz.model;

import java.util.Date;

import com.cdutcm.core.util.JsonIDSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ExecRecipelItem {
	
	@JsonSerialize(using = JsonIDSerializer.class)
	private Long id;


	@JsonSerialize(using = JsonIDSerializer.class)
	private Long recipelId;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "ctype")
	private String ctype;

	@JsonIgnore
	private Integer mOrder;

	@JsonIgnore
	private Date starttime;

	@JsonIgnore
	private Date endtime;

	@JsonProperty(value = "time")
	private String time;

	@JsonIgnore
	private Date lastupdate;

	@JsonProperty(value = "zz_method")
	private String zzMethod;

	@JsonProperty(value = "freq")
	private String freq;

	@JsonProperty(value = "strength")
	private String strength;

	@JsonProperty(value = "bx")
	private String bx;

	@JsonProperty(value = "jd")
	private String jd;

	@JsonProperty(value = "sd")
	private String sd;

	@JsonProperty(value = "jl")
	private String jl;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "gj")
	private String gj;

	@JsonProperty(value = "other_gf")
	private String otherGf;

	@JsonProperty(value = "cxxh")
	private String cxxh;

	@JsonProperty(value = "yj")
	private String yj;

	@JsonProperty(value = "jz")
	private String jz;

	@JsonProperty(value = "power")
	private String power;
	
	@JsonProperty(value = "status")
    private String status;
	// 存储图片的位置信息
	@JsonProperty(value = "position")
	private String position;
	
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRecipelId() {
		return recipelId;
	}

	public void setRecipelId(Long recipelId) {
		this.recipelId = recipelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Integer getmOrder() {
		return mOrder;
	}

	public void setmOrder(Integer mOrder) {
		this.mOrder = mOrder;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getZzMethod() {
		return zzMethod;
	}

	public void setZzMethod(String zzMethod) {
		this.zzMethod = zzMethod;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getBx() {
		return bx;
	}

	public void setBx(String bx) {
		this.bx = bx;
	}

	public String getJd() {
		return jd;
	}

	public void setJd(String jd) {
		this.jd = jd;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getJl() {
		return jl;
	}

	public void setJl(String jl) {
		this.jl = jl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGj() {
		return gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getOtherGf() {
		return otherGf;
	}

	public void setOtherGf(String otherGf) {
		this.otherGf = otherGf;
	}

	public String getCxxh() {
		return cxxh;
	}

	public void setCxxh(String cxxh) {
		this.cxxh = cxxh;
	}

	public String getYj() {
		return yj;
	}

	public void setYj(String yj) {
		this.yj = yj;
	}

	public String getJz() {
		return jz;
	}

	public void setJz(String jz) {
		this.jz = jz;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
	
}