package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassFormDTO {
	
	private String brchCd;
	private String yy;
	private String lectSmsterCd;
	private String lectCd;
	private String lectStatCd;
	private String optnUseYn;
	private String nvgDsplyUseYn;
	private String lectNm;
	private String optnTypCd;
	private int lectAmt;
	private int optnSeqno;
	private int optnNm;
	private int optnAmt;
	private String lectStDtm;
	private String lectSt;
	private String partRfndPsblYn;
	private String pblPmprcustParntBrchCd;
	private String pblPmprcustParntLectCd;
	private int pageIndex;
	private int initIndex;
	private int listCnt;
	private String groupLectTpCd;
	private String groupLectClCd;
	
}
