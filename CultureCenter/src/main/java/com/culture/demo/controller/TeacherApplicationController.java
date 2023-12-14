package com.culture.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.culture.demo.service.TeacherService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class TeacherApplicationController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherApplicationController.class);
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping(value = "/information/application/index.do")
	public String goTeacherApplication() {
		log.info("> /information/application/index... GET");				
		return "information.application.index";
	}
	
	@PostMapping(value = "/information/information/application/teacher/request.do", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> goPersonalAgreementCollection () {
		log.info("> /information/application/teacher/request.do... POST");				
		
		String html = "";
		html = this.teacherService.createTeacherHtml();
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping(value = "/information/application/teacher/insert.ajax", produces = "application/text; charset=UTF-8")
	public String goInsert(@RequestBody int cnt, String prinfClctAgrYn, String carrInfoClctAgrYn, Model model) {
		log.info("> /information/application/teacher/insert.ajax... POST");				
		
		return this.teacherService.teacherInfoOk(cnt, prinfClctAgrYn, carrInfoClctAgrYn);
	}
}
