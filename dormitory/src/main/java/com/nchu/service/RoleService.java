package com.nchu.service;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.Role;

public interface RoleService {
	Page<Role> findAll(Pageable pageable);
	ArrayList<Role> findAll();
	void delByRid(Integer rid);
	Role findByRname(String rname);
	Role findByMark(String mark);
	void save(Role role);
}
