package com.culture.demo.service;

import java.sql.SQLException;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.MemberDTO;

public interface MemberService {
	// 1. 회원 마이페이지 정보 가져오기
	public MemberDTO getMypageInfo( int memberSq );
	
	// 2. 회원 관심 지점 수정
	public int correctionInterestBranch( int memberSq, int branchId );

	// 회원 정보 조회(동반수강자 포함)
	public MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException;
	// 동반수강자(자녀) 추가
	public int insertChildren(ChildrenDTO dto) throws Exception;
	// 동반수강자(자녀) 삭제
	public int deleteChildren(int member_sq) throws Exception;
}
