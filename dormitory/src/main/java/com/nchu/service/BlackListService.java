package com.nchu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.nchu.entity.BlackList;

public interface BlackListService {
	Page<BlackList> findAll(Pageable pageable);//查找所有用户信息
	BlackList findById(Integer id);//通过用户id查找该用户信息
	void save(BlackList blackList);
	void delById(Integer id);
	 boolean batchImport(String fileName, MultipartFile file) throws Exception;
}
