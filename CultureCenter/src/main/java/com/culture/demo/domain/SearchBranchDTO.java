package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBranchDTO {
	
	private String type;
	private String brchCd;
	
	private String lrclsCtegryCd;
	private String mdclsCtegryCd;
	private String smclsCtegryCd;
	
	private String yyList;
	
	private String lectClCdList;
	
	private String lectStatCdList;
	
	private String stDaywCdList;
	
	private String timeTypeList;
	
	private String amtTypeList;
	private String stAmt;
	private String endAmt;
	
	private String q;
	
	private String orderSet;
	private int pageIndex;
	private int initIndex;
	private int listCnt;
	
	private int tcCdNo;
	private String brchCdList;
	private String q2;
	
}
