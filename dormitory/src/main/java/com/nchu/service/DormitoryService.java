package com.nchu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.dto.DormitoryResult;
import com.nchu.entity.Dormitory;

public interface DormitoryService {
	
	Page<Dormitory> findByBuildingid(Integer Buildingid,Pageable pageable);
	Dormitory findByDormitoryNum(String dormitoryNum);
	void save(Dormitory dormitory);
}
