package com.culture.demo.service;

import java.util.HashMap;
import java.util.Map;

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

	//  회원가입
	@Override
	public int memberJoin(MemberDTO memberDTO) throws Exception {
		log.info("> MemberServiceImpl.memberJoin...");
		return memberMapper.insert(memberDTO);
	}

	// 아이디 중복확인
	@Override
	public int idCheck(String id) {
		int result = memberMapper.idCheck(id);
		return result;
	}

	// 아이디 찾기
	@Override
	public String findId(String name, String phone) {
		log.info("> MemberServiceImpl.findId - name: {}, phone: {}" + name+ " / " + phone);
	    String result = memberMapper.findId(name, phone);
	    log.info("> MemberServiceImpl.findId - Result: {}" + result);
	    return result;
	}

	@Override
	public String findPW(String id, String phone) {
		log.info("> MemberServiceImpl.findPW - id: {}, phone: {}" + id+ " / " + phone);
	    String result = memberMapper.findPw(id, phone);
	    log.info("> MemberServiceImpl.findPW - Result: {}" + result);
	    return result;
	}


}
