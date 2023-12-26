package com.culture.demo.service;


import java.sql.SQLException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	// 1. 마이페이지 정보 조회
	@Override
	public MemberDTO getMypageInfo(int member_sq) {
		log.info("> MemberServiceImpl.getMypageInfo...");

		return this.memberMapper.selectMypageInfo(member_sq);
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

	// 아이디 중복확인
	@Override
	public int idCheck(String id) {
		memberMapper.idCheck(id);
		log.info(">> MemberServiceImpl.idCheck ...");
		return memberMapper.idCheck(id);
	}
	
	// 회원가입 휴대폰 인증번호 문자 발송
	@Override                                                          
	public JSONObject sendSMS(String verifCode, String to) {                  
		String api_key = "NCSIN5WS4RQD2HVQ";                           
	    String api_secret = "I9DTIJUEJQSJOPJVCBZTQ6EZ6W4BWP3Z";
	    Message coolsms = new Message(api_key, api_secret);            
	                                                                   
	    String from = "01049117043";                                   
	                                                                   
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);                                          
	    params.put("from", from);                                      
	    params.put("type", "SMS");                                     
	    params.put("text", "[문화센터] 인증번호는 " + verifCode + " 입니다.");        
	    params.put("app_version", "test app 1.2");                     
	             
	    JSONObject result = null;
	    try {                                                          
	    	result = coolsms.send(params);
	    	log.info(">> MemberServiceImpl.sendSMS()  result: " + result.toString());
	    } catch (CoolsmsException e) {                                 
	        System.out.println(e.getMessage());                        
	        System.out.println(e.getCode());	                  
	    }
    	return result;                                                 
	}

	// 아이디 찾기
	@Override
	public String findId(String name, String phone) {
		log.info(">> MemberServiceImpl.findId ...");
		String result = memberMapper.findId(name, phone);
		return result;
	}
	
	// 비밀번호 찾기
	@Override
	public String findPW(String id, String phone) {
		log.info(">> MemberServiceImpl.findPW ...");
		String result = memberMapper.findPw(id, phone);
		return result;
	}

	@Override
	public void updateCar(MemberDTO dto) throws Exception {
	    log.info(">> MemberServiceImpl.updateCar ...");
	    this.memberMapper.updateCar(dto);
	}

	// 수강내역 증가
	@Override
	public int updateOrderClass(int member_sq, int addCnt) throws Exception {
		log.info(">> MemberServiceImpl.updateOrderClass() ...");
		return this.memberMapper.updateOrderClass(member_sq,addCnt);
	}
	
	
}