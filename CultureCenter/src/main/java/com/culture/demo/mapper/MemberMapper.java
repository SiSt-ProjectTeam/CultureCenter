package com.culture.demo.mapper;

import com.culture.demo.domain.MemberDTO;

public interface MemberMapper {
	
	
	
	// 로그인 처리
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
	
	// 회원가입
	public int insert(MemberDTO memberDTO) throws Exception;
	
	

}
