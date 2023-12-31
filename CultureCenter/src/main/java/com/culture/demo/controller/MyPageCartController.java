package com.culture.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.security.CustomerUser;
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
	public String goCart(Model model,FrmSearchDTO params, HttpServletRequest request) throws Exception{
		log.info("/mypage/cart/list.do + GET : MyPageCartController.goCart() 장바구니페이지 이동");
		
		System.out.println(params.getPageIndex()+"/"+params.getBrchCd()+"/"+params.getBrchNm());
		List<ClassDTO> brchList = lecSearchService.getBranch();
		
		model.addAttribute("params", params);     // frm_search
		model.addAttribute("brchList", brchList); // 지점선택
		return "mypage.cart.list";
	}
	
	// 장바구니 목록 ajax           //인코딩깨짐! 직접 charset=UTF-8으로 맞춰줘야함!
	@PostMapping(value="list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getList(@RequestBody FrmSearchDTO params
														,Authentication authentication
														) throws Exception{
		log.info("/mypage/cart/list.ajax + POST : MyPageCartController.getList() 장바구니목록");
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		String html = cartService.createCartHtml(member_sq,params);
		
		return !html.isEmpty()? new ResponseEntity<>(html,HttpStatus.OK)
				              : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 장바구니 추가
	@PostMapping(value="insert.ajax", produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseEntity<Map<String, String>>  insertCart(@RequestBody CartDTO params
																		,Authentication authentication
																		) throws Exception{
		log.info("/mypage/cart/insert.ajax + POST : MyPageCartController.insertCart() 장바구니추가");
		System.out.println(params);
		Map<String, String> rtnMap = new HashedMap();
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		
		int resultCtn = 0;
		try {
			resultCtn = cartService.insert(member_sq,params.getDetail_class_sq());
			if(resultCtn==1) {
				rtnMap.put("result", "S");
				rtnMap.put("msg", "장바구니에 담겼습니다. 확인하시겠습니까?");
				return ResponseEntity.ok(rtnMap);
			}
		} catch (Exception e) {
			rtnMap.put("result", "F");
			rtnMap.put("msg", "이미 장바구니에 있는 강좌입니다.");
			return ResponseEntity.ok(rtnMap);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 장바구니 삭제
	@PostMapping(value="delete.ajax")
	public @ResponseBody ResponseEntity<Map<String, Object>> deleteCart(@RequestBody CartDTO params
			,Authentication authentication
																		) throws Exception{
		log.info("/mypage/cart/delete.ajax + POST : MyPageCartController.deleteCart() 장바구니삭제");
		String type = params.getType();
		String cartSeqno = params.getCartSeqno();
		System.out.println("cartSeqnos : "+cartSeqno+" / "+"type : "+type);
		Map<String, Object> rtnMap = new HashedMap();
		
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		int cnt = 0;
		try {
			cnt = cartService.delete(member_sq,type,cartSeqno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		rtnMap.put("cnt", cnt);
		rtnMap.put("type", type);
		return ResponseEntity.ok(rtnMap);
	}
	
	// 장바구니 자동삭제
	// 스케쥴러로 매일 정각에 30일 지난 강좌 삭제
	// 크론 : http://www.cronmaker.com/?0
	//@Scheduled(cron ="0 0/1 * 1/1 * ? ") // 1분 마다
	@Scheduled(cron ="0 0 0 1/1 * ? ") // 자정
	public void autoDelete() throws Exception{
		log.info("MyPageCartController.autoDelete() 장바구니"
				+ "자동삭제");
		cartService.autoDelete();
	}
}
