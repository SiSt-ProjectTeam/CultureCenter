package com.culture.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;

public interface ReviewMapper {
	
	// 수강후기 목록
	public List<ReviewDTO> getReviewList(@Param("branch_id") int branch_id, @Param("dto") FrmSearchDTO frmSearchDTO);
	
	// 수강후기 상세페이지
	public List<ReviewDTO> dtlReview(@Param("reviewDTO") ReviewDTO reviewDTO);
	
	// 수강후기 상세페이지 댓글 ajax
	// public List<ReviewDTO> getCommtList(@Param("review_sq") int review_sq, @Param("dto") FrmSearchDTO frmSearchDTO);

	

	
	
}
