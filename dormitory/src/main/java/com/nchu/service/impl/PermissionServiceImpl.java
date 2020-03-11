package com.nchu.service.impl;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.PermissionDao;
import com.nchu.entity.Permission;
import com.nchu.service.PermissionService;


@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public ArrayList<Permission> findAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Permission>) permissionDao.findAll();
	}

	@Override
	public Permission findByPname(String pname) {
		// TODO Auto-generated method stub
		return permissionDao.queryByPname(pname);
	}

	@Override
	public void save(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.save(permission);
	}

	@Override
	public void delByPname(String pname) {
		// TODO Auto-generated method stub
		Permission permission = permissionDao.queryByPname(pname);
		permissionDao.delete(permission);
	}
}
