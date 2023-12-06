package com.culture.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MyPageCartController {
	
	@GetMapping("/mypage/cart/list.do")
	public String cartPage(Model model) {
		log.info("장바구니페이지 이동");
		return "mypage.cart.list";
	}
}
