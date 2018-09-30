package com.cdutcm.tcms.sys.entity;

import java.util.Date;

import com.cdutcm.core.page.Page;

public class User {
	private long userId;
	private String account;
	private String username;
	private String password;
	private Integer status;
	private long roleId;
	private Date lastupdate;
	private Date createtime;
	private Role role;
	private Page page;

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	public Date getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}
	public Date getCreattime() {
		return createtime;
	}
	public void setCreattime(Date creattime) {
		this.createtime = creattime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", username=" + username + ", password=" + password
				+ ", status=" + status + ", roleId=" + roleId + ", lastupdate=" + lastupdate + ", createtime="
				+ createtime + ", role=" + role + ", page=" + page + "]";
	}
	
}