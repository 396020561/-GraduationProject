package com.nchu.service;

import java.util.ArrayList;

import com.nchu.entity.Access;

public interface AccessService {
	ArrayList<Access> findAccessByUid(String uid);
	//查询最近记录
	ArrayList<Access> findRecentAccessByUid(String uid);
	
	ArrayList<Access> findByUidAndTime(String uid,String beginTime,String endTime);
}
