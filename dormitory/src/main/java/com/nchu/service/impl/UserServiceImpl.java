package com.nchu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.AccountDao;
import com.nchu.dao.AccountRoleDao;
import com.nchu.dao.TClassDao;
import com.nchu.dao.UserDao;
import com.nchu.entity.Account;
import com.nchu.entity.AccountRole;
import com.nchu.entity.TClass;
import com.nchu.entity.User;
import com.nchu.service.AccountService;
import com.nchu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private TClassDao classDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	@Override
	public User findByUid(String uid) {
		// TODO Auto-generated method stub
		return userDao.queryByUid(uid);
	}

	@Override
	public Page<User> findByClassid(String classid, Pageable pageable) {
		// TODO Auto-generated method stub
		return userDao.queryByClassid(classid, pageable);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return userDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void delByUid(String uid) {
		// TODO Auto-generated method stub
		User user =userDao.queryByUid(uid);
		userDao.delete(user);
		accountService.delByUid(uid);
	}

	@Override
	@Transactional
	public void addUserAndAccount(User user) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String createTime=sdf.format(date);
		sdf=new SimpleDateFormat("yyyy-MM-dd");
		String register=sdf.format(date);
		user.setCreateTime(createTime);
		user.setRegisterTime(register);
		user.setLastVisitorNum(10);
		user.setTotalVisitor(0);
		userDao.save(user);
		String pwd="Nchu"+user.getIdCardNum().substring(12);
		String salt=user.getUid()+"Nchu"+pwd;
		ByteSource salt1 = ByteSource.Util.bytes(salt);
		String encryptionPwd= new SimpleHash("MD5", pwd, salt1, 1024).toString();
		Account account = new Account();
		account.setUid(user.getUid());
		account.setType(user.getType());
		account.setImage(user.getFaceid());
		account.setSalt(salt);
		account.setPassword(encryptionPwd);
		account.toString();
		accountDao.save(account);
		if(user.getType().equals("student")){
			TClass tclass = new TClass();
			tclass.setClassid(user.getClassid());
			tclass.setCollageid(user.getCollageid());
			tclass.setUid(user.getUid());
			tclass.setMajorid(user.getMajorid());
			classDao.save(tclass);
			Account account2 = accountDao.queryAccountByUid(user.getUid());
			AccountRole accountRole = new AccountRole();
			accountRole.setAid(account2.getAid());
			accountRole.setRid(1);
			accountRoleDao.save(accountRole);
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public ArrayList<User> findByClassid(String classid) {
		// TODO Auto-generated method stub
		return userDao.queryByClassid(classid);
	}

	
}
