package com.culture.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.culture.demo.domain.BranchDTO;
import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.mapper.TeacherMapper;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.TeacherService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/information/application/teacher/*")
public class TeacherApplicationAjaxController {

	@Setter(onMethod=@__({@Autowired}))
	TeacherService teacherService;

	@Autowired
	TeacherMapper teacherMapper;

	//지원하기 클릭 시 개인정보 수집 동의 페이지
	@PostMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goTeacherApplication(@RequestBody TeacherDTO teacherDTO, Authentication authentication) {
		log.info("> /information/application/teacher/request.do... POST");				

		int memberSq = 0;
		if (authentication != null) {
			CustomerUser principal = (CustomerUser) authentication.getPrincipal();
			memberSq =  principal.getMember_sq();
		}

		String html = this.teacherService.applyTeacherCheck(teacherDTO, memberSq);

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//개인정보 수집 동의 시 step2 페이지 이동
	@GetMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goInputTeacherInfo() {
		log.info("> /information/application/teacher/request.do...step2 GET");				

		String html = this.teacherService.createTeacherInfoHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/insert.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> goClausePrivacy(@RequestBody Map<String, Object> clauseData, Authentication authentication) {
		log.info("> /information/application/teacher/insert.ajax... POST");	

		int memberSq = 0;
		if (authentication != null) {
			CustomerUser principal = (CustomerUser) authentication.getPrincipal();
			memberSq =  principal.getMember_sq();
		}

		Map<String, Object> okMap = new HashMap<String, Object>();		
		okMap = this.teacherService.clausePrivacyOk(clauseData, memberSq);
		
		return new ResponseEntity<>(okMap, HttpStatus.OK);
	}

	//강사신청서 제출
	@PostMapping(value = "/submit.ajax", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity <String> submitTeacherInfo(@RequestParam("phtFileId") MultipartFile file
												   , @RequestParam("form") String submitData
												   , HttpServletRequest request
												   , Authentication authentication) throws Exception {
		log.info("> /information/application/teacher/submit.ajax... POST");

		int memberSq = 0;
		if (authentication != null) {
			CustomerUser principal = (CustomerUser) authentication.getPrincipal();
			memberSq =  principal.getMember_sq();
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> submitList = objectMapper.readValue(submitData, new TypeReference <Map<String, Object>>(){});

		String teacherImg = file.getOriginalFilename();
		this.teacherService.submitTeacherInfo(submitList, teacherImg, memberSq);

		String uploadRealPath = null;

		if (!file.isEmpty()) {
			uploadRealPath = "C:\\NewTeacherImg";

			File saveDir = new File(uploadRealPath);
			if (!saveDir.exists()) saveDir.mkdirs();

			String originalFilename = file.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);

			File dest = new File(uploadRealPath, filesystemName );
			file.transferTo(dest);

		}

		String html = this.teacherService.createSubmitHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//업로드 파일 이름 체크
	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;	
		
		while (true) {
			File f = new File(uploadRealPath, originalFilename);
			
			if (!f.exists()) {return originalFilename;}			
			
			String fileName = originalFilename.substring(0, originalFilename.length() - 4);
			String ext = originalFilename.substring(originalFilename.length() - 4);			
			originalFilename = fileName + "-" + (index) + ext;			
			index ++;			
		}
	}

	//강사신청서 작성 중 임시 저장
	@PostMapping(value = "/save.ajax", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity <Map<String, Object>> save(@RequestParam(value = "phtFileId", required = false) MultipartFile file,
													 @RequestParam("form") String saveData
													 , HttpServletRequest request
													 , Authentication authentication)throws Exception{

		log.info("> /information/application/teacher/save.ajax... POST");	

		int memberSq = 0;
		if (authentication != null) {
			CustomerUser principal = (CustomerUser) authentication.getPrincipal();
			memberSq =  principal.getMember_sq();
		}

		Map<String, Object> okMap = new HashMap<String, Object>();

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> saveDataList = objectMapper.readValue(saveData, new TypeReference <Map<String, Object>>(){});

			if (file != null && !file.isEmpty()) {
				
				String teacherImg = file.getOriginalFilename();			
				okMap= this.teacherService.saveTeacherInfo(saveDataList, teacherImg, memberSq);	
				
				String uploadRealPath = null;			
				uploadRealPath = "C:\\NewTeacherImg";
				
				File saveDir = new File(uploadRealPath);
				
				if (!saveDir.exists()) saveDir.mkdirs();
				
				String originalFilename = file.getOriginalFilename();
				String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);
				
				File dest = new File(uploadRealPath, filesystemName );
				file.transferTo(dest);
				
			}else {			
				okMap= this.teacherService.saveTeacherInfo(saveDataList, "", memberSq);				
			}			
		return new ResponseEntity<>(okMap, HttpStatus.OK);
	}

	//신청서 작성 중 삭제
	@PostMapping(value = "/delete.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> delete(Authentication authentication){
		log.info(">/information/application/teacher/delete.ajax......POST");

		int memberSq = 0;
		if (authentication != null) {
			CustomerUser principal = (CustomerUser) authentication.getPrincipal();
			memberSq =  principal.getMember_sq();
		}

		Map<String, Object> deleteMap = new HashMap<String, Object>();
		deleteMap = this.teacherService.deleteOk(memberSq);

		return new ResponseEntity<>(deleteMap, HttpStatus.OK); 
	}
}