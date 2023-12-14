package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.CartDTO;

public interface CartMapper {

	// 장바구니 목록 가져오기
	public List<CartDTO> getCarts(@Param("member_sq") int member_sq,@Param("branch_id") int branch_id,@Param("detail_class_sq") String lectDetailSq) throws ClassNotFoundException, SQLException;
	
	// 장바구니 추가
	public int insert(@Param("member_sq")int member_sq,@Param("detail_class_sq")int detail_class_sq) throws ClassNotFoundException, SQLException;

	// 장바구니 삭제
	public int delete(@Param("member_sq")int member_sq,@Param("type")String type,@Param("cartSeqno")String cartSeqno) throws ClassNotFoundException, SQLException;
	// 장바구니 자동삭제
	public void autoDelete() throws ClassNotFoundException, SQLException;
	
}
