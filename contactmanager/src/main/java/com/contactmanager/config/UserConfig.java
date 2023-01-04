package com.contactmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserConfig {

	@Bean
	public UserDetailsServiceImpl getDetailsService() {

		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(this.getDetailsService());
		provider.setPasswordEncoder(this.getEncoder());

		return provider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/**").permitAll()
		.and().formLogin().loginPage("/login")
		.defaultSuccessUrl("/user/dashboard")
		.and().csrf().disable();
		http.authenticationProvider(this.authenticationProvider());

		DefaultSecurityFilterChain build = http.build();

		return build;
	}

	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}
	
	

	// Configure method using old apis
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.authenticationProvider(authenticationProvider()); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests() .antMatchers("/admin/**").hasRole("ADMIN")
	 * .antMatchers("/user/**").hasRole("USER")
	 * .antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
	 * 
	 */

}
