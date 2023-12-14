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
	ClassDTO classDtl = null;
	
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
		classDtl = this.appSearchService.selectClassInfo(branch_id, yy, lectSmsterCd, lectCd);
		
		String lectStDtm = String.format("%s ~ %s", classDtl.getSchedule_start_dt().substring(0,10), classDtl.getSchedule_end_dt().substring(0,10));
		String rceptPrdStDt = String.format("%s ~ %s", classDtl.getReception_start_dt().substring(0,10), classDtl.getReception_end_dt().substring(0,10));
		String avDay = (classDtl.getMon().equals("Y")?"월":"") + (classDtl.getTue().equals("Y")?"화":"") + (classDtl.getWed().equals("Y")?"수":"") + (classDtl.getThu().equals("Y")?"목":"")
					+ (classDtl.getFri().equals("Y")?"금":"") + (classDtl.getSat().equals("Y")?"토":"") + (classDtl.getSun().equals("Y")?"일":"");
		String lectTime = String.format("(%s) %s:%s~%s:%s", avDay, classDtl.getStart_time().substring(0, 2), classDtl.getStart_time().substring(2), classDtl.getEnd_time().substring(0, 2), classDtl.getEnd_time().substring(2));
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		String fee = decimalFormat.format(classDtl.getClass_fee());
		
		model.addAttribute("dto", dto);
		model.addAttribute("classDtl", classDtl);
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
	
	
	
}