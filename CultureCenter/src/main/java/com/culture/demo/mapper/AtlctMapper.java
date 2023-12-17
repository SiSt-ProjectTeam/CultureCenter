package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.AtlctPersonalDTO;
import com.culture.demo.domain.FrmSearchDTO;

public interface AtlctMapper {
	
	// 1. 수강신청
	// 1-1. 주문 테이블 추가
	public int insertClassOrder();	
	// 1-2. 주문 상세 테이블 추가
	public int insertOrderDetail();
	
	// 2. 수강 취소
	// 2-1. 주문 상세 테이블 수정
	public int updateOrderDetail(AtlctPersonalDTO dto);
	// 2-2. 주문 테이블 수정
	public int updateClassOrder(AtlctDTO dto);
		
	// 3. 한 주문번호에 수강인이 모두 취소했는지 확인
	public double isAllCancel(int order_sq);
		
	// 4. 수강 내역 조회
	public ArrayList<AtlctDTO> selectAtlctList(@Param("frmSearchDTO") FrmSearchDTO frmSearchDTO, @Param("member_sq") int member_sq) throws SQLException;
}
