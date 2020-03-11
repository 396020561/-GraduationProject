package com.nchu.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aid;	//账号id
    private String uid;   //学号 登录账号
    private String password;    //用户密码
    private String image;       //用户头像
    private String last_ip;     //上次登录IP
    private String last_time;   //上次登录时间
    private String salt;     //加密的盐
    private Boolean status;//账号是否是黑名单
    private String type;   //账号类型

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String user_id) {
		this.uid = user_id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}



	@Override
	public String toString() {
		return "Account [id=" + aid + ", user_id=" + uid + ", password=" + password + ", image=" + image
				+ ", last_ip=" + last_ip + ", last_time=" + last_time + ", salt=" + salt + "]";
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer id) {
		this.aid = id;
	}

	public String getUserId() {
		return uid;
	}

	public void setUserId(String userId) {
		this.uid = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLast_ip() {
		return last_ip;
	}

	public void setLast_ip(String last_ip) {
		this.last_ip = last_ip;
	}

	public String getLast_time() {
		return last_time;
	}

	public void setLast_time(String last_time) {
		this.last_time = last_time;
	}

	public Set<Role> getSet() {
		return getSet();
	}


}