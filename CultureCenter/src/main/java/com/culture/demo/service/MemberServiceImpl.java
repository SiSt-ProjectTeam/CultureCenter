package com.culture.demo.service;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
    private PasswordEncoder passwordEncoder;

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

	   // 회원정보 수정
    @Override
    public boolean updateMember(MemberDTO memberDTO) throws Exception {
        log.info(">> MemberServiceImpl.updateMember ...");
        int rowsAffected = this.memberMapper.updateMember(memberDTO);
        return rowsAffected > 0; // 업데이트가 성공하면 true 반환, 실패하면 false 반환
    }
    
    // 비번 첵크
    @Override
    public boolean checkPassword(int member_sq, String enteredPassword) {
        log.info(">> MemberServiceImpl.checkPassword ...");

        // 회원의 저장된 암호화된 비밀번호 조회
        String storedPassword = memberMapper.getPasswordByMemberSq(member_sq);
        System.out.println(storedPassword);
        
        // 비밀번호 비교
        return passwordEncoder.matches(enteredPassword, storedPassword);
    }

    // 비번 변경
    @Override
    public boolean updatePassword(int member_sq, String newPassword) {
        log.info(">> MemberServiceImpl.updatePassword ...");

        // 새로운 비밀번호를 암호화
        String encryptedPassword = passwordEncoder.encode(newPassword);

        // 회원의 비밀번호 업데이트
        int rowsAffected = memberMapper.updatePassword(member_sq, encryptedPassword);

        return rowsAffected > 0; // 업데이트가 성공하면 true 반환, 실패하면 false 반환
    }

    @Override
    public boolean checkMemberDelete(int memberSq) {
        try {
            // 동반 수강자 정보 삭제
            memberMapper.deleteChildrenMember(memberSq);

            // 회원 권한 삭제
            memberMapper.deleteMemberAuthorities(memberSq);
            
            // 회원 삭제
            memberMapper.deleteMember(memberSq);

            return true;
        } catch (Exception e) {
            log.error("Error deleting member: " + e.getMessage());
            return false;
        }
    }

	@Override
	public String familyListHTML(int member_sq) throws ClassNotFoundException, SQLException {
		List<ChildrenDTO> list = getMemberWithChild(member_sq).getChildrenList();
		
		StringBuilder html = new StringBuilder();
		
		if(list.isEmpty()) {
			html.append("<div class=\"no_srch_area\">");
			html.append("	<div class=\"no_srch_div\">");
			html.append("		<p class=\"txt f_h2\">");
			html.append("			<span class=\"normal_value\">등록된 정보가 없습니다.</span>");
			html.append("		</p>");
			html.append("	</div>");
			html.append("</div>");
		} else {
			for(ChildrenDTO dto : list ) {
				html.append("<div class=\"info_list\">");
				html.append("        <div class=\"writer_info\">");
				html.append("            <p class=\"item_name f_body1\">"+dto.getChildren_nm()+"</p>");
				html.append("            <a href=\"javascript:mypageMember.deleteFamily("+dto.getChildren_sq()+")\" class=\"comment_remove f_caption1\">삭제</a>");
				html.append("        </div>");
				html.append("        <div class=\"type_div\">");
				html.append("            <p class=\"type\">자녀</p>");
				html.append("            <p class=\"type\">"+dto.getChild_birth_dt()+"</p>");
				html.append("			<p class=\"type\">"+dto.getRealGender()+"</p>");
				html.append("        </div>");
				html.append("</div>");

			}
		}
		
		return html.toString();
	}


	
	
}