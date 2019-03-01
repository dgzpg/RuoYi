package com.application.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.application.Entitys.Position;

public interface PositionRepository extends JpaRepository<Position, Integer>{
	
	//根据岗位名称查询
		@Query(value="select  * from position where position_name=?1",nativeQuery=true)
		public List<Position> queryByName(String name);
		
		//根据岗位编码查询
		@Query(value="select * from position where coding=?1",nativeQuery=true)
		public List<Position> queryByCoding(String coding);
		
		@Modifying
		@Transactional
		@Query(value="delete from position where position_id=?1",nativeQuery=true)
		public void deleteById(int id);

}
