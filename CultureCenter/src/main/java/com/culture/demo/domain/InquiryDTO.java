package com.culture.demo.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO {
	
	private int personal_faq_sq; // 문의번호
	private int branch_id; // 지점 아이디
	private String branch_nm; // 지점 아이디
	private int member_sq; // 회원번호
	private int faq_tp_id; // 문의 유형 아이디
	private String faq_tp; // 문의 유형
	private String faq_title; // 문의 제목
	private String faq_detail; // 문의 내용
	private String faq_status; // 문의 상태
	private Date faq_dt; // 문의 일자 (util Date)
	private int pageIndex;		// 현재 페이지
	private int initIndex;		// 게시물 시작 번호
	private int listCnt;		// 한 페이지당 출력할 게시물 갯수
	

}
