package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.AccessDao;
import com.nchu.entity.Access;
import com.nchu.service.AccessService;

@Service
public class AccessServiceImpl implements AccessService {

	@Autowired
	private AccessDao accessDao;
	
	@Override
	public ArrayList<Access> findAccessByUid(String uid) {
		// TODO Auto-generated method stub
		return accessDao.queryByUid(uid);
	}

	@Override
	public ArrayList<Access> findRecentAccessByUid(String uid) {
		// TODO Auto-generated method stub
		return accessDao.queryRecentByUid(uid);
	}
	@Override
	public ArrayList<Access> findByUidAndTime(String uid, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return accessDao.queryByUidAndTime(uid, beginTime, endTime);
	}
}
