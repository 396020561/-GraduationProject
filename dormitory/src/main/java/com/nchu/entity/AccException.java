package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCEXCEPTION")
public class AccException {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eid;//异常id
	private String uid;//用户id
	private String reason;//原因
	private String createTime;//创建时间
	private Integer devid;//设备id
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getDevid() {
		return devid;
	}
	public void setDevid(Integer devid) {
		this.devid = devid;
	}
	@Override
	public String toString() {
		return "AccException [eid=" + eid + ", uid=" + uid + ", reason=" + reason + ", createTime=" + createTime
				+ ", devid=" + devid + ", getEid()=" + getEid() + ", getUid()=" + getUid() + ", getReason()="
				+ getReason() + ", getCreateTime()=" + getCreateTime() + ", getDevid()=" + getDevid() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
