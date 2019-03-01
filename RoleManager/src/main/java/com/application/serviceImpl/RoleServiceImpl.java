package com.application.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.Entitys.Role;
import com.application.Entitys.User;
import com.application.reposity.RoleRepository;
import com.application.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;	
	
	//根据角色名来查询角色
	public List<Role> queryByRoleName(String roleName) {
		
		List<Role> roles=roleRepository.queryByRoleName(roleName);
		return roles;
	}
	///根据角色id来查找
	public Role queryById(int id) {
	
		return roleRepository.findById(id).get();
	}
	

	//查询所有角色
	public List<Role> queryAll() {
		
		return roleRepository.findAll();
	}
	
	//添加角色
	public boolean addRole(Role role)
	{
		//角色已经存在
		if(roleRepository.queryByRoleName(role.getRoleName()).size()>0)
		{
			return false;
		}
		roleRepository.saveAndFlush(role);
		return true;
	}
	
	//删除角色
	public boolean deleteRole(int id)
	{
		
		try {
			roleRepository.deleteRoleById(id);
		}
		catch (Exception e) {
			return false;
		}
		return true;
		
	}

	
	//修改角色
	public boolean editRole(int id,String roleName,int power) {
		if(roleRepository.queryByRoleName(roleName).size()>0)
		{
			return false;
		}
		Optional<Role> optional=roleRepository.findById(id);
		Role role=optional.get();
		role.setPower(power);
		role.setRoleName(roleName);
		roleRepository.saveAndFlush(role);
		return true;
		
	}

	//根据权利值查询角色
	public List<Role> queryByPower(int power) {
	
		return roleRepository.queryByPower(power);
	}
	
	//角色中添加用户
	public boolean roleAddUser(User user, String name) {
		
		return false;
	}

	
	
	
	
}
