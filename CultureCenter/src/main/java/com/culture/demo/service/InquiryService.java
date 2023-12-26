package com.culture.demo.service;

import java.sql.SQLException;

import com.culture.demo.domain.InquiryDTO;

public interface InquiryService {
	
	String createInquiryListHtml(int member_sq, InquiryDTO params) throws SQLException, ClassNotFoundException;
		
	int deleteInquiry(int personal_faq_sq) throws SQLException, ClassNotFoundException;
	
}
