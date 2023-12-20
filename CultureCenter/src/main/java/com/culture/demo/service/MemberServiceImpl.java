package com.culture.demo.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;

	// 1. 마이페이지 정보 조회
	@Override
	public MemberDTO getMypageInfo(int memberSq) {
		log.info("> MemberServiceImpl.getMypageInfo...");

		return this.memberMapper.selectMypageInfo(memberSq);
	}

	// 2. 회원 관심 지점 수정
	@Override
	public int correctionInterestBranch(int member_sq, int itrstBrchCd) {
		log.info("> MemberServiceImpl.correctionInterestBranch...");
		return this.memberMapper.updateInterestBranch(member_sq, itrstBrchCd);
	}
	// 회원 정보 조회(동반수강자 포함)
	@Override
	public MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException {
		log.info(">>PaymentServiceImpl.getMemberWithChild() ...");
		return memberMapper.selectMemberWithChild(member_sq);
	}
	// 동반수강자(자녀) 추가
	@Override
	public int insertChildren(ChildrenDTO dto) throws Exception {
		log.info(">> MemberServiceImpl.insertChildren ...");
		return this.memberMapper.insertChildren(dto);
	}
	// 동반수강자(자녀) 삭제
	@Override
	public int deleteChildren(int children_sq) throws Exception {
		log.info(">> MemberServiceImpl.deleteChildren ...");
		return this.memberMapper.deleteChildren(children_sq);
	}

}
