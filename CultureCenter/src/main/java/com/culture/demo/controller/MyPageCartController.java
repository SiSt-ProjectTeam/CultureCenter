package com.culture.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.service.CartService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/mypage/cart/*")
public class MyPageCartController {
	
	@Autowired
	private CartService cartService;
	
	// 장바구니 페이지 이동
	@GetMapping("list.do")
	public String goCart(Model model,FrmSearchDTO params) throws Exception{
		log.info("/mypage/cart/list.do + GET : MyPageCartController.goCart() 장바구니페이지 이동");
		model.addAttribute("params", params);
		return "mypage.cart.list";
	}
	
	// 장바구니 목록 ajax           //인코딩깨짐! 직접 charset=UTF-8으로 맞춰줘야함!
	@PostMapping(value="list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getList(@RequestBody FrmSearchDTO params
														//, Principal principal
														) throws Exception{
		log.info("/mypage/cart/list.ajax + POST : MyPageCartController.getList() 장바구니목록");
		System.out.println(params.getBrchCd()+"/"+params.getBrchNm()+"/"+params.getPageIndex());
		//System.out.println(principal.getName());
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 12;
		String html = cartService.createCartHtml(member_sq,params);
		
		return !html.isEmpty()? new ResponseEntity<>(html,HttpStatus.OK)
				              : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
