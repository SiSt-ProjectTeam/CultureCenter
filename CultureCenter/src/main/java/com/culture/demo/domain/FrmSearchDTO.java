package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrmSearchDTO {
	
	private int pageIndex;
	private String type;			// 결제완료/취소(cmplt/rfnd)
	private int lectSmsterCd;		// 학기ID
	private String yy;				// 연도
	private String q;				// 검색어
	private int atlctRsvNo;
	private int initIndex;
	private int listCnt;
	
	private String prevSesterYy;
	private int prevSesterLectSmsterCd;
	
	private int brchCd;
	private String brchNm;

} // class
