package com.culture.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.MemberDTO;

public interface MemberMapper {
	
	// 1. 마이페이지 정보 조회
	public MemberDTO selectMypageInfo(int member_sq);
	
	// 2. 회원 관심 지점 수정
	public int updateInterestBranch(@Param("member_sq") int member_sq, @Param("itrstBrchCd") int itrstBrchCd);
	
	// 회원 정보 조회(동반수강자 포함)
	public MemberDTO selectMemberWithChild(int member_sq);

	// 동반수강자(자녀) 추가
	public int insertChildren(ChildrenDTO dto) throws Exception;
	// 동반수강자(자녀) 삭제
	public int deleteChildren(int member_sq) throws Exception;
	
	// 4. 회원 정보 조회(시큐리티 정보 포함)
	public MemberDTO selectMemberWithAuthority(String member_sq);
	
	// 5. 회원 가입
	public int insertMember(@Param("memberDTO") MemberDTO memberDTO);
	// 일반회원 권한 설정
	public int insertAuth();
	
	// 아이디 중복 확인
	public int idCheck(String id) ;
	// 아이디 찾기
	String findId(@Param("name") String name, @Param("phone") String phone);
	// 비밀번호 찾기
	String findPw(@Param("id") String id, @Param("phone") String phone);
	// 차량번호 수정
	void updateCar(MemberDTO dto) throws Exception;
	// 회원정보 수정
	public int updateMember(MemberDTO memberDTO) throws Exception;	

	// 비밀번호 확인
	public String getPasswordByMemberSq(int member_sq);
	// 2. 비밀번호 업데이트
	public int updatePassword(@Param("member_sq") int member_sq, @Param("encryptedPassword") String encryptedPassword);

	// 동반 수강자 정보 삭제
	public int deleteChildrenMember(int member_sq);
	// 회원 권한 정보 삭제
	int deleteMemberAuthorities(int member_sq);
	// 회원 정보 삭제
	public int deleteMember(int member_sq);
}