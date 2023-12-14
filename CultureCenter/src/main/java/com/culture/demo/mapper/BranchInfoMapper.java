package com.culture.demo.mapper;

import java.util.List;

import com.culture.demo.domain.BranchDTO;

public interface BranchInfoMapper {

	//지점 소분류 목록 조회
	public List<BranchDTO> getBranchList(int brchAreaCd);
	
	//지점 정보 조회 
	public BranchDTO getBranchInfo(int brchCd);
	
}
