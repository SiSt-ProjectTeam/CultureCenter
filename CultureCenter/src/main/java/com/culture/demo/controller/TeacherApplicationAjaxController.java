package com.culture.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

	//개인정보 수집 동의 페이지
	@PostMapping(value = "/request.do", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> goTeacherApplication() {
		log.info("> /information/application/teacher/request.do... POST");				

		//if (isAuthenticated()) 
		//{
			String html = this.teacherService.createClausePrivacyHtml();
			
			return !html.equals("")
					? new ResponseEntity<>(html, HttpStatus.OK)
					: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
			
		//}else {
			
		//	return "member/login";
			
		//}
	}

	//개인정보 수집 동의 여부
	@PostMapping(value = "/insert.ajax", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, String>> goClausePrivacy(@RequestBody Map<String, String> clausePrivacyData, Model model) {
		log.info("> /information/application/teacher/insert.ajax... POST");	
		
		System.out.println("clausePrivacyData : " + clausePrivacyData);
		
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
	public ResponseEntity<Map<String, String>> delete(@RequestBody Map<String, String> deleteData, Model model){
		log.info(">/information/application/teacher/delete.ajax......POST");
		System.out.println("deleteData : " + deleteData);
		Map<String, String> deleteMap = new HashMap<String, String>();
		deleteMap = this.teacherService.deleteOk(deleteData);
		
		return new ResponseEntity<>(deleteMap, HttpStatus.OK); 
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
		}//if

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> submitList = objectMapper.readValue(submitData, new TypeReference <Map<String, Object>>(){});

		String teacherImg = file.getOriginalFilename();
		this.teacherService.submitTeacherInfo(submitList, teacherImg, memberSq);
		
		TeacherDTO teacherDTO = new TeacherDTO(); 
		teacherDTO.setFile(file);
		
		MultipartFile multipartFile = teacherDTO.getFile();
		String customUploadPath = "E:\\git\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\work\\upload\\teacher";

		if (!multipartFile.isEmpty()) {
			String uploadRealPath = customUploadPath;
			File saveDir = new File(uploadRealPath);
			if (!saveDir.exists()) saveDir.mkdirs();
			
			System.out.println("> uploadRealPath : " + uploadRealPath);

			String originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);

			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest);

			teacherDTO.setFilesrc(filesystemName);
			
		}//if
		
		String html = this.teacherService.createSubmitHtml();

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;	
		while (true) {
			File f = new File(uploadRealPath, originalFilename);			
			if (!f.exists()) {return originalFilename;}			
			String fileName = originalFilename.substring(0, originalFilename.length() - 4);
			String ext = originalFilename.substring(originalFilename.length() - 4);			
			originalFilename = fileName + "-" + (index) + ext;			
			index ++;			
		}//while
	}
	
	//로그인 여부 체크
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
	        return false;
	    }
	    return authentication.isAuthenticated();
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

	//강사신청서 입력 중 저장
	//강사신청서 입력 중 삭제
	//강사신청서 이미 제출된 회원 페이지 이동

}