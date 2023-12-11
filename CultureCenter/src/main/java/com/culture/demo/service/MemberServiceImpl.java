package com.culture.demo.service;

import org.springframework.stereotype.Service;

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

}
