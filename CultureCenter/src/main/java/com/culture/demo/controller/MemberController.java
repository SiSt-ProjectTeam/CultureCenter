package com.culture.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemberController {
	
	/*
	 * @RequestMapping(value = "/index.do", method = RequestMethod.GET) public
	 * String home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * return "index"; }
	 */
	
	@GetMapping("/lgnCheck.ajax")
	public @ResponseBody ResponseEntity<Map<String, Boolean>> loginCheck() throws Exception { //principal
		log.info("> /lgnCheck.ajax : MemberController.loginCheck() ... ");
		//principal 존재유무 판단
	    Map<String, Boolean> response = Map.of("lgnYn", true);
	    return ResponseEntity.ok(response);
	}
}
