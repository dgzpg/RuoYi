package com.application.service;

import java.util.List;


import com.application.Entitys.Position;

public interface PositionService {
	
	//查询所有岗位
	public List<Position> queryAll();
	
	//保存岗位
	public boolean addPosition(Position position,String departmentName);
	
	//根据岗位名查询
	public List<Position> queryByName(String name);
	
	//根据岗位编码查询
	public List<Position> queryByCoding(String coding);
	
	//根据id删除岗位
	public boolean deleteById(int id);
	
	//根据id 查询岗位
	public Position queryById(int id);
	
	//修改岗位的信息
	public boolean editPosition(int id,String name,String coding);
}
