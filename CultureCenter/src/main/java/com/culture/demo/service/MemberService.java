package com.culture.demo.service;

import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.MemberDTO;

public interface MemberService {
	// 1. 회원 마이페이지 정보 가져오기
	public MemberDTO getMypageInfo( int member_sq );
	
	// 2. 회원 관심 지점 수정
	public int correctionInterestBranch( int member_sq, int branchId );

	// 회원 정보 조회(동반수강자 포함)
	public MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException;
	// 동반수강자(자녀) 추가
	public int insertChildren(ChildrenDTO dto) throws Exception;
	// 동반수강자(자녀) 삭제
	public int deleteChildren(int member_sq) throws Exception;
	
	// 3. 회원 등록
	int registMember(MemberDTO memberDTO);
	
	// 회원가입 - 아이디 체크
	public int idCheck(String id) ;
	
	// 회원가입 휴대폰 인증번호 문자 발송  
	public JSONObject sendSMS(String verifCode, String to);               
	
	// 아이디 찾기 
	public String findId(String name, String phone);
	// 비밀번호 찾기 
	public String findPW(String id, String phone);
	
	// 차량번호 수정
	public void updateCar(MemberDTO dto) throws Exception ;

	// 회원정보 수정
	public boolean updateMember(MemberDTO memberDTO) throws Exception;	
	// 비밀번호 확인
	public boolean checkPassword(int member_sq, String enteredPassword);
	// 비밀번호 수정
	public boolean updatePassword(int member_sq, String newPassword);
	// 회원 탈퇴
	public boolean checkMemberDelete(int memberSq);
}
