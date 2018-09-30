package com.cdutcm.tcms.biz.model;

import java.util.ArrayList;
import java.util.List;

import com.cdutcm.core.page.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseRecipel {
	private Long id;

	private String name;

	private String ctype;

	private String typeId;

	private String fsCode;

	private String version;

	private String description;

	private Page page;
	
	@JsonIgnore
	private List<BaseRecipelItem> baseRecipelItem = new ArrayList<BaseRecipelItem>();

	

	public List<BaseRecipelItem> getBaseRecipelItem() {
		return baseRecipelItem;
	}

	public void setBaseRecipelItem(List<BaseRecipelItem> baseRecipelItem) {
		this.baseRecipelItem = baseRecipelItem;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId == null ? null : typeId.trim();
	}

	public String getFsCode() {
		return fsCode;
	}

	public void setFsCode(String fsCode) {
		this.fsCode = fsCode == null ? null : fsCode.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}