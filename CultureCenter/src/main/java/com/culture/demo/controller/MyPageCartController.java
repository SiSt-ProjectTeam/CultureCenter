package com.culture.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.FrmSearch;
import com.culture.demo.mapper.MyPageCartMapper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MyPageCartController {
	
	@Autowired
	private MyPageCartMapper myPageCartMapper; 
	
	@GetMapping("/mypage/cart/list.do")
	public String list(Model model,FrmSearch params) throws Exception{
		log.info("/mypage/cart/list.do + GET : MyPageCartController.list() 장바구니페이지 이동");
		System.out.println(params.getBrchCd()+"/"+params.getBrchNm()+"/"+params.getPageIndex());
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		User user = (User) authentication.getPrincipal();
//		
//		System.out.println("userName : "+user.getUsername() // 회원ID
//						   +"\n userAuth : "+user.getAuthorities()
//						   //보안(salt ..? )로 패스워드는 암호화처리되어 가져올 수 없음
//						   //+"\n userPWD : "+user.getPassword()
//						   );
		List<CartDTO> list = new ArrayList<CartDTO>();
		//list = myPageCartMapper.getCarts(user.getUsername(),params.getBrchCd());
		
		list = myPageCartMapper.getCarts("12",params.getBrchCd());
		model.addAttribute("list", list); 
		return "mypage.cart.list";
	}
}
