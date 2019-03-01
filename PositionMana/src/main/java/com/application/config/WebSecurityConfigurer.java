package com.application.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Resource
	private SessionRegistry seRegistry;
	
	@Bean
	public SessionRegistry sessionRegistry()
	{
		return new SessionRegistryImpl();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		
	
		
		 http.headers().frameOptions().disable();  
		
	
		http.sessionManagement().invalidSessionUrl("http://localhost:8010/sessionOut");
		
		http.sessionManagement().maximumSessions(1).sessionRegistry(seRegistry).expiredUrl("/log");
	}
	
}
