package com.application.serviceImpl;

import java.text.SimpleDateFormat;



import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entitys.Department;
import com.application.Entitys.Position;
import com.application.reposity.DepartmentRepository;
import com.application.reposity.PositionRepository;
import com.application.service.PositionService;


@Service
public class PositionServiceImpl implements PositionService{

	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	//查询所有的岗位
	public List<Position> queryAll() {
		
		return positionRepository.findAll();
	}
	
	//保存岗位
	public boolean addPosition(Position position,String departmentName) {
		
		String coding=position.getCoding();
		String name=position.getPositionName();
		if(positionRepository.queryByCoding(coding).size()==0&&positionRepository.queryByName(name).size()==0&&departmentRepository.queryByName(departmentName).size()>0)
		{
			Department department=departmentRepository.queryByName(departmentName).get(0);
			department.getPositions().add(position);
			
			position.setDepartmentName(department.getDepartmentName());
			positionRepository.save(position);
			departmentRepository.saveAndFlush(department);
			return true;
			
		}
		return false;
	}

	public List<Position> queryByName(String name) {
		
		return positionRepository.queryByName(name);
	}

	public List<Position> queryByCoding(String coding) {
		// TODO Auto-generated method stub
		return positionRepository.queryByCoding(coding);
	}

	public boolean deleteById(int id) {
	
		try {
			System.out.println(id);
			positionRepository.deleteById(id);
		
		} catch (Exception e) {
			
			return false;
		}
		return true;
		
	}

	public Position queryById(int id) {
		
		return positionRepository.findById(id).get();
	}

	public boolean editPosition(int id, String name, String coding) {
		if(positionRepository.queryByCoding(coding).size()==0&&positionRepository.queryByName(name).size()==0)
		{
			Position position=positionRepository.findById(id).get();
			position.setCoding(coding);
			position.setPositionName(name);
			positionRepository.saveAndFlush(position);
			return true;
		}
		return false;
	}
	
	

	


	
	
	
	

}



