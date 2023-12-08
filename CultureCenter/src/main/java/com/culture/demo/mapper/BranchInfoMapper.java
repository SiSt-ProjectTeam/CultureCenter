package com.culture.demo.mapper;

import java.util.List;

import com.culture.demo.domain.BranchDTO;

public interface BranchInfoMapper {

	//지점 소분류 목록 조회
	public List<BranchDTO> getBranchList(int branch_tp_id);
	
	//지점 소분류 html
	public String createBranchHtml(int branch_tp_id);
	
	//지점 정보 조회
	public List<BranchDTO> getBranchInfo(int branch_id);
	
	//지점 정보 html
	public String createBranchInfoHtml(int branch_id);	
}
