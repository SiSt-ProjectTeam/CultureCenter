package com.culture.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.culture.demo.service.TeacherService;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class CooperationController {

	@Autowired
	TeacherService teacherService;
	
	//제휴사 신청 페이지
	@PostMapping(value = "/information/application/cooperation/request1.ajax", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> gocooperation() {
		log.info("> /information/application/teacher/request.do... POST");				
		
		String html = this.teacherService.createCooperationHtml();
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
						: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
