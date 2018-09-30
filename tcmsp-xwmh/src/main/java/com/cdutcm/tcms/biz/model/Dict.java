package com.cdutcm.tcms.biz.model;

import java.util.ArrayList;
import java.util.List;

import com.cdutcm.core.page.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dict {
	
	private Long id;
	
	private int num;
	
	private Long pid;
	
	private String name;
	
	private String tips;
	
	private Page page;
	
	@JsonIgnore
	private List<Dict> dictItem=new ArrayList<Dict>();

	public List<Dict> getDictItem() {
		return dictItem;
	}

	public void setDictItem(List<Dict> dictItem) {
		this.dictItem = dictItem;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
}
