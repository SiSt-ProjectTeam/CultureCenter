package com.culture.demo.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{

	private MemberMapper memberMapper;

	@Override
	public MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException {
		log.info(">>PaymentServiceImpl.getMemberWithChild() ...");
		return memberMapper.selectMemberWithChild(member_sq);
	}
	
}
