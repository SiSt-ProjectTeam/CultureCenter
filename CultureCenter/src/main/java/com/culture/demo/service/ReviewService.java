package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;

public interface ReviewService {
	
	// 리뷰 ajax html
	String reviewHTML(FrmSearchDTO frmSearchDTO) throws SQLException;

	// 리뷰 상세페이지
	//List<ReviewDTO> dtlReview(ReviewDTO reviewDTO) throws SQLException, ClassNotFoundException;

	ReviewDTO dtlReview(@Param("brchCd") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd")int lectSmsterCd, @Param("lectCd")int lectCd
					  , @Param("tcCdNo")int teacher_sq, @Param("memberNo")int member_sq, FrmSearchDTO frmSearchDTO) throws SQLException;


	// 댓글목록 ajax html
	// , @Param("lectSmsterCd")int lectSmsterCd, @Param("lectCd")int lectCd, @Param("mbrNo")int member_sq
	String commtHTML(@Param("brchCd") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd")int lectSmsterCd, @Param("lectCd")int lectCd
					, @Param("tcCdNo")int teacher_sq, @Param("memberNo")int member_sq, FrmSearchDTO frmSearchDTO) throws SQLException;
	



	
}
