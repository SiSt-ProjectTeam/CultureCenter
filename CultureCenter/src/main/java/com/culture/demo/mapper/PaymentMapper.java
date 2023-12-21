package com.culture.demo.mapper;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.CartDTO;

public interface PaymentMapper {
	
	// 학기별강좌 - 수강가능인원  
	public int cntPeopleTotAv(@Param("detail_class_sq")int detail_class_sq,@Param("cnt")int cnt) throws Exception;
	// 세부강좌번호 -> 강좌 정보
	public CartDTO selectLect(int detail_class_sq) throws Exception;
	// 수강결제신청하는 강좌가 수강신청한 강좌인지
	public int cntClassOrderMatch(@Param("member_sq")int member_sq,@Param("detail_class_sq")int detail_class_sq) throws Exception;
	// 세부강좌번호 -> 강좌 : 강좌상태
	public int getClassStId(int detail_class_sq) throws Exception;
	// 세부강좌번호 -> 강좌 유형 : 대분류
	public String getLrclsctegrycd(int detail_class_sq) throws Exception;
	// 강좌명 
	public String getLectName(int detailLectCd) throws Exception;
	// 주문번호 가져오기
	public int getAtlctRsvNo() throws Exception;
}
