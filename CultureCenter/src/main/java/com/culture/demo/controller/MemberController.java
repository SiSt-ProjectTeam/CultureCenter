package com.culture.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {
	
	//로그인 체크
	@GetMapping("/lgnCheck.ajax")
	public @ResponseBody ResponseEntity<Map<String, Boolean>> loginCheck() throws Exception { // Principal principal;
		log.info("> /lgnCheck.ajax : MemberController.loginCheck() ... ");
		//principal 존재유무 판단
		
		Map<String, Boolean> response;
		//if(principal.getName().isEmpty()) response = Map.of("lgnYn", true);
		//else response = Map.of("lgnYn", false);
		response = Map.of("lgnYn", true);
	    return ResponseEntity.ok(response);
	}
	
	
	// 회원가입 폼
	@GetMapping("/mbrJoin.do")
	public String writeForm() throws Exception {
		log.info("> MemberController mbrJoin......");
		return "mypage.member.mbrJoin";
		}
}
