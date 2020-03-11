package com.nchu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nchu.dao.CollageDao;
import com.nchu.dao.LeaveDao;
import com.nchu.dao.MajorDao;
import com.nchu.dao.TClassDao;
import com.nchu.dao.UserDao;
import com.nchu.dto.LeaveResult;
import com.nchu.entity.Leave;
import com.nchu.entity.User;
import com.nchu.exception.LeaveException;
import com.nchu.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveDao leaveDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private MajorDao majorDao;

	@Autowired
	private TClassDao classDao;

	@Autowired
	private CollageDao collageDao;

	@Override
	public Page<Leave> findByApplyid(String applyid, Pageable pageable) {
		// TODO Auto-generated method stub
		return leaveDao.queryByApplyid(applyid, pageable);
	}

	// 转化为leaveResult结构在学生页面上展示
	@Override
	public ArrayList<LeaveResult> castLeaveResult(List<Leave> list) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date now = new Date();
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		before.setTime(now);
		ArrayList<LeaveResult> result = new ArrayList<>();
		for (Leave leave : list) {
			after.setTime(sdf.parse(leave.getEndTime()));
			if(before.after(after)&&leave.getStatus()==0){
				leave.setStatus(2);
				leaveDao.save(leave);
			}
			LeaveResult leaveResult = new LeaveResult();
			User applyUser = userDao.queryByUid(leave.getApplyid());
			User firsttreatUser = userDao.queryByUid(leave.getFirsttreatid());
			User secondtreatUser = userDao.queryByUid(leave.getSecondtreatid());
			try {
				leaveResult.setTreatName(firsttreatUser.getName() + "或" + secondtreatUser.getName());
			} catch (NullPointerException e) {
				// TODO: handle exception
				leaveResult.setTreatName(firsttreatUser.getName());
			}
			leaveResult.setLeaid(leave.getLeaid());
			leaveResult.setApplyName(applyUser.getName());
			leaveResult.setApplyid(leave.getApplyid());
			leaveResult.setBeginTime(leave.getBeginTime());
			leaveResult.setCancelTime(leave.getCancelTime());
			leaveResult.setCreateTime(leave.getCreateTime());
			leaveResult.setEndTime(leave.getEndTime());
			leaveResult.setIscancel(leave.getIscancel());
			leaveResult.setReason(leave.getReason());
			leaveResult.setStatus(leave.getStatus());
			leaveResult.setOperate(!isOnLeave(leave)&&leave.getStatus()==1);
			result.add(leaveResult);
		}
		return result;
	}

	// 将可以审批的假条转化为leaveResult结构在老师页面上展示
	@Override
	public ArrayList<LeaveResult> castTeacherLeaveResult(List<Leave> list) throws ParseException {
		// TODO Auto-generated method stub

		ArrayList<LeaveResult> result = new ArrayList<>();
		for (Leave leave : list) {
			// 假条能否审核
//			if (!isCheckLeave(leave)) {
//				// 跳过当前循环
//				continue;
//			}

			LeaveResult leaveResult = new LeaveResult();
			User applyUser = userDao.queryByUid(leave.getApplyid());
			User firsttreatUser = userDao.queryByUid(leave.getFirsttreatid());
			User secondtreatUser = userDao.queryByUid(leave.getSecondtreatid());
			try {
				leaveResult.setTreatName(firsttreatUser.getName() + "或" + secondtreatUser.getName());
			} catch (NullPointerException e) {
				// TODO: handle exception
				leaveResult.setTreatName(firsttreatUser.getName());
			}
			leaveResult.setLeaid(leave.getLeaid());
			leaveResult.setApplyName(applyUser.getName());
			leaveResult.setBeginTime(leave.getBeginTime());
			leaveResult.setCreateTime(leave.getCreateTime());
			leaveResult.setCancelTime(leave.getCancelTime());
			leaveResult.setEndTime(leave.getEndTime());
			leaveResult.setIscancel(leave.getIscancel());
			leaveResult.setReason(leave.getReason());
			leaveResult.setStatus(leave.getStatus());
			result.add(leaveResult);
		}
		return result;
	}
	//判断假条能否审核
	public boolean isCheckLeave(Leave leave) throws ParseException{
		if(!isOnLeave(leave))
			if(leave.getStatus()==0)
				return true;
		return false;
	}
	
	@Override
	public void addLeave(Leave leave) throws ParseException {
		// TODO Auto-generated method stub
		// 判断最近假条结束日期是否位于当前日期之前
		// 判断是否有请假记录
		if (!isOnLeave(leave)) {
			throw new LeaveException("处于假期期间或上次申请未审核不能重复申请");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date creatTime = new Date();
		String creatTimeStr = sdf.format(creatTime);
		leave.setCreateTime(creatTimeStr);
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		// 求请假的天数
		Date begin = sdf.parse(leave.getBeginTime());
		Date end = sdf.parse(leave.getEndTime());
		long diff = end.getTime() - begin.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		leave.setStatus(0);
		leave.setIscancel(0);
		User user = userDao.queryByUid(leave.getApplyid());
		if (days >= 3 && days<14) {
			//查找学工处id
			String firsttreatid = collageDao.queryByCollageid(user.getCollageid()).getCollageleader();
			leave.setFirsttreatid(firsttreatid);
			leaveDao.save(leave);
			return;
		}
		// 查找辅导员id
		String firsttreatid = majorDao.queryByCollageidAndMajorid(user.getCollageid(),user.getMajorid()).getUid();
		leave.setFirsttreatid(firsttreatid);
		// 查找班主任id
		String secondtreatid = classDao.queryByClassid(user.getClassid()).getUid();
		leave.setSecondtreatid(secondtreatid);
		leaveDao.save(leave);

	}

	// 判断假条能否申请 返回值 0：当前时间处于假期开始前,可以申请 1：当前时间处于假期中，不能再次申请 2：当前时间处于假期后
	public boolean isOnLeave(Leave leave) throws ParseException {
		String applyUser = leave.getApplyid();
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable pageable = PageRequest.of(0, 1, sort);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Page<Leave> list = leaveDao.queryByApplyid(applyUser, pageable);
		Date now = new Date();
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		Date beginTime = sdf.parse(leave.getBeginTime());
		before.setTime(beginTime);
		after.setTime(now);
		//假条起始时间需要处于当前时间之后
		if (after.after(before))
			return false;
		if (list.isEmpty())
			//之前没有请假记录，可以申请
			return true;
		Date date = sdf.parse(list.getContent().get(0).getBeginTime());
		before.setTime(date);
		after.setTime(now);
		if(list.getContent().get(0).getIscancel() == 0) {  //判断最近一条请假记录是否已销假
			if (list.getContent().get(0).getStatus() == 1) {
				if (after.after(before)) {
					before.setTime(sdf.parse(list.getContent().get(0).getEndTime()));
					// 上次假条通过，处于假期期间不能申请
					if (after.before(before))
						return false;
					//上次假条已到期，可以申请
					return true;
				}
				//上次假条通过，假期未开始不能申请
				return false;
			}
			if(list.getContent().get(0).getStatus() == 0){
				//上次申请未审核并且在申请假期开始时间之前，不能申请
				if(after.before(before))
					return false;
				//上次申请未审核并且在申请假期开始时间之后，可以申请,自动设置上次假条未通过
				Leave fail =list.getContent().get(0);
				fail.setStatus(2);
				leaveDao.save(fail);
				return true;
			}
			//上次假条审核未通过可以申请
			return true;
		}else 
			return true;
	}

	@Override
	public void leaveCancel(Integer leaid) throws ParseException {
		// TODO Auto-generated method stub
		// 通过leaid获取对应的leave
		Leave leave = leaveDao.queryByLeaid(leaid);
		// 判断假条状态
		if (!isOnLeave(leave)&&leave.getStatus() == 0)
			throw new LeaveException("假条没有通过审核不能提前销假！");
		// 判断假条结束日期
		if (isOnLeave(leave))
			throw new LeaveException("系统错误");
		// 设置假条是否销假状态
		if (leave.getIscancel() == 1)
			throw new LeaveException("假条已经提前销假!");
		leave.setIscancel(1);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		leave.setCancelTime(sdf.format(date).toString());
		leaveDao.save(leave);
	}

	@Override
	public Page<Leave> findByTreatid(String treatid, Pageable pageable) {
		// TODO Auto-generated method stub
		return leaveDao.queryByFirsttreatidOrSecondtreatid(treatid, treatid, pageable);
	}

	@Override
	public Leave findByLeaid(Integer leaid) {
		// TODO Auto-generated method stub
		return leaveDao.queryByLeaid(leaid);
	}

	@Override
	public void updateLeave(Leave leave) {
		// TODO Auto-generated method stub
		leaveDao.save(leave);
	}

	@Override
	public ArrayList<Leave> findByApplyid(String applyid, Sort sort) {
		// TODO Auto-generated method stub
		return leaveDao.queryByApplyid(applyid,sort);
	}

}
