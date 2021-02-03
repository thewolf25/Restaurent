package org.restaurant.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.restaurant.entity.Role;
import org.restaurant.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
public class MyUserDetails implements UserDetails{
	
	private String userName;
	private String password;
	private Boolean active;
	private List<GrantedAuthority> authorites;
	
	public MyUserDetails(User user) {
		// TODO Auto-generated constructor stub
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.getActive();
		this.authorites = user.getRoles().stream().map(s-> new SimpleGrantedAuthority(s.getRole())).collect(Collectors.toList());
 		//user.getRoles().stream().forEach(s -> this.authorites.add(new SimpleGrantedAuthority(s.getRole())));
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorites;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		System.out.println("++++++++++++++"+this.userName);
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.active;
	}

}
