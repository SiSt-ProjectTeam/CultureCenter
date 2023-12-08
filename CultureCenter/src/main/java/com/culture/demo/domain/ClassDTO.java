package com.culture.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
	
	// 지점 / branch
	private int branch_id;			// 지점 아이디
	private int branch_tp_id;		// 지점 유형 아이디
	private String branch_nm;		// 지점 이름
	
	// 지점유형 / branch_type	
	private String branch_tp;		// 지점 유형
	
	// 대분류 / large_category
	private String lrclsCtegryCd;	// 대분류코드
	private String lrclsCtegry;		// 대분류명
	
	// 중분류 / medium_category
	private String mdclsCtegryCd;	// 중분류코드
	private String mdclsCtegry;		// 중분류명
	
}
