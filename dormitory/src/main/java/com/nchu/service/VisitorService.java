package com.nchu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.Visitor;

public interface VisitorService {
	
	Page<Visitor> findByUid(String uid,Pageable pageable);
	
	void save(Visitor visitor);
	
	Visitor findByVisitor(Integer visitorid);
	
	Page<Visitor> findAll(Pageable pageable);
	
	void delById(Integer visitorid);
}
