package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.InquiryDTO;

public interface MypageInquiryMapper {
	
	public List<InquiryDTO> getInquiryList(int member_sq) throws ClassNotFoundException, SQLException;

	public List<InquiryDTO> getInquiryList(@Param("member_sq") int member_sq, @Param("dto") InquiryDTO params) throws ClassNotFoundException, SQLException;

	public List<InquiryDTO> getInquiryView(@Param("member_sq") int member_sq, @Param("personal_faq_sq") int personal_faq_sq) throws ClassNotFoundException, SQLException;

	public List<InquiryDTO> createInquiry(@Param("member_sq") int member_sq, @Param("dto") InquiryDTO params) throws ClassNotFoundException, SQLException;

	public int deleteInquiry(int personal_faq_sq) throws ClassNotFoundException, SQLException;

	public int totCnt(@Param("member_sq") int member_sq, @Param("dto") InquiryDTO params)throws ClassNotFoundException, SQLException;;
	
}
