package com.culture.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.MemberDTO;

public interface MemberMapper {
	
	// 1. 마이페이지 정보 조회
	public MemberDTO selectMypageInfo(int memberSq);
	
	// 2. 회원 관심 지점 수정
	public int updateInterestBranch(@Param("member_sq") int member_sq, @Param("itrstBrchCd") int itrstBrchCd);	

	// 로그인 처리
	public MemberDTO login(MemberDTO memberDTO) throws Exception;
			
	// 회원가입
	public int insert(MemberDTO memberDTO) throws Exception;
}