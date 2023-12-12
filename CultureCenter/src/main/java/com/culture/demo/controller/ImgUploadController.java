package com.culture.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class ImgUploadController {

	private static final Logger logger = LoggerFactory.getLogger(ImgUploadController.class);
	
	@GetMapping(value = "/display")
	public ResponseEntity<Resource> display(@Param("filename") String filename, @Param("no") int no){
		
		String path = "C:\\upload\\culture\\";
		String folder = "";
		
		switch (no) {
		case 1:
			folder = "branch\\";
			break;
		case 2:
			folder = "detail\\";
			break;
		case 3:
			folder = "teacher\\";
			break;
		case 4:
			folder = "thumbnail\\";
			break;
		}//switch
		
		Resource resource = new FileSystemResource(path + folder + filename);
		
		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}//if
		
		HttpHeaders header = new HttpHeaders();
		Path filePath = null;
		
		try {
			filePath = Paths.get(path + folder + filename);
			header.add("Content-Type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
		
	}
	
}
