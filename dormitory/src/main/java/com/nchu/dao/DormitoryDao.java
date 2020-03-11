package com.nchu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Dormitory;

public interface DormitoryDao extends JpaRepository<Dormitory, Integer> {
	
	Page<Dormitory> queryByBuildingid(Integer buildingid,Pageable pageable);
	Dormitory queryByDormitoryNum(String dormitoryNum );
}
