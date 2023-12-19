package com.culture.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.NoticeDTO;
import com.culture.demo.service.LecSearchService;
import com.culture.demo.service.NoticeSearchService;
import com.culture.demo.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/community/notice/*")
public class CommunityNoticeController {
	
	private LecSearchService lecSearchService;
	
	private NoticeService noticeService;
	
	private NoticeSearchService noticeSearchService;
	
	// 공지사항 페이지
	@GetMapping("list.do")
	public String getNoticeList(FrmSearchDTO frmSearchDTO, Model model) throws ClassNotFoundException, SQLException {
		//List<ReviewDTO> bList = reviewService.getBranch(branch_nm);
		log.info("> notice/list getNoitceList() GET.. ");
		
		List<ClassDTO> bList = lecSearchService.getBranch();
		List<NoticeDTO> clCdList =  noticeSearchService.getClCd();
		model.addAttribute("frmSearchDTO", frmSearchDTO);
		model.addAttribute("bList", bList);
		model.addAttribute("clCdList", clCdList);
		log.info("> clCdList() 호출..."+ clCdList);
		return "community.notice.list";
	}
	
	// 공지사항/이벤트 ajax
	@PostMapping(value = "list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getList(@RequestBody FrmSearchDTO frmSearchDTO)throws Exception{
		log.info("> notice/list.ajax : NoticeController.getList() POST 호출 ");

		String html = "";
		html = noticeService.noticeHTML(frmSearchDTO);
		
		return !html.equals("")
					? new ResponseEntity<>(html, HttpStatus.OK)
					: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
