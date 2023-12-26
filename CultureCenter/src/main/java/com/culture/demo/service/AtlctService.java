package com.culture.demo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.FrmSearchDTO;

public interface AtlctService {
	// 1. 수강 신청하기
	int insOrderDetailOrder(int member_sq, int totAmt, int lpntAmt, int crdStlmAmt, int addPoint,
			Map<Integer, List<String>> insData) throws Exception;
	// 1-2. 수강결제 중 취소
	boolean deleteOrder(int orderSq) throws Exception;
	
	// 2. 수강 취소하기
	boolean cancelAtlct(int memberSq, int orderSq, String orderDetailSqs, int cancelAmt, int cancelReasonId) throws SQLException;

	// 3. 수강내역 조회
	ArrayList<AtlctDTO> getAtlctList(FrmSearchDTO frmSearchDTO , int memberSq) throws SQLException;  
	
	// 3-1. 모두 취소되었는지 확인
	boolean allRefundCheck(int atlctRsvNo) throws SQLException;
		
	// 4. 수강내역 html 생성
	String createAtlctHtml(FrmSearchDTO frmSearchDTO, int memberSq) throws SQLException;
	
	// 5. 수강결제 승인까지 완료시 TID
	int updateTID(int order_sq, String string) throws Exception;

}
