package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Building;

public interface BuildingDao extends JpaRepository<Building, Integer> {
	
	ArrayList<Building> findAll(Sort sort);
	Page<Building> findAll(Pageable pageable);
	Building queryByBuildingid(Integer Buildingid);
	Building queryByName(String  name);
}
