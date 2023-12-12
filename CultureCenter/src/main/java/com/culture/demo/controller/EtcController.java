package com.culture.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class EtcController {
		
	// 만족도 평가
	@GetMapping("/mypage/teachereval/list.do")
	public String goTeachereval() throws Exception{
		log.info("/mypage/teachereval/list.do + GET : MyPageCartController.goTeachereval()");
		return "mypage.teachereval.list";
	}

	
	// 쿠폰
	@GetMapping("/mypage/coupon/list.do")
	public String goCoupon() throws Exception{
		log.info("/mypage/coupon/list.do + GET : MyPageCartController.goCoupon()");
		return "mypage.coupon.list";
	}

	
	// 사은품 신청
	@GetMapping("/mypage/freebie/appList.do")
	public String goFreebie() throws Exception{
		log.info("/mypage/freebie/appList.do + GET : MyPageCartController.goFreebie()");
		return "mypage.freebie.appList";
	}
	
	// 사은품 신청
	@GetMapping("/mypage/freebie/detailList.do")
	public String goFreebieDetail() throws Exception{
		log.info("/mypage/freebie/detailList.do + GET : MyPageCartController.goFreebieDetail()");
		return "mypage.freebie.detailList";
	}
	
}
