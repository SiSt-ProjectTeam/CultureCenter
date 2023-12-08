package com.culture.demo.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.FrmSearchDTO;

public interface AtlctService {
	// 1. 수강 신청하기
	
	// 2. 수강 취소하기
	boolean cancelAtlct(int memberSq, int orderSq, String orderDetailSqs, int cancelAmt, int cancelReasonId) throws SQLException;

	// 3.
		
	// 4. 수강상세내역  조회
	AtlctDTO getAtlctDetail(FrmSearchDTO frmSearchDTO, int member_sq, int order_sq) throws SQLException;
		
	// 5. 수강내역 조회
	ArrayList<AtlctDTO> getAtlctList( FrmSearchDTO frmSearchDTO , int memberSq, int order_sq ) throws SQLException;   
		
	// 6. 수강내역 html 생성
	String createAtlctHtml( FrmSearchDTO frmSearchDTO, int memberSq, int order_sq ) throws SQLException;
	
}
