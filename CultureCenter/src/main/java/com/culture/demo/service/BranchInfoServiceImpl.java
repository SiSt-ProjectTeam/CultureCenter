package com.culture.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.culture.demo.domain.BranchDTO;
import com.culture.demo.mapper.BranchInfoMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BranchInfoServiceImpl implements BranchInfoService{
	
	private BranchInfoMapper branchInfoMapper;

	@Override
	public List<BranchDTO> selectBranchList(int branch_tp_id) {
		log.info("> BranchInfoServiceImpl.selectBranchList ...");
		return this.branchInfoMapper.selectBranchList(branch_tp_id);
	}

	@Override
	public List<BranchDTO> selectBranchInfo(int branch_id) {
		log.info("> BranchInfoServiceImpl.selectBranchInfo ...");
		return this.branchInfoMapper.selectBranchInfo(branch_id);
	}

}













