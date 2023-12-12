package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrmSearchDTO {
	
	private int pageIndex;		// 현재 페이지
	private String type;		// 결제완료/취소(cmplt/rfnd)
	private int lectSmsterCd;	// 학기ID
	private String yy;			// 연도
	private String q;			// 검색어
	private int atlctRsvNo;		// 강좌주문번호
	private int initIndex;		// 게시물 시작 번호
	private int listCnt;		// 한 페이지당 출력할 게시물 갯수
	
	private String prevSesterYy;
	private int prevSesterLectSmsterCd;
	
	private int brchCd;
	private String brchNm;
	private String orderSet;	// 최신,별점순 정렬

} // class
