package com.nchu.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nchu.dao.TClassDao;
import com.nchu.dao.UserDao;
import com.nchu.entity.TClass;
import com.nchu.entity.User;
import com.nchu.service.ClassService;


@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private TClassDao tClassDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Page<TClass> findByCollageidAndMajorid(String collageid, String majorid,Pageable pageable) {
		// TODO Auto-generated method stub
		return tClassDao.queryByCollageidAndMajorid(collageid, majorid, pageable);
	}

	@Override
	public void delByClassid(String classid) {
		// TODO Auto-generated method stub
		ArrayList<User> users = userDao.queryByClassid(classid);
		System.out.println(1);
		if(!users.isEmpty())
			for(User user :users){
				user.setClassid(null);
				userDao.save(user);
			}
		TClass tclass=tClassDao.queryByClassid(classid);
		tClassDao.delete(tclass);
	}

	@Override
	public void save(TClass tclass) {
		// TODO Auto-generated method stub
		tClassDao.save(tclass);
	}

	@Override
	public Page<TClass> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return tClassDao.findAll(pageable);
	}

	@Override
	public TClass findByClassid(String classid) {
		// TODO Auto-generated method stub
		return tClassDao.queryByClassid(classid);
	}

	@Override
	public ArrayList<TClass> findByCollageidAndMajorid(String collageid, String majorid) {
		// TODO Auto-generated method stub
		return tClassDao.queryByCollageidAndMajorid(collageid, majorid);
	}

	@Override
	public ArrayList<TClass> findByUid(String uid) {
		// TODO Auto-generated method stub
		return tClassDao.queryByUid(uid);
	}

}
