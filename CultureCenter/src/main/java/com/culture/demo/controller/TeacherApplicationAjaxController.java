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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.service.TeacherService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/information/application/teacher/*")
public class TeacherApplicationAjaxController {

	@Setter(onMethod=@__({@Autowired}))
	TeacherService teacherService;

	//개인정보 수집 동의 페이지
	@PostMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goTeacherApplication() {
		log.info("> /information/application/teacher/request.do... POST");				

		String html = this.teacherService.createClausePrivacyHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//개인정보 수집 동의 여부
	@PostMapping(value = "/insert.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, String>> goClausePrivacy(@RequestBody Map<String, String> step, Model model) {
		log.info("> /information/application/teacher/insert.ajax... POST");	
		
		Map<String, String> okMap = new HashMap<String, String>();		
		okMap = this.teacherService.clausePrivacyOk(step);
		
		return new ResponseEntity<>(okMap, HttpStatus.OK);
	}

	//개인정보 수집 동의 시 step2 페이지 이동
	@GetMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goInputTeacherInfo() {
		log.info("> /information/application/teacher/request.do... GET");				

		String html = this.teacherService.createTeacherInfoHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//강사신청서 제출
	@PostMapping(value = "/submit.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> submitTeacherInfo(@RequestBody TeacherDTO teacherDTO, Model model){
		log.info("> /information/application/teacher/submit.ajax... POST");
		
		//this.teacherService
		
		//return new ResponseEntity<T>(teacherDTO , HttpStatus.OK);
		
	}
	
	//강사신청서 입력 중 저장
	//강사신청서 입력 중 삭제
	//강사신청서 이미 제출된 회원 페이지 이동

}
