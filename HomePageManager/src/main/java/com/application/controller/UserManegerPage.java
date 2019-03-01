package com.application.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.application.Entitys.User;

import com.application.service.UserService;

@Controller

public class UserManegerPage {

	@Autowired
	private UserService userService;
/*	@Autowired*/
	private SessionRepository<Session> sessionRepository;
	

	
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
	public void alertSessionOut(HttpServletResponse response) throws IOException
	{
		
	}
	
	//主页面
	@GetMapping("/userMainPage")
	public String getFirst(Model model)
	{
		System.out.println("测试");
		List<User> users=new ArrayList<User>();
		users=userService.getAll();
		model.addAttribute("users", users);
		return "/users/user";
	}
	
	//添加用户
	@GetMapping("/addUser")
	public String AddUser( String sex,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		User user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>1)
		{
			return "/users/addUser";
		}
		else {
			//这里提供一个显示框，提示用户没有权限进行添加
			alert(response);
			return null;
		}
		
	}

	@PostMapping("/getAddUser")
	public String getUser(Map<String , Object>map)
	{
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=servletRequestAttributes.getRequest();
		String countId=request.getParameter("countId");
		if(userService.queryByCountId(countId)!=null)
		{
			map.put("msg", "用户已经存在！");
			return "users/addUser";
		}
		User user=new User();
		user.setPassword("12345678");
		user.setCountId(countId);
		user.setRole(request.getParameter("role"));
		user.setPosition(request.getParameter("position"));
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		user.setRegistrastionDate(simpleDateFormat.format(date));
		user.setDepartment(request.getParameter("department"));
		if(userService.roleAndPositionAddUser(user)==false)
		{
			map.put("msg", "无该角色或该岗位！");
			return "users/addUser";
		}
		
		return "redirect:/userMainPage";
	}
	
	//删除用户
	@GetMapping("/deleteUser")
	public String DeleteUser(@RequestParam("id") int id,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		User user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>1)
		{
			userService.deleteUser(id);
			return "redirect:/userMainPage";
		}
		else 
		{
			//显示提示框，提示用户没有权限进行删除
			alert(response);
			return null;
		}
		
	}
	
	
	
	//他人修改用户
	@GetMapping("/edit") 

	public String  Edit(@PathParam("countId")String countId,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException
	{		
		User user1=(User)request.getSession().getAttribute("user");
		if(user1.getPower()>1)
		{
			//countID是需要修改的用户的id
			User user=userService.queryByCountId(countId);
			model.addAttribute("user", user);
			return "/users/editUser";	
		}
		else
		{
			//提示没有权限进行修改
			alert(response);
			return null;
		}
		
	}
	
	
	@PostMapping("/getEditUser")

	public String getEdit(@PathParam("countId")String countId,Map<String , Object> map,Model model)
	{
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request=servletRequestAttributes.getRequest();
		
		String role=request.getParameter("role");
		String position=request.getParameter("position");
		String department=request.getParameter("department");
		User user=userService.queryByCountId(countId);
		user.setRole(role);
		user.setPosition(position);
		user.setDepartment(department);
		if(userService.roleAndPositionAddUser(user)==false)
		{
			map.put("msg", "修改失败！");
			model.addAttribute("user", user);
			return "/users/editUser";
		}
		
		return "redirect:/userMainPage";
	}
	
	
	//当前用户修改密码
	@GetMapping("/changePassword")
    public String 	ChangePassword(HttpServletRequest request,Model model)
    {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		model.addAttribute("user", user);
    	return "main/editPassword";
    }
	
	
	@PostMapping("/getEditPassword")
	public String getChangePassword(HttpServletRequest request,@RequestParam("password")String password,@RequestParam("newpassword")String newpassword,@RequestParam("newpassword2")String newpassword2,Model model,Map<String , Object> map)
	{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		if(user.getPassword().equals(password)==false)
		{
			map.put("msg","原密码错误！");
			
			model.addAttribute("user", user);
			return "main/editPassword";
		}
		if(newpassword.equals(newpassword2)==false)
		{
			map.put("msg","前后密码不一致！");
			
			model.addAttribute("user", user);
			return "main/editPassword";
		}
		
		userService.updatePassword(user.getId(), newpassword);
		model.addAttribute("user", user);
		return "/main/userInformation";
	}
	
	/*
	 * 当前用户修改自己的个人信息
	 */
	@GetMapping("/editInformation")
	public String editInformation(HttpServletRequest request,Model model)
	{
		User user=(User) request.getSession().getAttribute("user");
		
		model.addAttribute("user", user);
		return "main/editInformation";
	}
	
	@PostMapping("/getEditInformation")

	public String getEditInformation(HttpServletRequest request,Model model)
	{
		//ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		User user1=(User) request.getSession().getAttribute("user");
		String userName=request.getParameter("userName");
		String sex=request.getParameter("sex");
		String telephone=request.getParameter("telephone");
		String personalDescription=request.getParameter("personalDescription");
		String email=request.getParameter("email");

		userService.editUser(userService.queryByCountId(user1.getCountId()).getId(), userName, sex, telephone, personalDescription, email);
		
		User user=userService.queryByCountId(user1.getCountId());
		model.addAttribute("user",user);
		return "main/userInformation";
	
	}
	
	/*
	 * 
	 * 查找用户，根据用户名或者账号进行查找
	 *name表示用户名或者账号
	 *
	 */
	@GetMapping("/searchUser")
	public String getSearchUser(@RequestParam("name")String name,Model model)
	{
		//通过账号查找
		User user=userService.queryByCountId(name);
		List<User> users=userService.queryUserByUserName(name);
		if(user!=null)
		{
			users.add(user);
		}
		model.addAttribute("users", users);
		return "users/user";
	}
	
	
	
	/**
	 * 获取个人信息请求，跳转至个人信息页面
	 * 
	 */
		@GetMapping("/information")
		public String getInformation(HttpServletRequest request,Model model)
		{
			
			User user=(User)request.getSession().getAttribute("user");
			
			System.out.println(user);
			model.addAttribute("user", user);
			
			return "main/userInformation";
		}
		
		
		/*
		 * 修改用户的图片
		 * 
		 */
		
		@GetMapping("/changePicture")
		public String changePicture(HttpServletRequest request,Model model)
		{
			User user=(User )request.getSession().getAttribute("user");
			model.addAttribute("user", user);
			return "main/editPicture";
		}
		
		@PostMapping("/getChangePicture")
		public String  getChangePicture(HttpServletRequest request,@RequestParam("file")MultipartFile file,Model model,Map<String , Object>map)
		{
			User user=(User)request.getSession().getAttribute("user");
			
			if(file!=null)
			{
				userService.savePicture(user.getCountId(), file);
				user=userService.queryByCountId(user.getCountId());
				model.addAttribute("user", user);
				return "main/userInformation";
			}
			else
			{
				
				map.put("msg", "文件为空");
				model.addAttribute("user", user);
				return "users/editPicture";
			}
		}
		
		@GetMapping("/sessionout")
		@ResponseBody
		public String sessionOut(HttpServletResponse response) throws IOException
		{
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('账号已过期，请重新登录！')");
			out.println("</script>");
			return "redirect: /login";
		}
		
		@GetMapping("/out")
		
		public String logout(HttpServletRequest request)
		{
			System.out.println(request.getSession().getAttribute("user"));
			HttpSession session=request.getSession();
			session.invalidate();
			return "/main/login";
		}
}
