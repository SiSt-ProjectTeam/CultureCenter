package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;

public interface ReviewService {
	
	// 리뷰 목록 조회
	//List<ReviewDTO> getReviewList(@Param("branch_id") int branch_id, @Param("orderSet") String orderSet, @Param("frmSearchDTO") FrmSearchDTO frmSearchDTO) throws SQLException;
	
	// 리뷰 ajax html
	String ReviewHTML(FrmSearchDTO frmSearchDTO) throws SQLException;

	


	
	// 지점별 목록
	//List<ReviewDTO> getBranch(@Param("branch_nm") String branch_nm) throws SQLException;
	//List<ReviewDTO> getBranch() throws SQLException;
	// 리뷰 상세보기
	
}
