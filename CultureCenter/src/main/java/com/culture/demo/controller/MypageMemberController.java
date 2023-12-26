package com.culture.demo.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.culture.demo.domain.ChildrenDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.CartService;
import com.culture.demo.service.MemberService;
import com.culture.demo.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MypageMemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private CartService cartService;
		
	@GetMapping(value = "/mypage/member/count.ajax", produces = "application/json; charset=UTF-8")
	public ResponseEntity<String> getMypageInfo(Authentication authentication) throws SQLException, JsonProcessingException, ClassNotFoundException {
		log.info("> /mypage/member/count.ajax... GET : MypageMemberController.getMypageInfo()");
		CustomerUser principal =  (CustomerUser) authentication.getPrincipal();
		
		String mypageInfo = "";
		ObjectMapper objectMapper = new ObjectMapper();
		MemberDTO dto = this.memberService.getMypageInfo(principal.getMember_sq());
		dto.setBasket_cnt( this.cartService.getTotCartCnt(principal.getMember_sq()));
		mypageInfo = objectMapper.writeValueAsString(dto);
		
		return !mypageInfo.equals("")
				? new ResponseEntity<>(mypageInfo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping("/setItrst.ajax")
	public ResponseEntity<Integer> setInterestBranch(@RequestBody Map<String, Integer> requestBody, Authentication authentication) {  //, 
		log.info("> /setItrst.ajax... POST : MypageMemberController.setInterestBranch()");	
		CustomerUser principal =  (CustomerUser) authentication.getPrincipal();
		
		int rtnCnt = this.memberService.correctionInterestBranch(principal.getMember_sq(), requestBody.get("itrstBrchCd"));
		
		return rtnCnt > 0
				? new ResponseEntity<>(rtnCnt, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 동반수강자(자녀) 추가 Ajax
	@PostMapping("/mypage/member/family/insert.ajax")
	public @ResponseBody ResponseEntity<Map<String, String>> insertFamily(@RequestBody ChildrenDTO dto) throws Exception{
		log.info("> /mypage/member/family/insert.ajax ... POST : MypageMemberController.insertFamily()");
		
		int rtnCnt = this.memberService.insertChildren(dto);
		
		return rtnCnt==1? ResponseEntity.ok(Map.of("rtnCode","1")):ResponseEntity.ok(Map.of("rtnCode","-1"));
	}
	// 동반수강자(자녀) 삭제 Ajax
	@PostMapping("/mypage/member/family/delete.ajax")
	public @ResponseBody ResponseEntity<Map<String, String>> deleteFamily(@RequestBody Map<String, Integer> param) throws Exception{
		log.info("> /mypage/member/family/delete.ajax ... POST : MypageMemberController.deleteFamily()");
		
		int rtnCnt = this.memberService.deleteChildren(param.get("children_sq"));
		
		return rtnCnt==1? ResponseEntity.ok(Map.of("rtnCode","1")):ResponseEntity.ok(Map.of("rtnCode","-1"));
	}
	
	// 회원정보변경 폼
	@GetMapping("/mypage/member/info.do")
	public String memberinfoForm(Authentication authentication, Model model) throws Exception { 
	    log.info("> MypageMemberController....memberinfoForm");

	    CustomerUser principal = (CustomerUser) authentication.getPrincipal();
	    int member_sq = principal.getMember_sq();

	    // 회원정보, 동반수강자 불러오기
	    MemberDTO mDto = memberService.getMemberWithChild(member_sq);

	    model.addAttribute("mDto", mDto);
	    return "mypage.member.info";
	}
	
	// 차량번호 추가 Ajax
	@PostMapping("/mypage/member/updateCarYn.ajax")
	public void updateCarYn(Authentication authentication, @RequestBody MemberDTO memberDTO) throws Exception {
		log.info("> /mypage/member/updateCarYn.ajax ... POST : MypageMemberController.updateCarYn");

		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();

	    memberDTO.setMember_sq(member_sq);
	    this.memberService.updateCar(memberDTO);
	}

	// 회원정보 변경 이동
	@GetMapping("/mypage/member/upMember.do")
	public String upMember(Authentication authentication, Model model) throws Exception {
		log.info("> /login/findId.do : MemberController.upMember() ... ");
		
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
	    int member_sq = principal.getMember_sq();

	    // 회원정보, 동반수강자 불러오기
	    MemberDTO mDto = memberService.getMemberWithChild(member_sq); 
	    model.addAttribute("mDto", mDto);
		return "mypage/member/up_member";
	}

	// 회원정보 변경 이동
	@PostMapping("/mypage/member/upMember.do")
	public ResponseEntity<String> updateMember(Authentication authentication, @RequestBody MemberDTO memberDTO) throws Exception {
        log.info("> MemberController.updateMember() ... ");
        
        CustomerUser principal = (CustomerUser) authentication.getPrincipal();
        int member_sq = principal.getMember_sq();

        memberDTO.setMember_sq(member_sq);
        boolean updateSuccess = this.memberService.updateMember(memberDTO);

        if (updateSuccess) {
            return ResponseEntity.ok("회원 정보 업데이트 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 정보 업데이트 실패");
        }
    }
	
	
	// 비밀번호 변경 이동
	@GetMapping("/mypage/member/upPw.do")
	public String upPw() throws Exception {
		log.info("> /login/findId.do : MemberController.upPw() ... ");
		return "mypage/member/up_pw";
	}
	
	// 비밀번호 확인 
	@PostMapping("/mypage/member/password.do")
	public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, Object> requestBody, Authentication authentication) {
	    log.info("> /mypage/member/password.do ... POST : MypageMemberController.checkPassword()");

	    CustomerUser principal = (CustomerUser) authentication.getPrincipal();
	    int member_sq = principal.getMember_sq();

	    String enteredPassword = (String) requestBody.get("pw");
	    boolean isPasswordCorrect = memberService.checkPassword(member_sq, enteredPassword);

	    Map<String, Boolean> response = new HashMap<>();
	    response.put("success", isPasswordCorrect);

	    return ResponseEntity.ok(response);
	}


	// 비밀번호 변경 
	@PostMapping("/mypage/member/upPw.do")
	public ResponseEntity<String> updatePassword(
	    @RequestParam("pw") String newPassword,
	    Authentication authentication) {
	    log.info("> /mypage/member/upPw.do ... POST : MypageMemberController.updatePassword()");

	    CustomerUser principal = (CustomerUser) authentication.getPrincipal();
	    int member_sq = principal.getMember_sq();
System.out.println(":");
	    boolean updateSuccess = memberService.updatePassword(member_sq, newPassword);

	    if (updateSuccess) {
	        return ResponseEntity.ok("비밀번호 업데이트 성공");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 업데이트 실패");
	    }
	}
	
	 // 회원탈퇴 
    @GetMapping("/mypage/member/checkMemberDelete.ajax")
    public String checkMemberDelete(Authentication authentication, RedirectAttributes redirectAttributes, HttpSession session) {
        try {
            CustomerUser principal = (CustomerUser) authentication.getPrincipal();
            int member_sq = principal.getMember_sq();

            boolean success = memberService.checkMemberDelete(member_sq);

            if (success) {
                log.info("회원 탈퇴 성공 - 회원번호: " + member_sq);
                session.invalidate();
                return "login.index";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "회원탈퇴 중 오류가 발생했습니다. 다시 시도해주세요.");
                return "mypage.member.info";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "회원탈퇴 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "mypage.member.info";
        }
    }




	
}