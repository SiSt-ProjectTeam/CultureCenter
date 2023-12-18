package com.culture.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/login/*")
public class LoginController {
	
	@Autowired
	private MemberService service;

	
	@GetMapping("/login.do")
	public String loginForm(@RequestParam(name = "error", required = false) boolean error) throws Exception {
		log.info("> LoginController loginForm....");

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
		log.info("> LoginController mbrJoin......");
		return "login.mbrJoin";
	}

	// 회원가입 처리
	@PostMapping("/mbrJoin.do")
	public String write(MemberDTO dto) throws Exception {
		// 회원 정보 로그에 출력
		log.info("> MemberController write 회원가입 정보: " + dto);
		// 서비스를 통해 회원가입 처리
		service.memberJoin(dto);

		return "redirect:/index.do";
	}
	
	@RequestMapping("/logout.do")
	public String logoutSuccess(HttpServletRequest request, HttpSession session, Model model) {
		// 세션 무효화
		// session.invalidate();
		return "login.logout"; 
	}
	
	@GetMapping("/idCheck")
	@ResponseBody
	public String idcheck(String memberId) {
		String chk = " ";
		int result = 0;
		log.info(">memberID = " + memberId);
	    result = service.idCheck(memberId);
	    log.info(">memberID 중복여부 = " + (result >= 1 ? "중복" : "중복아님"));
	    if (result >= 1) {
	        chk = "redundancy"; // 중복
	    } else if (result == 0) {
	        chk = "noredundancy"; // 중복아님
	    }
	    return chk;
	}
	
	// 아이디 찾기
	@PostMapping("/findId.do")
	@ResponseBody
	public String findid(String name, String phone) {
	    log.info(">findId =" + name +" / "+ phone);
 
	    String foundId = service.findId(name, phone);
	    log.info(">findIdfindId = " + foundId);

	    return foundId;
	}
	
	// 비밀번호 찾기
	@PostMapping("/findPw.do")
	@ResponseBody
	public String findPw(String id, String phone) {
	    log.info(">findId =" + id + " / " + phone);

	    String foundPw = service.findPW(id, phone);
	    log.info(">findIdfindPw = " + foundPw);

	    return foundPw;
	}




	
	
	// 아이디 찾기 이동
	@GetMapping("/findId.do")
	public String findId() throws Exception {
		log.info("> LoginController findId.do......");
		return "login/find_id";
	}
	// 비밀번호 찾기 이동
	@GetMapping("/findPw.do")
	public String findPw() throws Exception {
		log.info("> LoginController findPw.do......");
		return "login/find_pw";
	}

}
