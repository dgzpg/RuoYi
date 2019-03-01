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
@Table(name="department")
public class Department {
	
	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	//部门名称
	private String departmentName;
	
	//部门所属公司
	private String subordinateCompany;
	
	//部门的人数
	private int userNumber;
	
	//部门创建时间
	private String registrionDate;
	
	@OneToMany(targetEntity=Position.class)
	@JoinColumn(name="department_id",referencedColumnName="department_id")
	private Set<Position> positions=new HashSet<Position>(); 

	
	
	public Department() {
		super();
	}



	public Department(int id, String departmentName, String subordinateCompany, int userNumber, String registrionDate) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.subordinateCompany = subordinateCompany;
		this.userNumber = userNumber;
		this.registrionDate = registrionDate;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public String getSubordinateCompany() {
		return subordinateCompany;
	}



	public void setSubordinateCompany(String subordinateCompany) {
		this.subordinateCompany = subordinateCompany;
	}



	public int getUserNumber() {
		return userNumber;
	}



	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}



	public String getRegistrionDate() {
		return registrionDate;
	}



	public void setRegistrionDate(String registrionDate) {
		this.registrionDate = registrionDate;
	}
	
	
	


	public Set<Position> getPositions() {
		return positions;
	}



	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}



	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", subordinateCompany="
				+ subordinateCompany + ", userNumber=" + userNumber + ", registrionDate=" + registrionDate + "]";
	}
	
	
	
	
	
	
	

}
