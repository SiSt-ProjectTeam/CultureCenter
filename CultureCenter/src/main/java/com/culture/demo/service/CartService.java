package com.culture.demo.service;

import java.sql.SQLException;

import com.culture.demo.domain.FrmSearchDTO;

public interface CartService {
	
	// 장바구니 목록 html 작성
	String createCartHtml(int member_sq, FrmSearchDTO params) throws SQLException, ClassNotFoundException;
}