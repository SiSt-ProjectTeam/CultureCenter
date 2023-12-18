package com.culture.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Component("customUserDetailsService")
@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("> Load User By UserName : " + username);
		MemberDTO dto = this.memberMapper.selectMemberWithAuthority(username);
		log.warn("> Queiried by Member mapper : " + dto);
		
		// MemberDTO => UserDetails 타입 형 변환
		// CustomerUser extends    User implements UserDetails
		return dto == null ? null : new CustomerUser(dto);
	}

}
