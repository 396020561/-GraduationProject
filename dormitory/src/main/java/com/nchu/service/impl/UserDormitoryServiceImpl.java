package com.nchu.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nchu.dao.UserDao;
import com.nchu.dao.UserDormitoryDao;
import com.nchu.entity.User;
import com.nchu.entity.UserDormitory;
import com.nchu.service.UserDormitoryService;

@Service
public class UserDormitoryServiceImpl implements UserDormitoryService {

	
	@Autowired
	private UserDormitoryDao userDormitoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public ArrayList<UserDormitory> findByDormitoryNum(String dormitoryNum) {
		// TODO Auto-generated method stub
		return userDormitoryDao.queryByDormitoryNum(dormitoryNum);
	}

	@Override
	public UserDormitory findByUid(String uid) {
		// TODO Auto-generated method stub
		return userDormitoryDao.queryByUid(uid);
	}

	@Override
	public void save(UserDormitory userDormitory) {
		// TODO Auto-generated method stub
		userDormitoryDao.save(userDormitory);
	}

	public String existUser(String uid){
		UserDormitory userDormitory =userDormitoryDao.queryByUid(uid);
		String dormitoryNum=null;
		try{
			dormitoryNum = userDormitory.getDormitoryNum();
		}catch (NullPointerException e) {
			User user =userDao.queryByUid(uid);
			try{
				UserDormitory userDormitory1 = new UserDormitory();
				userDormitory1.setName(user.getName());
				userDormitory1.setUid(uid);
				userDormitoryDao.save(userDormitory1);
				return null;
			}catch (NullPointerException e1) {
				// TODO: handle exception
				return null;
			}
		}
		return dormitoryNum;
	}
	
	@Override
	public void exchange(String uid, String uid2) {
		// TODO Auto-generated method stub
		String dormitoryNum1=existUser(uid);
		String dormitoryNum2=existUser(uid2);
		UserDormitory userDormitory = userDormitoryDao.queryByUid(uid);
		UserDormitory userDormitory2 = userDormitoryDao.queryByUid(uid2);
		try{
			userDormitory.setDormitoryNum(dormitoryNum2);
			userDormitoryDao.save(userDormitory);
		}catch (NullPointerException e) {
			// TODO: handle exception
			
		}
		try{
			userDormitory2.setDormitoryNum(dormitoryNum1);
			userDormitoryDao.save(userDormitory2);
		}catch (NullPointerException e) {
			// TODO: handle exception
			
		}
	}

}
