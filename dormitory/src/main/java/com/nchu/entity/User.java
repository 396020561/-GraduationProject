package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;//表主键
	private String uid;//用户id
	private String faceid;//人脸id
	private String name;//姓名
	private String sex;//性别
	private String classid;//班级号
	private String phone;//电话号码
	private String idCardNum;//身份证号码
	private String majorid;//专业id
	private String collageid;//学院id
	private String homePlace;//生源地
	private String registerTime;//注册时间
	private String createTime;//创建时间
	private Integer lastVisitorNum;//剩余访客名额
	private Integer totalVisitor;//统计访客录入总次数
	private String grade;//注册年级如15级
	private String type;//信息类型
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public String getMajorid() {
		return majorid;
	}
	public void setMajorid(String majorid) {
		this.majorid = majorid;
	}
	public String getCollageid() {
		return collageid;
	}
	public void setCollageid(String collageid) {
		this.collageid = collageid;
	}
	public String getHomePlace() {
		return homePlace;
	}
	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getLastVisitorNum() {
		return lastVisitorNum;
	}
	public void setLastVisitorNum(Integer lastVisitorNum) {
		this.lastVisitorNum = lastVisitorNum;
	}
	public Integer getTotalVisitor() {
		return totalVisitor;
	}
	public void setTotalVisitor(Integer totalVisitor) {
		this.totalVisitor = totalVisitor;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uid=" + uid + ", faceid=" + faceid + ", name=" + name + ", sex=" + sex
				+ ", classid=" + classid + ", phone=" + phone + ", idCardNum=" + idCardNum + ", majorid=" + majorid
				+ ", collageid=" + collageid + ", homePlace=" + homePlace + ", registerTime=" + registerTime
				+ ", createTime=" + createTime + ", lastVisitorNum=" + lastVisitorNum + ", totalVisitor=" + totalVisitor
				+ ", getId()=" + getId() + ", getUid()=" + getUid() + ", getFaceid()=" + getFaceid() + ", getName()="
				+ getName() + ", getSex()=" + getSex() + ", getClassid()=" + getClassid() + ", getPhone()=" + getPhone()
				+ ", getIdCardNum()=" + getIdCardNum() + ", getMajorid()=" + getMajorid() + ", getCollageid()="
				+ getCollageid() + ", getHomePlace()=" + getHomePlace() + ", getRegisterTime()=" + getRegisterTime()
				+ ", getCreateTime()=" + getCreateTime() + ", getLastVisitorNum()=" + getLastVisitorNum()
				+ ", getTotalVisitor()=" + getTotalVisitor() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
