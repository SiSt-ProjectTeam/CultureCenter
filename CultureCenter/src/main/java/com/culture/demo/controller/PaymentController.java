package com.culture.demo.controller;

import java.security.Principal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// 수강결제1 페이지 이동
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
	// 수강결제1 수강자변경/추가 저장하기
	@PostMapping(value="actlAtlctNpleList.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> updateActlAtlct(@RequestBody Map<String, String> paramMap
																//,Principal principal
																) throws Exception{
		log.info("/payment/actlAtlctNpleList.ajax + POST :PaymentController.updateActlAtlct()...");
		
		Set<Entry<String, String>> en = paramMap.entrySet();
		for (Entry<String, String> entry : en) {
			System.out.println("key : "+entry.getKey()+"/ value : "+entry.getValue());
		}
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 79;
		int detail_class_sq = Integer.parseInt(paramMap.get("lectCd")); // 세부강좌번호
		int cnt = Integer.parseInt(paramMap.get("cnt")); // 수강신청 인원
		// 학기별강좌 - 수강가능인원
		int peopleTotAvCnt = paymentService.matchPeopleTotAv(detail_class_sq,cnt);
		// 수강신청한 강좌인지
		int orderDuplCnt = paymentService.matchClassOrder(member_sq,detail_class_sq);
		System.out.println("peopleTotAvCnt: "+ peopleTotAvCnt+ " / orderDuplCnt: "+orderDuplCnt);
		
		if(peopleTotAvCnt < 0) {
			return ResponseEntity.ok("<div data-rslt-cd='-1' data-lect-nm='"+paramMap.get("lectNm")+"' data-capaCnt='"+(peopleTotAvCnt+cnt)+"'><div>");
		}else if(orderDuplCnt != 0) {
			return ResponseEntity.ok("<div data-rslt-cd='-2' data-lect-nm='"+paramMap.get("lectNm")+"></div>");
		}else {
			String html = paymentService.createCourDetailWHtml(paramMap);
			System.out.println(html);
			return ResponseEntity.ok(html);
		}
		

	}
}
