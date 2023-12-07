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

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.service.LecSearchService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LecSearchService lecSearchService;

	ClassDTO dto = null;

	@GetMapping(value = "/index.do")
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Map<String, List<ClassDTO>> map = new HashMap<>();
		List<ClassDTO> list = lecSearchService.getBranch();
		List<ClassDTO> plist = null;
		
		for(int i=0; i<list.size(); i++) {
			String type = list.get(i).getBranch_tp();
			if (map.containsKey(type)) {
				map.get(type).add(list.get(i));
			} else {
				plist = new ArrayList<>();
				plist.add(list.get(i));
				map.put(type, plist);
			}
		}
		
		System.out.println(map);
		model.addAttribute("map", map);
		
		return "home.index";
	}
	
}
