package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User queryByUid(String uid);
	
	ArrayList<User> queryByClassid(String classid);
	
	Page<User> queryByClassid(String classid,Pageable pageable);
	
	Page<User> findAll(Pageable pageable);
}
