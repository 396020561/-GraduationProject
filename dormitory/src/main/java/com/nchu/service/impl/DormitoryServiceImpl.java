package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.DormitoryDao;
import com.nchu.dao.UserDormitoryDao;
import com.nchu.dto.DormitoryResult;
import com.nchu.entity.Dormitory;
import com.nchu.entity.UserDormitory;
import com.nchu.service.DormitoryService;

@Service
public class DormitoryServiceImpl implements DormitoryService {

	@Autowired
	private DormitoryDao dormitoryDao;
	
	
	@Override
	public Page<Dormitory> findByBuildingid(Integer buildingid,Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Dormitory> dormitories =dormitoryDao.queryByBuildingid(buildingid, pageable);
	
		return dormitories;
	}


	@Override
	public Dormitory findByDormitoryNum(String dormitoryNum) {
		// TODO Auto-generated method stub
		return dormitoryDao.queryByDormitoryNum(dormitoryNum);
	}


	@Override
	public void save(Dormitory dormitory) {
		// TODO Auto-generated method stub
		dormitoryDao.save(dormitory);
	}

}
