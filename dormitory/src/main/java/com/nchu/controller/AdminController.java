package com.nchu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nchu.dto.AccountResult;
import com.nchu.dto.BuildingResult;
import com.nchu.dto.ClassResult;
import com.nchu.dto.CollageResult;
import com.nchu.dto.DormitoryResult;
import com.nchu.dto.MajorResult;
import com.nchu.dto.Result;
import com.nchu.dto.RoleAndPermissionResult;
import com.nchu.entity.Account;
import com.nchu.entity.AccountRole;
import com.nchu.entity.BlackList;
import com.nchu.entity.Building;
import com.nchu.entity.Collage;
import com.nchu.entity.DorAdmin;
import com.nchu.entity.Dormitory;
import com.nchu.entity.Major;
import com.nchu.entity.Permission;
import com.nchu.entity.Role;
import com.nchu.entity.RolePermission;
import com.nchu.entity.TClass;
import com.nchu.entity.User;
import com.nchu.entity.UserDormitory;
import com.nchu.entity.Visitor;
import com.nchu.service.AccountRoleService;
import com.nchu.service.AccountService;
import com.nchu.service.BlackListService;
import com.nchu.service.BuildingService;
import com.nchu.service.ClassService;
import com.nchu.service.CollageService;
import com.nchu.service.DorAdminService;
import com.nchu.service.DormitoryService;
import com.nchu.service.MajorService;
import com.nchu.service.PermissionService;
import com.nchu.service.RolePermissionService;
import com.nchu.service.RoleService;
import com.nchu.service.UserDormitoryService;
import com.nchu.service.UserService;
import com.nchu.service.VisitorService;

import utils.DetectMobile;
import utils.FileUpload;

@Controller
public class AdminController {

	@Autowired
	private BlackListService blackListService;

	@Autowired
	private VisitorService visitorService;

	@Autowired
	private DormitoryService dormitoryService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserDormitoryService userDormitoryService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private UserService userService;

	@Autowired
	private CollageService collageService;

	@Autowired
	private MajorService majorService;

	@Autowired
	private ClassService classService;

	@Autowired
	private AccountRoleService accountRoleService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DorAdminService dorAdminService;

	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/blackList")
	public String blackListManager(HttpServletRequest request, ModelMap map, Integer page) {
		if (page == null || page.equals(""))
			page = 1;
		Page<BlackList> pages = blackListService.findAll(PageRequest.of(page - 1, 6, Direction.DESC, "createTime"));
		map.addAttribute("results", pages.getContent());
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_blackListManager";
		return "blackListManager";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/blackList/add")
	public String blackListAdd(BlackList blackList, MultipartFile image) throws IllegalStateException, IOException {
		try{
		String faceid = FileUpload.imageUpload(image);
		blackList.setFaceid(faceid);
		blackListService.save(blackList);
		}catch (Exception e) {
			// TODO: handle exception
			return "forward:/manager/blackList?page=1";
		}
		return "forward:/manager/blackList?page=1";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/blackList/edit/{id}")
	public String blackListEdit(BlackList blackList, MultipartFile image, @PathVariable("id") Integer id)
			throws IllegalStateException, IOException {
		String faceid = FileUpload.imageUpload(image);
		blackList.setFaceid(faceid);
		blackList.setId(id);
		blackListService.save(blackList);
		return "forward:/manager/blackList?page=1";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/blackList/delete")
	@ResponseBody
	public Result<Object> blackListDelete(Integer id) {
		System.out.println(id);
		try {
			System.out.println(id);
			blackListService.delById(id);
			return new Result<>(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/visitor")
	public String visitorManager(HttpServletRequest request, ModelMap map, Integer page) {
		if (page == null || page.equals(""))
			page = 1;
		Page<Visitor> pages = visitorService.findAll(PageRequest.of(page - 1, 6, Direction.DESC, "createTime"));
		map.addAttribute("results", pages.getContent());
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_visitorManager";
		return "visitorManager";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/visitor/delete")
	@ResponseBody
	public Result<Object> visitorDelete(Integer visitorid) {
		System.out.println(visitorid);
		try {
			System.out.println(visitorid);
			visitorService.delById(visitorid);
			return new Result<>(true, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}
//----------------------------------------------------宿舍与楼栋管理--------------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/dormitory")
	public String dormitoryManager(HttpServletRequest request, ModelMap map, Integer page, Integer buildingid) {
		HttpSession session = request.getSession();
		if (session.getAttribute("building") == null) {
			ArrayList<Building> buildings = buildingService.findAll(new Sort(Direction.ASC, "buildingid"));
			session.setAttribute("buildings", buildings);
		}
		if (page == null || page.equals(""))
			page = 1;
		if (buildingid == null || buildingid.equals(""))
			buildingid = 1;
		Page<Dormitory> pages = dormitoryService.findByBuildingid(buildingid,
				PageRequest.of(page - 1, 6, Direction.ASC, "dormitoryNum"));
		ArrayList<DormitoryResult> results = new ArrayList<>();
		for (Dormitory dormitory : pages.getContent()) {
			ArrayList<UserDormitory> userDormitories = userDormitoryService
					.findByDormitoryNum(dormitory.getDormitoryNum());
			DormitoryResult dormitoryResult = new DormitoryResult();
			dormitoryResult.setDormitoryNum(dormitory.getDormitoryNum());
			if (userDormitories.isEmpty()) {
				for (int i = 0; i < 4; i++) {
					UserDormitory userDormitory = new UserDormitory();
					userDormitory.setUid("空");
					dormitoryResult.getUserDormitories().add(userDormitory);
				}
				dormitoryResult.setDel(false);
				dormitoryResult.setAdd(true);
				dormitoryResult.setExc(false);
				results.add(dormitoryResult);
				continue;
			}
			if (userDormitories.size() < 4) {
				dormitoryResult.getUserDormitories().addAll(userDormitories);
				for (int i = 0; i < (4 - userDormitories.size()); i++) {
					UserDormitory userDormitory = new UserDormitory();
					userDormitory.setUid("空");
					dormitoryResult.getUserDormitories().add(userDormitory);
				}
				dormitoryResult.setDel(true);
				dormitoryResult.setAdd(true);
				dormitoryResult.setExc(true);
				results.add(dormitoryResult);
				continue;
			}
			dormitoryResult.setDel(true);
			dormitoryResult.setAdd(false);
			dormitoryResult.setExc(true);
			dormitoryResult.getUserDormitories().addAll(userDormitories);
			results.add(dormitoryResult);
		}
		Building building = buildingService.queryByBuildingid(buildingid);
		map.addAttribute("buildingName", building.getName());
		map.addAttribute("buildingid", buildingid);
		map.addAttribute("results", results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_dormitoryManager";
		return "dormitoryManager";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/building/add")
	public String buildingAdd(String name, HttpServletRequest request) {
		Building building = buildingService.findByName(name);
		try {
			building.setName(name);
			buildingService.save(building);
		} catch (NullPointerException e) {
			// TODO: handle exception
			building = new Building();
			building.setName(name);
			buildingService.save(building);
		}
		HttpSession session = request.getSession();
		ArrayList<Building> buildings = buildingService.findAll(new Sort(Direction.ASC, "buildingid"));
		session.setAttribute("buildings", buildings);
		return "forward:/manager/dormitory?buildingid=1&page=1";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/dormitory/add")
	public String dormitoryAdd(String dormitoryNum, HttpServletRequest request, String buildingName) {
		System.out.println(buildingName);
		Dormitory dormitory = dormitoryService.findByDormitoryNum(dormitoryNum);
		Building building = buildingService.findByName(buildingName);
		try {
			dormitory.setDormitoryNum(dormitoryNum);
			dormitoryService.save(dormitory);
		} catch (NullPointerException e) {
			// TODO: handle exception
			dormitory = new Dormitory();
			dormitory.setBuildingid(building.getBuildingid());
			dormitory.setDormitoryNum(dormitoryNum);
			dormitoryService.save(dormitory);
		}
		return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";
	}

	@RequiresRoles({ "admin" })
	@PostMapping("/userDormitory/add/{dormitoryNum}")
	public String userDormitoryAdd(String buildingName, @PathVariable("dormitoryNum") String dormitoryNum, String uid) {
		Building building = buildingService.findByName(buildingName);
		UserDormitory userDormitory = userDormitoryService.findByUid(uid);
		try {
			userDormitory.setDormitoryNum(dormitoryNum);
			userDormitoryService.save(userDormitory);
			return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";
		} catch (NullPointerException e) {
			// TODO: handle exception
			User user = userService.findByUid(uid);
			try {
				UserDormitory userDormitory2 = new UserDormitory();
				userDormitory2.setName(user.getName());
				userDormitory2.setUid(uid);
				userDormitory2.setDormitoryNum(dormitoryNum);
				userDormitoryService.save(userDormitory2);
				return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";
			} catch (NullPointerException e1) {
				// TODO: handle exception
				return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";
			}
		}

	}

	@RequiresRoles({ "admin" })
	@PostMapping("/userDormitory/del/{dormitoryNum}")
	public String userDormitoryDel(String buildingName, @PathVariable("dormitoryNum") String dormitoryNum, String uid) {
		Building building = buildingService.findByName(buildingName);
		UserDormitory userDormitory = userDormitoryService.findByUid(uid);
		try {
			userDormitory.setDormitoryNum(null);
			userDormitoryService.save(userDormitory);
			return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";
		} catch (NullPointerException e) {
			// TODO: handle exception
			return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";
		}

	}

	@RequiresRoles({ "admin" })
	@PostMapping("/userDormitory/exc")
	public String userDormitoryExc(String buildingName, String uid1, String uid2) {
		Building building = buildingService.findByName(buildingName);
		userDormitoryService.exchange(uid1, uid2);
		return "forward:/manager/dormitory?buildingid=" + building.getBuildingid() + "&page=1";

	}

	@RequiresRoles({ "admin" })
	@GetMapping("/userDormitory/search/{dormitoryNum}")
	public String userDormitoryExc(@PathVariable("dormitoryNum") String dormitoryNum, ModelMap map,
			HttpServletRequest request) {
		Dormitory dormitory = dormitoryService.findByDormitoryNum(dormitoryNum);
		ArrayList<DormitoryResult> results = new ArrayList<>();
		DormitoryResult dormitoryResult = new DormitoryResult();
		try {
			Building building = buildingService.queryByBuildingid(dormitory.getBuildingid());
			map.addAttribute("buildingName", building.getName());
			map.addAttribute("buildingid", dormitory.getBuildingid());
			map.addAttribute("page", 1);
			map.addAttribute("pageTotal", 1);
			ArrayList<UserDormitory> userDormitories = userDormitoryService.findByDormitoryNum(dormitoryNum);
			dormitoryResult.setDormitoryNum(dormitoryNum);
			if (userDormitories.isEmpty()) {
				for (int i = 0; i < 4; i++) {
					UserDormitory userDormitory = new UserDormitory();
					userDormitory.setUid("空");
					dormitoryResult.getUserDormitories().add(userDormitory);
				}
				dormitoryResult.setDel(false);
				dormitoryResult.setAdd(true);
				dormitoryResult.setExc(false);
				results.add(dormitoryResult);
				map.addAttribute("results", results);
				if (DetectMobile.isMobile(request))
					return "m_dormitoryManager";
				return "dormitoryManager";
			}
			if (userDormitories.size() < 4) {
				dormitoryResult.getUserDormitories().addAll(userDormitories);
				for (int i = 0; i < (4 - userDormitories.size()); i++) {
					UserDormitory userDormitory = new UserDormitory();
					userDormitory.setUid("空");
					dormitoryResult.getUserDormitories().add(userDormitory);
				}
				dormitoryResult.setDel(true);
				dormitoryResult.setAdd(true);
				dormitoryResult.setExc(true);
				results.add(dormitoryResult);
				map.addAttribute("results", results);
				if (DetectMobile.isMobile(request))
					return "m_dormitoryManager";
				return "dormitoryManager";
			}
			dormitoryResult.setDel(true);
			dormitoryResult.setAdd(false);
			dormitoryResult.setExc(true);
			dormitoryResult.getUserDormitories().addAll(userDormitories);
			results.add(dormitoryResult);
			map.addAttribute("results", results);
			if (DetectMobile.isMobile(request))
				return "m_dormitoryManager";
			return "dormitoryManager";
		} catch (NullPointerException e) {
			// TODO: handle exception
			map.addAttribute("page", 1);
			map.addAttribute("pageTotal", 1);
			map.addAttribute("results", results);
			if (DetectMobile.isMobile(request))
				return "m_dormitoryManager";
			return "dormitoryManager";
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/dormitoryAdmin")
	public String dormitoryAdmin(HttpServletRequest request, ModelMap map, Integer page) {
		if (page == null || page.equals(""))
			page = 1;
		Page<Building> buildings = buildingService
				.findAll(PageRequest.of(page - 1, 6, new Sort(Direction.ASC, "buildingid")));
		ArrayList<BuildingResult> results = new ArrayList<>();
		for (Building building : buildings.getContent()) {
			BuildingResult result = new BuildingResult();
			result.setBuildingName(building.getName());
			if (building.getAdminid() == null) {
				result.setAdminid("空");
				result.setAdd(true);
				result.setDel(false);
				results.add(result);
				continue;
			}
			result.setAdminid(building.getAdminid());
			DorAdmin dorAdmin = dorAdminService.findByUid(building.getAdminid());
			result.setAdminName(dorAdmin.getName());
			result.setAdd(false);
			result.setDel(true);
			results.add(result);
		}
		map.put("results", results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", buildings.getTotalPages() == 0 ? 1 : buildings.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_dormitoryAdmin";
		return "dormitoryAdmin";
	}
	
	@RequiresRoles({"admin"})
	@RequestMapping("/dormitoryAdmin/info")
	public String dorAdminInfo(Integer page,ModelMap map){
		if (page == null || page.equals(""))
			page = 1;
		Page<DorAdmin> pages = dorAdminService.findAll(PageRequest.of(page - 1, 6, new Sort(Direction.ASC, "uid")));
		map.put("results", pages.getContent());
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		return "dormitoryAdminInfo";
	}
	
	@RequiresRoles({"admin"})
	@RequestMapping("/dorAdmin/update")
	public String dorAdminUpdate(DorAdmin dorAdmin,MultipartFile image){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			System.out.println(1);
			System.out.println(dorAdmin.toString());
			String faceid = FileUpload.imageUpload(image);
			System.out.println(faceid);
			dorAdmin.setFaceid(faceid);
			dorAdmin.setCreateTime(sdf.format(new Date()));
			dorAdminService.save(dorAdmin);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "forward:/dormitoryAdmin/info?page=1";
		}
		return "forward:/dormitoryAdmin/info?page=1";
	}
	
	@RequiresRoles({"admin"})
	@RequestMapping("/dorAdmin/delete")
	@ResponseBody
	public Result<Object> dorAdminDelete(Integer dorid){
		try{
			DorAdmin dorAdmin = dorAdminService.findByDorid(dorid);
			System.out.println(dorAdmin.toString());
			dorAdminService.delete(dorAdmin);
			return new Result<>(true);
		}catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false,"系统错误");
		}
	}
	
	@RequiresRoles({ "admin" })
	@PostMapping("/dormitoryAdmin/add/{buildingName}")
	public String dorAdminAdd(@PathVariable("buildingName") String buildingName, String uid) {
		DorAdmin dorAdmin = dorAdminService.findByUid(uid);
		Building building = buildingService.findByName(buildingName);
		try {
			building.setAdminid(dorAdmin.getUid());
			buildingService.save(building);
			return "forward:/manager/dormitoryAdmin";
		} catch (NullPointerException e) {
			// TODO: handle exception
			return "forward:/manager/dormitoryAdmin";
		}
	}

	@RequiresRoles({ "admin" })
	@GetMapping("/dormitoryAdmin/del/{buildingName}")
	public String dorAdminDel(@PathVariable("buildingName") String buildingName) {
		Building building = buildingService.findByName(buildingName);
		building.setAdminid(null);
		buildingService.save(building);
		return "forward:/manager/dormitoryAdmin";
	}

	// ---------------------------------------------------collage---------------------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/collage")
	public String collageManager(HttpServletRequest request, Integer page, ModelMap map) {
		if (page == null || page.equals(""))
			page = 1;
		Page<Collage> pages = collageService.findAll(PageRequest.of(page - 1, 6, Direction.ASC, "collageid"));
		ArrayList<CollageResult> results = new ArrayList<>();
		for (Collage collage : pages.getContent()) {
			CollageResult collageResult = new CollageResult();
			collageResult.setCollageid(collage.getCollageid());
			collageResult.setCollagename(collage.getCollagename());
			if (collage.getCollageleader() == null || collage.getCollageleader().equals("")) {
				collageResult.setLeadername("无");
				collageResult.setDel(false);
				collageResult.setAdd(true);
				results.add(collageResult);
				continue;
			}
			User user = userService.findByUid(collage.getCollageleader());
			collageResult.setLeadername(user.getName());
			collageResult.setDel(true);
			collageResult.setAdd(false);
			results.add(collageResult);
		}
		map.addAttribute("results", results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_collageManager";
		return "collageManager";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/collage/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> collageAdd(Collage collage, HttpServletRequest request) {

		if (collage.getCollageid() == null || collage.getCollageid().equals(""))
			return new Result<>(false);
		if (collage.getCollagename() == null || collage.getCollagename().equals(""))
			return new Result<>(false);
		Collage collage2 = collageService.findByCollageid(collage.getCollageid());
		try {
			collage2.getCollageid();
			return new Result<>(false);
		} catch (NullPointerException e) {
			// TODO: handle exception
			collageService.save(collage);
			return new Result<>(true);
		}

	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/collage/del" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> collageDel(String collageid, HttpServletRequest request) {
		Collage collage = collageService.findByCollageid(collageid);
		try {
			collageService.delByCollageid(collage.getCollageid());
			return new Result<>(true);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/collageLeader/add/{collageid}" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> collageLeaderAdd(String uid, @PathVariable("collageid") String collageid,
			HttpServletRequest request) {
		User user = userService.findByUid(uid);
		Collage collage = collageService.findByCollageid(collageid);
		try {
			collage.setCollageleader(user.getUid());
			collageService.save(collage);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("false");
			return new Result<>(false);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/collageLeader/del" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> collageLeaderDel(String collageid, HttpServletRequest request) {
		Collage collage = collageService.findByCollageid(collageid);
		try {
			collage.setCollageleader(null);
			collageService.save(collage);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("false");
			return new Result<>(false);
		}
	}

	// ------------------------------------------------------major-------------------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/major")
	public String majorManager(HttpServletRequest request, Integer page, ModelMap map, String collageid) {
		if (page == null || page.equals(""))
			page = 1;
		if (collageid == null || collageid.equals("")) {
			Page<Major> pages = majorService.findAll(PageRequest.of(page - 1, 6, Direction.ASC, "majorid"));
			ArrayList<MajorResult> results = new ArrayList<>();
			for (Major major : pages.getContent()) {
				MajorResult majorResult = new MajorResult();
				majorResult.setMajorid(major.getMajorid());
				majorResult.setCollageid(major.getCollageid());
				majorResult.setName(major.getName());
				if (major.getUid() == null || major.getUid().equals("")) {

					majorResult.setInstructor("无");
					majorResult.setAdd(true);
					majorResult.setDel(false);
					results.add(majorResult);
					continue;
				}
				User user = userService.findByUid(major.getUid());
				majorResult.setInstructor(user.getName());
				majorResult.setAdd(false);
				majorResult.setDel(true);
				results.add(majorResult);
			}
			map.addAttribute("results", results);
			map.addAttribute("page", page);
			map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
			if (DetectMobile.isMobile(request))
				return "m_majorManager";
			return "majorManager";
		}
		Page<Major> pages = majorService.findByCollageid(collageid,
				PageRequest.of(page - 1, 6, Direction.ASC, "majorid"));
		ArrayList<MajorResult> results = new ArrayList<>();
		for (Major major : pages.getContent()) {
			MajorResult majorResult = new MajorResult();
			majorResult.setMajorid(major.getMajorid());
			majorResult.setCollageid(major.getCollageid());
			majorResult.setName(major.getName());
			if (major.getUid() == null || major.getUid().equals("")) {
				majorResult.setName("无");
				majorResult.setAdd(true);
				majorResult.setDel(false);
				results.add(majorResult);
				continue;
			}
			User user = userService.findByUid(major.getUid());
			majorResult.setInstructor(user.getName());
			majorResult.setAdd(false);
			majorResult.setDel(true);
			results.add(majorResult);
		}
		map.addAttribute("results", results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_majorManager";
		return "majorManager";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/instructor/add/{collageid}/{majorid}" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> instructorAdd(String uid, @PathVariable("collageid") String collageid,
			@PathVariable("majorid") String majorid, HttpServletRequest request) {
		User user = userService.findByUid(uid);
		Major major = majorService.findByCollageidAndMajorid(collageid, majorid);
		try {
			major.setUid(user.getUid());
			majorService.save(major);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("false");
			return new Result<>(false);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/instructor/del" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> instructorDel(String collageid, String majorid, HttpServletRequest request) {
		Major major = majorService.findByCollageidAndMajorid(collageid, majorid);
		try {
			major.setUid(null);
			majorService.save(major);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("false");
			return new Result<>(false);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/major/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> majorAdd(Major major, HttpServletRequest request) {

		if (major.getCollageid() == null || major.getCollageid().equals(""))
			return new Result<>(false);
		if (major.getMajorid() == null || major.getMajorid().equals(""))
			return new Result<>(false);
		if (major.getName() == null || major.getName().equals(""))
			return new Result<>(false);
		Major major2 = majorService.findByCollageidAndMajorid(major.getCollageid(), major.getMajorid());
		try {
			major2.getMajorid();
			return new Result<>(false);
		} catch (Exception e) {
			// TODO: handle exception
			majorService.save(major);
			return new Result<>(true);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/major/del" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> majorDel(String collageid, String majorid, HttpServletRequest request) {
		Major major = majorService.findByCollageidAndMajorid(collageid, majorid);
		try {
			majorService.delByCollageidAndMajorid(collageid, major.getMajorid());
			return new Result<>(true);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false);
		}
	}

	// -----------------------------------------------class-------------------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/class")
	public String classManager(HttpServletRequest request, Integer page, ModelMap map, String collageid,
			String majorid) {
		if (page == null || page.equals(""))
			page = 1;
		if (collageid == null || collageid.equals("") || majorid == null || majorid.equals("")) {
			Page<TClass> pages = classService.findAll(PageRequest.of(page - 1, 6, Direction.ASC, "classid"));
			ArrayList<ClassResult> results = new ArrayList<>();
			for (TClass tClass : pages.getContent()) {
				ClassResult classResult = new ClassResult();
				classResult.setClassid(tClass.getClassid());
				if (tClass.getUid() == null || tClass.getUid().equals("")) {
					classResult.setHeadmaster("无");
					classResult.setAdd(true);
					classResult.setDel(false);
					results.add(classResult);
					continue;
				}
				User user = userService.findByUid(tClass.getUid());
				classResult.setHeadmaster(user.getName());
				classResult.setAdd(false);
				classResult.setDel(true);
				results.add(classResult);
			}
			map.addAttribute("results", results);
			map.addAttribute("page", page);
			map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
			if (DetectMobile.isMobile(request))
				return "m_classManager";
			return "classManager";
		}
		Page<TClass> pages = classService.findByCollageidAndMajorid(collageid, majorid,
				PageRequest.of(page - 1, 6, Direction.ASC, "classid"));
		ArrayList<ClassResult> results = new ArrayList<>();
		for (TClass tClass : pages.getContent()) {
			ClassResult classResult = new ClassResult();
			classResult.setClassid(tClass.getClassid());
			if (tClass.getUid() == null || tClass.getUid().equals("")) {
				classResult.setHeadmaster("无");
				classResult.setAdd(true);
				classResult.setDel(false);
				results.add(classResult);
				continue;
			}
			User user = userService.findByUid(tClass.getUid());
			classResult.setHeadmaster(user.getName());
			classResult.setAdd(false);
			classResult.setDel(true);
			results.add(classResult);
		}
		map.addAttribute("results", results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		if (DetectMobile.isMobile(request))
			return "m_classManager";
		return "classManager";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/class/add" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> classAdd(TClass tClass, HttpServletRequest request) {
		if (tClass.getClassid() == null || tClass.getClassid().equals(""))
			return new Result<>(false);
		String collageid = Integer.parseInt(tClass.getClassid().substring(2, 4)) + "";
		String majorid = tClass.getClassid().substring(4, 5);
		TClass class1 = classService.findByClassid(tClass.getClassid());
		try {
			class1.getClassid();
			return new Result<>(false);
		} catch (NullPointerException e) {
			// TODO: handle exception
			tClass.setCollageid(collageid);
			tClass.setMajorid(majorid);
			classService.save(tClass);
			return new Result<>(true);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/class/del" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> classDel(String classid, HttpServletRequest request) {
		System.out.println(classid);
		try {
			classService.delByClassid(classid);
			return new Result<>(true);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result<>(false);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/headmaster/add/{classid}" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> headmasterAdd(String uid, @PathVariable("classid") String classid,
			HttpServletRequest request) {
		User user = userService.findByUid(uid);
		TClass tClass = classService.findByClassid(classid);
		try {
			tClass.setUid(user.getUid());
			classService.save(tClass);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("false");
			return new Result<>(false);
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping(value = { "/headmaster/del" }, method = RequestMethod.POST)
	@ResponseBody
	public Result<Object> headmasterDel(String classid, HttpServletRequest request) {
		TClass tClass = classService.findByClassid(classid);
		try {
			tClass.setUid(null);
			classService.save(tClass);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("false");
			return new Result<>(false);
		}
	}

	// ----------------------------------------------------class-student-----------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/student/{classid}")
	public String studentClassManager(HttpServletRequest request, Integer page, @PathVariable("classid") String classid,
			ModelMap map) {
		HttpSession session = request.getSession();
		session.setAttribute("classid", classid);
		if (page == null || page.equals(""))
			page = 1;
		Page<User> pages = userService.findByClassid(classid, PageRequest.of(page - 1, 6, Direction.ASC, "uid"));
		map.addAttribute("results", pages.getContent());
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		return "userClassManager";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/{classid}/student/add")
	@ResponseBody
	public Result<Object> userClassAdd(HttpServletRequest request, @PathVariable("classid") String classid,
			String uid) {
		if (classid == null)
			return new Result<>(false, "系统异常");
		User user = userService.findByUid(uid);
		try {
			if (user.getClassid() != null)
				return new Result<>(false, "此用户已有班级");
			if (!user.getType().equals("student"))
				return new Result<>(false, "此用户不是学生");
			user.setClassid(classid);
			userService.save(user);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "此用户不存在");
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/class/student/del")
	@ResponseBody
	public Result<Object> userClassDel(String uid) {
		User user = userService.findByUid(uid);
		try {
			user.setClassid(null);
			userService.save(user);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}

	// ---------------------------------------------------student-------------------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/manager/student")
	public String studentClassManager(HttpServletRequest request, Integer page, ModelMap map) {
		if (page == null || page.equals(""))
			page = 1;
		Page<User> pages = userService.findAll(PageRequest.of(page - 1, 6, Direction.ASC, "uid"));
		ArrayList<User> results = new ArrayList<>();
		for (User user : pages.getContent()) {
			if (user.getType().equals("student"))
				results.add(user);
		}
		map.addAttribute("results", results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		return "studentManager";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/studentAdd")
	public String toStudentAdd() {

		return "studentAdd";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/student/add")
	public String studentAdd(User user, MultipartFile image) {
		try {
			String faceid = FileUpload.imageUpload(image);
			user.setFaceid(faceid);
			userService.addUserAndAccount(user);
		} catch (Exception e) {
			// TODO: handle exception
			return "studentAdd";
		}
		return "studentAdd";
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/student/remove")
	@ResponseBody
	public Result<Object> studentRemove(String uid) {
		try {
			userService.delByUid(uid);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}

	// --------------------------------------------------account-------------------------------------------
	@RequiresRoles({ "admin" })
	@RequestMapping("/account")
	public String account(Integer page, String uid, ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("roles") == null) {
			ArrayList<Role> roles = roleService.findAll();
			session.setAttribute("roles", roles);
		}
		if (session.getAttribute("permissions") == null) {
			ArrayList<Permission> permissions = permissionService.findAll();
			session.setAttribute("permissions", permissions);
		}
		if (page == null || page.equals(""))
			page = 1;
		if (uid == null || uid.equals("")) {
			ArrayList<AccountResult> results = new ArrayList<>();
			Page<Account> pages = accountService.findAll(PageRequest.of(page - 1, 6, Direction.ASC, "uid"));
			for (Account account : pages) {
				AccountResult accountResult = new AccountResult();
				accountResult.setAid(account.getAid());
				accountResult.setImage(account.getImage());
				accountResult.setType(account.getType());
				accountResult.setUid(account.getUid());
				HashSet<Role> myRoles = accountRoleService.findRolesByAid(account.getAid());
				accountResult.setRoles(myRoles);
				results.add(accountResult);
			}
			map.put("results", results);
			map.addAttribute("page", page);
			map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
			return "accountManager";
		} else {
			ArrayList<AccountResult> results = new ArrayList<>();
			Account account = accountService.findAccountByUid(uid);
			AccountResult accountResult = new AccountResult();
			accountResult.setAid(account.getAid());
			accountResult.setImage(account.getImage());
			accountResult.setType(account.getType());
			accountResult.setUid(account.getUid());
			HashSet<Role> myRoles = accountRoleService.findRolesByAid(account.getAid());
			accountResult.setRoles(myRoles);
			results.add(accountResult);
			map.put("results", results);
			map.addAttribute("page", page);
			map.addAttribute("pageTotal", 1);
			return "accountManager";
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/account/delete")
	@ResponseBody
	public Result<Object> accountRemove(String uid) {
		try {
			accountService.delByUid(uid);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}

	@RequiresRoles({ "admin" })
	@RequestMapping("/role/delete")
	@ResponseBody
	public Result<Object> roleRemove(Integer rid) {
		try {
			roleService.delByRid(rid);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}
	
	@RequiresRoles({ "admin" })
	@RequestMapping("/per/delete")
	@ResponseBody
	public Result<Object> perRemove(String pname,Integer rid) {
		try {
			Permission permission = permissionService.findByPname(pname);
			RolePermission rp = rolePermissionService.findByRidAndPid(rid,permission.getPid());
			rp.getRid();
			rolePermissionService.delete(rp);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}
	
	@RequiresRoles({ "admin" })
	@RequestMapping("/myRole/add")
	@ResponseBody
	public Result<Object> myRoleAdd(Integer aid, Integer rid) {
		try {
			AccountRole accountRole = new AccountRole();
			accountRole.setAid(aid);
			accountRole.setRid(rid);
			accountRoleService.save(accountRole);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}
	
	@RequiresRoles({ "admin" })
	@RequestMapping("/role/add")
	public String roleAdd(Role role) {
		try {
			Role r1 = roleService.findByRname(role.getRname());
			r1.getRname();
			return "forward:/RoleAndPer";
		} catch (NullPointerException e) {
			// TODO: handle exception
			roleService.save(role);
			return "forward:/RoleAndPer";
		}
	}
	
	@RequiresRoles({ "admin" })
	@RequestMapping("/permission/add")
	public String perAdd(Permission permission) {
		try {
			Permission permission2 = permissionService.findByPname(permission.getPname());
			permission.getPname();
			return "forward:/RoleAndPer";
		} catch (NullPointerException e) {
			// TODO: handle exception
			permissionService.save(permission);
			return "forward:/RoleAndPer";
		}
	}
	
	@RequiresRoles({ "admin" })
	@RequestMapping("/myPer/add")
	@ResponseBody
	public Result<Object> myPerAdd(Integer rid, Integer pid) {
		try {
			RolePermission rolePermission = new RolePermission();
			rolePermission.setPid(pid);
			rolePermission.setRid(rid);
		    rolePermissionService.save(rolePermission);
			return new Result<>(true);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new Result<>(false, "系统异常");
		}
	}

	@RequiresRoles({"admin"})
	@RequestMapping("/RoleAndPer")
	public String relation(HttpServletRequest request,ModelMap map,Integer page){
		HttpSession session = request.getSession();
		if(session.getAttribute("roles")==null){
			ArrayList<Role> roles = roleService.findAll();
			session.setAttribute("roles", roles);
		}
		if(session.getAttribute("permissions")==null){
			ArrayList<Permission> permissions = permissionService.findAll();
			session.setAttribute("permissions", permissions);
		}
		if(page==null||page.equals(""))
			page=1;
		Page<Role> pages = roleService.findAll(PageRequest.of(page - 1, 6, Direction.ASC, "rid"));
		ArrayList<RoleAndPermissionResult> results = new ArrayList<>();
		for(Role role :pages.getContent()){
			RoleAndPermissionResult accountResult = new RoleAndPermissionResult();
			accountResult.setRid(role.getRid());
			accountResult.setMark(role.getMark());
			HashSet<String> set = rolePermissionService.findPermissionsByRid(role.getRid());
			accountResult.setRoles(set);
			results.add(accountResult);
		}
		map.put("results",results);
		map.addAttribute("page", page);
		map.addAttribute("pageTotal", pages.getTotalPages() == 0 ? 1 : pages.getTotalPages());
		return "roleAndPermission";
	}
}
