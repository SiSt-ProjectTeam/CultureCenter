package com.culture.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
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

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.service.CartService;
import com.culture.demo.service.LecSearchService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/mypage/cart/*")
public class MyPageCartController {
	
	private CartService cartService;
	private LecSearchService lecSearchService;
	
	// 장바구니 페이지 이동
	@GetMapping("list.do")
	public String goCart(Model model,FrmSearchDTO params) throws Exception{
		log.info("/mypage/cart/list.do + GET : MyPageCartController.goCart() 장바구니페이지 이동");
		
		System.out.println(params.getPageIndex()+"/"+params.getBrchCd()+"/"+params.getBrchNm());
		List<ClassDTO> brchList = lecSearchService.getBranch();
		
		model.addAttribute("params", params);
		model.addAttribute("brchList", brchList);
		return "mypage.cart.list";
	}
	
	// 장바구니 목록 ajax           //인코딩깨짐! 직접 charset=UTF-8으로 맞춰줘야함!
	@PostMapping(value="list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getList(@RequestBody FrmSearchDTO params
														//, Principal principal
														) throws Exception{
		log.info("/mypage/cart/list.ajax + POST : MyPageCartController.getList() 장바구니목록");
		//System.out.println(principal.getName());
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 12;
		String html = cartService.createCartHtml(member_sq,params);
		
		return !html.isEmpty()? new ResponseEntity<>(html,HttpStatus.OK)
				              : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="insert.ajax")
	public @ResponseBody Map<String, String> insertCart() throws Exception{
		log.info("/mypage/cart/insert.ajax + GET : MyPageCartController.insertCart() 장바구니추가");
		Map<String, String> rtnMap = new HashedMap();
		rtnMap.put("result", "S");
		rtnMap.put("msg", "장바구니에 담겼습니다. 확인하시겠습니까?");
		//rtnMap.put("msg", "이미 장바구니에 있는 강좌입니다.");
		
		return rtnMap;
	}
}
