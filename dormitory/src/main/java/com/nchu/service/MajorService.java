package com.nchu.service;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.Major;

public interface MajorService {
	void delByCollageid(String collageid);
	void delByCollageidAndMajorid(String collageid,String majorid);
	Page<Major> findAll(Pageable pageable);
	Page<Major> findByCollageid(String collageid,Pageable pageable);
	ArrayList<Major> findByCollageid(String collageid);
	Major findByCollageidAndMajorid(String collageid,String majorid);
	void save(Major major);
	ArrayList<Major> findByUid(String uid);
}
