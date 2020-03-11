package com.nchu.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.TClass;

public interface ClassService {
	
	Page<TClass> findByCollageidAndMajorid(String collageid,String majorid,Pageable pageable);
	
	Page<TClass> findAll(Pageable pageable);
	
	void delByClassid(String classid);
	
	void save(TClass tclass);
	
	TClass findByClassid(String classid);
	
	ArrayList<TClass> findByCollageidAndMajorid(String collageid,String majorid);
	
	ArrayList<TClass> findByUid(String uid);
}
