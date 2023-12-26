package com.culture.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;
import com.culture.demo.service.LecSearchService;
import com.culture.demo.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/community/review/*")
public class CommunityReviewController {
	
	private ReviewService reviewService;
	
	private LecSearchService lecSearchService;
	
	
	
	// 리뷰 페이지
	@GetMapping("list.do")
	public String getReviewList(@ModelAttribute FrmSearchDTO frmSearchDTO, Model model) throws ClassNotFoundException, SQLException {
		//List<ReviewDTO> bList = reviewService.getBranch(branch_nm);
		log.info("> review/list getReviewList() GET.. ");
		
		List<ClassDTO> bList = lecSearchService.getBranch();
		model.addAttribute("bList", bList);
		
		return "community.review.list";
	}
	
	// 후기목록 조회	ajax 처리
	@PostMapping(value = "list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getList(@RequestBody FrmSearchDTO frmSearchDTO)throws Exception{
		log.info("> review/list.ajax : ReviewController.getList() POST 호출 ");

		String html = reviewService.reviewHTML(frmSearchDTO);
		
		return !html.equals("")
					? new ResponseEntity<>(html, HttpStatus.OK)
					: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 수강후기 상세페이지
	@GetMapping("dtl.do")
	public String reviewDtl(@ModelAttribute ReviewDTO reviewDTO, Model model) throws ClassNotFoundException, SQLException {
		log.info("> review/dtl getReviewDtl() GET ... reviewDTO ");
		
		
		return "community.review.dtl";
	}
	
	/*
	// 수강후기 상세페이지 댓글 ajax
	@PostMapping(value = "/comment/list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getCommt(@RequestBody FrmSearchDTO frmSearchDTO)throws Exception{
		log.info("> review/comment/list.ajax : ReviewController.getCommt() POST 호출 ");
		
		
		String html = reviewService.commtHTML(2, frmSearchDTO);
		
		return !html.equals("")
					? new ResponseEntity<>(html, HttpStatus.OK)
					: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
	
	// 수강 상세보기
}
