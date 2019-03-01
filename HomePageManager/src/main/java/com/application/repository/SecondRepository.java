package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.Entity.SecondMenu;

public interface SecondRepository extends JpaRepository<SecondMenu, Integer>
{
	
}