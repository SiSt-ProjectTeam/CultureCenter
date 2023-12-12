package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainLectSearchDTO {
	
	private String orderSet;
	private int lrclsCtegryCd;
	private int pageIndex;		// 현재 페이지
	private int initIndex;		// 게시물 시작 번호
	private int listCnt;		// 한 페이지당 출력할 게시물 갯수

} // class
