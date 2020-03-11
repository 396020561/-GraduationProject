package com.nchu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MobileController {
	
	@RequestMapping("/mobile/leaveRec")
	public String leaveRec() {
		
		return "mobile/m_leaveRec";
	}
	
	@RequestMapping("/mobile/leaveAdd")
	public String leaveAdd() {
		
		return "mobile/m_leave";
	}
	
	@RequestMapping("/mobile/visitorInside")
	public String visitorInside() {
		
		return "mobile/m_visitorInside";
	}
	
	@RequestMapping("/mobile/visitorOutside")
	public String visitorOutside() {
		
		return "mobile/m_visitorOutside";
	}
	
	@RequestMapping("/mobile/query")
	public String query() {
		
		return "mobile/m_query";
	}
	
	@RequestMapping("/mobile/dormManage")
	public String dormMange() {
		
		return "mobile/m_dormManage";
	}
	
	@RequestMapping("/mobile/myInfo")
	public String myInfo() {
		
		return "mobile/m_myInfo";
	}
	
	@RequestMapping("/mobile/changePwd")
	public String changePwd() {
		
		return "mobile/m_changePwd";
	}
	
	@RequestMapping("/mobile/about")
	public String about() {
		
		return "mobile/m_about";
	}
	
	@RequestMapping("/mobile/visitorList")
	public String visitorList() {
		
		return "mobile/m_visitorList";
	}
	
	//教职工
	@RequestMapping("/mobile/teacher")
	public String teacher() {
		
		return "mobile/m_teacher";
	}
	
	@RequestMapping("/mobile/leaveCheck")
	public String leaveCheck() {
		
		return "mobile/m_leaveCheck";
	}
	
	@RequestMapping("/mobile/queryAttend")
	public String queryAttend() {
		
		return "mobile/m_queryAttend";
	}
	
}
