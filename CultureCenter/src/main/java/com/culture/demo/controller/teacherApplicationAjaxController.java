package com.culture.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.culture.demo.mapper.TeacherMapper;
import com.culture.demo.service.TeacherService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/information/application/teacher/*")
public class teacherApplicationAjaxController {
	
	@Setter(onMethod=@__({@Autowired}))
	TeacherService teacherService;
	
	@Autowired
	TeacherMapper teacherMapper;

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
	public ResponseEntity<Map<String, String>> goClausePrivacy(@RequestBody Map<String, String> clausePrivacyData, BindingResult bindingResult ,Model model) {
		log.info("> /information/application/teacher/insert.ajax... POST");	
		System.out.println("clausePrivacyData : " + clausePrivacyData);
		
		System.out.println("bindingResult : " + bindingResult);
		System.out.println("에러코드카운트 : "+bindingResult.getErrorCount());
				
		Map<String, String> okMap = new HashMap<String, String>();		
		okMap = this.teacherService.clausePrivacyOk(clausePrivacyData);
		
		return new ResponseEntity<>(okMap, HttpStatus.OK);
	}

	//개인정보 수집 동의 시 step2 페이지 이동
	@GetMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goInputTeacherInfo() {
		log.info("> /information/application/teacher/request.do...2 GET");				

		String html = this.teacherService.createTeacherInfoHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//신청서 작성 중 삭제
	@PostMapping(value = "/delete.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, String>> delete(@RequestBody Map<String, String> deleteData, BindingResult bindingResult , Model model){
		log.info(">/information/application/teacher/delete.ajax......POST");
		System.out.println(deleteData);
		System.out.println("bindingResult : " + bindingResult);
		System.out.println("에러코드카운트 : "+bindingResult.getFieldErrorCount());
		Map<String, String> deleteMap = new HashMap<String, String>();
		deleteMap = this.teacherService.deleteOk(deleteData);
		
		return new ResponseEntity<>(deleteMap, HttpStatus.OK); 
	}
	
	
	//강사신청서 제출
	@PostMapping(value = "/submit.ajax") //, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
	public ResponseEntity <Map<String, Object>> submitTeacherInfo(@RequestParam Map<String, String> submitData) throws Exception {
		log.info("> /information/application/teacher/submit.ajax... POST");
		
		String submitDataString =  submitData.get("form");

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> submitList = objectMapper.readValue(submitDataString, new TypeReference <Map<String, Object>>(){});

		System.out.println(submitData.get("imgPre"));
		
		this.teacherService.submitTeacherInfo(submitList);

		return new ResponseEntity <Map<String, Object>>(HttpStatus.OK);
	}
	
	/*
	//강사신청서 작성 중 임시 저장
	@PostMapping(value = "/save.ajax")
	public ResponseEntity<Map<String, String>> save(@RequestBody Map<String, String> step, Model model) {
		log.info("> /information/application/teacher/save.ajax... POST");	
		System.out.println(step);
        return new ResponseEntity<>(HttpStatus.OK);
	}
	*/
	
	
	
	/*
	//강사신청완료 시 step3 페이지 이동
	@GetMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> submitSuccess(){
		log.info("> /information/application/teacher/request.do...3 GET");
		
		String html = this.teacherService.createSubmitHtml();
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
	//강사신청서 입력 중 저장
	//강사신청서 입력 중 삭제
	//강사신청서 이미 제출된 회원 페이지 이동

}