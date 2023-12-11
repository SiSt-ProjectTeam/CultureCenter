package com.culture.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class InformationGuideController {
	
	@GetMapping("/information/guide/settle/list.do")
	public String settle() {
		log.info("> InformationGuideController.settle() GET 호출");
		return "information.guide.settle.list";
	}
	
	@GetMapping("/information/guide/online/list.do")
	public String online() {
		log.info("> InformationGuideController.online() GET 호출");
		return "information.guide.online.list";
	}
	
}
