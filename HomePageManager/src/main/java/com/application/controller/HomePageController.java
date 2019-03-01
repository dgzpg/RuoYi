package com.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.Entity.FirstMenu;
import com.application.Entitys.User;
import com.application.repository.FirstMenuRepository;
import com.application.service.UserService;


@Controller

public class HomePageController {

	

	@Autowired
	private FirstMenuRepository firstMenuRepository;
	
	@Autowired
	private UserService userService;
	
	
	
	
	
	
	
	
	//进入主页面，获取登录账号的信息，获取菜单信息
	@GetMapping("/mainPage")
	public String  getMainPage(HttpServletRequest request,Model model)
	{
		User user1=(User)request.getSession().getAttribute("user");
		
		
		User user=userService.queryByCountId(user1.getCountId());
		List<FirstMenu> menus=firstMenuRepository.findAll();
		model.addAttribute("user", user);
		model.addAttribute("menus", menus);
		return "main/index";
	}
	

}
