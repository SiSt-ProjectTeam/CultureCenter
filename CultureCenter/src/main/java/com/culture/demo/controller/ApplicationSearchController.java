package com.culture.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.SearchBranchDTO;
import com.culture.demo.service.AppSearchService;
import com.culture.demo.service.LecSearchService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ApplicationSearchController {
	
	@Autowired
	private LecSearchService lecSearchService;
	
	@Autowired
	private AppSearchService appSearchService;

	ClassDTO dto = null;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/application/search/list.do")
	public String listPage(Locale locale, Model model, @RequestParam("type") String type, @RequestParam("brchCd") String branch_id) {
		logger.info("Welcome home! The client locale is {}.", locale);
		log.info("> /list.do ApplicationSearchController.listPage() GET 호출");
		
		Map<String, List<ClassDTO>> bmap = new HashMap<>();
		List<ClassDTO> blist = lecSearchService.getBranch();
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
		List<ClassDTO> clist = lecSearchService.getCategory();
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
	
	@PostMapping(value="/search/list.ajax", produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> getBranchList(@RequestBody SearchBranchDTO searchBranchDTO) throws Exception {
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
		if(!searchBranchDTO.getStDaywCdList().isEmpty()) {
			searchBranchDTO.getStDaywCdList().replaceAll("Y", "1");
			searchBranchDTO.getStDaywCdList().replaceAll("N", "0");
			dayl = searchBranchDTO.getStDaywCdList().split(",");
		}
		if(!searchBranchDTO.getTimeTypeList().isEmpty()) timel = searchBranchDTO.getTimeTypeList().split(",");
		if(!searchBranchDTO.getAmtTypeList().isEmpty()) amtl = searchBranchDTO.getAmtTypeList().split(",");
		
		String html = "";
		html = this.appSearchService.LecHTML(branch_id, searchBranchDTO, yyl, lectcll, lectstl, dayl, timel, amtl);
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(html, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
