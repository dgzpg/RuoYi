package com.application.config;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.application.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Resource
	private SessionRegistry seRegistry;
	
	@Autowired
	private UserDetailsService userService;
	
	@Bean
	public SessionRegistry sessionRegistry()
	{
		return new SessionRegistryImpl();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		//http.authorizeRequests().and().formLogin().loginPage("main.login").permitAll();
		
		http.csrf().disable().headers().frameOptions().sameOrigin();
		System.out.println("进入安全代码");
		/*http.authorizeRequests()
				.antMatchers("/log","/css/**","/img/**","/js/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/log").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/mainPage").failureUrl("/fail").permitAll()
			.and()
			.logout()
				.permitAll();*/
	
		http.sessionManagement().invalidSessionUrl("/sessionOut");
		
		
		http.sessionManagement().maximumSessions(1).sessionRegistry(seRegistry).expiredUrl("/log");
	}
	
	/*protected void config(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userService);
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService( userService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}*/
	
}
