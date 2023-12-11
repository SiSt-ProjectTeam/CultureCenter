package com.culture.demo.controller;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.FaqDTO;
import com.culture.demo.service.FaqService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
//@RequestMapping("/information/faq/*")
public class FaqInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(FaqInfoController.class);
	
	@Autowired
	private FaqService faqService;
	
	@GetMapping(value = "/information/faq/list.do")
	public String goFaq(Locale locale, Model model) {
		log.info("> /information/faq/list.do... GET");
				
		return "information.faq.list";
	}
	
	@PostMapping(value = "/information/faq/list.ajax" , produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> faqList(@PathVariable FaqDTO dto, Model model){
		log.info("> /faqList...POST - Ajax");
		model.addAttribute("clCd", this.faqService.getFaqList(dto.getClCd()));
		System.out.println(dto);
		
		String html = "";
		//System.out.println(clCd + "/" + faqSeqno + "/" + pageIndex + "/" + initIndex + "/" + listCnt + "/" + q);	
		
		//if (clCd == null) {
		//	clCd = 0;
		//	html = this.faqService.createFaqHtml(clCd, q);
		//}else {
		//	html = this.faqService.createFaqHtml(clCd, q);
		//}
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
