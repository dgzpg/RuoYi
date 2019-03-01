package com.application.Entity;




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
@Table(name="menu")
public class FirstMenu {
	
	@Id
	@Column(name="menuid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(targetEntity=SecondMenu.class)
	@JoinColumn(name="menuid",referencedColumnName="menuid")
	private Set<SecondMenu> menus;
	
	
	
	
	public FirstMenu() {
		super();
	}


	public FirstMenu(Long id, String name, Set<SecondMenu> menus) {
		super();
		this.id = id;
		this.name = name;
		this.menus = menus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<SecondMenu> getMenus() {
		return menus;
	}


	public void setMenus(Set<SecondMenu> menus) {
		this.menus = menus;
	}

	

	
	

	@Override
	public String toString() {
		return "FirstMenu [id=" + id + ", name=" + name + ", menus=" + menus + "]";
	}
	
	
	
	
	
	
	
	
	

}
