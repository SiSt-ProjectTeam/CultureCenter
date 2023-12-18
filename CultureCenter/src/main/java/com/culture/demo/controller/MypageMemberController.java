package com.culture.demo.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.culture.demo.service.MemberServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MypageMemberController {

	@Autowired
	private MemberServiceImpl memberServiceImpl;
		
	@GetMapping(value = "/mypage/member/count.ajax", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getMypageInfo(Principal principal) throws SQLException, JsonProcessingException {
		log.info("> /mypage/member/count.ajax... GET : MypageMemberController.getMypageInfo()");
		String mypageInfo = "";
		ObjectMapper objectMapper = new ObjectMapper();
		mypageInfo = objectMapper.writeValueAsString( this.memberServiceImpl.getMypageInfo(principal.getName()) );
		
		return !mypageInfo.equals("")
				? new ResponseEntity<>(mypageInfo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/setItrst.ajax")
	public ResponseEntity<Integer> setInterestBranch(@RequestBody Map<String, Integer> requestBody, Principal principal) {
		log.info("> /setItrst.ajax... POST : MypageMemberController.setInterestBranch()");		
		int rtnCnt = this.memberServiceImpl.correctionInterestBranch(principal.getName(), requestBody.get("itrstBrchCd"));
		
		return rtnCnt > 0
				? new ResponseEntity<>(rtnCnt, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
}
