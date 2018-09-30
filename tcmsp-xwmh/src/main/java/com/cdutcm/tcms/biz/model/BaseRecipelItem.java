package com.cdutcm.tcms.biz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseRecipelItem {
    private Long id;

    private Long recipelId;

    private String name;

    private String ctype;

    private Integer mOrder;

    private String time;

    private Date lastupdate;

    private String zzMethod;

    private String freq;

    private String strength;

    private String bx;

    private String jd;

    private String sd;

    private String jl;

    private String type;

    private String gj;

    private String otherGf;

    private String cxxh;

    private String jz;

    private String power;

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
        this.name = name == null ? null : name.trim();
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype == null ? null : ctype.trim();
    }

    public Integer getmOrder() {
        return mOrder;
    }

    public void setmOrder(Integer mOrder) {
        this.mOrder = mOrder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
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
        this.zzMethod = zzMethod == null ? null : zzMethod.trim();
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq == null ? null : freq.trim();
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength == null ? null : strength.trim();
    }

    public String getBx() {
        return bx;
    }

    public void setBx(String bx) {
        this.bx = bx == null ? null : bx.trim();
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd == null ? null : jd.trim();
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd == null ? null : sd.trim();
    }

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl == null ? null : jl.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj == null ? null : gj.trim();
    }

    public String getOtherGf() {
        return otherGf;
    }

    public void setOtherGf(String otherGf) {
        this.otherGf = otherGf == null ? null : otherGf.trim();
    }

    public String getCxxh() {
        return cxxh;
    }

    public void setCxxh(String cxxh) {
        this.cxxh = cxxh == null ? null : cxxh.trim();
    }

    public String getJz() {
        return jz;
    }

    public void setJz(String jz) {
        this.jz = jz == null ? null : jz.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }
}