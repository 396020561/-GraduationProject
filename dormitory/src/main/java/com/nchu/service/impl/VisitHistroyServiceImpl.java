package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.VisitHistoryDao;
import com.nchu.entity.VisitHistory;
import com.nchu.service.VisitHistoryService;

@Service
public class VisitHistroyServiceImpl implements VisitHistoryService {
	
	@Autowired VisitHistoryDao visitHistoryDao;

	@Override
	public ArrayList<VisitHistory> findByUid(String uid, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		return visitHistoryDao.queryVisitHistory(uid, beginTime, endTime);
	}
	

}
