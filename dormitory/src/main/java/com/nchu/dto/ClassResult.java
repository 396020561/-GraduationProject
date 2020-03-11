package com.nchu.dto;

public class ClassResult {
	
	private String classid;
	private String headmaster;
	private boolean add;
	private boolean del;
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getHeadmaster() {
		return headmaster;
	}
	public void setHeadmaster(String headmaster) {
		this.headmaster = headmaster;
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
