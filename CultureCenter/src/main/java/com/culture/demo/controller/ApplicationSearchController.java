package com.culture.demo.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.ClassFormDTO;
import com.culture.demo.domain.SearchBranchDTO;
import com.culture.demo.service.AppSearchService;
import com.culture.demo.service.LecSearchService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ApplicationSearchController {
	
	@Autowired
	private LecSearchService lecSearchService;
	
	@Autowired
	private AppSearchService appSearchService;

	ClassDTO dto = null;
	
	@GetMapping("/application/search/list.do")
	public String listPage(Model model, @RequestParam("type") String type, @RequestParam("brchCd") String branch_id) {
		log.info("> /list.do ApplicationSearchController.listPage() GET 호출");
		
		Map<String, List<ClassDTO>> bmap = new HashMap<>();
		List<ClassDTO> blist = this.lecSearchService.getBranch();
		List<ClassDTO> bplist = null;
		
		for(int i=0; i<blist.size(); i++) {
			String tp = blist.get(i).getBranch_tp();
			if (bmap.containsKey(tp)) {
				bmap.get(tp).add(blist.get(i));
			} else {
				bplist = new ArrayList<>();
				bplist.add(blist.get(i));
				bmap.put(tp, bplist);
			}
		}
		
		Map<String, List<ClassDTO>> cmap = new HashMap<>();
		List<ClassDTO> clist = this.lecSearchService.getCategory();
		List<ClassDTO> cplist = null;
		
		for(int i=0; i<clist.size(); i++) {
			String lrclsCtegry = clist.get(i).getLrclsCtegry();
			if(cmap.containsKey(lrclsCtegry)) {
				cmap.get(lrclsCtegry).add(clist.get(i));
			} else {
				cplist = new ArrayList<>();
				cplist.add(clist.get(i));
				cmap.put(lrclsCtegry, cplist);
			}
		}
		
		model.addAttribute("bmap", bmap);
		model.addAttribute("cmap", cmap);
		
		return "application.search.list";
	}
	
	@PostMapping(value="/search/list.ajax", produces="application/text; charset=UTF-8")
	public ResponseEntity<String> getBranchList(@RequestBody SearchBranchDTO searchBranchDTO, HttpServletRequest request) throws Exception {
		log.info("> /list.ajax ApplicationSearchController.getBranchList() POST 호출");
		log.info("> SearchBranchDTO : " + searchBranchDTO);
		
		int branch_id = Integer.parseInt(searchBranchDTO.getBrchCd());
		String yyl[] = null; 
		String lectcll[] = null; 
		String lectstl[] = null; 
		String dayl[] = null; 
		String timel[] = null; 
		String amtl[] = null; 
		
		if(!searchBranchDTO.getYyList().isEmpty()) yyl = searchBranchDTO.getYyList().split(",");
		if(!searchBranchDTO.getLectClCdList().isEmpty()) lectcll = searchBranchDTO.getLectClCdList().split(",");
		if(!searchBranchDTO.getLectStatCdList().isEmpty()) lectstl = searchBranchDTO.getLectStatCdList().split(",");
		if(!searchBranchDTO.getStDaywCdList().isEmpty()) dayl = searchBranchDTO.getStDaywCdList().split(",");
		if(!searchBranchDTO.getTimeTypeList().isEmpty()) timel = searchBranchDTO.getTimeTypeList().split(",");
		if(!searchBranchDTO.getAmtTypeList().isEmpty()) amtl = searchBranchDTO.getAmtTypeList().split(",");
		
		String html = "";
		html = this.appSearchService.LecHTML(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl, request);
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/application/search/view.do")
	public String viewPage(Model model, @RequestParam("branch_id") int branch_id, @RequestParam("yy") int yy, @RequestParam("lectSmsterCd") int lectSmsterCd, @RequestParam("lectCd") int lectCd) throws Exception {
		log.info("/application/search/view.do ApplicationSearchController.viewPage() GET 호출");
		dto = this.appSearchService.DetailClassInfo(branch_id, yy, lectSmsterCd, lectCd);
		
		String lectStDtm = String.format("%s ~ %s", dto.getSchedule_start_dt().substring(0,10), dto.getSchedule_end_dt().substring(0,10));
		String rceptPrdStDt = String.format("%s ~ %s", dto.getReception_start_dt().substring(0,10), dto.getReception_end_dt().substring(0,10));
		String avDay = (dto.getMon().equals("Y")?"월":"") + (dto.getTue().equals("Y")?"화":"") + (dto.getWed().equals("Y")?"수":"") + (dto.getThu().equals("Y")?"목":"")
					+ (dto.getFri().equals("Y")?"금":"") + (dto.getSat().equals("Y")?"토":"") + (dto.getSun().equals("Y")?"일":"");
		String lectTime = String.format("(%s) %s:%s~%s:%s", avDay, dto.getStart_time().substring(0, 2), dto.getStart_time().substring(2), dto.getEnd_time().substring(0, 2), dto.getEnd_time().substring(2));
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		String fee = decimalFormat.format(dto.getClass_fee());
		
		model.addAttribute("dto", dto);
		model.addAttribute("lectStDtm", lectStDtm);
		model.addAttribute("rceptPrdStDt", rceptPrdStDt);
		model.addAttribute("lectTime", lectTime);
		model.addAttribute("fee", fee);
		
		return "application.search.view";
	}
	
	@GetMapping(value="/application/search/teacherView.ajax", produces="application/text; charset=UTF-8")
	public ResponseEntity<String> teacherView(@RequestParam("tcCdNo") int member_sq) throws Exception {
		log.info("/application/search/teacherView.ajax ApplicationSearchController.teacherView() GET 호출");
		log.info("member_sq : " + member_sq);
		String html = "";
		html = this.appSearchService.teacherHTML(member_sq);
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/application/search/view.ajax")
	public ResponseEntity<String> viewAjax(@RequestBody ClassFormDTO classFormDTO) {
		log.info("/application/search/view.ajax ApplicationSearchController.viewAjax() POST 호출");
		
		String html = "";
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
}
/*
<div class="tit_area">
	<div class="instructor_img teacherHasImg has_img">
		<!-- 강사님 사진이 있을 경우 has_img 클래스 추가해주세요 -->
		<p class="img_resize_w img">
			<img class="teacherWebPath" src="https://culture.lotteshopping.com/files/CUL_ONL/OLD/COMMON/IMAGES/TECH/036845.jpg" alt="036845.jpg">
			</p>
	</div>
	<div class="tit_wrap">
		<p class="title f_desc lectNm"></p>
		<p class="instructor_name tcNm">김현진</p>
	</div>
</div>

<div class="dot_txt_box" style="display:none;">
	<p class="f_body2 teacherSelfIntrdnCont"></p>
</div>

<div class="sub_inner">
	<div class="sub_tit_area">
		<p class="tit f_h2">학력 및 경력</p>
	</div>
	<div class="instructor_info">
		<div class="info_list teacherInfo1">
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">학력</p>
						</div>
						<p class="f_body1">세종대학교 &nbsp;음악과</p>
					</div>
					<div class="type_div">
						<p class="type"></p>
						<p class="type">2019</p>
					</div>
				</div>
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">학력</p>
						</div>
						<p class="f_body1">숙명여자대학교 대학원&nbsp;관현악과</p>
					</div>
					<div class="type_div">
						<p class="type"></p>
						<p class="type">2021</p>
					</div>
				</div>
			<div class="list_title career">
					<div class="title">
						<div class="label_div">
							<p class="label small black">경력</p>
						</div>
						<p class="f_body1">동수원 초등학교(오케스트라 교육 및 첼로 지도)</p>
					</div>
					<div class="type_div">
						<p class="type">202203  ~         </p>
					</div>
				</div>
			<div class="list_title career">
					<div class="title">
						<div class="label_div">
							<p class="label small black">경력</p>
						</div>
						<p class="f_body1">광교종합사회복지관(첼로 강사)</p>
					</div>
					<div class="type_div">
						<p class="type">202203  ~         </p>
					</div>
				</div>
			<div class="list_title career">
					<div class="title">
						<div class="label_div">
							<p class="label small black">경력</p>
						</div>
						<p class="f_body1">서울시청 오케스트라(오케스트라 교육 및 첼로 지)</p>
					</div>
					<div class="type_div">
						<p class="type">202001  ~ 202011  </p>
					</div>
				</div>
			<div class="list_title career">
					<div class="title">
						<div class="label_div">
							<p class="label small black">경력</p>
						</div>
						<p class="f_body1">안산드림청소년 오케스트라(오케스트라 교육 및 첼로 지도)</p>
					</div>
					<div class="type_div">
						<p class="type">202107  ~ 202110  </p>
					</div>
				</div>
			<div class="list_title career">
					<div class="title">
						<div class="label_div">
							<p class="label small black">경력</p>
						</div>
						<p class="f_body1">한국음악협회-더스트라움(음악분야 연주)</p>
					</div>
					<div class="type_div">
						<p class="type">202107  ~ 202111  </p>
					</div>
				</div>
			</div>
	</div>
</div>
<div class="sub_inner">
	<div class="sub_tit_area">
		<p class="tit f_h2">수상내역 및 자격증</p>
	</div>
	<div class="instructor_info">
		<div class="info_list teacherInfo2">

			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">수상내역</p>
						</div>
						<p class="f_body1">Seoul Arts 콩쿠르 고등부 3등</p>
					</div>
					<div class="type_div">
						<p class="type">아마데우스</p>
						<p class="type">20131201</p>
					</div>
				</div>
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">수상내역</p>
						</div>
						<p class="f_body1">사계음악콩쿠르 고등부 3등</p>
					</div>
					<div class="type_div">
						<p class="type">한음아트페스티벌</p>
						<p class="type">20131201</p>
					</div>
				</div>
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">수상내역</p>
						</div>
						<p class="f_body1">ISAN 음악콩쿠르 대학부 1등</p>
					</div>
					<div class="type_div">
						<p class="type">ISAM music</p>
						<p class="type">20140501</p>
					</div>
				</div>
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">수상내역</p>
						</div>
						<p class="f_body1">한미문화교류협회 대학부 2등</p>
					</div>
					<div class="type_div">
						<p class="type">한미문화교류협회</p>
						<p class="type">20171001</p>
					</div>
				</div>
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">수상내역</p>
						</div>
						<p class="f_body1">세종음악콩쿠르 대학부 1등</p>
					</div>
					<div class="type_div">
						<p class="type">세종대학교</p>
						<p class="type">20181101</p>
					</div>
				</div>
			<div class="list_title edu">
					<div class="title">
						<div class="label_div">
							<p class="label small black">수상내역</p>
						</div>
						<p class="f_body1">별망성음악콩쿠르 일반부 우수상</p>
					</div>
					<div class="type_div">
						<p class="type">안산음악협회</p>
						<p class="type">20201001</p>
					</div>
				</div>
			</div>
	</div>
</div>
<div class="flex_btn_wrap">
	<a href="javascript:$('.instructor_intro_pop .btn_close').click();" class="border_btn"> <span>닫기</span>
	</a>
</div>


*/
