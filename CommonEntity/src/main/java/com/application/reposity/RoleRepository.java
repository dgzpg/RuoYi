package com.application.reposity;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.application.Entitys.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

		//根据角色名来查询
		@Query(value="select * from role where role_name=?1",nativeQuery=true)
		public List<Role> queryByRoleName(String roleName);
		
		//根据权限来查询
		@Query(value="select * from role where power=?1",nativeQuery=true)
		public List<Role> queryByPower(int power);
		
		@Modifying
		@Transactional
		@Query(value="delete from role where role_id=?1",nativeQuery=true)
		public void deleteRoleById(int id);
}
