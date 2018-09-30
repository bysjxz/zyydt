package com.cdutcm.tcms.biz.model;

import java.util.Date;
import java.util.List;

import com.cdutcm.core.page.Page;
import com.cdutcm.core.util.JsonIDSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Recipel {
	@JsonProperty(value = "id")
	@JsonSerialize(using = JsonIDSerializer.class)
	private Long id;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "status")
	private String status;
	@JsonProperty(value = "emrId")
	@JsonSerialize(using = JsonIDSerializer.class)
	private long emrId;
	@JsonIgnore
	private String description;
	@JsonIgnore
	private Date lastupdate;
	@JsonProperty(value = "recipelItem")
	private List<RecipelItem> recipelItem;

	private Page page;

	public long getEmrId() {
		return emrId;
	}

	public void setEmrId(long emrId) {
		this.emrId = emrId;
	}

	public List<RecipelItem> getRecipelItem() {
		return recipelItem;
	}

	public void setRecipelItem(List<RecipelItem> recipelItem) {
		this.recipelItem = recipelItem;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}
}