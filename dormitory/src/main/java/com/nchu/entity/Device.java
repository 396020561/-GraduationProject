package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEVICE")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer devid;//设备id
	private String name;//设备名称
	private String place;//设备地点
	private String ipAddr;//设备ip地址
	private String macAddr;//设备mac地址
	private String softVersion;//软件版本
	private Integer status;//设备状态
	public Integer getDevid() {
		return devid;
	}
	public void setDevid(Integer devid) {
		this.devid = devid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	public String getSoftVersion() {
		return softVersion;
	}
	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Device [devid=" + devid + ", name=" + name + ", place=" + place + ", ipAddr=" + ipAddr + ", macAddr="
				+ macAddr + ", softVersion=" + softVersion + ", status=" + status + ", getDevid()=" + getDevid()
				+ ", getName()=" + getName() + ", getPlace()=" + getPlace() + ", getIpAddr()=" + getIpAddr()
				+ ", getMacAddr()=" + getMacAddr() + ", getSoftVersion()=" + getSoftVersion() + ", getStatus()="
				+ getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
