package com.culture.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.culture.demo.domain.TeacherDTO;
import com.culture.demo.service.TeacherService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class TeacherApplicationController {

	
	@Setter(onMethod=@__({@Autowired}))
	TeacherService teacherService;
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherApplicationController.class);

	@GetMapping(value = "/information/application/index.do")
	public String goTeacherApplication() {
		log.info("> /information/application/index... GET");

		return "information.application.index";
	}

	//강사 신청서 사진 업로드
	@PostMapping(value = "/information/application/index.do")
	public String applicationReg(TeacherDTO teacherDTO
								,HttpServletRequest request
								) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		
		CommonsMultipartFile multipartFile = teacherDTO.getFile();
		String uploadRealPath = null;
		
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/application/upload");
			
			File saveDir = new File(uploadRealPath);
			if (!saveDir.exists()) saveDir.mkdirs();
			
			System.out.println("> uploadRealPath : " + uploadRealPath);

			String originalFilename = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(uploadRealPath, originalFilename);

			File dest = new File(uploadRealPath, filesystemName );
			multipartFile.transferTo(dest);

			teacherDTO.setFilesrc(filesystemName);
			
		}//if
		
		//로그인 기능 미구현으로 세션처리 X 
		teacherDTO.setMember_sq(10);
		int insertCount = 1;
		if (insertCount == 1) {
			return "redirect:/information/application/index.do";
		}else {
			return "/information/application/index.jsp?error";
		}//if
		
	}
	
	
	//강사 신청서 upload 폴더 안에 이름이 같은 파일이 있으면 (1)을 붙여서 업로드
	//@RequestMapping("information/application/")
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
	
}