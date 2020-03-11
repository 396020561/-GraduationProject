package com.nchu.dto;

import java.util.HashSet;

import com.nchu.entity.Permission;

public class RoleAndPermissionResult {
	
	private Integer rid;
	private String mark;
	HashSet<String> roles = new HashSet<>();
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public HashSet<String> getRoles() {
		return roles;
	}
	public void setRoles(HashSet<String> roles) {
		this.roles = roles;
	}
	
}
