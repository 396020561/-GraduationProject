package com.nchu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.User;

public interface UserService {

	User findByUid(String uid);
	
	Page<User> findByClassid(String classid,Pageable pageable);
	
	void save(User user);
	
	Page<User> findAll(Pageable pageable);
	void delByUid(String uid);
	
	void addUserAndAccount(User user);
	List<User> findAll();
	
	ArrayList<User>findByClassid(String classid);
}
