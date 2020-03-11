package com.nchu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COLLAGE")
public class Collage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String collageid;//学院id
	private String collageleader;//学院领导
	private String collagename;//学院名称
	
	public String getCollageleader() {
		return collageleader;
	}
	public void setCollageleader(String collageleader) {
		this.collageleader = collageleader;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCollageid() {
		return collageid;
	}
	public void setCollageid(String collageid) {
		this.collageid = collageid;
	}
	public String getCollagename() {
		return collagename;
	}
	public void setCollagename(String collagename) {
		this.collagename = collagename;
	}
}
