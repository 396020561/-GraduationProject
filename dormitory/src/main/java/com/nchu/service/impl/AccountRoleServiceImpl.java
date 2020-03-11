package com.nchu.service.impl;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.AccountDao;
import com.nchu.dao.AccountRoleDao;
import com.nchu.dao.RoleDao;
import com.nchu.entity.Account;
import com.nchu.entity.AccountRole;
import com.nchu.entity.Role;
import com.nchu.service.AccountRoleService;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {

	@Autowired
	private AccountRoleDao accountRoleDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private RoleDao roleDao;
	
	//通过账号id查找所有的AccountRole对象
	@Override
	public HashSet<AccountRole> findByAid(Integer aid) {
		// TODO Auto-generated method stub
		return accountRoleDao.queryByAid(aid);
	}
	
	//通过角色id查找所有的AccountRole对象
	@Override
	public HashSet<AccountRole> findByRid(Integer rid) {
		// TODO Auto-generated method stub
		return accountRoleDao.queryByRid(rid);
	}

	//通过账号id查找所有的角色名称
	@Override
	public HashSet<String> findRoleNamesByAid(Integer aid) {
		// TODO Auto-generated method stub
		HashSet<AccountRole> ars=findByAid(aid);
		HashSet<String> roles=new HashSet<>();
		for(AccountRole ar:ars){
			roles.add(roleDao.queryRoleByRid(ar.getRid()).getRname());
		}
		return roles;
	}

	//通过角色id查找所有的账号
	@Override
	public HashSet<Account> findAccountsByRid(Integer rid) {
		// TODO Auto-generated method stub
		HashSet<AccountRole> ars=findByRid(rid);
		HashSet<Account> accounts=new HashSet<>();
		for(AccountRole ar:ars){
			accounts.add(accountDao.queryAccountByAid(ar.getAid()));
		}
		return accounts;
	}

	//通过账号id查找所有的角色
	@Override
	public HashSet<Role> findRolesByAid(Integer aid) {
		// TODO Auto-generated method stub
		HashSet<AccountRole> ars=findByAid(aid);
		HashSet<Role> roles=new HashSet<>();
		for(AccountRole ar:ars){
			roles.add(roleDao.queryRoleByRid(ar.getRid()));
		}
		return roles;
	}

	@Override
	public void save(AccountRole accountRole) {
		// TODO Auto-generated method stub
		accountRoleDao.save(accountRole);
	}

	@Override
	public void delByAid(Integer aid) {
		// TODO Auto-generated method stub
		accountRoleDao.deleteByAid(aid);
	}

}
