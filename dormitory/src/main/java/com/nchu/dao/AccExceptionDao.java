package com.nchu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.AccException;

public interface AccExceptionDao extends JpaRepository<AccException,Integer> {

}
