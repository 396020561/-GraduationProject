package com.nchu.dao;

import java.util.HashSet;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.AccountRole;

public interface AccountRoleDao extends JpaRepository<AccountRole, Integer> {
	
	HashSet<AccountRole> queryByAid(Integer aid);
	HashSet<AccountRole> queryByRid(Integer rid);
	void deleteByAid(Integer aid);
}
