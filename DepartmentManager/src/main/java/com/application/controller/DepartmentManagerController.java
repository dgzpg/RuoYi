package com.application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.application.Entitys.Department;
import com.application.Entitys.User;
import com.application.reposity.UserRepository;
import com.application.service.DepartmentService;

@Controller
public class DepartmentManagerController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	
	public void alert(HttpServletResponse response) throws IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('您没有权限进行此操作！')");
		out.println("history.back()");
		out.println("</script>");
	}
	public void alertDelete(HttpServletResponse response) throws IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('删除失败！')");
		out.println("history.back()");
		out.println("</script>");
	}
	
	//添加部门跳转控制
	@GetMapping("/addDepartment")
	public String addDepartment(HttpServletResponse response,HttpServletRequest request) throws IOException
	{
		user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>3)
		{
			return "departments/addDepartment";
		}
		else
		{
			alert(response);
			return null;
		}
	}
	
	
	//添加部门的实际操作
	@PostMapping("/getAddDepartment")
	public String getAddDepartment( @RequestParam("departmentName")String departmentName,HttpServletRequest request,@RequestParam("company")String company,Map<String , Object> map)
	{
		Department department=new Department();
		department.setDepartmentName(departmentName);
		department.setSubordinateCompany(company);
		department.setUserNumber(0);
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		department.setRegistrionDate(simpleDateFormat.format(date));
		
		if(departmentService.addDepartment(department))
		{
			return "redirect:/mainPage";
		}
		else
		{
			map.put("msg", "添加失败，该部门已经存在！");
			return "departments/addDepartment";
		}
		
		
	}
	
	
	//修改部门跳转控制
	@GetMapping("/edit")
	public String edit(@RequestParam("id")int id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException
	{
		user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>3)
		{
			Department department=departmentService.queryById(id);
			model.addAttribute("department", department);
			return "departments/editDepartment";
		}
		else
		{
			alert(response);
			return null;
			
		}
	}
	
	//修改部门控制
	@PostMapping("/getEdit")
	
	public String  getEdit(@PathParam("id")int id,@RequestParam("name")String name,@RequestParam("company")String company,Model model,Map<String , Object>map)
	{
		
		if(departmentService.edit(id, name, company))
		{
			return "redirect:/mainPage";
		}
		else
		{
			Department department=departmentService.queryById(id);
			model.addAttribute("department", department);
			map.put("msg", "该部门名已经存在！");
			return "departments/editDepartment";
		}
		
	}
	
	
	//删除部门
	@GetMapping("/deleteDepartment")
	public String deleteDepartment(HttpServletResponse response,HttpServletRequest request,@PathParam("id")int id,Map<String , Object>map,Model model) throws IOException
	{
		user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>3)
		{
			if(departmentService.deleteDepartment(id)==false)
			{
				alertDelete(response);
			}
			List<Department>departments=departmentService.queryAll();
			model.addAttribute("departments", departments);
			return "redirect:/mainPage";
		}
		else {
			alert(response);
			return null;
		}
	}
	
	
	///查找部门
	@GetMapping("/searchDepartment")
	public String searchDepartment(@RequestParam("name")String name,Model model)
	{
		List<Department> departments=departmentService.queryByName(name);
		for(Department department : departmentService.queryByCompany(name))
		{
			departments.add(department);
		}
		model.addAttribute("departments", departments);
		return "departments/department";
	}
	
}
