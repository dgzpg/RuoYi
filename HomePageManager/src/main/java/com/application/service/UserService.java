package com.application.service;





import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.application.Entitys.User;






public interface UserService {
	
	//根据用户名和密码来查找，主要用于登录
	public User queryForLogin(String  countId,String password);
	
	//根据用户名来判断是否存在账号，用于注册
	public User queryForRegistry(String countId,String password);
	
	//根据用户账号来查询，
	public User queryByCountId(String  countId);
	
	
	//保存用户
	public void saveUser(User user);
	
	//查询所有用户
	public List<User> getAll();
	
	/*//根据id来查找
	public User queryById(int id);*/
	
	//管理员修改用户信息
	public void updateUser(int id, String role, String position,
			String department);
	//用户修改用户信息
	public void editUser(int id,String userName,String sex,String telephone,String personalDescription,String email);
	
	
	//修改用户密码
	public void updatePassword(int id,String password);
	
	//根据id删除用户
	public void deleteUser(int id);
	
	//根据用户名来查找用户
	public List<User> queryUserByUserName(String userName);
	
	//根据账号来上传图片
	public void savePicture(String countId,MultipartFile file);
	
	//角色添加用户
	public boolean roleAndPositionAddUser(User user);
	
	
}
