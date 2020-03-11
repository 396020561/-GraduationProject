package com.nchu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Account;

public interface AccountDao extends JpaRepository<Account, Integer> {

	Account queryAccountByUid(String uid);
	Account queryAccountByAid(int aid);
}
