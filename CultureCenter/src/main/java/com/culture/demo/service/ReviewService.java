package com.culture.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;

public interface ReviewService {
	
	// 리뷰 ajax html
	String reviewHTML(FrmSearchDTO frmSearchDTO) throws SQLException;

	// 리뷰 상세페이지
	ReviewDTO dtlReview(@Param("brchCd") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd")int lectSmsterCd, @Param("lectCd")int lectCd
					  , @Param("tcCdNo")int teacher_sq, @Param("mbrNo")int member_sq, FrmSearchDTO frmSearchDTO) throws SQLException;

	// 댓글목록 ajax html
	String commtHTML(int member_sq, FrmSearchDTO params) throws SQLException;

	// 댓글 등록
	int insertComm(int review_sq, int member_sq, String comment_content) throws SQLException, ClassNotFoundException;

	// 댓글 삭제
	int deleteComm(int sortSeqno) throws SQLException, ClassNotFoundException; 
	
}
