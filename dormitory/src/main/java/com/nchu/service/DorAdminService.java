package com.nchu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.DorAdmin;

public interface DorAdminService {
	DorAdmin findByUid(String uid);
	Page<DorAdmin> findAll(Pageable pageable);
	void save(DorAdmin dorAdmin);
	DorAdmin findByDorid(Integer dorid);
	void delete(DorAdmin dorAdmin);
}
