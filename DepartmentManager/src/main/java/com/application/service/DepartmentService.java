package com.application.service;

import java.util.List;



import com.application.Entitys.Department;

public interface DepartmentService {
	
	//根据部门名称查询
	public List<Department> queryByName(String departmentName);
	
	//根据部门所属分公司查询
	public List<Department> queryByCompany(String company);

	//查询所有的部门
	public List<Department> queryAll();
	
	
	//添加部门
	public boolean addDepartment(Department department);
	
	
	//根据部门id查找部门
	public Department queryById(int id);
	
	//根据部门id修改部门信息
	public boolean edit(int id,String name,String company);
	
	//根据id删除部门
	public boolean deleteDepartment(int id);
	
	
}
