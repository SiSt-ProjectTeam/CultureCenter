package com.culture.demo.controller;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.MemberDTO;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.EmailSenderService;
import com.culture.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class MemberController {
	
	private PasswordEncoder passwordEncoder;
	
	private MemberService memberService;
	
	private EmailSenderService emailSenderService;
	
	//로그인 체크
	@GetMapping("/lgnCheck.ajax")
	public @ResponseBody ResponseEntity<Map<String, Boolean>> loginCheck(Authentication authentication) throws Exception {
		log.info("> /lgnCheck.ajax : MemberController.loginCheck() ... ");
		CustomerUser principal = null;
		try {
			principal = (CustomerUser) authentication.getPrincipal();
		} catch (Exception e) {
			System.out.println("/lgnCheck.ajax : 로그인안된상태...");
		}
		Map<String, Boolean> response;
		if(principal != null) response = Map.of("lgnYn", true);
		else response = Map.of("lgnYn", false);
	    return ResponseEntity.ok(response);
	}
	
	// 로그인 페이지 이동
	@GetMapping("/login/index.do")
	public String goLogin(@RequestParam(name = "join", required = false) String joinStatus, Model model) throws Exception {
		log.info("> /login/index.do : MemberController.goLogin() ... ");
				
		if ("success".equals(joinStatus)) {
	        String alertScript = "<script>alert('회원가입이 성공했습니다. 로그인 후 사용 가능합니다.');</script>";
	        model.addAttribute("alertScript", alertScript);
	    } else if ("fail".equals(joinStatus)) {
	        String alertScript = "<script>alert('회원가입이 실패했습니다. 잠시후 다시 시도바랍니다.');</script>";
	        model.addAttribute("alertScript", alertScript);
	    }
		
	    return "login.index";
	}
	
	// 로그인 실패 페이지 이동
	@GetMapping("/login/result.do")
	public String goLoginResult() throws Exception {
		log.info("> /login/result.do : MemberController.goLoginResult() ... ");
				
	    return "login.result";
	}

	// 회원가입 페이지 이동
	@GetMapping("/member/join.do")
	public String goJoin() throws Exception {
		log.info("> /member/join.do   GET: MemberController.goJoin() ... ");
				
	    return "mypage.member.join";
	}

	// 회원가입
	@PostMapping("/member/join.do")
	public String join(MemberDTO memberDTO, HttpServletResponse response) throws Exception {
		log.info("> /member/join.do   POST : MemberController.join() ... ");
		
		String pw = memberDTO.getPw();
		memberDTO.setPw(this.passwordEncoder.encode(pw));

		int result = this.memberService.registMember(memberDTO);
		if (result > 0) {
			String fromAddress = "yuhuijin270@gmail.com";
			String subject = "[문화센터] 회원가입을 진심으로 환영합니다";
			String msgBody = "<div style=\"background:#ffffff;margin:0;padding:0;font-family:'Apple SD Gothic', '맑은고딕', 'Nanum Gothic', sans-serif\">\r\n"
						+ "    <div style=\"background:#ffffff;margin:0 auto;padding:0;width:100%;max-width:600px;box-sizing:border-box;-webkit-text-size-adjust:none\">\r\n"
						+ "        <table align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#fff;margin:0;padding:0;max-width:600px\">\r\n"
						+ "            <tbody><tr><td style=\"background:#ffffff;padding:63px 2px 43px\">\r\n"
						+ "                        <a href=\"http://192.168.10.174/index.do\" rel=\"noreferrer noopener\" target=\"_blank\"><img src=\"https://culture.lotteshopping.com/common/images/logo-ldcs-color.svg\" width=\"116\" height=\"38\" border=\"0\" alt=\"문화센터\" style=\"display:block;\" loading=\"lazy\"></a>\r\n"
						+ "                    </td></tr><tr><td style=\"background:#ffffff;padding: 0 2px 0;\">\r\n"
						+ "                        <table align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
						+ "                            <!--- - - - - - - - - -tbody start- - - - - - - - -->\r\n"
						+ "                            <tbody><tr><td align=\"left\" style=\"background:#fff;padding:0\">\r\n"
						+ "        <p style=\"margin:0 0 24px;padding:0;font-family:'Apple SD Gothic', '맑은고딕', 'Nanum Gothic', sans-serif;font-size:26px;color:#000;letter-spacing:-1px;line-height:32px;font-weight:bold\">\r\n"
						+ "            안녕하세요. "+memberDTO.getName()+"님!<br>\r\n"
						+ "            가입을 환영합니다 :)\r\n"
						+ "        </p>\r\n"
						+ "        <p style=\"margin:0;padding:0;font-family:'Apple SD Gothic', '맑은고딕', 'Nanum Gothic',sans-serif;font-size:18px;color:#404040;letter-spacing:-1px;line-height:24px\">\r\n"
						+ "            문화 센터 회원 가입이 완료되었습니다.<br> 지금 바로 강좌를 확인해보세요!\r\n"
						+ "        </p>\r\n"
						+ "        <table align=\"center\" width=\"500\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:0\">\r\n"
						+ "            <tbody><tr><td height=\"56\"></td></tr>\r\n"
						+ "            <tr><td align=\"center\" height=\"56\" style=\"border-radius:4px;background:#000\">\r\n"
						+ "                <a href=\"http://192.168.10.174/index.do\" target=\"_blank\" style=\"display:block;line-:56px;border:0;font-family:'apple sd gothic', '맑은고딕', 'nanum gothic',sans-serif;color:#5cf636;font-size:16px;letter-spacing:-0.5px;text-decoration:none;font-weight:bold\" rel=\"noreferrer noopener\">\r\n"
						+ "                    문화센터 강좌 둘러보기\r\n"
						+ "                </a>\r\n"
						+ "            </td>\r\n"
						+ "            <td height=\"20\"></td>\r\n"
						+ "            <td align=\"center\" height=\"56\" style=\"border-radius:4px;background:#000\">\r\n"
						+ "                <a href=\"http://192.168.10.174/login/index.do\" target=\"_blank\" style=\"display:block;line-:56px;border:0;font-family:'apple sd gothic', '맑은고딕', 'nanum gothic',sans-serif;color:#5cf636;font-size:16px;letter-spacing:-0.5px;text-decoration:none;font-weight:bold\" rel=\"noreferrer noopener\">\r\n"
						+ "                    로그인 바로가기\r\n"
						+ "                </a>\r\n"
						+ "            </td></tr>\r\n"
						+ "            <tr><td height=\"56\"></td></tr>\r\n"
						+ "        </tbody></table>\r\n"
						+ "    </td></tr>\r\n"
						+ "                            <!--- - - - - - - - - -tbody end- - - - - - - - - - -->\r\n"
						+ "                        </tbody></table>\r\n"
						+ "                    </td></tr><tr><td align=\"left\" style=\"border-top:1px solid #ececec;background:#fff;padding:0 0 72px\">\r\n"
						+ "                        <p style=\"margin:24px 0;padding:0;font-family:'Apple SD Gothic', '맑은고딕', 'Nanum Gothic',sans-serif;color:#909090;font-size:13px;line-height:18px;letter-spacing:-0.5px;text-align:left\">\r\n"
						+ "                            ※ 본 메일은 발신전용 메일로 회신을 받을 수 없습니다.<br>\r\n"
						+ "                            서울특별시 강남구 테헤란로 132, 8층<br>\r\n"
						+ "                            고객센터 : 02-0000-1111, cultureCenter@culture.com (평일 09:00 - 18:00, 점심시간 12:00 - 13:00, 주말·공휴일 휴무)\r\n"
						+ "                        </p>\r\n"
						+ "                        <div style=\"font-family:'Apple SD Gothic', '맑은고딕', 'Nanum Gothic',sans-serif;color:#909090;font-size:13px;line-height:16px;letter-spacing:-0.5px;text-align:left\">\r\n"
						+ "                            <a href=\"\" target=\"_blank\" style=\"font-family:'apple sd gothic', '맑은고딕', 'nanum gothic',sans-serif;color:#909090;font-size:13px;line-:16px;letter-spacing:-0.5px;text-align:left;text-decoration:underline\" rel=\"noreferrer noopener\">이용약관</a>\r\n"
						+ "                            &nbsp;&nbsp;|&nbsp;&nbsp;\r\n"
						+ "                            <a href=\"\" target=\"_blank\" style=\"font-family:'apple sd gothic', '맑은고딕', 'nanum gothic',sans-serif;color:#909090;font-size:13px;line-:16px;letter-spacing:-0.5px;text-align:left;text-decoration:underline\" rel=\"noreferrer noopener\">개인정보\r\n"
						+ "                                처리방침</a>\r\n"
						+ "                        </div>\r\n"
						+ "                    </td></tr></tbody>\r\n"
						+ "        </table>\r\n"
						+ "    </div>\r\n"
						+ "</div>";
			
			String sendResult = this.emailSenderService.sendEmail(memberDTO.getEmail(), fromAddress, subject, msgBody, true);	
			log.info("> Welcome Email Send : " + sendResult );
			String alertScript = "<script>alert('회원가입이 성공했습니다. 로그인 후 사용 가능합니다.');</script>";
	        response.getWriter().write(alertScript);
	        
	        return "redirect:/login/index.do?join=success";
			
		} else {
			String alertScript = "<script>alert('회원가입이 실패했습니다. 잠시후 다시 시도바랍니다.');</script>";
	        response.getWriter().write(alertScript);
	        
	        return "redirect:/login/index.do?join=fail";
		}
        
	}
	
	@GetMapping("/login/idCheck")
	@ResponseBody
	public String idcheck(String memberId) {
	    String chk = "";
	    int result = 0;
	    log.info(">memberID = " + memberId);
	    result = memberService.idCheck(memberId);
	    log.info(">memberID 중복여부 = " + (result >= 1 ? "중복" : "중복아님"));
	    if (result >= 1) {
	        chk = "redundancy"; // 중복
	    } else {
	    	chk = "noredundancy"; // 중복아님
	    }
	    return chk;
	}
	
	// 회원가입 휴대폰 인증번호 문자 발송                                                                
	@PostMapping("/sendSMS")                                                         
	public @ResponseBody ResponseEntity<Map<String, Object>> sendSMS(String phoneNumber) throws Exception {
		log.info(">> /sendSMS.ajax  POST: MemberController.sendSMS()... ");                  
			
	    // 인증번호 생성		                                                                      
	    Random rand = new Random();                                                      
	    String verifCode = ""; 
	    for(int i=0; i<6; i++) verifCode += Integer.toString(rand.nextInt(10)); 
	    
	    JSONObject sendResult = this.memberService.sendSMS(verifCode, phoneNumber);
	    Map<String, Object> rtnMap = new HashedMap();
	    rtnMap.put("verifCode", verifCode);
	    rtnMap.put("sendResult", sendResult);
	    return  ResponseEntity.ok(rtnMap);           
	}

	// 아이디 찾기
	@PostMapping("/login/findId.do")
	@ResponseBody
	public String findid(String name, String phone) {
		log.info("> MemberController.findid() ... ");
		String foundId = memberService.findId(name, phone);
		return foundId;
	}

	// 비밀번호 찾기
	@PostMapping("/login/findPw.do")
	@ResponseBody
	public String findPw(String id, String phone) {
		log.info("> MemberController.findPw() ... ");
		String foundPw = memberService.findPW(id, phone);
		return foundPw;
	}
	
	// 아이디 찾기 이동
	@GetMapping("/login/findId.do")
	public String findId() throws Exception {
		log.info("> /login/findId.do : MemberController.findId() ... ");
		return "login/find_id";
	}
	// 비밀번호 찾기 이동
	@GetMapping("/login/findPw.do")
	public String findPw() throws Exception {
		log.info("> /login/findPw.do : MemberController.findPw() ... ");
		return "login/find_pw";
	}
	
	
}
