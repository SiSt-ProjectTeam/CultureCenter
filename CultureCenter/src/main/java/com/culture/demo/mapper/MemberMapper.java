package com.culture.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.MemberDTO;

public interface MemberMapper {
	
	// 1. 마이페이지 정보 조회
	public MemberDTO selectMypageInfo(int memberSq);
	
	// 2. 회원 관심 지점 수정
	public int updateInterestBranch(@Param("member_sq") int member_sq, @Param("itrstBrchCd") int itrstBrchCd);
	
	// 3. 회원 정보 조회(동반수강자 포함)
	public MemberDTO selectMemberWithChild(int member_sq);
}