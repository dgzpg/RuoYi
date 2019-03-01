package com.application.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.application.Entitys.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	

	@Query(value="select * from department where department_name=?1",nativeQuery=true)
	public List<Department> queryByName(String departmentName);
	
	@Query(value="select * from department where subordinate_company=?1",nativeQuery=true)
	public List<Department> queryByCompany(String company);
	
	@Query(value="select * from department where department_name=?1 and subordinate_company=?2",nativeQuery=true)
	public Department queryByNameAndCompany(String name,String company);
	
	@Modifying
	@Transactional
	@Query(value="delete from department where department_id=?1",nativeQuery=true)
	public void deleteById(int id);
}
