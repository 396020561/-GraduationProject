package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VISITHISTORY")
public class VisitHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hid; //访问记录id;//表主键
	private String createTime; //来访时间
	private String endTime; //离开时间
	private Integer visitorid; //访客id
	private String vname; //访客姓名
	private String place; //访问宿舍
	private String uid; //被访者id
	private String uname; //被访者姓名
	
	public Integer getHid() {
		return hid;
	}
	
	public void setHid(Integer hid) {
		this.hid = hid;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Integer getVisitorid() {
		return visitorid;
	}
	
	public void setVisitorid(Integer visitorid) {
		this.visitorid = visitorid;
	}
	
	public String getVname() {
		return vname;
	}
	
	public void setVname(String vname) {
		this.vname = vname;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
}
