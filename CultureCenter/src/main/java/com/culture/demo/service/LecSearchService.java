package com.culture.demo.service;

import java.util.List;

import com.culture.demo.domain.ClassDTO;

public interface LecSearchService {

	// 지점 유형별 지점 이름 얻어오기
	List<ClassDTO> getBranch();
	
	// 대분류별 중분류 카테고리 얻어오기
	List<ClassDTO> getCategory();

	// 강좌로 찾기 카테고리
	List<ClassDTO> getSmCate();
	
	// 강좌로 찾기 팝업
	String getMdCateNm(String mdclsCtegryCd);

	// 지점으로 찾기 팝업
	String getBranchNm(String branch_id);
	
}
