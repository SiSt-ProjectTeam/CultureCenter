package com.culture.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.MainLectSearchDTO;
import com.culture.demo.domain.NoticeDTO;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.AppSearchService;
import com.culture.demo.service.LecSearchService;
import com.culture.demo.service.MemberService;
import com.culture.demo.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private LecSearchService lecSearchService;
	private AppSearchService appSearchService;
	private MemberService memberService;
	private NoticeService noticeService;

	@GetMapping({"/index.do","/"})
	public String home(Locale locale, Model model, Principal principal) throws Exception {
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
		
		// 공지사항
		List<NoticeDTO> noticeList = this.noticeService.getMainNoticeList();
		for (NoticeDTO noticeDTO : noticeList) {
			
			Document document = Jsoup.parse(noticeDTO.getPosting_content());
	        String textContent = Jsoup.clean(document.body().html(), Whitelist.none());

			noticeDTO.setPosting_content( textContent );
		}
		model.addAttribute("noticeList", noticeList);		
		
		return "home.index";
	}
	

	@PostMapping(value={"/getRecommendationClassList.ajax", "/getNewClassList.ajax", "/getCategoryClassList.ajax"}, produces = "application/text; charset=UTF-8")
	public ResponseEntity<String> getMainLectList(@RequestBody MainLectSearchDTO mainLectSearchDTO, HttpServletRequest request, Authentication authentication
			) throws Exception {
		
		log.info("> /getRecommendationClassList.ajax /getNewClassList.ajax /getCategoryClassList.ajax... POST : HomeController.getMainLectList()");
		int branch_id = 0;
		
		if (authentication != null) {
			CustomerUser principal =  (CustomerUser) authentication.getPrincipal();
			branch_id = this.memberService.getMypageInfo(principal.getMember_sq()).getBranch_id();
		}
		mainLectSearchDTO.setBranch_id(branch_id);
		String html = this.appSearchService.mainLecHTML(mainLectSearchDTO, request);
		
		return !html.equals("")
				? new ResponseEntity<>(html, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
