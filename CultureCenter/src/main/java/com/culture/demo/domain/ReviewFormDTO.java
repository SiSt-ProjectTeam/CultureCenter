package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewFormDTO {
	private int pageIndex;
	private int listCnt;
	private int initIndex;
	
	private int brchCd;
	private int yy;
	private int lectSmsterCd;
	private int lectCd;
	private int tcCdNo;
	private int memberNo;
	
	private int sortSeqno;
	private String cmntCont;
	private int review_sq;
 
}
