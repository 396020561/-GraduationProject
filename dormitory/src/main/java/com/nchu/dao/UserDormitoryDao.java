package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.UserDormitory;

public interface UserDormitoryDao extends JpaRepository<UserDormitory, Integer> {
	
	ArrayList<UserDormitory> queryByDormitoryNum(String dormitoryNum);
	UserDormitory queryByUid(String uid);
}
