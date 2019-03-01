package com.application.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.application.Entitys.Role;
import com.application.service.RoleService;

@Controller
public class HomePage {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/mainPage")
	public String mainPage(Model model)
	{
		List<Role> roles=roleService.queryAll();
		model.addAttribute("roles", roles);
		return "/roles/role";
	}
	
	
}
