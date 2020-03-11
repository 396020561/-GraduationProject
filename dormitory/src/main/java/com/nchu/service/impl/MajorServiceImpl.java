package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.MajorDao;
import com.nchu.entity.Major;
import com.nchu.entity.TClass;
import com.nchu.service.ClassService;
import com.nchu.service.MajorService;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorDao majorDao;
	
	@Autowired
	private ClassService classService;
	
	@Override
	public void delByCollageid(String collageid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Major> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return majorDao.findAll(pageable);
	}

	@Override
	public Page<Major> findByCollageid(String collageid, Pageable pageable) {
		// TODO Auto-generated method stub
		return majorDao.queryByCollageid(collageid, pageable);
	}

	@Override
	public Major findByCollageidAndMajorid(String collageid, String majorid) {
		// TODO Auto-generated method stub
		return majorDao.queryByCollageidAndMajorid(collageid, majorid);
	}

	@Override
	public void save(Major major) {
		// TODO Auto-generated method stub
		majorDao.save(major);
	}

	@Override
	public void delByCollageidAndMajorid(String collageid, String majorid) {
		// TODO Auto-generated method stub
		ArrayList<TClass> classes = classService.findByCollageidAndMajorid(collageid, majorid);
		System.out.println(1);
		if(!classes.isEmpty())
			for(TClass tclass:classes){
				classService.delByClassid(tclass.getClassid());
			}
		Major major =majorDao.queryByCollageidAndMajorid(collageid, majorid);
		majorDao.delete(major);
	}

	@Override
	public ArrayList<Major> findByCollageid(String collageid) {
		// TODO Auto-generated method stub
		return majorDao.queryByCollageid(collageid);
	}

	@Override
	public ArrayList<Major> findByUid(String uid) {
		// TODO Auto-generated method stub
		return majorDao.queryByUid(uid);
	}
	
}
