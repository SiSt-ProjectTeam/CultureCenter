package com.culture.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.FrmSearchDTO;
import com.culture.demo.domain.ReviewDTO;

public interface ReviewMapper {
	
	// 수강후기 목록
	//public List<ReviewDTO> getList(@Param("branch_nm") String branch_nm, @Param("searchCondition") int searchCondition)throws ClassNotFoundException, SQLException;
	//public List<FrmSearchDTO> getList(@Param("q") String q, @Param("orderSet") String orderSet,@Param("branchNm") String branchNm);
	//public ArrayList<ReviewDTO> getReviewList(@Param("frmSearchDTO") FrmSearchDTO) throws SQLException;
	public List<ReviewDTO> getReviewList(@Param("orderSet") String orderSet,@Param("branch_id") int branch_id, @Param("q") String q, @Param("frmSearchDTO") FrmSearchDTO frmSearchDTO);
	/*
	// 수강후기 총 갯수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	*/
	// 수강후기 지점목록 가져오기
	//public List<ReviewDTO> getBranch(String branch_nm);  
	
}
