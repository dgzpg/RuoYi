package com.application.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.application.Entity.FirstMenu;
import com.application.repository.FirstMenuRepository;
import com.application.service.MenuService;

public class MenuServiceImpl implements MenuService{


	@Autowired
	private FirstMenuRepository menuRepository;
	
	

	
	public List<FirstMenu> queryAllFirstMenu() {
		
		return menuRepository.findAll();
	}
	
}
