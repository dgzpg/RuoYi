package com.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Entitys.Department;
import com.application.service.DepartmentService;

@Controller
public class MainPageController {
	
	@Autowired
	private DepartmentService departmentService;
	@GetMapping("/mainPage")
	public String mainPage(Model model)
	{
		List<Department>departments=departmentService.queryAll();
		model.addAttribute("departments", departments);

		return "departments/department";
	}
	
	
	
	
	
}
