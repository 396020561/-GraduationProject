package com.nchu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.BlackList;

public interface BlackListDao extends JpaRepository<BlackList, Integer> {
	Page<BlackList> findAll(Pageable pageable);//查找所有用户信息
	BlackList queryById(Integer id);//通过用户id查找该用户信息
	void deleteById(Integer id);
}
