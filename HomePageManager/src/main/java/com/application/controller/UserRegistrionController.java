package com.application.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.application.Entitys.User;
import com.application.service.UserService;


//用户注册控制
@Controller
public class UserRegistrionController {
	

	@Autowired
	private UserService userService;
	
	@GetMapping("/registry")
	public String getRegistry(HttpServletRequest request,@RequestParam("countId")String countId,@RequestParam("password")String password,RedirectAttributes redirectAttributes,Map<String , Object>map)
	{
		
		/*
		 * 如果返回，表示可以注册，并且成功注册，可以直接进入主页面
		 *如果返回false，表示注册失败，账号已经存在
		 */
		
		
		if(userService.queryByCountId(countId)==null)
		{
			HttpSession session=request.getSession();
			User user=new User();
			user.setCountId(countId);
			user.setPassword(password);
			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			user.setRegistrastionDate(simpleDateFormat.format(date));
			userService.saveUser(user);
			redirectAttributes.addAttribute("countId", countId);
			
			session.setAttribute("user", user);
			return "redirect:mainPage";
		}
		else
		{
			return "main/registry";
		}
	
	}
	
	
	
	
	@GetMapping("/reg")
	public String getReg()
	{
		return "main/registry";
	}
	
	
	
	
}
