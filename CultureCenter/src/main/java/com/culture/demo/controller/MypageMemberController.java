package com.culture.demo.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MypageMemberController {

	@Autowired
	private MemberService memberService;
		
	@GetMapping(value = "/mypage/member/count.ajax", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getMypageInfo(Authentication authentication) throws SQLException, JsonProcessingException {
		log.info("> /mypage/member/count.ajax... GET : MypageMemberController.getMypageInfo()");
		CustomerUser principal =  (CustomerUser) authentication.getPrincipal();
		
		String mypageInfo = "";
		ObjectMapper objectMapper = new ObjectMapper();
		mypageInfo = objectMapper.writeValueAsString( this.memberService.getMypageInfo(principal.getMember_sq()) );
		
		return !mypageInfo.equals("")
				? new ResponseEntity<>(mypageInfo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/setItrst.ajax")
	public ResponseEntity<Integer> setInterestBranch(@RequestBody Map<String, Integer> requestBody, Authentication authentication) {  //, 
		log.info("> /setItrst.ajax... POST : MypageMemberController.setInterestBranch()");	
		CustomerUser principal =  (CustomerUser) authentication.getPrincipal();
		
		int rtnCnt = this.memberService.correctionInterestBranch(principal.getMember_sq(), requestBody.get("itrstBrchCd"));
		
		return rtnCnt > 0
				? new ResponseEntity<>(rtnCnt, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 동반수강자(자녀) 추가 Ajax
	@PostMapping("/mypage/member/family/insert.ajax")
	public @ResponseBody ResponseEntity<Map<String, String>> insertFamily(@RequestBody ChildrenDTO dto) throws Exception{
		log.info("> /mypage/member/family/insert.ajax ... POST : MypageMemberController.insertFamily()");
		
		int rtnCnt = this.memberService.insertChildren(dto);
		
		return rtnCnt==1? ResponseEntity.ok(Map.of("rtnCode","1")):ResponseEntity.ok(Map.of("rtnCode","-1"));
	}
	// 동반수강자(자녀) 삭제 Ajax
	@PostMapping("/mypage/member/family/delete.ajax")
	public @ResponseBody ResponseEntity<Map<String, String>> deleteFamily(@RequestBody Map<String, Integer> param) throws Exception{
		log.info("> /mypage/member/family/delete.ajax ... POST : MypageMemberController.deleteFamily()");
		
		int rtnCnt = this.memberService.deleteChildren(param.get("children_sq"));
		
		return rtnCnt==1? ResponseEntity.ok(Map.of("rtnCode","1")):ResponseEntity.ok(Map.of("rtnCode","-1"));
	}
}
