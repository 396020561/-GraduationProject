package com.nchu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.nchu.dao.AccountRoleDao;
import com.nchu.dao.BlackListDao;
import com.nchu.dao.CollageDao;
import com.nchu.dao.LeaveDao;
import com.nchu.dao.RoleDao;
import com.nchu.entity.Access;
import com.nchu.entity.Account;
import com.nchu.entity.AccountRole;
import com.nchu.entity.Major;
import com.nchu.entity.Role;
import com.nchu.entity.TClass;
import com.nchu.entity.Visitor;
import com.nchu.service.AccessService;
import com.nchu.service.AccountRoleService;
import com.nchu.service.AccountService;
import com.nchu.service.ClassService;
import com.nchu.service.MajorService;
import com.nchu.service.RolePermissionService;
import com.nchu.service.VisitorService;
import com.sun.accessibility.internal.resources.accessibility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DormitoryApplicationTests {

	@Autowired
	private RolePermissionService rpService;

	@Autowired
	private AccountRoleService arService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private LeaveDao leaveDao;

	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private ClassService classService;
	@Autowired
	private MajorService majorService;
	
	@Autowired
	private CollageDao collageDao;
	@Test
	public void contextLoads() {

	}

	@Test
	public void Tes21(){
		classService.delByClassid("150461");
	}
	
	@Test
	public void test01() {
		Account account = accountService.findAccountByAid(1);
		System.out.println(account.getStatus());
	}

	@Test
	public void encryption() {

		// Account account = accountService.findAccountByUid("root");
		ByteSource salt = ByteSource.Util.bytes("rootroot");
		Object md = new SimpleHash("MD5", "root", salt, 1024);
		System.out.println(md.toString());
	}
	
	@Autowired
	private BlackListDao blackListDao;
	
	@Autowired
	private AccessService accessService;
	
	@Test
	public void accessibility(){
		ArrayList<Access> list = accessService.findByUidAndTime("15046220", "2019-05-01 00:00", "2019-05-20 00:00");
		System.out.println(list.toString());
	}
	
	@Test
	public void del(){
		blackListDao.deleteById(30);
	}

	@Test
	public void leaveDao() {
		System.out
				.println(leaveDao.queryByApplyid("15046220", new Sort(Direction.DESC, "createTime")).get(0).toString());
	}

	@Test
	public void numDays() throws ParseException {
		String big = "2019-04-28";
		String small = "2019-03-15";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date bigDate = sdf.parse(big);
		Date smallDate = sdf.parse(small);
		long diff = bigDate.getTime() - smallDate.getTime();
		long day = diff / (1000 * 60 * 60 * 24);
		System.out.println(day);
	}

	@Test
	public void visitorSave() {
		Visitor visitor = new Visitor();
		Date date = new Date();
		visitor.setBeginTime(date.toString());
		visitor.setEndTime(date.toString());
		visitor.setCreateTime(date.toString());
		visitor.setFaceid("12346");
		visitor.setName("123");
		visitor.setPhone("13123");
		visitor.setSex("男");
		visitor.setUid("131231");
		visitorService.save(visitor);
	}
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	@Test
	public void addRole(){
		Integer aid = null;
		Integer rid = null;
		//accountRoleDao
		HashSet<AccountRole> queryByAid = accountRoleDao.queryByAid(aid);
		
	}
}
