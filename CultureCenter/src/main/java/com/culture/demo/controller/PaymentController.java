package com.culture.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.FrmSubmitDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.service.CartService;
import com.culture.demo.service.PaymentService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/payment/*")
public class PaymentController {

	private CartService cartService;
	private PaymentService paymentService;
	
	// 수강결제1
	@PostMapping("step1.do")
	public String goPaymentStep1(FrmSubmitDTO dto, Model model) throws Exception{ //Principal principal
		log.info("/payment/step1.do + POST :PaymentController.goPaymentStep1()...");
		System.out.println(dto);
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 79;
		// 수강결제할 정보 가져오기
		String lectDetailSq = dto.getLectDetailSq();
		model.addAttribute("list", cartService.getList(member_sq, lectDetailSq) );
		
		// 회원정보,동반수강자 불러오기
		MemberDTO mDto = paymentService.getMemberWithChild(member_sq);
		model.addAttribute("mDto", mDto);
		
		return "payment.step1";
	}
}