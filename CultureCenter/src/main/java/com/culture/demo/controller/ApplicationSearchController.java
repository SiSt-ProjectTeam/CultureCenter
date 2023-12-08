package com.culture.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.service.LecSearchService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/application/search/*")
@Controller
public class ApplicationSearchController {
	
	@Autowired
	private LecSearchService lecSearchService;

	ClassDTO dto = null;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/list.do")
	public String listPage(Locale locale, Model model, @RequestParam("type") String type, @RequestParam("brchCd") String branch_id) {
		logger.info("Welcome home! The client locale is {}.", locale);
		log.info("> ApplicationSearchController.listPage() GET 호출");
		
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
	

}
