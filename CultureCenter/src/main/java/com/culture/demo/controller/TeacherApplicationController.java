package com.culture.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class TeacherApplicationController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherApplicationController.class);
	
	
	@GetMapping(value = "/information/application/index.do")
	public String goTeacherApplication() {
		log.info("> /information/application/index... GET");				
		return "information.application.index";
	}
	
	/*@PostMapping(value = "/information/application/teacher/request.do")
	public ResponseEntity<String> goTa() {
		log.info("> /information/application/teacher/request.do");
		
		String html = "";
		html = this.
		
		return "";
	}*/
	
}
