package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCESS")
public class Access {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer accid;//通行id
	private String uid;//用户id
	private Integer direction;//通行类型
	private String createTime;//创建时间
	private Integer devid;//设备id
	private String place;//地址
	
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Integer getAccid() {
		return accid;
	}
	public void setAccid(Integer accid) {
		this.accid = accid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
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
		return "Access [accid=" + accid + ", uid=" + uid + ", direction=" + direction + ", createTime=" + createTime
				+ ", devid=" + devid + ", getAccid()=" + getAccid() + ", getUid()=" + getUid() + ", getDirection()="
				+ getDirection() + ", getCreateTime()=" + getCreateTime() + ", getDevid()=" + getDevid()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
