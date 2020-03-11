package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nchu.entity.AccAttend;

public interface AccAttendDao extends JpaRepository<AccAttend,Integer> {
	
	ArrayList<AccAttend> queryByUid(String uid);
	
	//晚归记录
	@Query(value="select * from accattend where category=0 and uid=?1 and create_time>?2 and create_time<?3",nativeQuery = true)
	ArrayList<AccAttend> queryBackLate(String uid,String beginTime,String endTime);
	
	//未归记录
	@Query(value="select * from accattend where category=1 and uid=?1 and create_time>?2 and create_time<?3",nativeQuery = true)
	ArrayList<AccAttend> queryNoBack(String uid,String beginTime,String endTime);
	
	//未出记录
	@Query(value="select * from accattend where category=2 and uid=?1 and create_time>?2 and create_time<?3",nativeQuery = true)
	ArrayList<AccAttend> queryNoOut(String uid,String beginTime,String endTime);
	
	//异常记录
	@Query(value="select * from accattend where category=4 and uid=?1 and create_time>?2 and create_time<?3",nativeQuery = true)
	ArrayList<AccAttend> queryException(String uid,String beginTime,String endTime);
	

}
