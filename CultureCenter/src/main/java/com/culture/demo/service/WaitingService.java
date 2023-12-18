package com.culture.demo.service;

import java.sql.SQLException;

import com.culture.demo.domain.FrmSearchDTO;

public interface WaitingService {
	
	// 장바구니 목록 html 작성
	String createWaitingHtml(int member_sq, FrmSearchDTO params) throws SQLException, ClassNotFoundException;
	
	int deleteWaiting(int late_sq) throws SQLException, ClassNotFoundException;
	
}
