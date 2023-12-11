package com.culture.demo.controller;

import java.sql.SQLException;

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
	public ResponseEntity<String> getMypageInfo() throws SQLException, JsonProcessingException {  /* , Principal principal */
		log.info("> /mypage/member/count.ajax... GET : MypageMemberController.getMypageInfo()");
		String mypageInfo = "";
		int member_sq = 12;
		// int member_sq = Integer.parseInt( principal.getName() );
		ObjectMapper objectMapper = new ObjectMapper();
		mypageInfo = objectMapper.writeValueAsString( this.memberServiceImpl.getMypageInfo(member_sq) );
		
		return !mypageInfo.equals("")
				? new ResponseEntity<>(mypageInfo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/setItrst.ajax")
	public ResponseEntity<String> setInterestBranch(@RequestBody int itrstBrchCd) {
		log.info("> /setItrst.ajax... POST : MypageMemberController.setInterestBranch()");
		int member_sq = 12;
		// int member_sq = Integer.parseInt( principal.getName() );
		
		
		return this.memberServiceImpl.correctionInterestBranch(member_sq, itrstBrchCd) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
}
