package com.nchu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nchu.entity.Role;

public interface RoleDao extends JpaRepository<Role,Integer> {
	Role queryRoleByRid(Integer rid);
	Role queryByRname(String rname);
	Role queryByMark(String mark);
}
