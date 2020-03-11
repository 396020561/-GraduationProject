package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nchu.entity.VisitHistory;

public interface VisitHistoryDao extends JpaRepository<VisitHistory,Integer> {
	
	@Query(value="select * from visithistory where uid=?1 and create_time>?2 and create_time<?3",nativeQuery = true)
	ArrayList<VisitHistory> queryVisitHistory(String uid,String beginTime,String endTime);

}
