package com.culture.demo.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.demo.service.BranchInfoService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/information/branch/*")
public class BranchInfoController {

	private static final Logger logger = LoggerFactory.getLogger(BranchInfoController.class);

	@Autowired
	private BranchInfoService branchInfoService;
	
	
	@GetMapping(value = "/list.do")
	public String home(Locale locale, Model model) {
		log.info("> /information/branch/list.do... GET");
				    
	    return "information.branch.list";
	}
	

	//지점 대분류 클릭 -> 소분류 목록 조회
    @PostMapping(value = "/list.ajax", produces = "application/text; charset=UTF-8" )
    public ResponseEntity<String> selectBranchList(int brchAreaCd) {
        log.info("> /selectBranchList...POST - Ajax");
        
        String html = "";      
        html = this.branchInfoService.createBranchHtml(brchAreaCd);     
        return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
    }  
    
    //지점 소분류 클릭 -> 지점 정보 조회
    @PostMapping(value = "/view.ajax", produces = "application/text; charset=UTF-8" )
    public ResponseEntity<String> viewBranchInfo(int brchCd) {  	
        log.info("> /viewBranchInfo...POST - Ajax");
        
        String html = "";     
        html = this.branchInfoService.createBranchHtml(brchCd);       
        return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
    }  
    
    
}//class
