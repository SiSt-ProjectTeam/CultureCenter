package com.culture.demo.service;

import com.culture.demo.domain.MemberDTO;

public interface MemberService {
	// 1. 회원 마이페이지 정보 가져오기
	public MemberDTO getMypageInfo( int memberSq );
	
	// 2. 회원 관심 지점 수정
	public int correctionInterestBranch( int memberSq, int branchId );
	
}
