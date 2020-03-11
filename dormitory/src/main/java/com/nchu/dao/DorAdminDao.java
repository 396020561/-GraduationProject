package com.nchu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.DorAdmin;

public interface DorAdminDao extends JpaRepository<DorAdmin, Integer> {

	DorAdmin queryByUid(String uid);
	DorAdmin queryByDorid(Integer dorid);
	Page<DorAdmin> findAll(Pageable pageable);
}
