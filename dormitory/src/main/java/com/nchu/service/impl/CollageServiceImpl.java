package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.CollageDao;
import com.nchu.dao.MajorDao;
import com.nchu.entity.Collage;
import com.nchu.entity.Major;
import com.nchu.service.CollageService;
import com.nchu.service.MajorService;


@Service
public class CollageServiceImpl implements CollageService {

	@Autowired
	private CollageDao  collageDao;
	
	@Autowired
	private MajorService majorService;
	
	@Override
	public Collage findByCollageid(String collageid) {
		// TODO Auto-generated method stub
		return collageDao.queryByCollageid(collageid);
	}

	@Override
	public Page<Collage> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return collageDao.findAll(pageable);
	}

	@Override
	public void save(Collage collage) {
		// TODO Auto-generated method stub
		collageDao.save(collage);
	}

	@Override
	public void delByCollageid(String collageid) {
		// TODO Auto-generated method stub
		ArrayList<Major> majors = majorService.findByCollageid(collageid);
		System.out.println(1);
		if(!majors.isEmpty())
			for(Major major:majors){
				majorService.delByCollageidAndMajorid(collageid, major.getMajorid());
			}
		Collage collage =collageDao.queryByCollageid(collageid);
		collageDao.delete(collage);
	}

	@Override
	public Collage findByCollageleader(String collageleader) {
		// TODO Auto-generated method stub
		return collageDao.queryByCollageleader(collageleader);
	}

}
