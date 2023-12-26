package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.culture.demo.domain.CartDTO;
import com.culture.demo.domain.FrmSearchDTO;

public interface CartService {
	
	// 장바구니 목록 html 작성
	String createCartHtml(int member_sq, FrmSearchDTO params) throws SQLException, ClassNotFoundException;
	// 장바구니 목록 가져오기
	List<CartDTO> getList(int member_sq, String lectDetailSq) throws SQLException, ClassNotFoundException;
	// 장바구니 추가
	int insert(int member_sq, int detail_class_sq) throws SQLException, ClassNotFoundException;
	// 장바구니 삭제
	int delete(int member_sq, String type, String cartSeqno) throws SQLException, ClassNotFoundException;
	// 장바구니 자동삭제
	void autoDelete() throws SQLException, ClassNotFoundException;
	// 장바구니 총 개수
	int getTotCartCnt(int member_sq) throws SQLException, ClassNotFoundException;
	
	
	// 주문번호로 세부강좌번호 가져오기
	String getDetailSqByOrderSq(int order_sq) throws Exception;
}
