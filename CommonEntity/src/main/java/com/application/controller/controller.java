package com.application.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Entitys.Department;
import com.application.Entitys.Position;
import com.application.Entitys.Role;
import com.application.Entitys.User;
import com.application.reposity.DepartmentRepository;
import com.application.reposity.PositionRepository;
import com.application.reposity.RoleRepository;
import com.application.reposity.UserRepository;


@Controller
public class controller {
	
	@Autowired
	private DepartmentRepository departmentReposity;
	
	@Autowired
	private PositionRepository positionReposity;
	
	@Autowired
	private UserRepository userReposity;
	
	@Autowired
	private RoleRepository roleReposity;
	
	@GetMapping("/test")
	@ResponseBody
	private String one()
	{
		roleReposity.deleteRoleById(8);
		return "test";
	}

	@GetMapping("/test2")
	@ResponseBody
	public String test2()
	{
		User user=new User();
		user.setCountId("12121");
		user.setUserName("zhangsan");
		userReposity.save(user);
		User user2 =new User();
		user2.setCountId("1111");
		user2.setUserName("lisi");
		userReposity.save(user2);
		int id=positionReposity.queryByName("研发人员").get(0).getId();
		Position position=positionReposity.findById(id).get();
		position.getUsers().add(user);
		position.getUsers().add(user2);
		positionReposity.saveAndFlush(position);
		
		return "ret";
	}
}
