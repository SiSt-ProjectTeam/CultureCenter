package com.culture.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.PaymentFrmDTO;

public interface PaymentService {
	
	// 세부강좌 -> 강좌정보
	public CartDTO getLect(int detail_class_sq) throws Exception;
	// 수강자변경시 list html
	public String createCourDetailWHtml(Map<String, String> paramMap) throws Exception;
	// 학기별강좌 - 수강가능인원
	public int matchPeopleTotAv(int detail_class_sq, int cnt) throws Exception;
	// 수강신청한 강좌인지
	public int matchClassOrder(int member_sq, int detail_class_sq) throws Exception;
	// 강좌상태
	public int getClassStId(int detail_class_sq) throws Exception;
	// 강좌유형:대분류
	public String getLrclsctegrycd(int detail_class_sq) throws Exception;
	// 강좌명
	public String getLectName(int detailLectCd) throws Exception;
	// 주문번호
	public int getOrderSq(int member_sq) throws Exception;
	// iframe에 들어갈 nicepay 불러오는 html
	public String createNicePayHtml(PaymentFrmDTO frm) throws Exception;
	// 결제인증 후 NicePay의 승인처리
	public HashMap<String, String> getAcknowledgeNicePay(Map<String, Object> params) throws Exception;
}
