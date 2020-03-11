package com.nchu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nchu.entity.Collage;

public interface CollageService {
	Collage findByCollageid(String collageid);
	
	Page<Collage> findAll(Pageable pageable);
	
	void save(Collage collage);
	
	void delByCollageid(String collageid);
	
	Collage findByCollageleader(String collageleader);
}
