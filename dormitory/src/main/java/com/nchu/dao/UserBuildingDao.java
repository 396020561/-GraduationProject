package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.UserBuilding;

public interface UserBuildingDao extends JpaRepository<UserBuilding, Integer> {
	UserBuilding queryByUid(String uid);
	UserBuilding queryByUidAndBuildingid(String uid,Integer buildingid);
	ArrayList<UserBuilding> queryByBuildingid(Integer buildingid);
}
