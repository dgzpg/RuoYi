package com.application.controller;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.application.Entitys.Position;

import com.application.service.PositionService;

@Controller
public class MainPage {

	@Autowired
	private PositionService positionService;
	
	
	@GetMapping("/mainPage")
	public String mainPage(Model model)
	{
		List<Position>positions=positionService.queryAll();
		model.addAttribute("positions", positions);
		return "/MainPage";
	}
	
}
