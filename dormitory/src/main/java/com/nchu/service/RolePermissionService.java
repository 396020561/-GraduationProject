package com.nchu.service;

import java.util.HashSet;
import com.nchu.entity.Permission;
import com.nchu.entity.Role;
import com.nchu.entity.RolePermission;

public interface RolePermissionService {
	
	HashSet<RolePermission> findByPid(Integer pid);//通过权限id查找所有的RolePermission对象
	HashSet<RolePermission> findByRid(Integer rid);//通过角色id查找所有的RolePermission对象
	HashSet<String> findRolesByPid(Integer pid);//通过权限id查找所有的角色名称
	HashSet<String> findPermissionsByRid(Integer rid);//通过角色id查找所有权限名称
	void save(RolePermission rolePermission);
	RolePermission findByRidAndPid(Integer rid,Integer pid);
	void delete(RolePermission rolePermission);
}
