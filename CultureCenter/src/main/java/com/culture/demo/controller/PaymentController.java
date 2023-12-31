package com.culture.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.FrmSubmitDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.domain.PaymentFrmDTO;
import com.culture.demo.security.CustomerUser;
import com.culture.demo.service.AtlctService;
import com.culture.demo.service.CartService;
import com.culture.demo.service.ComplexService;
import com.culture.demo.service.MemberService;
import com.culture.demo.service.PaymentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/payment/*")
public class PaymentController {

	private CartService cartService;
	private PaymentService paymentService;
	private MemberService memberService;
	private AtlctService atlctService; 
	private ComplexService complexserivce;
	
	// 수강결제1(step1) 페이지 이동
	@PostMapping("step1.do")
	public String goPaymentStep1(FrmSubmitDTO dto, Model model,Authentication authentication) throws Exception{ 
		log.info("/payment/step1.do + POST :PaymentController.goPaymentStep1()...");
		System.out.println(dto);
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		// 수강결제할 정보 가져오기
		String lectDetailSq = dto.getLectDetailSq();
		if (lectDetailSq.contains(",")) {
			model.addAttribute("list", cartService.getList(member_sq, lectDetailSq) );
		}else {
			int detail_class_sq = Integer.parseInt(lectDetailSq);
			List<CartDTO> list = new ArrayList<CartDTO>();
			list.add(paymentService.getLect(detail_class_sq));
			model.addAttribute("list", list);
		}
		// 회원정보,동반수강자 불러오기
		MemberDTO mDto = memberService.getMemberWithChild(member_sq);
		System.out.println(mDto);
		model.addAttribute("mDto", mDto);

		return "payment.step1";
	}

	// 수강결제1 수강자변경/추가 저장하기
	@PostMapping(value="actlAtlctNpleList.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> updateActlAtlct(@RequestBody Map<String, String> paramMap
																,Authentication authentication
																) throws Exception{
		log.info("/payment/actlAtlctNpleList.ajax + POST :PaymentController.updateActlAtlct()...");
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		int detail_class_sq = Integer.parseInt(paramMap.get("lectCd")); // 세부강좌번호
		int cnt = Integer.parseInt(paramMap.get("cnt")); // 수강신청 인원
		// 학기별강좌 - 수강가능인원
		int peopleTotAvCnt = paymentService.matchPeopleTotAv(detail_class_sq,cnt);
		// 수강신청한 강좌인지
		int orderDuplCnt = paymentService.matchClassOrder(member_sq,detail_class_sq);
		System.out.println("peopleTotAvCnt: "+ peopleTotAvCnt+ " / orderDuplCnt: "+orderDuplCnt);

		if(peopleTotAvCnt < 0) {
			return ResponseEntity.ok("<div data-rslt-cd='-1' data-lect-nm='"+paramMap.get("lectNm")+"' data-capaCnt='"+(peopleTotAvCnt+cnt)+"'><div>");
		}else if(orderDuplCnt != 0) {
			return ResponseEntity.ok("<div data-rslt-cd='-2' data-lect-nm='"+paramMap.get("lectNm")+"></div>");
		}else {
			String html = paymentService.createCourDetailWHtml(paramMap);
			//System.out.println(html);
			return ResponseEntity.ok(html);
		}
	}

	// 수강결제2(step2)페이지 이동
	@PostMapping("step2.do")
	public String goPaymentStep2(FrmSubmitDTO dto, Model model) throws Exception{
		log.info("/payment/step2.do + POST :PaymentController.goPaymentStep2()...");
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> payLectsStudentsList = mapper.readValue(dto.getJsonStr(), new TypeReference<List<Map<String, Object>>>(){});
		for (int i = 0; i < payLectsStudentsList.size(); i++) {
			CartDTO lectInfo = paymentService.getLect((Integer)payLectsStudentsList.get(i).get("lectCd"));
			payLectsStudentsList.get(i).put("dto", lectInfo);
		}
		//System.out.println("payLectsStudentsList : "+payLectsStudentsList);
		int branchCd = (int) payLectsStudentsList.get(0).get("brchCd");
		String atlctType = dto.getAtlctType();

		model.addAttribute("list", payLectsStudentsList);
		model.addAttribute("branchCd", branchCd);
		model.addAttribute("atlctType", atlctType);
		return "payment.step2";
	}

	// step1 -> step2 check사항 ajax / step2 -> 결제 check사항 ajax
	@PostMapping("validateStep{index}.ajax")
	public @ResponseBody ResponseEntity<Map<String, Object>> validateStep1(@RequestBody Map<String, String> jsonData
																			,@PathVariable("index") String step
																			,Authentication authentication
																			) throws Exception{
		log.info("/payment/validateStep"+step+".ajax + POST :PaymentController.validateStep"+step+"()...");
		Map<String, Object> rtnMap = new HashedMap();
		System.out.println((jsonData));
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		
		// jsonData를 직접 변환(deserialize)
		ObjectMapper mapper = new ObjectMapper(); 
		List<Map<String, Object>> payLectsStudentsList = null;
		ArrayList<Map<String, Object>> StudentsList = null;
		String data = jsonData.get("jsonStr"); // jsonData로 넘어온 jsonStr
		Map<String, Object> jsonStr = null; // step2 -> 3 일때 data 저장할 변수

		System.out.println(data);

		if (step.equals("1")) { // step1 -> step2  
			payLectsStudentsList = mapper.readValue(data, new TypeReference<List<Map<String, Object>>>(){});
		}else { // step2 -> 결제
			jsonStr =mapper.readValue(data, new TypeReference<Map<String, Object>>(){});
			payLectsStudentsList = (List<Map<String, Object>>) jsonStr.get("arrLect");
		}

		List<Integer> detailLectCdList = new ArrayList();
		List<String> lectNmList = new ArrayList();
		Map<Integer, List<String>> insData = new HashedMap();
		for (Map<String, Object> lecStudents : payLectsStudentsList) {
			List<String> studentNmList = new ArrayList();
			// 수강자 ( ArrayList로 넘어옴 )
			StudentsList = (ArrayList<Map<String, Object>>) lecStudents.get("arrActlAtlctNple");

			int detailLectCd = (int) lecStudents.get("lectCd");
			int cnt = StudentsList.size();

			int classStId = paymentService.getClassStId(detailLectCd);
			int peopleTotAvCnt = paymentService.matchPeopleTotAv(detailLectCd,cnt);
			int orderDuplCnt = paymentService.matchClassOrder(member_sq,detailLectCd);
			String lrclsCtegryCd = paymentService.getLrclsctegrycd(detailLectCd);
			String lectNm = paymentService.getLectName(detailLectCd);

			if(classStId!=2) { // 접수중인 강좌 확인(강좌상태)
				rtnMap.put("rsltCd", "-1");
				return ResponseEntity.ok(rtnMap);
			}else if(peopleTotAvCnt<0) { // 수강 가능인원 확인
				rtnMap.put("rsltCd", "-2");
				rtnMap.put("lectNm", lectNm);
				rtnMap.put("capaCnt", peopleTotAvCnt+cnt);
				return ResponseEntity.ok(rtnMap);
			}else if(orderDuplCnt!=0) { // 수강신청 중복 확인
				rtnMap.put("rsltCd", "-3");
				rtnMap.put("lectNm", lectNm);
				return ResponseEntity.ok(rtnMap);
			}			
			for (Map<String, Object> student : StudentsList) {
				if(!lrclsCtegryCd.equals("01")) {// 실수강자 확인(영유아,아동강좌 본인이 들어가있는지)
					if(student.get("fmlyRelCdNm").toString().equals("본인")) {
						rtnMap.put("rsltCd", "-6");
						rtnMap.put("lectNm", lectNm);
						return ResponseEntity.ok(rtnMap);
					}
				}
				studentNmList.add((String)student.get("actlAtlctNpleNm"));
			}
			lectNmList.add(lectNm);
			insData.put(detailLectCd, studentNmList);			
		}

		if (step.equals("1")) { // step1 -> step2  
			System.out.println("강좌 접수중/수강인원/수강신청중복 확인");
			// 대기접수에서 넘어온건지
			String atlctType = jsonData.get("atlctType")==null? "normal":jsonData.get("atlctType");

			rtnMap.put("jsonStr", jsonData.get("jsonStr"));
			rtnMap.put("atlctType", atlctType);

		}else { // step2 -> 결제 ( fn_validate_step2_callback에 의해 frm_temp를 채울 값들 )
			int totAmt = (int)jsonStr.get("totLectStlmAmt");
			int lpntAmt = (int)jsonStr.get("lpntUseAmt"); 
			int crdStlmAmt = totAmt - lpntAmt;
			int addPoint = (int) (totAmt * 0.01);
			// 주문, 수강내역테이블에 추가 + 주문번호
			int atlctRsvNo = atlctService.insOrderDetailOrder(member_sq,totAmt,lpntAmt,crdStlmAmt,addPoint,insData);
			
			rtnMap.put("rsltCd", "1"); // check 결과
			rtnMap.put("atlctRsvNo", atlctRsvNo); // 주문번호
			rtnMap.put("lpntUseAmt", lpntAmt);
			rtnMap.put("brchCd", (int)payLectsStudentsList.get(0).get("brchCd"));
			rtnMap.put("crdStlmAmt",crdStlmAmt);

			String goodsName = "";
			if(lectNmList.size() > 1 ) {
				goodsName = lectNmList.get(0)+" 외 "+ ( lectNmList.size() - 1 ) +"개";
			}else {
				goodsName = lectNmList.get(0);
			}
			rtnMap.put("goodsName",goodsName);

			MemberDTO dto = memberService.getMemberWithChild(member_sq);
			rtnMap.put("buyerName", dto.getName()); // 회원 이름
			rtnMap.put("mbrNo", member_sq); // 회원번호
			rtnMap.put("mbrId", dto.getEmail()); // 회원 이메일
		}

		return ResponseEntity.ok(rtnMap);
	}
	
	//frm_temp가 submit된 요청을 처리 => iframe에 nicepay html
	// step2에서 nicePay결제창 불러오기                 //html을 생성해서 넘길거임
	@PostMapping(value="payment_request.do", produces = "text/html; charset=UTF-8")
	@ResponseBody // 뷰리졸버를 거치지 않고 바로 http응답을 작성하게!
	public String getNicePay(PaymentFrmDTO frm) throws Exception{
		log.info("/payment/payment_request.do + POST :PaymentController.getNicePay()...");
		System.out.println("frm : "+ frm);
		// iframe에 들어갈 nicepay 불러오는 html
		String html = paymentService.createNicePayHtml(frm);
		return html;
	}
	
	//결제창, 결제 -> 인증사의 결제인증 -> NicePay서버 "승인받는 단계"
	@GetMapping("payment_result.do")
	@ResponseBody
	public String nicePayAcknowledge(@RequestParam Map<String, Object> param) throws Exception{
		log.info("/payment/payment_result.do + POST :PaymentController.nicePayAcknowledge()...");
		System.out.println(param);
		// 승인 service
		HashMap<String, String> rtnMap = paymentService.getAcknowledgeNicePay(param);
		System.out.println("rtnMap : "+ rtnMap);
		// TID를 주문테이블에 추가하는 로직구현
		String tid = (String)rtnMap.get("TID");
		int order_sq = Integer.parseInt( (String)param.get("Moid") );
		int rntUpdateClassOrder = atlctService.updateTID(order_sq,tid);
		 // form_success를 submit하는 코드
		String html = "\r\n<script>\r\n"
				+ "window.parent.document.getElementById('frm_success').submit();\r\n"
				+ "</script>\r\n";
		return html;
	}
	
	// 수강결제3(step3)페이지 이동
	@RequestMapping(value="payment_step3.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String goStep3(@RequestParam("atlctRsvNo") int orderSq, Authentication authentication, Model model) throws Exception{
		log.info("/payment/payment_step3.do + POST :PaymentController.goStep3()...");
		CustomerUser principal = (CustomerUser) authentication.getPrincipal();
		int member_sq = principal.getMember_sq();
		System.out.println("member_sq : "+ member_sq+  "/ orderSq : "+ orderSq);
		// 장바구니삭제, 수강내역증가
		ArrayList<AtlctDTO> list = complexserivce.paymentStep3(orderSq,member_sq);

		model.addAttribute("list", list);
		return "payment.step3";	
	}
	
	// 결제창에서 수강결제 취소
	@GetMapping(value="payment_close.ajax", produces = "application/text; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> cancelNicePay(@RequestParam("orderSq") int orderSq) throws Exception{
		// 주문, 수강내역테이블 삭제
		log.info("/payment/payment_close.ajax + GET :PaymentController.cancelNicePay()...");
		boolean flag = atlctService.deleteOrder(orderSq);
		System.out.println("flag :"+flag);
		return  flag? new ResponseEntity<String>("success", HttpStatus.OK): new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
