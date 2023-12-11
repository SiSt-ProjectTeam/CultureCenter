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
	private String pageIndex;
	private String initIndex;
	private String listCnt;
	private String tcCdNo;
	
	/*
	
	private String yy1;			// 학기 파라미터1
	private String yy2;			// 학기 파라미터2
	
	private String lectClCd1;	// 강좌구분 파라미터1
	private String lectClCd2;	// 강좌구분 파라미터2
	private String lectClCd3;	// 강좌구분 파라미터3
	
	private String lectStatCd1;	// 강좌상태 파라미터1
	private String lectStatCd2;	// 강좌상태 파라미터2
	private String lectStatCd3;	// 강좌상태 파라미터3
	private String lectStatCd4;	// 강좌상태 파라미터4
	private String lectStatCd5;	// 강좌상태 파라미터5
	private String lectStatCd6;	// 강좌상태 파라미터6
	private String lectStatCd7;	// 강좌상태 파라미터7
	
	private String[] days;
	private String stDaywCd1;	// 요일 파라미터1
	private String stDaywCd2;	// 요일 파라미터2
	private String stDaywCd3;	// 요일 파라미터3
	private String stDaywCd4;	// 요일 파라미터4
	private String stDaywCd5;	// 요일 파라미터5
	private String stDaywCd6;	// 요일 파라미터6
	private String stDaywCd7;	// 요일 파라미터7
	
	private String timeType1;	// 시간대 파라미터1
	private String timeType2;	// 시간대 파라미터2
	private String timeType3;	// 시간대 파라미터3
	private String timeType4;	// 시간대 파라미터4
	private String timeType5;	// 시간대 파라미터5
	private String timeType6;	// 시간대 파라미터6
	private String timeType7;	// 시간대 파라미터7
	
	private String amtType1;	// 수강료-범위 파라미터1
	private String amtType2;	// 수강료-범위 파라미터2
	private String amtType3;	// 수강료-범위 파라미터3
	private String amtType4;	// 수강료-범위 파라미터4
	private String amtType5;	// 수강료-범위 파라미터5
	private String amtType6;	// 수강료-범위 파라미터6
	private String amtType7;	// 수강료-범위 파라미터7
	
	private String stAmt;		// 수강료-입력(최소)
	private String endAmt;		// 수강료-입력(최대)
	
	private String q;			// 강좌명 or 강사명으로 검색
	
	private String orderSet;	// 정렬
	*/
}
