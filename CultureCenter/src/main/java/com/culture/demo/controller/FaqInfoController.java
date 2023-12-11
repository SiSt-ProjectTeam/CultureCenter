package com.culture.demo.controller;

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

import com.culture.demo.domain.FaqDTO;
import com.culture.demo.service.FaqService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/information/faq/*")
public class FaqInfoController {

	private static final Logger logger = LoggerFactory.getLogger(FaqInfoController.class);

	@Autowired
	private FaqService faqService;

	@GetMapping(value = "/list.do")
	public String goFaq() {
		log.info("> /information/faq/list.do... GET");
		return "information.faq.list";
	}

	@PostMapping(value = "/list.ajax" , produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> faqList(@RequestBody FaqDTO dto , Model model) {
		log.info("> /faqList...POST - Ajax");	

		String html = "";
		html = this.faqService.createFaqHtml(dto);

		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
