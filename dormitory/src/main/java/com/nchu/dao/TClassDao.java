package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.TClass;

public interface TClassDao extends JpaRepository<TClass,Integer> {
	
	TClass queryByClassid(String classid);
	
	Page<TClass> queryByCollageidAndMajorid(String collageid,String majorid,Pageable pageable);
	
	ArrayList<TClass> queryByCollageidAndMajorid(String collageid,String majorid);
	
	Page<TClass> findAll(Pageable pageable);
	
	ArrayList<TClass> queryByUid(String uid);
}
