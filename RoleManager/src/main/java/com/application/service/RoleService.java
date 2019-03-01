package com.application.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.application.Entitys.Role;
import com.application.Entitys.User;


@Service
public interface RoleService {

	//根据角色名称来查询角色
	public List<Role> queryByRoleName(String roleName);
	
	//根据角色的id来查找
	public Role queryById(int id);
	
	
	///获取所有角色
	public List<Role> queryAll();
	
	
	//添加角色
	public boolean addRole(Role role);
	
	//删除角色
	
	public boolean deleteRole(int id);
	
	
	//根据id修改角色权限
	public boolean editRole(int id,String roleName,int power);
	
	
	//根据角色权限来查询角色
	public List<Role> queryByPower(int power);
	
	//角色中添加用户
	public boolean roleAddUser(User user,String name);
	
}
