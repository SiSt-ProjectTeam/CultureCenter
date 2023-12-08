package com.culture.demo.mapper;

import java.util.List;
import com.culture.demo.domain.BranchDTO;

public interface BranchInfoMapper {
	
	//대분류로 소분류 지점 목록 조회
	public List<BranchDTO> selectBranchList(int branch_tp_id);
}
