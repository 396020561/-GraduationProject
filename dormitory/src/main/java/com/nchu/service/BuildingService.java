package com.nchu.service;

import com.nchu.entity.Building;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BuildingService {
	
	ArrayList<Building> findAll(Sort sort);
	Building queryByBuildingid(Integer buildingid);
	Page<Building> findAll(Pageable pageable);
	void save(Building building);
	Building findByName(String name);
}
