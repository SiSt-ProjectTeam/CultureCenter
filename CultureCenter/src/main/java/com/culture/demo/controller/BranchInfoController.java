package com.culture.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.BranchDTO;
import com.culture.demo.service.BranchInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class BranchInfoController {
	
	private BranchInfoService branchInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(BranchInfoController.class);
	
	@GetMapping(value = "/information/branch/list.do")
	public String branchList (BranchDTO dto
							, Model model
							, @RequestParam("brchCd") int branch_id) {
		
		log.info("> /information/branch/list.do... GET");
		
		this.branchInfoService.selectBranchList(branch_id);
		model.addAttribute("brchCd", dto.getBranch_id());
		return "information.branch.list";
		
	}
}
