package com.application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.application.Entitys.Role;
import com.application.Entitys.User;
import com.application.service.RoleService;

@Controller
public class RoleManager {
	
	@Autowired
	private RoleService roleService;
	
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
	
	
	
	
	//此处的name代表角色名和角色的权值
		@GetMapping("/getRoles")

		public String getRole(@RequestParam("name") String name,Model model)
		{
			if(name=="")
			{
				return "redirect:/mainPage";
			}
			
			List<Role> roles=roleService.queryByRoleName(name);
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
			if(pattern.matcher(name).matches())
			{
					int power=Integer.valueOf(name);
					for(Role role : roleService.queryByPower(power))
					{
						roles.add(role);
					}
			}
			model.addAttribute("roles", roles);
			return "roles/role";
		}
		
		//添加角色页面跳转
		@GetMapping("/addRole")
		public String addRole(HttpServletResponse response,HttpServletRequest request) throws IOException
		{
			User user=(User)request.getSession().getAttribute("user");
			if(user.getPower()>4)
			{
				return "roles/addRole";
			}
			else
			{
				alert(response);
				return null;
			}
			
		}
		
		//实现添加角色
		@PostMapping("/getAddRole")
		public String getAddRole(@RequestParam("roleName")String roleName,@RequestParam("power") String power,Map<String , Object>map)
		{
			int pow=Integer.valueOf(power);
			Role role=new Role();
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			role.setRegistrionDate(simpleDateFormat.format(date));
			role.setPower(pow);
			role.setRoleName(roleName);
			if(roleService.addRole(role)==false)
			{
				map.put("msg", "角色已经存在！");
				return "/roles/addRole";
			}
			
			return "redirect:/mainPage";
		}
		
		//修改跳转控制
		@GetMapping("/edit")
		public String edit(HttpServletResponse response,HttpServletRequest request,@PathParam("id")int id,Model model) throws IOException
		{
			User user=(User)request.getSession().getAttribute("user");
			if(user.getPower()>4)
			{
				Role role=new Role();
				role=roleService.queryById(id);
				model.addAttribute("role", role);
				return "roles/editRole";
			}
			else
			{
				alert(response);
				return null;
			}
		}
		
		//修改控制
		@PostMapping("/getEdit")
		
		public String getEdit(@PathParam("id")int id,@RequestParam("Power")String Power,@RequestParam("roleName")String roleName,Model model,Map<String, Object>map)
		{
			int power=Integer.valueOf(Power);
			if(roleService.editRole(id, roleName,power)==false)
			{
				Role role=new Role();
				role=roleService.queryById(id);
				model.addAttribute("role", role);
				map.put("msg", "该角色名已经存在！");
				return "/roles/editRole";
			}
			return "redirect:/mainPage";
		}
		
		
		
		@GetMapping("/deleteRole")
		public String deleteRole(@PathParam("id")int id,HttpServletResponse response,HttpServletRequest request) throws IOException
		{
			User user=(User)request.getSession().getAttribute("user");
			if(user.getPower()>4)
			{
				if(roleService.deleteRole(id))
				{
					return "redirect:/mainPage";
				}
				else
				{
					alertDelete(response);
					return null;
				}
			}
			else
			{
				alert(response);
				return null;
			}
			
		}
		
		
		

}
