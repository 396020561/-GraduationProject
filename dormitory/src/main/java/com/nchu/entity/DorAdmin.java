package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DORADMIN")
public class DorAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer dorid;// 
	private Integer buildingid;// 楼栋id
	private String uid;// 员工编号
	private String faceid;// 人脸图片id
	private String name;// 宿管姓名
	private String sex;// 性别
	private String phone;// 宿管号码
	private String createTime;// 创建时间
	private String idCardNum;//身份证号码
	
	
	public Integer getDorid() {
		return dorid;
	}

	public void setDorid(Integer dorid) {
		this.dorid = dorid;
	}

	@Override
	public String toString() {
		return "DorAdmin [dorid=" + dorid + ", buildingid=" + buildingid + ", uid=" + uid + ", faceid=" + faceid
				+ ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", createTime=" + createTime + ", idCardNum="
				+ idCardNum + "]";
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
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


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}
