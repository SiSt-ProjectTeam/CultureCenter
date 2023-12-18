package com.culture.demo.controller;

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

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.WaitingListDTO;
import com.culture.demo.service.WaitingService;

@Controller
@RequestMapping("/mypage/waiting/*")
public class MypageWaitingController{
	
	@Autowired
	private WaitingService waitingService;  
	
	private static final Logger logger = LoggerFactory.getLogger(MypageWaitingController.class);
	
	// 대기 삭제
	@RequestMapping(value = "/cancel.do" , method = RequestMethod.POST)
	public String deleteWaiting(@RequestParam("atlctRsvNo") int late_sq) {
		logger.info("MypageWaitingController.java > deleteWaiting...");
	    try {
	        int rowCount = waitingService.deleteWaiting(late_sq);
	        if (rowCount > 0) {
	    	    return "mypage.waiting.list";
	        } else {
	        }
	    } catch (Exception e) {
	    	logger.info("Processing 404 Error");
	    }
	    return "mypage.waiting.list";
	}
	
	// 리스트 출력
	@RequestMapping(value = "/list.do", method = RequestMethod.GET) 
	public String waitinglistPage(Model model, WaitingListDTO params) {
	    logger.info("MypageWaitingController.java > waitinglistPage...");

	    try {
	    	
//	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    	User user = (User) authentication.getPrincipal();
//	    	
//	        // 여기서 WaitingListDTO 가져오기
//	        List<WaitingListDTO> waitingListDTO = waitingService.createWaitingHtml(user.getUsername(n), params); // 여기서 1은 member_sq에 해당하는 값으로 바꿔주세요.

	        // 모델에 데이터 추가
	        model.addAttribute("params", params);
	    } catch (Exception e) {
	        // 예외 처리
	        logger.error("Error occurred: {}", e.getMessage());
	    }

	    return "mypage.waiting.list";
	}
	
	//필터(지점) ajax 처리
	@RequestMapping(value = "/list.ajax", method = RequestMethod.POST, produces = "application/text; charset=UTF-8") 
    public @ResponseBody ResponseEntity<String> filterList(@RequestBody FrmSearchDTO params) throws Exception {
        logger.info("MypageWaitingController.java > filterList...");
    	    	
//	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    	User user = (User) authentication.getPrincipal();
//	    	
//	        // 여기서 WaitingListDTO 가져오기
//	        WaitingListDTO waitingListDTO = waitingListMapper.getWaitingList(user.getUsername()); 
//          String member_sq = user.getUsername();

//      	List<WaitingListDTO> filteredList = waitingListMapper.getWaitingList(member_sq, branch_nm);
        	System.out.println("Controller : " + params);
        	int member_sq = 50;
            String html = waitingService.createWaitingHtml(member_sq, params);
            
            return !html.isEmpty()? new ResponseEntity<>(html,HttpStatus.OK)
		              : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 

	}

	
}



