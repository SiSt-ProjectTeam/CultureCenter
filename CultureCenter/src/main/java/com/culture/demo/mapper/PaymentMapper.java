package com.culture.demo.mapper;

import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
	
	// 학기별강좌 - 수강가능인원  
	public int cntPeopleTotAv(@Param("detail_class_sq")int detail_class_sq,@Param("cnt")int cnt)throws Exception;
	// 수강결제신청하는 강좌가 수강신청한 강좌인지
	public int cntClassOrderMatch(@Param("member_sq")int member_sq,@Param("detail_class_sq")int detail_class_sq) throws Exception;
}
