package com.culture.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomerUser extends User {

	private static final long serialVersionUID = 1L;
	
	private int member_sq ;

	
	public CustomerUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int member_sq) {
		super(username, password, authorities);
		this.member_sq = member_sq;
	}

}
