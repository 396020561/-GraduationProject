package com.nchu.dao;

import java.util.HashSet;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.RolePermission;

public interface RolePermissionDao extends JpaRepository<RolePermission, Integer> {
	HashSet<RolePermission> queryByRid(Integer rid);
	HashSet<RolePermission> queryByPid(Integer pid);
	RolePermission queryByRidAndPid(Integer rid,Integer pid);
}
