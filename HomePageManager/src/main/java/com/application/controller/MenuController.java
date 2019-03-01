package com.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/getRole")
	public String getRole()
	{
		return "redirect:http://localhost:8030/mainPage";
	}
	
	@GetMapping("/getUser")
	public String getUser()
	{
		System.out.println("user");
		return "redirect:/userMainPage";
	}
	
	@GetMapping("/getDepartment")
	public String getDepartment()
	{
		return "redirect:http://localhost:8040/mainPage";
	}
	
	@GetMapping("/getPosition")
	public String getPosition()
	{
		return "redirect:http://localhost:8050/mainPage";
	}
}
