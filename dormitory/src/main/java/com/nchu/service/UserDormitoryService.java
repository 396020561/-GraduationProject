package com.nchu.service;

import java.util.ArrayList;

import com.nchu.entity.UserDormitory;

public interface UserDormitoryService {
	
	ArrayList<UserDormitory> findByDormitoryNum(String dormitoryNum);
	UserDormitory findByUid(String uid);
	void save(UserDormitory userDormitory);
	void exchange(String uid,String uid2);
	public String existUser(String uid);
}
