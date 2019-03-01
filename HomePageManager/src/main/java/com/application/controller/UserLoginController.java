package com.application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.application.Entitys.User;
import com.application.service.UserService;

//用户登录控制

@Controller
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	public String  getAdmin(@RequestParam("username")String  username,@RequestParam("password")String password,
			HttpServletRequest request,RedirectAttributes redirectAttributes,Map<String ,Object> map)
	//public String getadmin()
	{
		
		User user=userService.queryForLogin(username, password);
		if(user!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("user",user );
			
			redirectAttributes.addAttribute("countId", user.getCountId());
			return "redirect:/mainPage";
		}
		else
		{
			map.put("msg", "用户名密码错误");
			return "main/login";
		}
		
	}
	@GetMapping("/fail")
	public String getFail()
	{
		return "main/fail";
	}
	
	@GetMapping("/log")
	public String getLog()
	{
		return "main/login";
	}
	
	@GetMapping("/getSession")
	@ResponseBody
	public User getSession(HttpServletRequest request)
	{
		return (User)request.getSession().getAttribute("user");
	}
	
	
	@GetMapping("/sessionOut")
	public String sessionOut(HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('账号已过期，请重新登录！')");
		out.println("</script>");
		return "/main/login";
	}
	
	
}
