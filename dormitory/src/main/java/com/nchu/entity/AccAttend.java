package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCATTEND")
public class AccAttend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer attid;//考勤id
	private String createTime;//创建时间
	private String uid;//用户id
	private Integer category;//考勤类型
	private Integer devid;//设备id
	private String occurTime;//发生时间
	
	public Integer getAttid() {
		return attid;
	}
	public void setAttid(Integer attid) {
		this.attid = attid;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	public Integer getDevid() {
		return devid;
	}
	public void setDevid(Integer devid) {
		this.devid = devid;
	}
	
	public String getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}
	

}
