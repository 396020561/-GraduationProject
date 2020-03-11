package com.nchu.dto;

import java.util.ArrayList;

import com.nchu.entity.UserDormitory;

public class DormitoryResult {
	
	private String dormitoryNum = "";
	private ArrayList<UserDormitory> userDormitories = new ArrayList<>();
	private boolean add;
	private boolean del;
	private boolean exc;
	
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
	public boolean isExc() {
		return exc;
	}
	public void setExc(boolean exc) {
		this.exc = exc;
	}
	public String getDormitoryNum() {
		return dormitoryNum;
	}
	public void setDormitoryNum(String dormitoryNum) {
		this.dormitoryNum = dormitoryNum;
	}
	public ArrayList<UserDormitory> getUserDormitories() {
		return userDormitories;
	}
	public void setUserDormitories(ArrayList<UserDormitory> userDormitories) {
		this.userDormitories = userDormitories;
	}
	
}
