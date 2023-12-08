package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.CartDTO;

public interface MyPageCartMapper {

	// 장바구니 목록 가져오기
	public List<CartDTO> getCarts(@Param("member_sq") int member_sq,@Param("branch_id") int branch_id) throws ClassNotFoundException, SQLException;
	
	// 장바구니 삭제
	
	// 장바구니 추가
	
	//
}
