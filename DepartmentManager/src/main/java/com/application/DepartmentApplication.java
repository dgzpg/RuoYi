package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DepartmentApplication {

	
	
	@Bean 
	public RestTemplate geTemplate()
	{
		return new RestTemplate();
	}
	
	public static void main(String []args)
	{
		SpringApplication.run(DepartmentApplication.class, args);
	}
	
}
