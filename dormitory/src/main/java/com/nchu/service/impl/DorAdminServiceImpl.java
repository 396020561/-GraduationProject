package com.nchu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.DorAdminDao;
import com.nchu.entity.DorAdmin;
import com.nchu.service.DorAdminService;

@Service
public class DorAdminServiceImpl implements DorAdminService {

	@Autowired
	private DorAdminDao dorAdminDao;
	
	@Override
	public DorAdmin findByUid(String uid) {
		// TODO Auto-generated method stub
		return dorAdminDao.queryByUid(uid);
	}

	@Override
	public Page<DorAdmin> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return dorAdminDao.findAll(pageable);
	}

	@Override
	public void save(DorAdmin dorAdmin) {
		// TODO Auto-generated method stub
		dorAdminDao.save(dorAdmin);
	}

	@Override
	public DorAdmin findByDorid(Integer dorid) {
		// TODO Auto-generated method stub
		return dorAdminDao.queryByDorid(dorid);
	}

	@Override
	public void delete(DorAdmin dorAdmin) {
		// TODO Auto-generated method stub
		dorAdminDao.delete(dorAdmin);
	}

}
