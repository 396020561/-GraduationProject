package com.nchu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Collage;

public interface CollageDao extends JpaRepository<Collage, Integer> {

	Collage queryByCollageid(String collageid);

	Collage queryByCollageleader(String collageleader);

	Page<Collage> findAll(Pageable pageable);

}
