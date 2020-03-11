package com.nchu.service;

import java.util.ArrayList;

import com.nchu.entity.AccAttend;

public interface AccAttendService {
	ArrayList<AccAttend> findByUid(String uid);
	ArrayList<AccAttend> findBackLate(String uid,String beginTime,String endTime);
	ArrayList<AccAttend> findNoBack(String uid,String beginTime,String endTime);
	ArrayList<AccAttend> findNoOut(String uid,String beginTime,String endTime);
	ArrayList<AccAttend> findException(String uid,String beginTime,String endTime);

}
