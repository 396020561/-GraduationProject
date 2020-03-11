package com.nchu.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.AccountDao;
import com.nchu.dao.AccountRoleDao;
import com.nchu.entity.Account;
import com.nchu.entity.AccountRole;
import com.nchu.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	//通过账号查找Account对象
	@Override
	public Account findAccountByUid(String user_id) {
		// TODO Auto-generated method stub
		Account account = accountDao.queryAccountByUid(user_id);
		return account;
	}

	//通过账号id查找Account对象
	@Override
	public Account findAccountByAid(Integer user_id) {
		// TODO Auto-generated method stub
		return accountDao.queryAccountByAid(user_id);
	}

	@Override
	public void delByUid(String uid) {
		// TODO Auto-generated method stub
		Account account = accountDao.queryAccountByUid(uid);
		HashSet<AccountRole> byAid = accountRoleDao.queryByAid(account.getAid());
		for(AccountRole accountRole:byAid){
			accountRoleDao.delete(accountRole);
		}
		accountDao.delete(account);
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return accountDao.findAll(pageable) ;
	}
	

}
