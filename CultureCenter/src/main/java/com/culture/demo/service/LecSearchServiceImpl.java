package com.culture.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culture.demo.domain.ClassDTO;
import com.culture.demo.mapper.LecSearchMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class LecSearchServiceImpl implements LecSearchService {

	@Autowired
	private LecSearchMapper lecSearchMapper;
	
	@Override
	public List<ClassDTO> getBranch() {
		log.info(">> LecSearchServiceImpl.getBranch() 호출");
		return lecSearchMapper.getBranch();
	}
	
	@Override
	public List<ClassDTO> getCategory() {
		log.info(">> LecSearchServiceImpl.getCategory() 호출");
		return lecSearchMapper.getCategory();
	}

	@Override
	public List<ClassDTO> getSmCate() {
		log.info(">> LecSearchServiceImpl.getSmCate() 호출");
		return lecSearchMapper.getSmCate();
	}

	@Override
	public String getMdCateNm(String mdclsCtegryCd) {
		log.info(">> LecSearchServiceImpl.getMdCateNm() 호출");
		return lecSearchMapper.getMdCateNm(mdclsCtegryCd);
	}

	@Override
	public String getBranchNm(String branch_id) {
		log.info(">> LecSearchServiceImpl.getBranchNm() 호출");
		return lecSearchMapper.getBranchNm(branch_id);
	}

}
