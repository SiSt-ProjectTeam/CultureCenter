package com.culture.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.MemberDTO;

public interface MemberMapper {
	
	// 1. 마이페이지 정보 조회
	public MemberDTO selectMypageInfo(String id);
	
	// 2. 회원 관심 지점 수정
	public int updateInterestBranch(@Param("id") String id, @Param("itrstBrchCd") int itrstBrchCd);
	
	// 3. 회원 정보 조회(동반수강자 포함)
	public MemberDTO selectMemberWithChild(int member_sq);
	
	// 4. 회원 정보 조회(시큐리티 정보 포함)
	public MemberDTO selectMemberWithAuthority(String member_sq);
	
	// 5. 회원 가입
	public int insertMember(@Param("memberDTO") MemberDTO memberDTO);
	// 일반회원 권한 설정
	public int insertAuth();
	
	
}