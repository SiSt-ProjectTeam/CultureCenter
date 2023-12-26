package com.culture.demo.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.service.AtlctService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/mypage/atlct/*")
public class MypageAtlctController {
	
	private static final Logger logger = LoggerFactory.getLogger(MypageAtlctController.class);

	@Autowired
	private AtlctService atlctService;
	
	@GetMapping(value = "/list.do")
	public String goAtlct(@ModelAttribute("frmSearchDTO") FrmSearchDTO frmSearchDTO) {
		log.info("> /mypage/atlct/list.do... GET : MypageAtlctController.goAtlct()");

		return "mypage.atlct.list";
	}
	
	@PostMapping(value = "/list.ajax", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> getAtlctList(@RequestBody FrmSearchDTO frmSearchDTO) throws SQLException {  /* , Principal principal */
		log.info("> /mypage/atlct/list.ajax... POST : MypageAtlctController.getAtlctList()");
		log.info(">>>> frmSearchDTO: " + frmSearchDTO);
		String html = "";
		int member_sq = 12;
		// int member_sq = Integer.parseInt( principal.getName() );
		html = this.atlctService.createAtlctHtml(frmSearchDTO, member_sq);
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/view.do")
	public String goAtlctDetail(@ModelAttribute("frmSearchDTO") FrmSearchDTO frmSearchDTO, Model model) throws SQLException {
		log.info("> /mypage/atlct/list.do... GET : MypageAtlctController.goAtlctDetail()");

		int member_sq = 12;
		// int member_sq = Integer.parseInt( principal.getName() );
		model.addAttribute("atlctList", this.atlctService.getAtlctList(frmSearchDTO, member_sq));
		model.addAttribute("allRfndCk", this.atlctService.allRefundCheck(frmSearchDTO.getAtlctRsvNo()));
		
		return "mypage.atlct.view";
	}
	
	
}
