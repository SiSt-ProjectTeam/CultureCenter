package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainLectSearchDTO {

	private int branch_id;
	
	private String path;
	private String orderSet;
	private int lrclsCtegryCd;
	private int mdclsCtegryCd;
	
	private int pageIndex;
	private int initIndex;
	private int listCnt;

} // class
