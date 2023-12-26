package com.culture.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.InquiryDTO;
import com.culture.demo.service.InquiryService;

@Controller
@RequestMapping("/mypage/inquiry/*")
public class MypageInquiryController{
	
	@Autowired
	private InquiryService inquiryService;  
	
	private static final Logger logger = LoggerFactory.getLogger(MypageInquiryController.class);
	
	// 문의하기
	@RequestMapping(value = "/insert.ajax", method = RequestMethod.POST)
    public ResponseEntity<String> insertInquiry(@RequestBody InquiryDTO params) {
		System.out.println(params);
			try {
				int rowCount = inquiryService.insertInquiry(params);
				if (rowCount > 0) {
					return new ResponseEntity<>("Success", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	
	// 문의 삭제
	@RequestMapping(value = "/delete.do" , method = RequestMethod.POST)
	public String deleteInquiry(@RequestBody int actCnt) {
		logger.info("MypageInquiryController.java > deleteinquiry...");
	    try {
	        int rowCount = inquiryService.deleteInquiry(actCnt);
	        if (rowCount > 0) {
	    	    return "mypage.inquiry.list";
	        } 
	    } catch (Exception e) {
	    	logger.info("Processing 404 Error");
	    }
	    return "mypage.inquiry.list";
	}
	
	// 리스트 출력
	@RequestMapping(value = "/list.do", method = RequestMethod.GET) 
	public String inquirylistPage(Model model, InquiryDTO params) {
	    logger.info("MypageInquiryController.java > inquirylistPage...");

	    try {
	    	
//	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    	User user = (User) authentication.getPrincipal();
//	    	
//	        // 여기서 WaitingListDTO 가져오기
//	        List<WaitingListDTO> waitingListDTO = waitingService.createWaitingHtml(user.getUsername(n), params); // 여기서 1은 member_sq에 해당하는 값으로 바꿔주세요.

	        // 모델에 데이터 추가
	    	System.out.println(params);
	        model.addAttribute("params", params);
	    } catch (Exception e) {
	        // 예외 처리
	        logger.error("Error occurred: {}", e.getMessage());
	    }

	    return "mypage.inquiry.list";
	}
	
	//필터(문의유형) ajax 처리
	@RequestMapping(value = "/list.ajax", method = RequestMethod.POST, produces = "application/text; charset=UTF-8") 
    public @ResponseBody ResponseEntity<String> inquiryFilterList(@RequestBody InquiryDTO params) throws Exception {
        logger.info("MypageInquiryController.java > inquiryFilterList...");
    	    	
//	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    	User user = (User) authentication.getPrincipal();
//	    	
//	        // 여기서 WaitingListDTO 가져오기
//	        WaitingListDTO waitingListDTO = waitingListMapper.getWaitingList(user.getUsername()); 
//          String member_sq = user.getUsername();

//      	List<WaitingListDTO> filteredList = waitingListMapper.getWaitingList(member_sq, branch_nm);
        	System.out.println("Controller : " + params);
        	int member_sq = 12;
            String html = inquiryService.createInquiryListHtml(member_sq, params);
            
            return !html.isEmpty()? new ResponseEntity<>(html,HttpStatus.OK)
		              : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 

	}
	
	// 뷰 출력
	@RequestMapping(value = "/view.do", method = RequestMethod.GET) 
	public String inquiryViewPage(Model model ,@RequestParam("personal_faq_sq") int personal_faq_sq) throws Exception {
	    logger.info("MypageInquiryController.java > inquiryViewPage...");

	   
	    	
//	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    	User user = (User) authentication.getPrincipal();
//	    	
//	        // 여기서 WaitingListDTO 가져오기
//	        List<WaitingListDTO> waitingListDTO = waitingService.createWaitingHtml(user.getUsername(n), params); 
	    List<InquiryDTO> list = inquiryService.getInquiryView(12, personal_faq_sq); 

        model.addAttribute("inquiryView", list);

        return "mypage.inquiry.view";
	}
	
}



