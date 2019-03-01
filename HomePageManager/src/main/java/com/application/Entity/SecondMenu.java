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
@Table(name="secondmenu")
public class SecondMenu {

	@Id
	@Column(name="secondmenuid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@OneToMany(targetEntity=ThirdMenu.class)
	@JoinColumn(name="secondmenuid",referencedColumnName="secondmenuid")
	private Set<ThirdMenu> menus;
	
	private String url;
	
	
	
	

	public SecondMenu() {
		super();
	}

	public SecondMenu(Long id, String name, Set<ThirdMenu> menus, String url) {
		super();
		this.id = id;
		this.name = name;
		this.menus = menus;
		this.url = url;
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

	public Set<ThirdMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<ThirdMenu> menus) {
		this.menus = menus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
	
	
	
}
