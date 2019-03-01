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
@Table(name="position")
public class Position {

	@Id
	@Column(name="position_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//岗位编码
	private String coding;
	
	//岗位名称
	private String positionName;
	
	private String departmentName;
	
	//岗位人数
	private int userNumber=0;
	
	//创建时间
	private String createTime;
	
	@OneToMany(targetEntity=User.class)
	@JoinColumn(name="position_id",referencedColumnName="position_id")
	private Set<User> users=new HashSet<User>();

	public Position() {
		super();
	}

	public Position(int id, String coding, String positionName, int userNumber, String createTime) {
		super();
		this.id = id;
		this.coding = coding;
		this.positionName = positionName;
		this.userNumber = userNumber;
		this.createTime = createTime;
	}

	
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", coding=" + coding + ", positionName=" + positionName + ", userNumber="
				+ userNumber + ", createTime=" + createTime + "]";
	}
	
	
	
	
	
	
	
	
	
}
