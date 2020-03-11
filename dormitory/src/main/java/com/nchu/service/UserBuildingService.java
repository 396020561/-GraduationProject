package com.nchu.service;

import java.util.ArrayList;

import com.nchu.entity.UserBuilding;

public interface UserBuildingService {
	UserBuilding findByUid(String uid);
	ArrayList<UserBuilding> findByBuildingid(Integer buildingid);
	void save(UserBuilding userBuilding);
	UserBuilding findByUidAndBuildingid(String uid,Integer buildingid);
}
