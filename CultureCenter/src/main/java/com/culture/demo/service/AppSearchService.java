package com.culture.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.domain.SearchBranchDTO;

public interface AppSearchService {

	// 강좌 정보 가져오기
	List<ClassDTO> selectClassList(@Param("branch_id") int branch_id, @Param("searchBranchDTO") SearchBranchDTO searchBranchDTO,
		    @Param("yyl") String[] yyl,
		    @Param("lectcll") String[] lectcll,
		    @Param("lectstl") String[] lectstl,
		    @Param("dayl") String[] dayl,
		    @Param("timel") String[] timel,
		    @Param("amtl") String[] amtl) throws Exception;
	
	// 강좌 목록 ajax html 생성
	String LecHTML(@Param("branch_id") int branch_id, @Param("searchBranchDTO") SearchBranchDTO searchBranchDTO,
		    @Param("yyl") String[] yyl,
		    @Param("lectcll") String[] lectcll,
		    @Param("lectstl") String[] lectstl,
		    @Param("dayl") String[] dayl,
		    @Param("timel") String[] timel,
		    @Param("amtl") String[] amtl) throws Exception;
	
}
