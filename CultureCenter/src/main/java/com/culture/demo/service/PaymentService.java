package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.MemberDTO;

public interface PaymentService {
	
	// 회원 정보 조회(동반수강자 포함)
	MemberDTO getMemberWithChild(int member_sq) throws SQLException, ClassNotFoundException;
	// 세부강좌 -> 강좌정보
	CartDTO getLect(int detail_class_sq) throws Exception;
	// 수강자변경시 list html
	String createCourDetailWHtml(Map<String, String> paramMap) throws Exception;
	// 학기별강좌 - 수강가능인원
	int matchPeopleTotAv(int detail_class_sq, int cnt) throws Exception;
	// 수강신청한 강좌인지
	int matchClassOrder(int member_sq, int detail_class_sq) throws Exception;
	// 강좌상태
	public int getClassStId(int detail_class_sq) throws Exception;
	// 강좌유형:대분류
	public String getLrclsctegrycd(int detail_class_sq) throws Exception;
	// 강좌명
	String getLectName(int detailLectCd) throws Exception;	
	
}
