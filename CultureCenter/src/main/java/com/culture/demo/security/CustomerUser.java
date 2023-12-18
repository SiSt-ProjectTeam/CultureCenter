package com.culture.demo.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.culture.demo.domain.MemberDTO;

import lombok.Getter;

@Getter
public class CustomerUser extends User {
		
	private static final long serialVersionUID = 1L;
	
	private MemberDTO memberDTO ;

	public CustomerUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}

	public CustomerUser(MemberDTO dto) {
		super(dto.getId(), dto.getPw()
				// List<AuthVO>  -> 
	            //                    Collection<? extends GrantedAuthority> authorities
	            , dto.getAuthList()
	            	.stream()
	            	.map( auth->new SimpleGrantedAuthority( auth.getAuthority() ) )
	            	.collect( Collectors.toList() ));
		
		this.memberDTO = dto;
	}
}
