package com.culture.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.culture.demo.mapper.MyPageCartMapper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MyPageCartController {
	
	@Autowired
	private MyPageCartMapper myPageCartMapper; 
	
	@GetMapping("/mypage/cart/list.do")
	public String list(Model model) throws Exception{
		log.info("/mypage/cart/list.do + GET : MyPageCartController.list() 장바구니페이지 이동");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
//		
//		System.out.println("userName : "+user.getUsername() // 회원ID
//						   +"\n userAuth : "+user.getAuthorities()
//						   //보안(salt ..? )로 패스워드는 암호화처리되어 가져올 수 없음
//						   //+"\n userPWD : "+user.getPassword()
//						   );
		myPageCartMapper.getCarts(user.getUsername());
		return "mypage.cart.list";
	}
}
