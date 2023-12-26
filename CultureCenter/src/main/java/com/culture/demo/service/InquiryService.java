package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.culture.demo.domain.InquiryDTO;

public interface InquiryService {
	
	String createInquiryListHtml(int member_sq, InquiryDTO params) throws SQLException, ClassNotFoundException;
	
	List<InquiryDTO> getInquiryView(int member_sq, int personal_faq_sq) throws SQLException, ClassNotFoundException;
		
	int deleteInquiry(int personal_faq_sq) throws SQLException, ClassNotFoundException;
	
	int insertInquiry(InquiryDTO params) throws SQLException, ClassNotFoundException;
	
}
