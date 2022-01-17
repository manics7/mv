package com.example.movie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure (WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}
	
	
	
	@Order(1)
	@Configuration
	class UserConfig extends WebSecurityConfigurerAdapter {
		protected void configure (HttpSecurity http) throws Exception {
			http
				.csrf().disable()
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.and()
				.formLogin()
					.loginPage("/")
					.permitAll()
					.and()
				.logout()
					.permitAll()
					.invalidateHttpSession(true);
			
		}
	}
	
	@Order(2)
	@Configuration
	class BusinessConfig extends WebSecurityConfigurerAdapter {
		protected void configure (HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/businessPage").hasRole("BUSINESS")
				.and()
			.formLogin()
				.loginPage("/")
				.permitAll()
				.defaultSuccessUrl("/businessPage")
				.and()
			.logout()
				.permitAll()
				.invalidateHttpSession(true);
		
		}
	}
	
	@Order(3)
	@Configuration
	class AdminConfig extends WebSecurityConfigurerAdapter {
		
		protected void configure (HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/admingPage").hasRole("admin");
		}
		
	}
}