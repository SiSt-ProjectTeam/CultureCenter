package com.culture.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.FrmSubmitDTO;
import com.culture.demo.domain.MemberDTO;
import com.culture.demo.service.CartService;
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
	
	// 수강결제1(step1) 페이지 이동
	@PostMapping("step1.do")
	public String goPaymentStep1(FrmSubmitDTO dto, Model model) throws Exception{ //Principal principal
		log.info("/payment/step1.do + POST :PaymentController.goPaymentStep1()...");
		System.out.println(dto);
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 79;
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
		MemberDTO mDto = paymentService.getMemberWithChild(member_sq);
		model.addAttribute("mDto", mDto);
		
		return "payment.step1";
	}
	// 수강결제1 수강자변경/추가 저장하기
	@PostMapping(value="actlAtlctNpleList.ajax", produces = "application/text; charset=UTF-8")
	public @ResponseBody ResponseEntity<String> updateActlAtlct(@RequestBody Map<String, String> paramMap
																//,Principal principal
																) throws Exception{
		log.info("/payment/actlAtlctNpleList.ajax + POST :PaymentController.updateActlAtlct()...");
		/*
		Set<Entry<String, String>> en = paramMap.entrySet();
		for (Entry<String, String> entry : en) {
			System.out.println("key : "+entry.getKey()+"/ value : "+entry.getValue());
		}
		*/
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 79;
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
	// step1 -> step2 check사항 ajax
	@PostMapping("validateStep1.ajax")
	public @ResponseBody ResponseEntity<Map<String, Object>> validateStep1(@RequestBody Map<String, String> jsonData
																			// ,Principal principal
																			) throws Exception{
		log.info("/payment/validateStep1.ajax + POST :PaymentController.validateStep1()...");
		Map<String, Object> rtnMap = new HashedMap();
		
		//int member_sq = Integer.parseInt(principal.getName());
		int member_sq = 79;
		
		String jsonStr = jsonData.get("jsonStr"); // jsonData String		 
		
		// jsonData를 직접 변환(deserialize)
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> payLectsStudentsList = mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
		
		for (Map<String, Object> lecStudents : payLectsStudentsList) {
			// 수강자 ArrayList로 넘어옴
			ArrayList<Map<String, Object>> StudentsList = (ArrayList<Map<String, Object>>) lecStudents.get("arrActlAtlctNple");

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
			}else if(!lrclsCtegryCd.equals("01")) { // 실수강자 확인(영유아,아동강좌 본인이 들어가있는지)
				for (Map<String, Object> student : StudentsList) {
					if(student.get("fmlyRelCdNm").toString().equals("본인")) {
						rtnMap.put("rsltCd", "-6");
						rtnMap.put("lectNm", lectNm);
						return ResponseEntity.ok(rtnMap);
					}
				}
			}//else
			int[] arrDetailLectCd = new int[payLectsStudentsList.size()];
			String[] arrLectNm = new String[payLectsStudentsList.size()];
		}
		// 강의시간 중복 확인
		boolean isDupl = false;
		if(isDupl) { // 강의시간 중복 확인
			rtnMap.put("rsltCd", "-4");
			return ResponseEntity.ok(rtnMap);
		}else {
			System.out.println("강좌 접수중/수강인원/수강신청중복/\"강의시간중복(구현중)\" 확인");
			// 대기접수에서 넘어온건지
			String atlctType = jsonData.get("atlctType")==null? "normal":jsonData.get("atlctType");
			
			rtnMap.put("jsonStr", jsonData.get("jsonStr"));
			rtnMap.put("rsltCd", "1");
			rtnMap.put("atlctType", atlctType);
			return ResponseEntity.ok(rtnMap);
		}
	}
	
	// 수강결제2(step2)페이지 이동
	@PostMapping("step2.do")
	public String goPaymentStep2(FrmSubmitDTO dto, Model model) throws Exception{
		log.info("/payment/step2.do + POST :PaymentController.goPaymentStep2()...");
		System.out.println("stpe2 dto : "+dto);
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> payLectsStudentsList = mapper.readValue(dto.getJsonStr(), new TypeReference<List<Map<String, Object>>>(){});
		for (int i = 0; i < payLectsStudentsList.size(); i++) {
			CartDTO lectInfo = paymentService.getLect((Integer)payLectsStudentsList.get(i).get("lectCd"));
			payLectsStudentsList.get(i).put("dto", lectInfo);
		}
		System.out.println("payLectsStudentsList : "+payLectsStudentsList);
		model.addAttribute("list", payLectsStudentsList);
		return "payment.step2";
	}
}
