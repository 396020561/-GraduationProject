package com.nchu.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.VisitorDao;
import com.nchu.entity.Visitor;
import com.nchu.service.VisitorService;

@Service
public class VisitorServiceImpl implements VisitorService {

	@Autowired
	private VisitorDao visitorDao;
	
	@Override
	public Page<Visitor> findByUid(String uid, Pageable pageable) {
		// TODO Auto-generated method stub
		return visitorDao.queryByUid(uid, pageable);
	}

	@Override
	public void save(Visitor visitor) {
		// TODO Auto-generated method stub
		visitorDao.save(visitor);
	}

	@Override
	public Visitor findByVisitor(Integer visitorid) {
		// TODO Auto-generated method stub
		return visitorDao.queryByVisitorid(visitorid);
	}

	@Override
	public Page<Visitor> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return visitorDao.findAll(pageable);
	}

	@Override
	public void delById(Integer visitorid) {
		// TODO Auto-generated method stub
		visitorDao.deleteById(visitorid);
	}

}
