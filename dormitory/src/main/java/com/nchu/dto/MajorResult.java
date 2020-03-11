package com.nchu.dto;

public class MajorResult {
	
	private String collageid;
	private String majorid;
	private String name;
	private String instructor;
	private boolean add;
	private boolean del;
	
	public String getMajorid() {
		return majorid;
	}
	public void setMajorid(String majorid) {
		this.majorid = majorid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getCollageid() {
		return collageid;
	}
	public void setCollageid(String collageid) {
		this.collageid = collageid;
	}
	
	public boolean isAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	
}
