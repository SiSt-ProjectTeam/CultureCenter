package com.culture.demo.controller;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.culture.demo.domain.TeacherDTO;

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
	
	/*
	//강사 신청서 사진 업로드
	@PostMapping(value = "/information/application/upload.do")
	public String applicationReg(TeacherDTO teacherDTO
								,HttpServletRequest request
								) throws ClassNotFoundException, SQLException, IllegalStateException {
		
		CommonsMultipartFile multipartFile = teacherDTO.getFile();
		String uploadRealPath = null;
		
		if (!multipartFile.isEmpty()) {
			uploadRealPath = request.getServletContext().getRealPath("/application/upload")
		}
		
		return
	}
	*/
	
	//강사 신청서 upload 폴더 안에 이름이 같은 폴더가 있으면 (1)을 붙여서 업로드
	@RequestMapping("information/application/*")
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