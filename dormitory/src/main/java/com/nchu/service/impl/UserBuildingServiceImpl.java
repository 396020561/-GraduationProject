package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.UserBuildingDao;
import com.nchu.entity.UserBuilding;
import com.nchu.service.UserBuildingService;

@Service
public class UserBuildingServiceImpl implements UserBuildingService {

	@Autowired
	private UserBuildingDao userBuildingDao;
	
	@Override
	public UserBuilding findByUid(String uid) {
		// TODO Auto-generated method stub
		return userBuildingDao.queryByUid(uid);
	}

	@Override
	public ArrayList<UserBuilding> findByBuildingid(Integer buildingid) {
		// TODO Auto-generated method stub
		return userBuildingDao.queryByBuildingid(buildingid);
	}

	@Override
	public void save(UserBuilding userBuilding) {
		// TODO Auto-generated method stub
		userBuildingDao.save(userBuilding);
	}

	@Override
	public UserBuilding findByUidAndBuildingid(String uid, Integer buildingid) {
		// TODO Auto-generated method stub
		return userBuildingDao.queryByUidAndBuildingid(uid, buildingid);
	}

}
