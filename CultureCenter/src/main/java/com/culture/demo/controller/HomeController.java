package com.culture.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.service.AppSearchService;
import com.culture.demo.service.LecSearchService;
import com.culture.demo.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LecSearchService lecSearchService;

	@Autowired
	private AppSearchService appSearchService;

	@Autowired
	private MemberService memberService;
	
	ClassDTO dto = null;

	@GetMapping({"/index.do","/"})
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Map<String, List<ClassDTO>> bmap = new HashMap<>();
		List<ClassDTO> blist = lecSearchService.getBranch();
		List<ClassDTO> bplist = null;
		
		for(int i=0; i<blist.size(); i++) {
			String type = blist.get(i).getBranch_tp();
			if (bmap.containsKey(type)) {
				bmap.get(type).add(blist.get(i));
			} else {
				bplist = new ArrayList<>();
				bplist.add(blist.get(i));
				bmap.put(type, bplist);
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
		
		return "home.index";
	}
	

	@PostMapping(value={"/getRecommendationClassList.ajax", "/getNewClassList.ajax", "/getCategoryClassList.ajax"}, produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> getMainLectList(@RequestBody MainLectSearchDTO mainLectSearchDTO, HttpServletRequest request) throws Exception {   /* , Principal principal */
		log.info("> /getRecommendationClassList.ajax /getNewClassList.ajax /getCategoryClassList.ajax... POST : HomeController.getRecommendationClassList()");
		int member_sq = 12;
		// int member_sq = Integer.parseInt( principal.getName() );
		int branch_id = this.memberService.getMypageInfo(member_sq)
							.getBranch_id();
		mainLectSearchDTO.setBranch_id(branch_id);
		String html = this.appSearchService.mainLecHTML(mainLectSearchDTO, request);		
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}