package com.nchu.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Visitor;

public interface VisitorDao extends JpaRepository<Visitor, Integer> {
	
	Page<Visitor> queryByUid(String uid,Pageable pageable);//通过用户id查找该用户录入的访客信息
	Visitor queryByVisitorid(Integer visitorid);//通过访客id查找访客信息
	
}
