package com.nchu.service;

import java.util.ArrayList;
import java.util.HashSet;

import com.nchu.entity.Permission;

public interface PermissionService {
	ArrayList<Permission> findAll();
	
	Permission findByPname(String pname);
	void save(Permission permission);
	void delByPname(String pname);
}
