package com.nchu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nchu.dto.LeaveResult;
import com.nchu.dto.Result;
import com.nchu.entity.AccAttend;
import com.nchu.entity.Access;
import com.nchu.entity.Leave;
import com.nchu.entity.User;
import com.nchu.entity.VisitHistory;
import com.nchu.entity.Visitor;
import com.nchu.exception.LeaveException;
import com.nchu.service.AccAttendService;
import com.nchu.service.AccessService;
import com.nchu.service.LeaveService;
import com.nchu.service.UserBuildingService;
import com.nchu.service.UserService;
import com.nchu.service.VisitHistoryService;
import com.nchu.service.VisitorService;

import utils.DetectMobile;
import utils.FileUpload;;

@Controller
public class StudentController {

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private VisitorService visitorService;

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
	
	@RequiresRoles(value={"student"})
	@RequestMapping("/overview")
	public String overview(){
		
		return "overview";
	}

	@RequiresRoles(value = { "student" })
	@RequestMapping("/leave")
	public String leavePage(HttpServletRequest request,Model map, Integer page) throws ParseException {
		if(page==null||page.equals(""))
			page=1;
		Subject subject = SecurityUtils.getSubject();
		String uid = subject.getPrincipal().toString();
		
		if(DetectMobile.isMobile(request)) {
			Page<Leave> pages = leaveService.findByApplyid(uid, PageRequest.of(page - 1, 9999, Direction.DESC, "createTime"));
			List<Leave> leaves = pages.getContent();
			ArrayList<LeaveResult> results = leaveService.castLeaveResult(leaves);
			map.addAttribute("results", results);
			return "mobile/m_leaveRec";
		}

		Page<Leave> pages = leaveService.findByApplyid(uid, PageRequest.of(page - 1, 6, Direction.DESC, "createTime"));// jpa当前页是按0的下标开始
		List<Leave> leaves = pages.getContent();
		ArrayList<LeaveResult> results = leaveService.castLeaveResult(leaves);
		
		map.addAttribute("results", results);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		map.addAttribute("page", page);
		return "leave";
	}

	@RequiresRoles(value = { "student" })
	@RequestMapping("/leave/add")
	public String leaveAddPage() {
		return "leaveAdd";
	}

	@RequiresRoles(value = { "student" })
	@RequestMapping(value = "/leave/addLeave", method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> addLeave(Leave leave) throws ParseException {
		System.out.println(leave.getBeginTime());
		Subject subject = SecurityUtils.getSubject();
		String uid = subject.getPrincipal().toString();
		leave.setApplyid(uid);
		try {
			leaveService.addLeave(leave);
		} catch (LeaveException e) {
			return new Result<Object>(true, e.getMessage());
			// }catch (Exception e) {
			// // TODO: handle exception
			// return new Result<Object>(true, "系统异常！");
		}
		return new Result<>(true, "申请成功！");
	}

	@RequiresRoles(value = { "student" })
	@RequestMapping("/visitor")
	public String visitor(HttpServletRequest request, Model map, Integer page) {
		if(page==null||page.equals(""))
			page=1;
		Subject subject = SecurityUtils.getSubject();
		if(DetectMobile.isMobile(request)) {
			Page<Visitor> pages = visitorService.findByUid(subject.getPrincipal().toString(),
					PageRequest.of(page - 1, 9999, Direction.DESC, "createTime"));
			map.addAttribute("results", pages.getContent());
			return "mobile/m_visitorList";
		}
		Page<Visitor> pages = visitorService.findByUid(subject.getPrincipal().toString(),
				PageRequest.of(page - 1, 6, Direction.DESC, "createTime"));
		map.addAttribute("results", pages.getContent());
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		return "visitor";

	}

	@RequiresRoles(value = { "student" })
	@RequestMapping("leave/cancel")
	@ResponseBody
	public Result<Object> leaveCancel(Integer leaid) {
		try {
			leaveService.leaveCancel(leaid);
			return new Result<>(true);
		} catch (LeaveException e) {
			// TODO: handle exception
			return new Result<>(false, e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false, "系统错误!");
		}
	}

	@RequiresRoles(value = { "student" })
	@PostMapping("/visitor/add")
	public String visitorAdd(Visitor visitor, MultipartFile image) {
		Subject subject = SecurityUtils.getSubject();
		String uid = subject.getPrincipal().toString();
		User user = userService.findByUid(uid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			visitor.setBuildingid(userBuildingService.findByUid(user.getUid()).getBuildingid());
			visitor.setUid(uid);
			String faceid = FileUpload.imageUpload(image);
			visitor.setFaceid(faceid);
			visitor.setCreateTime(sdf.format(new Date()));
			visitorService.save(visitor);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "forward:/visitor?page=1";
		}
		return "forward:/visitor?page=1";
	}

	@RequiresRoles(value = { "student" })
	@PostMapping("/visitor/edit/{id}")
	public String visitorEdit(@RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime,
			@PathVariable("id") Integer visitorid) {
		Visitor visitor = visitorService.findByVisitor(visitorid);
		visitor.setBeginTime(beginTime);
		visitor.setEndTime(endTime);
		visitorService.save(visitor);
		return "forward:/visitor?page=1";

	}
	
	@RequiresPermissions({"accAttend"})
	@RequestMapping("/accAttend")
	public String AccAttend(){
		return "m_query";
	}
	
	@RequiresPermissions({"accAttend"})
	@PostMapping("/queryAccAttend")
	public String queryBackLate(@RequestParam("category") Integer category,@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime,Model map) {
		Subject subject = SecurityUtils.getSubject();
		String uid = subject.getPrincipal().toString();
//		if(uid.indexOf("1504")==-1) {
//			if(stuNum==null || stuNum.equals("")) {
//				stuNum="1";
//				uid = "15046220";
//			}else {
//				uid = stuNum;
//			}	
//		}

		switch (category) {
		case 0:
			ArrayList<AccAttend> backLate = accAttendService.findBackLate(uid, beginTime, endTime);
			map.addAttribute("results", backLate);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "晚归记录");
			break;
		case 1:
			ArrayList<AccAttend> noBack = accAttendService.findNoBack(uid, beginTime, endTime);
			map.addAttribute("results", noBack);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "未归记录");
			break;
		case 2:
			ArrayList<AccAttend> noOut = accAttendService.findNoOut(uid, beginTime, endTime);
			map.addAttribute("results", noOut);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "未出记录");
			break;
		case 3:
			
			break;
		case 4:
			ArrayList<AccAttend> exc = accAttendService.findException(uid, beginTime, endTime);
			map.addAttribute("results", exc);
			map.addAttribute("cat", "e");
			map.addAttribute("pageName", "异常记录");
			break;
		case 5:
			ArrayList<VisitHistory> visit = visitHistoryService.findByUid(uid, beginTime, endTime);
			map.addAttribute("results", visit);
			map.addAttribute("cat", "v");
			map.addAttribute("pageName", "访客记录");
			break;
		case 6:
			ArrayList<Access> access = accessService.findByUidAndTime(uid, beginTime, endTime);
			map.addAttribute("results", access);
			map.addAttribute("cat", "a");
			map.addAttribute("pageName", "通行记录");
			break;
		default:
			break;
		}
		
		return "m_queryResult";

	}
}
