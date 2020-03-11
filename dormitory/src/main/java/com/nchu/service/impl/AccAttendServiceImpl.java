package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.AccAttendDao;
import com.nchu.entity.AccAttend;
import com.nchu.service.AccAttendService;

@Service
public class AccAttendServiceImpl implements AccAttendService{
	
	@Autowired
	private AccAttendDao accAttendDao;

	@Override
	public ArrayList<AccAttend> findByUid(String uid) {
		// TODO Auto-generated method stub
		return accAttendDao.queryByUid(uid);
	}

	@Override
	public ArrayList<AccAttend> findBackLate(String uid, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return accAttendDao.queryBackLate(uid, beginTime, endTime);
	}

	@Override
	public ArrayList<AccAttend> findNoBack(String uid, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return accAttendDao.queryNoBack(uid, beginTime, endTime);
	}

	@Override
	public ArrayList<AccAttend> findNoOut(String uid, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return accAttendDao.queryNoOut(uid, beginTime, endTime);
	}

	@Override
	public ArrayList<AccAttend> findException(String uid, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return accAttendDao.queryException(uid, beginTime, endTime);
	}

}
