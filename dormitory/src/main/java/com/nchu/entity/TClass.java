package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLASS")
public class TClass {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String classid;//班级号
	private String uid;//班主任id
	private String majorid;
	private String collageid;
	
	public String getCollageid() {
		return collageid;
	}
	public void setCollageid(String collageid) {
		this.collageid = collageid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMajorid() {
		return majorid;
	}
	public void setMajorid(String majorid) {
		this.majorid = majorid;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "TClass [classid=" + classid + ", uid=" + uid + "]";
	}
	
}
