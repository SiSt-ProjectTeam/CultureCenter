package com.culture.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.AtlctService;

//github.com/SiSt-ProjectTeam/CultureCenter.git

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/mypage/atlct/*")
public class MypageAtlctController {

	@Autowired
	private AtlctService atlctService;
	
	@GetMapping(value = "/list.do")
	public String goAtlct(@ModelAttribute("frmSearchDTO") FrmSearchDTO frmSearchDTO) {
		log.info("> /mypage/atlct/list.do... GET : MypageAtlctController.goAtlct()");

		return "mypage.atlct.list";
	}
	
	@PostMapping(value = "/list.ajax", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> getAtlctList(@RequestBody FrmSearchDTO frmSearchDTO, Authentication authentication) throws SQLException {
		log.info("> /mypage/atlct/list.ajax... POST : MypageAtlctController.getAtlctList()");
		log.info(">>>> frmSearchDTO: " + frmSearchDTO);
		String html = "";
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		html = this.atlctService.createAtlctHtml(frmSearchDTO, principal.getMember_sq());
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/view.do")
	public String goAtlctDetail(@ModelAttribute("frmSearchDTO") FrmSearchDTO frmSearchDTO, Model model, Authentication authentication) throws SQLException {
		log.info("> /mypage/atlct/list.do... GET : MypageAtlctController.goAtlctDetail()");

		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		model.addAttribute("atlctList", this.atlctService.getAtlctList(frmSearchDTO, principal.getMember_sq()));
		model.addAttribute("allRfndCk", this.atlctService.allRefundCheck(frmSearchDTO.getAtlctRsvNo()));
		
		return "mypage.atlct.view";
	}
	
	
}
