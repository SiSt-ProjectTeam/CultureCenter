package com.culture.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.culture.demo.service.TeacherService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class TeacherApplicationAjaxController {

	@Setter(onMethod=@__({@Autowired}))
	TeacherService teacherService;

	//개인정보 수집 동의창 띄우기
	@PostMapping(value = "/information/application/teacher/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goPersonalAgreementCollection() {
		log.info("> /information/application/teacher/request.do... POST");				

		String html = this.teacherService.createTeacherHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/information/application/teacher/insert.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, String>> goOneStep(@RequestBody Map<String, String> step, Model model) {
		log.info("> /information/application/teacher/insert.ajax... POST");	
		
		Map<String, String> okMap = new HashMap<String, String>();		
		okMap = this.teacherService.teacherInfoOk(step);
		
		return new ResponseEntity<>(okMap, HttpStatus.OK);
	}

	@GetMapping(value = "/information/application/teacher/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goTwoStep() {
		log.info("> /information/application/teacher/request.do_={no}... GET");				

		String html = this.teacherService.createInsertHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
