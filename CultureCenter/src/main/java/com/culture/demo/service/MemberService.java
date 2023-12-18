package com.culture.demo.service;

import com.culture.demo.domain.MemberDTO;

public interface MemberService {
	// 1. 회원 마이페이지 정보 가져오기
	public MemberDTO getMypageInfo( String id );
	
	// 2. 회원 관심 지점 수정
	public int correctionInterestBranch( String id, int branchId );
	
	// 3. 회원 등록
	int registMember(MemberDTO memberDTO);
	
}
