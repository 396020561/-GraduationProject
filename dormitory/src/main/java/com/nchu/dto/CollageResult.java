package com.nchu.dto;

public class CollageResult {
	
	private String collageid;
	private String collagename;
	private String leadername;
	private boolean add;
	private boolean del;
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
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
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
