package com.application.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.application.Entity.FirstMenu;
import com.application.Entity.SecondMenu;
import com.application.Entity.ThirdMenu;

public interface FirstMenuRepository extends JpaRepository<FirstMenu, Integer>{
	
	

}




interface ThirdRepository extends JpaRepository<ThirdMenu, Integer>
{
	
}
