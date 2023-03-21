//package com.dataserve.archivemanagement.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	private UserDetailsService userDetailsService;
//
//	public SecurityConfig(UserDetailsService userDetailsService) {
//		super();
//		this.userDetailsService = userDetailsService;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		super.configure(auth);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//	}
//
//}
