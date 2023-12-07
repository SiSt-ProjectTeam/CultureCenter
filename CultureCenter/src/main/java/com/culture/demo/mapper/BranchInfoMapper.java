package com.culture.demo.mapper;

import java.util.List;
import com.culture.demo.domain.BranchDTO;

public interface BranchInfoMapper {
	
	//대분류로 소분류 지점 목록 조회
	public List<BranchDTO> selectBranchList(int branch_tp_id);
	
	//소분류 클릭하면 지점 정보 조회
	public List<BranchDTO> selectBranchInfo(int branch_id);
}
