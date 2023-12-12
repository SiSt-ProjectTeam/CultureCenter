package com.culture.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@GetMapping("/login.do")
	public String loginForm(@RequestParam(name = "error", required = false) boolean error) throws Exception {
		log.info("> MemberController loginForm....");

	    if (error) {
	        // 인증 실패 시 
	    	log.info("loginResult 로그인 실패");
	    	return "login.loginResult";
	    }
	    
	    return "login.index";
	}
	
	// 회원가입 폼
		@GetMapping("/mbrJoin.do")
		public String writeForm() throws Exception {
			log.info("> MemberController mbrJoin으로......");
			 return "mypage.member.mbrJoin";
		}
		
		
		// 회원가입 처리
		@PostMapping("/mbrJoin.do")
		public String write(MemberDTO dto) throws Exception {
	        // 회원 정보 로그에 출력
			log.info("> MemberController write 회원가입 정보: " + dto);
	        // 서비스를 통해 회원가입 처리
	        service.insert(dto);
			
			return "redirect:/index.do";
		}
		
		
		 @RequestMapping("/logout.do")
		    public String logoutSuccess(HttpServletRequest request, HttpSession session, Model model) {
			 // 세션 무효화
			    session.invalidate();
		        return "login.logout"; 
		    }
		
		

		

	}