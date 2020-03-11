package com.nchu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jayway.jsonpath.internal.filter.LogicalOperator;
import com.nchu.dto.AccAttendResult;
import com.nchu.dto.LeaveResult;
import com.nchu.dto.Result;
import com.nchu.entity.AccAttend;
import com.nchu.entity.Access;
import com.nchu.entity.Collage;
import com.nchu.entity.Leave;
import com.nchu.entity.Major;
import com.nchu.entity.TClass;
import com.nchu.entity.User;
import com.nchu.entity.UserBuilding;
import com.nchu.entity.VisitHistory;
import com.nchu.service.AccAttendService;
import com.nchu.service.AccessService;
import com.nchu.service.ClassService;
import com.nchu.service.CollageService;
import com.nchu.service.LeaveService;
import com.nchu.service.MajorService;
import com.nchu.service.UserBuildingService;
import com.nchu.service.UserService;
import com.nchu.service.VisitHistoryService;

import utils.DetectMobile;
import utils.ObjectChangeUtil;

@Controller
public class TeacherController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserBuildingService userBuildingService;
	
	@Autowired
	private AccAttendService accAttendService;
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private VisitHistoryService visitHistoryService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private CollageService collageSevice;

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@RequestMapping("/teacher")
	public String index(HttpServletRequest request) {

		if (DetectMobile.isMobile(request))
			return "m_teacher";
		return "teacher";
	}

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@RequestMapping("/leave/check")
	public String leaveCheck(HttpServletRequest request, ModelMap map, Integer page) throws ParseException {

		if (page == null || page.equals(""))
			page = 1;
		Subject subject = SecurityUtils.getSubject();
		String uid = (String) subject.getPrincipal();
		if (DetectMobile.isMobile(request)) {
			Page<Leave> pages = leaveService.findByTreatid(uid, PageRequest.of(page - 1, 9999, Direction.DESC, "createTime"));// jpa当前页是按0的下标开始
			List<Leave> leaves = pages.getContent();
			ArrayList<LeaveResult> results = leaveService.castTeacherLeaveResult(leaves);
			map.put("leaves", results);
			
			return "mobile/m_leaveCheckList";
		}

		Page<Leave> pages = leaveService.findByTreatid(uid, PageRequest.of(page - 1, 6, Direction.ASC, "status"));// jpa当前页是按0的下标开始

		List<Leave> leaves = pages.getContent();
		System.out.println(leaves.get(0).toString());
		ArrayList<LeaveResult> results = leaveService.castTeacherLeaveResult(leaves);
		map.put("leaves", results);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		map.addAttribute("page", page);
		return "leaveCheck";
	}

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@PostMapping("leave/check/success")
	@ResponseBody
	public synchronized Result<Object> success(Integer leaid) {
		if (leaid == null || leaid.equals(""))
			return new Result<>(false, "系统异常");
		Leave leave = leaveService.findByLeaid(leaid);
		if (leave.getStatus() == 0) {
			leave.setStatus(1);
			leaveService.updateLeave(leave);
			return new Result<>(true, "审批成功");
		}
		return new Result<>(false, "其他教职工已审批");
	}

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@PostMapping("leave/check/fail")
	@ResponseBody
	public synchronized Result<Object> fail(Integer leaid) {
		if (leaid == null || leaid.equals(""))
			return new Result<>(false, "系统异常");
		Leave leave = leaveService.findByLeaid(leaid);
		if (leave.getStatus() == 0) {
			leave.setStatus(2);
			leaveService.updateLeave(leave);
			return new Result<>(true, "审批成功");
		}
		return new Result<>(false, "其他教职工已审批");
	}

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@RequestMapping("/register")
	public String register(HttpServletRequest request) {
		if (DetectMobile.isMobile(request))
			return "m_register";
		return "register";
	}

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@RequestMapping("/register/building")
	@ResponseBody
	public Result<Object> registerBuilding(Integer buildingid){
		Subject subject = SecurityUtils.getSubject();
		String uid = (String) subject.getPrincipal();
		if(buildingid>14||buildingid<1||buildingid==null||buildingid.equals(""))
			return new Result<>(false, "系统异常");
		UserBuilding userBuilding = userBuildingService.findByUidAndBuildingid(uid, buildingid);
		try{
			userBuilding.getBuildingid();
			return new Result<>(true,"注册成功");
		}catch (Exception e) {
			// TODO: handle exception
			userBuilding = new UserBuilding();
			userBuilding.setBuildingid(buildingid);
			userBuilding.setBuildingid(buildingid);
			userBuilding.setUid(uid);
			userBuildingService.save(userBuilding);
			return new Result<>(true, "注册成功");
		}
		
	}

	@RequiresRoles(value = { "instructor", "headmaster","deputy","leader","schoolLeader" }, logical = Logical.OR)
	@RequestMapping("/record/late")
	public String late() {
		return "late";
	}
	
	@RequiresPermissions({"accAttend"})
	@RequestMapping("/student/accAttend")
	public String query(){
		return "studentAccAttend";
	}
	
	@RequiresPermissions({"accAttend"})
	@PostMapping("/student/queryAccAttend")
	public String queryBackLate(@RequestParam("category") Integer category,@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime,Model map) {
		
		Subject subject = SecurityUtils.getSubject();
		String uid = subject.getPrincipal().toString();
		ArrayList<TClass> list;
		if(subject.hasRole("headmaster")){
			list = classService.findByUid(uid);
		}else if(subject.hasRole("instructor")){
			ArrayList<Major> majors = majorService.findByUid(uid);
			list = new ArrayList<>();
			for(Major major:majors){
				ArrayList<TClass> classes = classService.findByCollageidAndMajorid(major.getCollageid(), major.getMajorid());
				list.addAll(classes);
			}
		}else if(subject.hasRole("leader")){
			Collage collage = collageSevice.findByCollageleader(uid);
			ArrayList<Major> majors = majorService.findByCollageid(collage.getCollageid());
			list = new ArrayList<>();
			for(Major major:majors){
				ArrayList<TClass> classes = classService.findByCollageidAndMajorid(major.getCollageid(), major.getMajorid());
				list.addAll(classes);
			}
		}else list=new ArrayList<>();
		ArrayList<User> users = new ArrayList<>();
		for(TClass tclass:list){
			ArrayList<User> users2 = userService.findByClassid(tclass.getClassid());
			for(User user:users2)
				if(user.getType().equals("student"))
					users.add(user);
		}
		ArrayList<AccAttend> backLate = new ArrayList<>();
		ArrayList<AccAttendResult> result = new ArrayList<>();
		switch (category) {
		case 0:
			for(User user:users){
				backLate.addAll(accAttendService.findBackLate(user.getUid(), beginTime, endTime));
				result.addAll(ObjectChangeUtil.accExce(backLate, user));
			}
			map.addAttribute("results", result);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "晚归记录");
			break;
		case 1:
			for(User user:users){
				backLate.addAll(accAttendService.findNoBack(user.getUid(), beginTime, endTime));
				result.addAll(ObjectChangeUtil.accExce(backLate, user));
			}
			map.addAttribute("results", result);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "未归记录");
			break;
		case 2:
			for(User user:users){
				backLate.addAll(accAttendService.findNoOut(user.getUid(), beginTime, endTime));
				result.addAll(ObjectChangeUtil.accExce(backLate, user));
			}
			map.addAttribute("results", result);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "未出记录");
			break;
		case 3:
			
			break;
		case 4:
			for(User user:users){
				backLate.addAll(accAttendService.findException(user.getUid(), beginTime, endTime));
				result.addAll(ObjectChangeUtil.accExce(backLate, user));
			}
			map.addAttribute("results", result);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "异常记录");
			break;
		case 5:
			ArrayList<VisitHistory> visit = new ArrayList<>();
			for(User user:users){
				visit.addAll(visitHistoryService.findByUid(user.getUid(), beginTime, endTime));
			}
			map.addAttribute("results", visit);
			map.addAttribute("cat", "v");
			map.addAttribute("pageName", "访客记录");
			break;
		case 6:
			
			ArrayList<Access> access = new ArrayList<>();
			for(User user:users){
				access.addAll(accessService.findByUidAndTime(user.getUid(), beginTime, endTime));
				System.out.println(access.size());
				result.addAll(ObjectChangeUtil.accUser(access, user));
			}
			map.addAttribute("results", result);
			map.addAttribute("cat", "a");
			map.addAttribute("pageName", "通行记录");
			break;
		default:
			break;
		}
		
		return "studentAccAttendInfo";

	}
	
	@RequiresRoles(value = { "instructor", "leader" }, logical = Logical.OR)
	@RequestMapping("/mobile/leaveDetail")
	public String leaveDetail(@RequestParam("leaid") Integer leaid,Model map) throws ParseException {
		Leave leave = leaveService.findByLeaid(leaid);
		List<Leave> leaves =new ArrayList<>();
		leaves.add(leave);
		ArrayList<LeaveResult> results = leaveService.castLeaveResult(leaves);
		map.addAttribute("results", results);
		return "mobile/m_leaveDetail";
	}
	
}
