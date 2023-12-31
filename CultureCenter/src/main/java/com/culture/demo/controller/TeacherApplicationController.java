package com.culture.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}