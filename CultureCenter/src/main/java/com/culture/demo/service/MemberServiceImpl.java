package com.culture.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	// 1. 마이페이지 정보 조회
	@Override
	public MemberDTO getMypageInfo(String id) {
		log.info("> MemberServiceImpl.getMypageInfo...");
		
		return this.memberMapper.selectMypageInfo(id);
	}

	// 2. 회원 관심 지점 수정
	@Override
	public int correctionInterestBranch(String id, int itrstBrchCd) {
		log.info("> MemberServiceImpl.correctionInterestBranch...");
		return this.memberMapper.updateInterestBranch(id, itrstBrchCd);
	}

	// 3. 회원 등록
	@Override
	//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public int registMember(MemberDTO memberDTO) {
		log.info("> MemberServiceImpl.registMember...");
		int result=0;
		
		result += this.memberMapper.insertMember(memberDTO);
		result += this.memberMapper.insertAuth();
		
		return result;
	}

}
