package com.nchu.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nchu.dao.BuildingDao;
import com.nchu.entity.Building;
import com.nchu.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingDao buildingDao;
	
	@Override
	public ArrayList<Building> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return buildingDao.findAll(sort);
	}

	@Override
	public Building queryByBuildingid(Integer buildingid) {
		// TODO Auto-generated method stub
		return buildingDao.queryByBuildingid(buildingid);
	}

	@Override
	public void save(Building building) {
		// TODO Auto-generated method stub
		buildingDao.save(building);
	}

	@Override
	public Building findByName(String name) {
		// TODO Auto-generated method stub
		return buildingDao.queryByName(name);
	}

	@Override
	public Page<Building> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return buildingDao.findAll(pageable);
	}

	
}
