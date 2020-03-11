package com.nchu.service.impl;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.PermissionDao;
import com.nchu.dao.RoleDao;
import com.nchu.dao.RolePermissionDao;
import com.nchu.entity.Permission;
import com.nchu.entity.Role;
import com.nchu.entity.RolePermission;
import com.nchu.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Autowired
	private RoleDao roleDao;
	
	//通过权限id查找所有的RolePermission对象
	@Override
	public HashSet<RolePermission> findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return rolePermissionDao.queryByPid(pid);
	}

	//通过角色id查找所有的RolePermission对象
	@Override
	public HashSet<RolePermission> findByRid(Integer rid) {
		// TODO Auto-generated method stub
		return rolePermissionDao.queryByRid(rid);
	}
	
	//通过权限id查找所有的角色名称
	@Override
	public HashSet<String> findRolesByPid(Integer pid) {
		// TODO Auto-generated method stub
		HashSet<RolePermission> rps= findByPid(pid);
		HashSet<String> roles=new HashSet<>();
		for(RolePermission rp:rps){
			roles.add(roleDao.queryRoleByRid(rp.getRid()).getRname());
		}
		return roles;
	}

	//通过角色id查找所有权限名称
	@Override
	public HashSet<String> findPermissionsByRid(Integer rid) {
		// TODO Auto-generated method stub
		HashSet<RolePermission> rps=findByRid(rid);
		HashSet<String> permissions=new HashSet<>();
		try{
			for(RolePermission rp:rps){
				permissions.add(permissionDao.queryPermissionByPid(rp.getPid()).getPname());
			}
		}catch (NullPointerException e) {
			// TODO: handle exception0
		}
		return permissions;
	}
	
	public void save(RolePermission rolePermission){
		rolePermissionDao.save(rolePermission);
	}

	@Override
	public RolePermission findByRidAndPid(Integer rid, Integer pid) {
		// TODO Auto-generated method stub
		return rolePermissionDao.queryByRidAndPid(rid, pid);
	}

	@Override
	public void delete(RolePermission rolePermission) {
		// TODO Auto-generated method stub
		rolePermissionDao.delete(rolePermission);
	}

}
