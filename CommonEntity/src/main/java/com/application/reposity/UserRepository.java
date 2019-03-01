package com.application.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entitys.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	

	//根据用户名和密码查找是否存在该对象
	@Query(value="select * from user a where a.count_Id=?1 and a.password=?2",nativeQuery=true)
	public User queryByName(String countId ,String password);
	
	
	//根据用户名查找，用于验证是否该用户名已经注册
	@Query(value="select * from user where count_id=?1",nativeQuery=true)
	public User queryByCountId(String  countId);
	
	
	
	@Query(value="select * from user where user_name=?1",nativeQuery=true)
	public List<User> queryByUserName(String userName);
	
	@Query(value="select role_id frome user where id=?1",nativeQuery=true)
	public int queryRoleIdById(int id);
}
