package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Major;

public interface MajorDao extends JpaRepository<Major, Integer> {
	
	Major queryByCollageidAndMajorid(String collageid,String majorid);
	Page<Major> findAll(Pageable pageable);
	Page<Major> queryByCollageid(String collageid,Pageable pageable);
	ArrayList<Major> queryByCollageid(String collageid);
	ArrayList<Major> queryByUid(String uid);
}
