package com.culture.demo.service;

import java.sql.SQLException;

import com.culture.demo.domain.WaitingListDTO;

public interface WaitingService {
	
	// 장바구니 목록 html 작성
	String createWaitingHtml(int member_sq, WaitingListDTO params) throws SQLException, ClassNotFoundException;
	
	
}
