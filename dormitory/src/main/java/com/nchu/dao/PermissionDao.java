package com.nchu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Permission;

public interface PermissionDao extends JpaRepository<Permission, Integer> {
	
	Permission queryPermissionByPid(Integer pid);
	Permission queryByPname(String pname);
}
