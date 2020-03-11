package com.nchu.service;

import java.util.ArrayList;

import com.nchu.entity.VisitHistory;

public interface VisitHistoryService {
	
	ArrayList<VisitHistory> findByUid(String uid,String beginTime,String endTime);

}
