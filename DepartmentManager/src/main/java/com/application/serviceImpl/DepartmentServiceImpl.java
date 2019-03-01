package com.application.serviceImpl;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.application.Entitys.Department;

import com.application.reposity.DepartmentRepository;
import com.application.reposity.UserRepository;
import com.application.service.DepartmentService;



@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	

	
	//根据部门名称来查询
	public List<Department> queryByName(String departmentName) 
	{
		
		return departmentRepository.queryByName(departmentName);
	}

	
	//根据部门所属公司查询
	public List<Department> queryByCompany(String company) 
	{
		
		return departmentRepository.queryByCompany(company);
	}

	//查询所有部门
	public List<Department> queryAll() {
		
		return departmentRepository.findAll();
	}

	//添加部门
	
	public boolean addDepartment(Department department) 
	{
		if(departmentRepository.queryByNameAndCompany(department.getDepartmentName(), department.getSubordinateCompany())!=null)
		{
			return false;
		}
		else
		{
			departmentRepository.saveAndFlush(department);
			return true;
		}
		
	}


	public Department queryById(int id) {
		
		return departmentRepository.findById(id).get();
	}


	public boolean edit(int id, String name, String company) {
		//表示已经有该部门名和所属公司的部门
		if(departmentRepository.queryByNameAndCompany(name, company)!=null)
		{
			return false;
		}
		else
		{
			Department department=departmentRepository.findById(id).get();
			department.setDepartmentName(name);
			department.setSubordinateCompany(company);
			departmentRepository.saveAndFlush(department);
			return true;
		}
	
	}


	public boolean deleteDepartment(int id) {
		try {
			departmentRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
	

}
