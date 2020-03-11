package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VISITOR")
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer visitorid;// 访客id;//表主键
	private Integer buildingid;// 楼栋id
	private String uid;// 录入访客的用户id
	private String faceid;// 人脸图片id
	private String name;// 访客姓名
	private String sex;// 访客性别
	private String phone;// 访客号码
	private String beginTime;// 起始时间
	private String endTime;// 结束时间
	private String createTime;// 创建时间
	private String type;// 访客来源
	private String relationship;//已录取者的关系
	private String idCardNum;//身份证号码
	
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(Integer buildingid) {
		this.buildingid = buildingid;
	}



	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getVisitorid() {
		return visitorid;
	}

	public void setVisitorid(Integer visitorid) {
		this.visitorid = visitorid;
	}

	public String getFaceid() {
		return faceid;
	}

	public void setFaceid(String faceid) {
		this.faceid = faceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Visitor [visitorid=" + visitorid + ", faceid=" + faceid + ", name=" + name + ", sex=" + sex + ", phone="
				+ phone + ", beginTime=" + beginTime + ", endTime=" + endTime + ", createTime=" + createTime
				+ ", getVisitorid()=" + getVisitorid() + ", getFaceid()=" + getFaceid() + ", getName()=" + getName()
				+ ", getSex()=" + getSex() + ", getPhone()=" + getPhone() + ", getBeginTime()=" + getBeginTime()
				+ ", getEndTime()=" + getEndTime() + ", getCreateTime()=" + getCreateTime() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
