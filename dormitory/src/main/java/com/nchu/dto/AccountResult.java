package com.nchu.dto;

import java.util.HashSet;

import com.nchu.entity.Role;

public class AccountResult {
	private Integer aid; // 账号id
	private String uid; // 学号 登录账号
	private String image; // 用户头像
	private String type; // 账号类型
	HashSet<Role> roles = new HashSet<>();
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public HashSet<Role> getRoles() {
		return roles;
	}
	public void setRoles(HashSet<Role> roles) {
		this.roles = roles;
	}
	
}
