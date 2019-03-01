package com.application.Entitys;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="role")
public class Role {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int id;
	@Column(name="role_name")
	private String roleName;
	
	private int num=0;
	
	//注册时间
	private String registrionDate;
	
	//权限，为1,2,3表示三种权限，1是所有权限，2是部分权限，3是没有权限
	private int power;

	@OneToMany(targetEntity=User.class)
	@JoinColumn(name="role_id",referencedColumnName="role_id")
	private Set<User> users=new HashSet<User>();
	
	
	
	
	public Role() {
		super();
	}





	public Role(int id, String roleName, String registrionDate, int power) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.registrionDate = registrionDate;
		this.power = power;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getRoleName() {
		return roleName;
	}





	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}





	public String getRegistrionDate() {
		return registrionDate;
	}





	public void setRegistrionDate(String registrionDate) {
		this.registrionDate = registrionDate;
	}





	public int getPower() {
		return power;
	}





	public void setPower(int power) {
		this.power = power;
	}





	public Set<User> getUsers() {
		return users;
	}





	public void setUsers(Set<User> users) {
		this.users = users;
	}





	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", registrionDate=" + registrionDate + ", power=" + power
				+ "]";
	}
	
	
	
	
	
	
	
	
	

}
