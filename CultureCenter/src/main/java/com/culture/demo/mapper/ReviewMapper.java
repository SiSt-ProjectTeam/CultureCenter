package com.culture.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;

public interface ReviewMapper {
	
	// 수강후기 목록
	public List<ReviewDTO> getReviewList(@Param("branch_id") int branch_id, @Param("dto") FrmSearchDTO frmSearchDTO);
	
	// 수강후기 상세페이지
	//public List<ReviewDTO> dtlReview(@Param("reviewDTO") ReviewDTO reviewDTO);
	public ReviewDTO dtlReview(@Param("brchCd")int branch_id,@Param("yy") int yy,@Param("lectSmsterCd") int lectSmsterCd
							  ,@Param("lectCd") int lectCd,@Param("tcCdNo") int teacher_sq,@Param("memberNo") int member_sq);
	
	// 수강후기 상세페이지 댓글목록 ajax
	//   
	public List<ReviewDTO> getCommtList(@Param("brchCd") int branch_id, @Param("yy") int yy, @Param("lectSmsterCd") int lectSmsterCd
									, @Param("tcCdNo") int tcCdNO, @Param("lectCd") int teacher_sq, @Param("mbrNo") int member_sq, @Param("dto") FrmSearchDTO frmSearchDTO);
	//public List<ReviewDTO> getCommtList(@Param("review_sq") int review_sq, @Param("dto") FrmSearchDTO frmSearchDTO);
	
	// 후기댓글 등록
	public int insertComm(@Param("review_sq") int review_sq, @Param("member_sq")int member_sq, @Param("comment_cotent") String comment_content) throws ClassNotFoundException, SQLException;
}
