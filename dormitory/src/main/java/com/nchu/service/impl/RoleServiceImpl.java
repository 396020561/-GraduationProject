package com.nchu.service.impl;

import java.util.ArrayList;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.RoleDao;
import com.nchu.dao.RolePermissionDao;
import com.nchu.entity.Role;
import com.nchu.entity.RolePermission;
import com.nchu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public Page<Role> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return roleDao.findAll(pageable);
	}

	@Override
	public ArrayList<Role> findAll() {
		// TODO Auto-generated method stub
		return (ArrayList<Role>) roleDao.findAll();
	}

	@Override
	@Transactional
	public void delByRid(Integer rid) {
		// TODO Auto-generated method stub
		Role role = roleDao.queryRoleByRid(rid);
		HashSet<RolePermission> rps = rolePermissionDao.queryByRid(rid);
		try{
			for(RolePermission rp:rps){
				rolePermissionDao.delete(rp);
			}
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
		roleDao.delete(role);
	}

	@Override
	public Role findByRname(String rname) {
		// TODO Auto-generated method stub
		return roleDao.queryByRname(rname);
	}

	@Override
	public Role findByMark(String mark) {
		// TODO Auto-generated method stub
		return roleDao.queryByMark(mark);
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.save(role);
	}

}
