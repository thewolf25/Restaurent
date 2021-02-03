package org.restaurant.security;


import javax.servlet.Filter;

import org.restaurant.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
@EnableWebSecurity
@AllArgsConstructor
public class MySeccurityConfig extends WebSecurityConfigurerAdapter{

	private UserDetailsService userDetailsService;
	private JwtRequestFilter jwtRequestFilter; 
	//PasswordEncoder passwordEncoder;
//	//configuration JPA
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);
	}
//	
//	
//	//configuration JPA
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http.cors().and().csrf()
//		.disable()
//		.authorizeRequests()
//		.antMatchers(HttpMethod.POST, "http://127.0.0.1:9898/login").permitAll().anyRequest().authenticated().and().formLogin();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter,  UsernamePasswordAuthenticationFilter.class);
	
	}
//	//ldap Security
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.ldapAuthentication().userDnPatterns("uid={0},ou=people").groupSearchBase("ou=groups").contextSource()
//          .url("ldap://localhost:8389/dc=springframework,dc=org")
//          .and()
//        .passwordCompare()
//          .passwordEncoder(new BCryptPasswordEncoder())
//          .passwordAttribute("oussPassword");
//	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManagerBeanm() throws Exception {
				
					return super.authenticationManagerBean();
				}
	}

