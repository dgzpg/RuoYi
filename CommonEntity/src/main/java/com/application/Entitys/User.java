package com.application.Entitys;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//必须全部为数字

	private String  countId;
	private String userName="无";
	private String password;
	private String department="无";
	private String sex="男";
	private String role="普通角色";
	private String  telephone="无";
	private String position="无";
	private String email="无";
	//用户权力值
	private int power=0;
	
	
	


	private String registrastionDate;
	//图片路径
	private String Path="profile.jpg";
	//个人描述
	private String personalDescription="无";
	
	
	
	public User() {
		super();
	}



	public User(int id, String countId, String userName, String password, String department, String sex, String role,
			String  telephone, String position, String email, String registrastionDate, String path) {
		super();
		this.id = id;
		this.countId = countId;
		this.userName = userName;
		this.password = password;
		this.department = department;
		this.sex = sex;
		this.role = role;
		this.telephone = telephone;
		this.position = position;
		this.email = email;
		this.registrastionDate = registrastionDate;
		Path = path;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public int  getPower() {
		return power;
	}



	public void setPower(int power) {
		this.power = power;
	}


	public String getCountId() {
		return countId;
	}



	public void setCountId(String countId) {
		this.countId = countId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String  getTelephone() {
		return telephone;
	}



	public void setTelephone(String  telephone) {
		this.telephone = telephone;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRegistrastionDate() {
		return registrastionDate;
	}



	public void setRegistrastionDate(String registrastionDate) {
		this.registrastionDate = registrastionDate;
	}



	public String getPath() {
		return Path;
	}



	public void setPath(String path) {
		Path = path;
	}



	public String getPersonalDescription() {
		return personalDescription;
	}



	public void setPersonalDescription(String personalDescription) {
		this.personalDescription = personalDescription;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", countId=" + countId + ", userName=" + userName + ", password=" + password
				+ ", department=" + department + ", sex=" + sex + ", role=" + role + ", telephone=" + telephone
				+ ", position=" + position + ", email=" + email + ", registrastionDate=" + registrastionDate + ", Path="
				+ Path + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
