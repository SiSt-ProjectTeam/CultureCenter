package com.culture.demo.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
/* @AllArgsConstructor */
@RequestMapping("/community/review/*")
public class CommunityReviewController {
	
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private LecSearchService lecSearchService;
	
	
	ReviewDTO dto = null;
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

		String html = "";
		html = reviewService.reviewHTML(frmSearchDTO);
		
		return !html.equals("")
					? new ResponseEntity<>(html, HttpStatus.OK)
					: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 수강후기 상세페이지
	/* @RequestParam("sortSeqno") int sortSeqno, */
	@GetMapping("dtl.do")
	/*

	*/
	public String reviewDtl(@RequestParam("brchCd") int branch_id, @RequestParam("yy") int yy, @RequestParam("lectSmsterCd") int lectSmsterCd
			,@RequestParam("lectCd") int lectCd, @RequestParam("tcCdNo") int teacher_sq,@RequestParam("memberNo") int member_sq, FrmSearchDTO frmSearchDTO,
			Model model) throws Exception {
		log.info("> review/dtl getReviewDtl() GET ... ");
		dto = this.reviewService.dtlReview(branch_id, yy, lectSmsterCd, lectCd, teacher_sq, member_sq, frmSearchDTO);
		// reviewDtl = this.reviewService.selectReview(branch_id, yy, lectSmsterCd, lectCd, teacher_sq, member_sq, comment_content, dto.getReview_sq());
		String name = dto.getName();
		String dateWritingoutDt = String.format("%s", dto.getDate_writingout_dt() );
		String reviewTitle = dto.getReview_title();
		String reviewContent = dto.getReview_content();
		String classSt = dto.getClass_st();
		String lrclsctegry = dto.getLrclsctegry();
		String mdclsctegry = dto.getMdclsctegry();
		String classNm = dto.getClass_nm();
		String branchNm = dto.getBranch_nm();
		String teacherNm = dto.getTeacher_nm();
		String smstNm = dto.getSmst_nm();
		String rceptPrdStDt = String.format("%s ~ %s", dto.getSchedule_start_dt(), dto.getSchedule_end_dt() );
		String classImg = dto.getClass_img();
		int rating = dto.getRating();
		
		model.addAttribute("dto", dto);
		model.addAttribute("name", name);
		model.addAttribute("dateWritingoutDt", dateWritingoutDt);
		model.addAttribute("reviewTitle", reviewTitle);
		model.addAttribute("reviewContent", reviewContent);
		model.addAttribute("classSt", classSt);
		model.addAttribute("lrclsctegry", lrclsctegry);
		model.addAttribute("mdclsctegry", mdclsctegry);
		model.addAttribute("classNm", classNm);
		model.addAttribute("branchNm", branchNm);
		model.addAttribute("teacherNm", teacherNm);
		model.addAttribute("smstNm", smstNm);
		model.addAttribute("rceptPrdStDt", rceptPrdStDt);
		model.addAttribute("classImg", classImg);
		model.addAttribute("rating", rating);
		
		return "community.review.dtl";
	}
	

	// 수강후기 상세페이지 댓글 ajax
	@PostMapping(value = "/comment/list.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getCommt(@RequestBody FrmSearchDTO frmsearchDTO)throws Exception{
		log.info("> review/comment/list.ajax : ReviewController.getCommt() POST 호출 ");
		log.info("Controller 호출" + frmsearchDTO);
		int brchCd = dto.getBranch_id();
		int yy = dto.getYy();
		int lectSmsterCd = dto.getLectSmsterCd();
		int lectCd = dto.getLectCd();
		int tcCdNo = dto.getTeacher_sq();
		int memberNo = dto.getMember_sq();
		String html = ""; 
		html = reviewService.commtHTML(brchCd, yy, lectSmsterCd, lectCd, tcCdNo, memberNo, frmsearchDTO);
		
		return !html.equals("")
					? new ResponseEntity<>(html, HttpStatus.OK)
					: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
