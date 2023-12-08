package com.culture.demo.service;

import java.util.List;

import com.culture.demo.domain.AtlctDTO;
import com.culture.demo.domain.BranchDTO;
import com.culture.demo.domain.ClassDTO;

public interface BranchInfoService {
	
	//대분류로 소분류 지점 목록 조회
	public List<BranchDTO> selectBranchList(int branch_tp_id);
	
	
}
