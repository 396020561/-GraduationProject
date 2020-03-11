package com.nchu.service;

import java.util.HashSet;
import com.nchu.entity.Account;
import com.nchu.entity.AccountRole;
import com.nchu.entity.Role;

public interface AccountRoleService {
	
	HashSet<AccountRole> findByAid(Integer aid);//通过账号id查找所有的AccountRole对象
	HashSet<AccountRole> findByRid(Integer rid);//通过角色id查找所有的AccountRole对象
	HashSet<String> findRoleNamesByAid(Integer aid);//通过账号id查找所有的角色名称
	HashSet<Account> findAccountsByRid(Integer rid);//通过角色id查找所有的账号
	HashSet<Role> findRolesByAid(Integer aid);//通过账号id查找所有的角色
	void save(AccountRole accountRole);
	void delByAid(Integer aid);
}
