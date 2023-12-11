package com.culture.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = {"/member", "/login"})
@Log4j
public class MemberController {
	
	// 자동 DI
	@Autowired
	private MemberMapper service;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	/*
	 * @RequestMapping(value = "/index.do", method = RequestMethod.GET) public
	 * String home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * return "index"; }
	 */
	
	// http://localhost/member/login.do
	/*
	 * @GetMapping("/login.do") public String loginForm() throws Exception {
	 * log.info("loginForm 홈으로"); return "login/index"; }
	 */
	

	
	@GetMapping("/login.do")
	public String loginForm(@RequestParam(name = "error", required = false) boolean error) throws Exception {
		log.info("login~~....");

	    if (error) {
	        // 인증 실패 시 
	    	log.info("loginResult 로그인 실패");
	    	return "login/loginResult";
	    }
	    
	    return "login.index";
	}



	
/*	
	@PostMapping("/login.do")
	// 사용자 아디와 비번을 입력해서 보낸다 = > 받는다
	public String login(MemberDTO memberDTO, HttpSession session)  throws Exception{
		log.info("로그인 처리 - memberDTO " + memberDTO);
		
		
		
	    MemberDTO loginResult = service.login(memberDTO);

	    if (loginResult != null) {
	        // 로그인 성공
	    	log.info("로그인 성공 - memberDTO " + memberDTO);
	        session.setAttribute("login", loginResult);
	        return "redirect:/index.do";
	    } else {
	        // 로그인 실패
	    	log.info("로그인 실패 로그인 실패 "); 
	    	return "/login/loginResult";
	    }
	}
	*/
	
	// 회원가입 폼
	@GetMapping("/mbrJoin.do")
	public String writeForm() throws Exception {
		log.info("mbrJoin으로........");
		 return "mypage.member.mbrJoin";
	}
	
	
	// 회원가입 처리
	@PostMapping("/mbrJoin.do")
	public String write(MemberDTO dto) throws Exception {
		
		service.insert(dto);
		
		
		return "redirect:.index.do";
	}
	
	
	 @RequestMapping("/logout.do")
	    public String logoutSuccess(HttpServletRequest request, HttpSession session, Model model) {
		 // 세션 무효화
		    session.invalidate();
	        return "login.logout"; 
	    }
	
	

	

}
