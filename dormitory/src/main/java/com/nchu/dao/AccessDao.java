package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nchu.entity.Access;

public interface AccessDao extends JpaRepository<Access,Integer> {

	@Query(value="SELECT * FROM access where uid=:uid AND DATE_SUB(CURDATE(), INTERVAL 3 DAY) <= date(create_time)",nativeQuery = true)
	ArrayList<Access> queryRecentByUid(@Param("uid") String uid);
	
	//查询最近30天通行记录
	@Query(value="SELECT * FROM access where uid=:uid AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(create_time)",nativeQuery = true)
	ArrayList<Access> queryByUid(@Param("uid") String uid);
	
	@Query(value="SELECT * FROM access where uid=?1 and create_time>?2 and create_time<?3 AND DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(create_time)",nativeQuery = true)
	ArrayList<Access> queryByUidAndTime(String uid,String beginTime,String endTime);
}
