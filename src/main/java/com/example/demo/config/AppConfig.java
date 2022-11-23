package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class AppConfig extends WebSecurityConfigurerAdapter {
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication().withUser("shubh").password("shubh123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("user123").roles("dev");
		
		
	}
	//in which method you want to add security
    //here security added in all API
	//for all controller's end ponts
	/*
	 * protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable();
	 * http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
	 * }
	 */
	
	//if we want to provide security for particular controller
	/*
	 * protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable();
	 * http.authorizeRequests().antMatchers("/noAuth/**").fullyAuthenticated().and()
	 * .httpBasic(); }
	 */
	//if want to provide security based on roles
	 protected void configure(HttpSecurity http) throws Exception
	    {
	        http.csrf().disable();
	        http.authorizeRequests().antMatchers("/noAuth/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().httpBasic();
	    }
	    
    
    @Bean
    public NoOpPasswordEncoder getNoOpPasswordEncoder()
    {
        return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
    }
}
