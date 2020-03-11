package com.nchu.dao;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nchu.entity.Leave;

public interface LeaveDao extends JpaRepository<Leave, Integer> {
	
	ArrayList<Leave> queryByApplyid(String applyid,Sort sort);//根据用户id获取获取对应的假条并以对应的顺序展示
	
	Page<Leave> queryByApplyid(String applyid,Pageable pageable);//分页展示
	
	Leave queryByLeaid(Integer leaid);
	
	Page<Leave> queryByFirsttreatidOrSecondtreatid(String firsttreatid,String secondtreatid,Pageable pageable);//分页展示
}
