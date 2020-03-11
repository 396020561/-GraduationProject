package com.nchu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nchu.dto.LeaveResult;
import com.nchu.entity.AccAttend;
import com.nchu.entity.Access;
import com.nchu.entity.Account;
import com.nchu.entity.Leave;
import com.nchu.entity.User;
import com.nchu.service.AccAttendService;
import com.nchu.service.AccessService;
import com.nchu.service.AccountService;
import com.nchu.service.LeaveService;
import com.nchu.service.UserService;

import utils.DetectMobile;

@Controller
public class IndexController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccessService accessService;
		
	@Autowired
	private UserService userService;
		
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private AccAttendService accAttendService;

	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	@RequestMapping(value = "/toLogin", method = RequestMethod.POST)
	public String toLogin(String username, String password, String cat, ModelMap map, HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		Account account = accountService.findAccountByUid(username);

		if (account == null) {
			map.put("msg", "账号不存在！");
			return "login";
		}
		if (!account.getType().equals(cat)) {
			map.put("msg", "账号类型错误！");
			return "login";
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			session.setAttribute("image", account.getImage() + ".png");
		} catch (AuthenticationException e) {
			token.clear();
			map.put("msg", "账号或密码错误！");
			return "login";
		}
		String applyid = subject.getPrincipal().toString();
		System.out.println(applyid);
		ArrayList<Leave> leaves =leaveService.findByApplyid(applyid, new Sort(Direction.DESC,"createTime"));
		Leave leave = new Leave();
		if(!leaves.isEmpty())
			 leave = leaves.get(0);
		if (DetectMobile.isMobile(request)) {
			if(cat.equals("teacher")) {
				Page<Leave> pages = leaveService.findByTreatid(applyid, PageRequest.of(0, 9999, Direction.DESC, "createTime"));// jpa当前页是按0的下标开始
				List<Leave> content = pages.getContent();
				ArrayList<LeaveResult> results = leaveService.castTeacherLeaveResult(content);
				ArrayList<LeaveResult> newRes = new ArrayList<LeaveResult>();
				for(LeaveResult item:results) {
					if(item.getStatus()==0) {
						newRes.add(item);
					}
				}
				map.put("leaves", newRes);
				return "mobile/m_" + cat;
			}
			map.put("leave", leave);
			return "mobile/m_" + cat;
		}
		return cat;

	}

	@RequiresRoles(value = { "student" })
	@RequestMapping("/")
	public String index(HttpServletRequest request,ModelMap map) {
		Subject subject = SecurityUtils.getSubject();
		String applyid = subject.getPrincipal().toString();
		ArrayList<Leave> leaves = leaveService.findByApplyid(applyid, new Sort(Direction.DESC, "createTime"));
		Leave leave = new Leave();
		
		
		if (!leaves.isEmpty()) {
			leave = leaves.get(0);
			map.put("leave", leave);
		}
			
		if (DetectMobile.isMobile(request)) {
			
			return "mobile/m_student";
		}
		return "student";

	}
	
	// 查询最近一个月通行记录
		@PostMapping("/getAccess")
		@ResponseBody
		public ArrayList<Access> getAccess() {
			Subject subject = SecurityUtils.getSubject();
			String uid = subject.getPrincipal().toString();
			ArrayList<Access> access = accessService.findAccessByUid(uid);
			return access;
		}

		// 查询最近3天通行记录
		@PostMapping("/getRecentAccess")
		@ResponseBody
		public ArrayList<Access> getRecentAccess() {
			Subject subject = SecurityUtils.getSubject();
			String uid = subject.getPrincipal().toString();
			ArrayList<Access> access = accessService.findRecentAccessByUid(uid);
			return access;
		}

		// 查询访客数量
		@PostMapping("/getUserInfo")
		@ResponseBody
		public User getUserInfo(){
			Subject subject = SecurityUtils.getSubject();
			String uid = subject.getPrincipal().toString();
			User user = userService.findByUid(uid);
			return user;
		}
		
		// 查询考勤数据（晚归、未归、未出、请假、异常）
		@PostMapping("/getAccAttend")
		@ResponseBody
		public ArrayList<AccAttend> getAccAttend() {
			Subject subject = SecurityUtils.getSubject();
			String uid = subject.getPrincipal().toString();
			ArrayList<AccAttend> accAttend = accAttendService.findByUid("15046220");
			return accAttend;
		}
}
